package org.azukazu.gtm.application

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import org.azukazu.gtm.domain.model.ErrorMessageInterface
import org.azukazu.gtm.domain.model.image_info.ImageInfo
import org.azukazu.gtm.domain.model.search_word.SearchWord
import org.springframework.stereotype.Component

/**
 * LINEへの通知を行うNotificator
 */
@Component
@LineMessageHandler
interface LineNotificator {

    /**
     * 画像付きメッセージを通知する
     */
    fun notifyOfMessageWithImage(
        replyToken: ReplyToken,
        searchWord: SearchWord,
        imageInfo: ImageInfo
    )

    /**
     * メッセージを通知する
     */
    fun notifyOfMessage(
        replyToken: ReplyToken,
        text: String
    )

    /**
     * エラーメッセージを通知する
     */
    fun notifyErrorMessage(
        replyToken: ReplyToken,
        errorMessage: ErrorMessageInterface
    )
}


