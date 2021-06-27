package org.azukazu.gtm.infrastructure.transmission.line.messaging_api

import com.linecorp.bot.client.LineMessagingClient
import com.linecorp.bot.model.ReplyMessage
import com.linecorp.bot.model.message.ImageMessage
import com.linecorp.bot.model.message.TextMessage
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import org.azukazu.gtm.application.LineNotificator
import org.azukazu.gtm.domain.model.ErrorMessageInterface
import org.azukazu.gtm.domain.model.image_info.ImageInfo
import org.azukazu.gtm.application.ReplyToken
import org.azukazu.gtm.domain.model.search_word.SearchWord
import org.springframework.stereotype.Component

/**
 * LINE Messaging API を使用するクライアント
 */
@Component
@LineMessageHandler
class LineMessagingApiClient(
    private val client: LineMessagingClient
) : LineNotificator {

    override fun notifyOfMessageWithImage(
        replyToken: ReplyToken,
        searchWord: SearchWord,
        imageInfo: ImageInfo) {

        val replyMessage = generateReplyMessage(
            replyToken,
            searchWord,
            imageInfo)

        client.replyMessage(replyMessage).get()
    }

    /**
     * 返信メッセージの生成(文字+画像)
     */
    private fun generateReplyMessage(
        replyToken: ReplyToken,
        searchWord: SearchWord,
        imageInfo: ImageInfo): ReplyMessage =

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

    override fun notifyOfMessage(
        replyToken: ReplyToken,
        text: String) {

        val replyMessage = generateReplyMessageOnlyText(
            replyToken,
            text)

        client.replyMessage(replyMessage).get()
    }

    override fun notifyErrorMessage(
        replyToken: ReplyToken,
        errorMessage: ErrorMessageInterface) {

        notifyOfMessage(replyToken, errorMessage.value())
    }

    /**
     * 返信メッセージの生成(文字のみ)
     */
    private fun generateReplyMessageOnlyText(
        replyToken: ReplyToken,
        text: String): ReplyMessage =

        ReplyMessage(
            replyToken.value,
            TextMessage(text)
        )
}
