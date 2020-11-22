package org.azukazu.gtm.domain.model.search_word

import org.azukazu.gtm.domain.model.GeneralErrorMessage
import org.azukazu.gtm.domain.model.photozou.PhotozouErrorMessage
import java.util.regex.Pattern

/**
 * 検索ワード
 */
class SearchWord(val value: String) {

    companion object {

        /**
         * 検索ワードを生成
         * ex. ポメラニアンとってきて -> ポメラニアン
         */
        fun of(text: String): SearchWord {

            val triggerWord = TriggerWord.extractTriggerWord(text)

            val searchWordValue = text.substring(0, text.length - triggerWord.value.length) // 例 ポメラニアンとってきて - 5文字(とってきて) -> ポメラニアン

            if (searchWordValue.isBlank()) {
                throw InvalidSearchWordException(GeneralErrorMessage.SEARCH_WORD_IS_BLANK)
            }

            if (Pattern.matches("^[0-9a-zA-Z-~ -/:-@\\[-~]{1,2}$", searchWordValue)) {
                throw InvalidSearchWordException(PhotozouErrorMessage.SEARCH_WORD_IS_TOO_SHORT)
            }

            return SearchWord(searchWordValue)
        }
    }
}
