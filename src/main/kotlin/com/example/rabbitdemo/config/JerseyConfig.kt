package com.example.rabbitdemo.config

import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.server.ServerProperties
import org.springframework.stereotype.Component
import jakarta.ws.rs.ApplicationPath
import org.glassfish.jersey.jackson.JacksonFeature
import org.glassfish.jersey.media.multipart.MultiPartFeature

@Component
@ApplicationPath("/api")
class JerseyConfig : ResourceConfig() {
    init {
        // 注册API包
        packages("com.example.rabbitdemo.api")
        
        // 禁用WADL
        property(ServerProperties.WADL_FEATURE_DISABLE, true)
        
        // 注册Jackson
        register(JacksonFeature::class.java)
        
        // 注册Multipart支持
        register(MultiPartFeature::class.java)
    }
} 