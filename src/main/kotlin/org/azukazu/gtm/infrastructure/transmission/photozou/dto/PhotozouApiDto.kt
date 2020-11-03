package org.azukazu.gtm.infrastructure.transmission.photozou.dto

import org.azukazu.gtm.model.Url
import org.azukazu.gtm.model.photozou.ImageInfo

data class PhotozouApiDto(
    val stat: String,
    val info: PhotozoApiPhotoInfoDto
)

data class PhotozoApiPhotoInfoDto(
    var CONTENTS_VIEW_LANG: Boolean,
    val NO_VIEW_FCEBOOK_TWITTER: Boolean,
    val photo_num: Int,
    val photo: List<PhotozoApiPhotoInfoDetailDto>
)

data class PhotozoApiPhotoInfoDetailDto(
    var photo_id: Int,
    val user_id: Int,
    val album_id: Int,
    val photo_title: String,
    val favorite_num: Int,
    val comment_num: Int,
    val view_num: Int,
    val copyright: String,
    val original_height: Int,
    val original_width: Int,
    val date: String,
    val regist_time: String,
    val url: String,
    val image_url: String,
    val original_image_url: String,
    val thumbnail_image_url: String,
    val large_tag: String,
    val medium_tag: String
) {
    fun toImageInfo(): ImageInfo =
        ImageInfo(
            Url(original_image_url),
            Url(thumbnail_image_url)
        )
}
