package org.azukazu.gtm.infrastructure.configuration

import com.linecorp.bot.client.LineMessagingClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LineMessageClientConfiguration(
    @Value("\${line.bot.channel-token}")
    private val channelToken: String
) {

    @Bean
    fun lineMessagingClient(): LineMessagingClient =
        LineMessagingClient
            .builder(channelToken)
            .build()
}
