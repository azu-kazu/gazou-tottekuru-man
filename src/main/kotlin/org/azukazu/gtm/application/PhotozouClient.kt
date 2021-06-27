package org.azukazu.gtm.application

import org.azukazu.gtm.domain.model.image_info.ImageInfo
import org.azukazu.gtm.domain.model.search_word.SearchWord
import org.springframework.stereotype.Component

/**
 * フォト蔵から画像を取得するクライアント
 */
@Component
interface PhotozouClient {

    /**
     * 画像の検索
     */
    fun searchImages(searchWord: SearchWord): List<ImageInfo>?
}