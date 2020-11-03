package org.azukazu.gtm.infrastructure.handler

import com.linecorp.bot.model.event.MessageEvent
import com.linecorp.bot.model.event.message.TextMessageContent
import com.linecorp.bot.spring.boot.annotation.EventMapping
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler
import lombok.extern.slf4j.Slf4j
import org.azukazu.gtm.application.NotifyLineOfImageSearchResultApplicationService
import org.azukazu.gtm.model.SearchWord
import org.azukazu.gtm.model.line.ReplyToken

@Slf4j
@LineMessageHandler
class LineMessageHandler(
    private val notifyLineOfImageSearchResultApplicationService: NotifyLineOfImageSearchResultApplicationService
) {

    /**
     * 画像検索結果を通知する
     */
    @EventMapping
    fun handleTextMessageEvent(request: MessageEvent<TextMessageContent>) =
        notifyLineOfImageSearchResultApplicationService.notifyLineOfImageSearchResultAtRandom(
            ReplyToken(request.replyToken),
            SearchWord.from(request.message.text))
}
