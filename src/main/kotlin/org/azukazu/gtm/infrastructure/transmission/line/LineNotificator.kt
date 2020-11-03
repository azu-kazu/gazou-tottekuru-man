package org.azukazu.gtm.infrastructure.transmission.line

import com.linecorp.bot.client.LineMessagingClient
import com.linecorp.bot.model.ReplyMessage
import com.linecorp.bot.model.message.ImageMessage
import com.linecorp.bot.model.message.TextMessage
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import org.azukazu.gtm.model.SearchWord
import org.azukazu.gtm.model.line.ReplyToken
import org.azukazu.gtm.model.photozou.ImageInfo
import org.springframework.stereotype.Component

/**
 * LINEへの通知を行うNotificator
 */
@Component
@LineMessageHandler
class LineNotificator(
    private val client: LineMessagingClient
) {

    /**
     * 画像を通知する
     */
    fun notifyLineOfImage(replyToken: ReplyToken,
                          searchWord: SearchWord,
                          imageInfo: ImageInfo) {

        val replyMessage = generateReplyMessage(
            replyToken,
            searchWord,
            imageInfo)

        client.replyMessage(replyMessage).get()
    }

    /**
     * 返信メッセージの生成
     */
    fun generateReplyMessage(
        replyToken: ReplyToken,
        searchWord: SearchWord,
        imageInfo: ImageInfo
    ): ReplyMessage =
        ReplyMessage(
            replyToken.value,
            listOf(
                TextMessage(searchWord.value),
                ImageMessage(
                    imageInfo.originalImageUrl.generateSslUri(),
                    imageInfo.thumbnailImageUrl.generateSslUri()
                )
            )
        )
}
