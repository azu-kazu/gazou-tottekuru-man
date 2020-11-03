package org.azukazu.gtm.application

import org.azukazu.gtm.infrastructure.transmission.line.LineNotificator
import org.azukazu.gtm.infrastructure.transmission.photozou.PhotozouClient
import org.azukazu.gtm.domain.model.SearchWord
import org.azukazu.gtm.domain.model.line.ReplyToken
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * 画像検索結果をLINEに通知するアプリケーションサービス
 */
@Service
class NotifyLineOfImageSearchResultApplicationService(
    private val photozouClient: PhotozouClient,
    private val lineNotificator: LineNotificator
) {

    /**
     * ランダムに選んだ1枚を通知する
     */
    fun notifyImageAtRandom(replyToken: ReplyToken,
                            searchWord: SearchWord) {

        logger.info("検索画像の通知処理を開始. リプライトークン = {}, 検索ワード = {}", replyToken.value, searchWord.value)

        photozouClient.searchImages(searchWord)
            ?.let { lineNotificator.notifyLineOfImage(replyToken, searchWord, it.shuffled()[0]) }
            ?: lineNotificator.notifyLineOfMessage(replyToken, "検索結果が0件です。")

        logger.info("検索結果の通知が完了. リプライトークン = {}", replyToken.value)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(NotifyLineOfImageSearchResultApplicationService::class.java)
    }
}
