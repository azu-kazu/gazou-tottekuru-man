package org.azukazu.gtm.domain.model.search_word

import org.azukazu.gtm.domain.model.ErrorMessageInterface

/**
 * 不正な検索ワードの例外
 */
class InvalidSearchWordException(
    val errorMessage: ErrorMessageInterface
) : IllegalArgumentException()
