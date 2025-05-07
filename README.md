# RabbitMQ Demo with Spring Boot

这是一个使用Spring Boot和RabbitMQ的示例项目，展示了如何配置和使用RabbitMQ进行消息的发送和接收。

## 技术栈

- JDK 21
- Spring Boot 3.2.3
- RabbitMQ
- Gradle
- Kotlin (配置类)
- Java (消费者类)

## 项目结构

```
src/main/
├── java/
│   └── com/example/rabbitdemo/
│       ├── consumer/
│       │   └── MessageConsumer.java    # 消息消费者
│       └── RabbitDemoApplication.java  # 应用程序入口
├── kotlin/
│   └── com/example/rabbitdemo/
│       └── config/
│           ├── RabbitMQConfig.kt       # RabbitMQ配置
│           └── RabbitMQConstants.kt    # 常量定义
└── resources/
    └── application.yml                 # 应用配置
```

## 主要功能

1. 消息发送
   - 通过REST API发送消息
   - 使用Direct Exchange和Routing Key
   - 支持消息持久化

2. 消息接收
   - 自动消息确认
   - 简单的消息处理逻辑
   - 支持消息重试

## 配置说明

### RabbitMQ配置

```yaml
spring:
  rabbitmq:
    host: xxxx
    port: xxxx
    username: xxxx
    password: xxxx
    virtual-host: /
```

### 队列和交换机配置

- 队列名称: `demo.queue`
- 交换机名称: `demo.exchange`
- 路由键: `demo.routing.key`

## 使用方法

1. 启动应用
```bash
./gradlew bootRun
```

2. 发送消息
```bash
curl -X POST -H "Content-Type: text/plain" -d "Hello RabbitMQ" http://localhost:8080/api/messages
```

3. 查看消息接收
应用控制台会打印接收到的消息。

## 代码说明

### 消费者 (MessageConsumer.java)
```java
@Component
public class MessageConsumer {
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
```

### 配置类 (RabbitMQConfig.kt)
```kotlin
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
```

## 注意事项

1. 确保RabbitMQ服务器已启动并可访问
2. 检查配置文件中的连接信息是否正确
3. 确保防火墙允许RabbitMQ端口访问

## 后续优化方向

1. 添加消息持久化配置
2. 实现消息重试机制
3. 添加死信队列处理
4. 实现消息确认机制
5. 添加监控和日志记录

## License

MIT License 