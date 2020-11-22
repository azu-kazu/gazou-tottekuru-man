package org.azukazu.gtm.domain.model.image_info

/**
 * 画像情報
 */
class ImageInfo private constructor(

    /**
     * 画像URL(オリジナルサイズ)
     */
    var originalImageUrl: Url,

    /**
     * 画像URL(縮小サイズ)
     */
    val thumbnailImageUrl: Url
) {
    companion object {

        fun of(originalImageUrl: Url,
               thumbnailImageUrl: Url): ImageInfo =
            ImageInfo(
                originalImageUrl,
                thumbnailImageUrl
            )
    }
}
