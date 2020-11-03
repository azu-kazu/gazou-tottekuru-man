package org.azukazu.gtm.domain.model.photozou

import org.azukazu.gtm.domain.model.ErrorMessage

/**
 * エラーメッセージ(フォト蔵)
 */
enum class PhotozouErrorMessage(val value: String) : ErrorMessage {

    SEARCH_WORD_IS_TOO_SHORT("半角英数字や記号だけの場合は, 3文字以上の言葉を入力してね");

    override fun value(): String = value
}
