package org.azukazu.gtm.domain.model

/**
 * エラーメッセージ(一般)
 */
enum class GeneralErrorMessage(val value: String) : ErrorMessage {

    UNEXPECTED("上手く行かなかったよ。作者に問い合わせてね"),
    TORIGGER_WORD_IS_NOT_INCLUDED("トリガーワードを末尾に入力してね"),
    SEARCH_WORD_IS_BLANK("検索ワードを入力してね");

    override fun value(): String = value
}
