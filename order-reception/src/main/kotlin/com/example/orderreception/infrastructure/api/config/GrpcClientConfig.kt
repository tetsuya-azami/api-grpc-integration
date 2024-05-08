package com.example.orderreception.infrastructure.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder
import net.devh.boot.grpc.client.channelfactory.GrpcChannelConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Files
import java.nio.file.Path


@Configuration
class GrpcClientConfig {
    @Bean
    fun grpcChannelConfigurer(): GrpcChannelConfigurer = GrpcChannelConfigurer { channelBuilder, name ->
        if (channelBuilder is NettyChannelBuilder) {
            val objectMapper = ObjectMapper()
            val retryConfig: HashMap<String, Any> =
                objectMapper.readValue(Files.readString(Path.of("src/main/resources/grpc-retry-config.json")))

            channelBuilder
                .defaultServiceConfig(retryConfig)
                .enableRetry()
        }
    }
}