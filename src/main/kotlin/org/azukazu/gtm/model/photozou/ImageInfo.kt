package org.azukazu.gtm.model.photozou

import org.azukazu.gtm.model.Url

/**
 * 画像情報
 */
class ImageInfo(

    /**
     * 画像URL(オリジナルサイズ)
     */
    var originalImageUrl: Url,

    /**
     * 画像URL(縮小サイズ)
     */
    val thumbnailImageUrl: Url
)
