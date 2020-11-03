package org.azukazu.gtm.model

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
        fun from(text: String): SearchWord {

            if (text.isEmpty()) {
                throw IllegalArgumentException("検索ワードを入力してください")
            }

            if (Pattern.matches("^[0-9a-zA-Z-~ -/:-@\\[-~]{1,2}$", text)) {
                throw IllegalArgumentException("半角英数字や記号のみの場合は, 3文字以上の言葉を入力してください")
            }

            val triggerWord =
                runCatching {
                    TriggerWord.values()
                        .first { text.endsWith(it.value) }
                }.getOrElse {
                    throw IllegalArgumentException("トリガーワードを末尾に入力してください")
                }

            return SearchWord(
                text.substring(0, text.length - triggerWord.value.length) // ポメラニアンとってきて - 5 -> ポメラニアン
            )
        }
    }
}
