package org.azukazu.gtm.application

import org.azukazu.gtm.domain.model.ErrorMessage
import org.azukazu.gtm.domain.model.GeneralErrorMessage
import org.azukazu.gtm.domain.model.InvalidSearchWordException
import org.azukazu.gtm.domain.model.line.ReplyToken
import org.azukazu.gtm.infrastructure.transmission.line.LineNotificator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * エラーメッセージをLINEに通知するアプリケーションサービス
 */
@Service
class NotifyLineOfErrorMessageApplicationService(
    private val lineNotificator: LineNotificator
) {

    fun notify(replyToken: ReplyToken, throwable: Throwable) {

        val errorMessage = selectErrorMessage(throwable)

        logger.info("エラーメッセージの送信. リプライトークン = {}, エラーメッセージ = {}", replyToken.value, errorMessage)

        lineNotificator.notifyLineOfErrorMessage(replyToken, errorMessage)

        logger.info("エラーメッセージの送信が完了. リプライトークン = {},", replyToken.value)
    }

    private fun selectErrorMessage(throwable: Throwable): ErrorMessage =
        when (throwable) {
            is InvalidSearchWordException -> {
                throwable.errorMessage
            }
            else -> {
                throwable.printStackTrace()
                GeneralErrorMessage.UNEXPECTED
            }
        }

    companion object {
        private val logger = LoggerFactory.getLogger(NotifyLineOfErrorMessageApplicationService::class.java)
    }
}
