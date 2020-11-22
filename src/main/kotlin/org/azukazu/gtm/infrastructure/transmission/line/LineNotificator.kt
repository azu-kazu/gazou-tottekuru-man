package org.azukazu.gtm.infrastructure.transmission.line

import com.linecorp.bot.client.LineMessagingClient
import com.linecorp.bot.model.ReplyMessage
import com.linecorp.bot.model.message.ImageMessage
import com.linecorp.bot.model.message.TextMessage
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import org.azukazu.gtm.domain.model.ErrorMessageInterface
import org.azukazu.gtm.domain.model.search_word.SearchWord
import org.azukazu.gtm.domain.model.line.ReplyToken
import org.azukazu.gtm.domain.model.image_info.ImageInfo
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
     * 画像付きメッセージを通知する
     */
    fun notifyLineOfImage(
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

    /**
     * メッセージを通知する
     */
    fun notifyLineOfMessage(
        replyToken: ReplyToken,
        text: String) {

        val replyMessage = generateReplyMessageOnlyText(
            replyToken,
            text)

        client.replyMessage(replyMessage).get()
    }

    /**
     * エラーメッセージを通知する
     */
    fun notifyLineOfErrorMessage(
        replyToken: ReplyToken,
        errorMessage: ErrorMessageInterface) {

        notifyLineOfMessage(replyToken, errorMessage.value())
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
