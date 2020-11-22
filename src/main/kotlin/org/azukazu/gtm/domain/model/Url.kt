package org.azukazu.gtm.domain.model

import java.net.URI

/**
 * Url
 */
class Url(private val value: String) {

    /**
     * SSL用URIを生成
     */
    fun generateSslUri(): URI {
        if (value.startsWith("https:")) {
            return URI(value)
        }

        val sslStr = buildString {
            append(value)
            insert(4, "s")
        }
        return URI(sslStr)
    }
}
