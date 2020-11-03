package org.azukazu.gtm.domain.model

/**
 * 不正な検索ワードの例外
 */
class InvalidSearchWordException(
    val errorMessage: ErrorMessage
) : IllegalArgumentException()
