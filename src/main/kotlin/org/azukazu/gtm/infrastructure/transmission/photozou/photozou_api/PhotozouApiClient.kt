package org.azukazu.gtm.infrastructure.transmission.photozou.photozou_api

import org.azukazu.gtm.application.PhotozouClient
import org.azukazu.gtm.domain.model.image_info.ImageInfo
import org.azukazu.gtm.domain.model.image_info.Url
import org.azukazu.gtm.domain.model.search_word.SearchWord
import org.azukazu.gtm.infrastructure.transmission.photozou.photozou_api.dto.PhotozouApiDto
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.rmi.UnexpectedException

/**
 * フォト蔵APIを使用するクライアント
 */
@Component
class PhotozouApiClient(
    private val restTemplate: RestTemplate
) : PhotozouClient {

    companion object {
        private const val PHOTOZO_SEARCH_API = "https://api.photozou.jp/rest/search_public.json"
    }

    override fun searchImages(searchWord: SearchWord): List<ImageInfo>? {

        val uri = buildString {
            append(PHOTOZO_SEARCH_API)
            append("?keyword=${searchWord.value}")
        }

        val responseDto = restTemplate.getForObject(
            uri,
            PhotozouApiDto::class.java)
            ?: throw UnexpectedException("API使用時に予期せぬエラー")

        if (!responseDto.isValid()) {
            throw UnexpectedException(responseDto.err!!.joinToString(",\n") { e -> e.code })
        }

        return responseDto.info!!.photo
            ?.let { photoInfo ->
                photoInfo.map { photoDetail ->
                    ImageInfo.of(
                        Url(photoDetail.original_image_url),
                        Url(photoDetail.thumbnail_image_url)
                    )
                }
            }
    }
}
