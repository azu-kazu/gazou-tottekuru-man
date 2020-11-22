package org.azukazu.gtm.domain.model.search_word

import org.azukazu.gtm.domain.model.GeneralErrorMessage

/**
 * トリガーワード
 */
enum class TriggerWord(val value: String) {

    TOTTEKITE("とってきて"),
    KURE("くれ"),
    YOKOSE("よこせ");

    companion object {

        /**
         * 末尾に含まれるトリガーワードを返す
         */
        fun extractTriggerWord(text: String): TriggerWord =
            runCatching {
                values().first { text.endsWith(it.value) }
            }.getOrElse {
                throw InvalidSearchWordException(GeneralErrorMessage.TORIGGER_WORD_IS_NOT_INCLUDED)
            }
    }
}
