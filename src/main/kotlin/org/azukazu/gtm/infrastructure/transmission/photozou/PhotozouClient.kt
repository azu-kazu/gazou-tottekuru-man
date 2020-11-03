package org.azukazu.gtm.infrastructure.transmission.photozou

import org.azukazu.gtm.infrastructure.transmission.photozou.dto.PhotozouApiDto
import org.azukazu.gtm.model.SearchWord
import org.azukazu.gtm.model.photozou.ImageInfo
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.rmi.UnexpectedException

/**
 * フォト蔵APIを使用するクライアント
 */
@Component
class PhotozouClient(
    private val restTemplate: RestTemplate
) {

    companion object {
        private const val PHOTOZO_SEARCH_API = "https://api.photozou.jp/rest/search_public.json"
    }

    /**
     * 画像の検索
     */
    fun searchImages(searchWord: SearchWord): List<ImageInfo> {

        val uri = buildString {
            append(PHOTOZO_SEARCH_API)
            append("?keyword==${searchWord.value}")
        }

        val responseDto = restTemplate.getForObject(
            uri,
            PhotozouApiDto::class.java)
            ?: throw UnexpectedException("予期せぬエラー")

        return responseDto.info.photo
            .map { p -> p.toImageInfo() }
    }
}
