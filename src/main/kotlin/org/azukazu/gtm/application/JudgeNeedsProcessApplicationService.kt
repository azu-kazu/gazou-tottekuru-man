package org.azukazu.gtm.application

import org.azukazu.gtm.domain.model.search_word.TriggerWord
import org.springframework.stereotype.Service

/**
 * 処理が必要かどうかを判断するアプリケーションサービス
 */
@Service
class JudgeNeedsProcessApplicationService {

    /**
     * 処理が必要かどうかを判断する
     *
     * LINE BOTをグループトークに参加させる場合は常にイベントが飛んできてしまう。
     * 意図的な利用かを判断する。
     */
    fun judge(text: String): Boolean =
        TriggerWord.values()
            .any { text.endsWith(it.value) }
}
