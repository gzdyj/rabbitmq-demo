package com.example.rabbitdemo.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.support.converter.SimpleMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.example.rabbitdemo.consumer.MessageConsumer

@Configuration
class RabbitMQConfig {

    @Bean
    fun queue(): Queue = QueueBuilder.durable(RabbitMQConstants.QUEUE_NAME).build()

    @Bean
    fun exchange(): DirectExchange = DirectExchange(RabbitMQConstants.EXCHANGE_NAME)

    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding =
        BindingBuilder.bind(queue)
            .to(exchange)
            .with(RabbitMQConstants.ROUTING_KEY)

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate =
        RabbitTemplate(connectionFactory).apply {
            messageConverter = SimpleMessageConverter()
        }

    @Bean
    fun messageListenerContainer(
        connectionFactory: ConnectionFactory,
        consumer: MessageConsumer
    ): SimpleMessageListenerContainer =
        SimpleMessageListenerContainer().apply {
            this.connectionFactory = connectionFactory
            setQueueNames(RabbitMQConstants.QUEUE_NAME)
            setMessageListener { message: Message -> 
                consumer.receiveMessage(String(message.body))
            }
            setAcknowledgeMode(AcknowledgeMode.AUTO)
            setPrefetchCount(RabbitMQConstants.PREFETCH_COUNT)
        }
} 