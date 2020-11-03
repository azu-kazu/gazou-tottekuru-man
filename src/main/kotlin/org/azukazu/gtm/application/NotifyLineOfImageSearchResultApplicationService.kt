package org.azukazu.gtm.application

import org.azukazu.gtm.infrastructure.transmission.line.LineNotificator
import org.azukazu.gtm.infrastructure.transmission.photozou.PhotozouClient
import org.azukazu.gtm.model.SearchWord
import org.azukazu.gtm.model.line.ReplyToken
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
     * 画像を検索し、ランダムに選んだ1枚をLINEに通知する
     */
    fun notifyLineOfImageSearchResultAtRandom(replyToken: ReplyToken,
                                              searchWord: SearchWord) {

        logger.info("画像検索を開始. 検索ワード = {}", searchWord.value)

        val images = photozouClient.searchImages(searchWord)

        lineNotificator.notifyLineOfImage(
            replyToken,
            searchWord,
            images.shuffled()[0])

        logger.info("画像通知が完了. 検索ワード = {}", searchWord.value)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(NotifyLineOfImageSearchResultApplicationService::class.java)
    }
}
