package org.azukazu.gtm.domain.model.photozou

import org.azukazu.gtm.domain.model.ErrorMessageInterface

/**
 * エラーメッセージ(フォト蔵)
 * TODO: インフラ層に移動
 */
enum class PhotozouErrorMessage(val value: String) : ErrorMessageInterface {

    SEARCH_WORD_IS_TOO_SHORT("半角英数字や記号だけの場合は, 3文字以上の言葉を入力してね");

    override fun value(): String = value
}
