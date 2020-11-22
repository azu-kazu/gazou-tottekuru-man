package org.azukazu.gtm.infrastructure.transmission.photozou.dto

data class PhotozouApiDto(
    val stat: String,
    val info: PhotozouApiPhotoInfoDto?,
    val err: List<PhotozouApiErrorDto>?
) {
    fun isValid(): Boolean =
        stat != "fail" && err == null
}

data class PhotozouApiPhotoInfoDto(
    var CONTENTS_VIEW_LANG: Boolean,
    val NO_VIEW_FCEBOOK_TWITTER: Boolean,
    val photo_num: Int,
    val photo: List<PhotozouApiPhotoInfoDetailDto>?
)

data class PhotozouApiPhotoInfoDetailDto(
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
)

data class PhotozouApiErrorDto(
    var code: String,
    val msg: String
)
