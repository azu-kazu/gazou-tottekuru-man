package org.azukazu.gtm.domain.service

import org.azukazu.gtm.domain.model.search_word.TriggerWord
import org.springframework.stereotype.Service

/**
 * 検索対象かどうかを判断するドメインサービス
 */
@Service
class SearchTargetChecker {

    /**
     * 検索対象かどうか確認する。
     *
     * LINE BOTをグループトークに参加させる場合は常にイベントが飛んできてしまう。
     * 検索を意図しているか判断する。
     */
    fun check(text: String): Boolean =
        TriggerWord.values()
            .any { text.endsWith(it.value) }
}
