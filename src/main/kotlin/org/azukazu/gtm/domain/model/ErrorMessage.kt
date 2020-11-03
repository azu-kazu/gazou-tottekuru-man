package org.azukazu.gtm.domain.model

/**
 * エラーメッセージ
 */
interface ErrorMessage {

    /**
     * メッセージの取得
     */
    fun value(): String
}
