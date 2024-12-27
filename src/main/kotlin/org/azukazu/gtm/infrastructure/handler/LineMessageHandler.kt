package org.azukazu.gtm.infrastructure.handler

import com.linecorp.bot.model.event.MessageEvent
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.spring.boot.annotation.EventMapping
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import lombok.extern.slf4j.Slf4j
import org.azukazu.gtm.application.NotifyLineOfErrorMessageApplicationService
import org.azukazu.gtm.application.NotifyLineOfImageSearchResultApplicationService
import org.azukazu.gtm.application.ReplyToken

@Slf4j
@LineMessageHandler
class LineMessageHandler(
    private val notifyLineOfImageSearchResultApplicationService: NotifyLineOfImageSearchResultApplicationService,
    private val notifyLineOfErrorMessageApplicationService: NotifyLineOfErrorMessageApplicationService
) {

    /**
     * 画像検索結果を通知する
     */
    @EventMapping
    fun handleTextMessageEvent(request: MessageEvent<TextMessageContent>) {

        runCatching {
            notifyLineOfImageSearchResultApplicationService.notifyImageAtRandom(
                ReplyToken(request.replyToken),
                request.message.text
            )
        }.onFailure { throwable ->
            notifyLineOfErrorMessageApplicationService.notify(
                ReplyToken(request.replyToken),
                throwable
            )
        }
        return // Result型を無視
    }
}
