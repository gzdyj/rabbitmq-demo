package com.example.rabbitdemo.config

object RabbitMQConstants {
    const val QUEUE_NAME = "demo.queue"
    const val EXCHANGE_NAME = "demo.exchange"
    const val ROUTING_KEY = "demo.routing.key"
    
    // 消息监听器配置
    const val LISTENER_METHOD = "receiveMessage"
    const val PREFETCH_COUNT = 1
    
    // 重试配置
    const val INITIAL_INTERVAL = 1000L
    const val MAX_ATTEMPTS = 3
    const val MAX_INTERVAL = 10000L
    const val MULTIPLIER = 2.0
} 