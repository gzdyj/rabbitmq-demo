# RabbitMQ 示例项目

一个使用 Spring Boot 和 RabbitMQ 的示例项目，展示了如何使用 JAX-RS (Jersey) 实现 REST API 并与 RabbitMQ 集成。

## 环境要求

- JDK 21
- Gradle
- RabbitMQ 服务器

## 配置说明

### 本地配置

1. 在项目根目录创建 `.env` 文件（此文件不会被 Git 追踪）
2. 从 `.env.example` 复制内容到 `.env`
3. 在 `.env` 中更新你的 RabbitMQ 配置：

```properties
# RabbitMQ 配置
RABBITMQ_HOST=你的rabbitmq主机地址
RABBITMQ_PORT=5672
RABBITMQ_USERNAME=你的用户名
RABBITMQ_PASSWORD=你的密码
RABBITMQ_VHOST=/

# 服务器配置
SERVER_PORT=8080
```

注意：`.env` 文件包含敏感信息，永远不要提交到版本控制系统。

## 构建和运行

1. 构建项目：
```bash
./gradlew build
```

2. 运行应用：
```bash
./gradlew bootRun
```

## API 接口

### 发送消息 (GET)
```bash
curl "http://localhost:8080/api/messages?message=Hello%20RabbitMQ"
```
响应：
```json
{
  "code": 0,
  "message": "success",
  "data": "Message sent: Hello RabbitMQ"
}
```

### 发送消息 (POST)
```bash
curl -X POST -H "Content-Type: text/plain" -d "Hello RabbitMQ" http://localhost:8080/api/messages/send
```
响应：
```json
{
  "code": 0,
  "message": "success",
  "data": "ok"
}
```

## 项目结构

```
src/main/
├── java/
│   └── com/example/rabbitdemo/
│       ├── api/
│       │   ├── MessageApi.java         # REST API 接口
│       │   └── ApiResponse.java        # 统一响应结构
│       ├── common/
│       │   └── ApiResponse.java        # 公共工具类
│       ├── config/
│       │   └── DotenvEnvironmentPostProcessor.java  # 环境配置
│       ├── producer/
│       │   └── MessageProducer.java    # 消息生产者
│       └── RabbitDemoApplication.java  # 应用程序入口
├── kotlin/
│   └── com/example/rabbitdemo/
│       └── config/
│           └── JerseyConfig.kt         # JAX-RS 配置
└── resources/
    ├── application.yml                 # 应用配置
    └── META-INF/
        └── spring.factories            # Spring 配置
```

## 功能特性

- 使用 RabbitMQ 的异步消息处理
- 使用 JAX-RS (Jersey) 的 RESTful API
- 基于 .env 文件的环境配置
- 消息确认机制
- 错误处理
- JSON 响应格式

## 依赖说明

- Spring Boot 3.2.3
- Spring AMQP
- Jersey (JAX-RS)
- Jackson JSON 处理
- Dotenv 环境配置

## 注意事项

1. 确保 RabbitMQ 服务器已启动并可访问
2. 检查配置文件中的连接信息是否正确
3. 确保防火墙允许 RabbitMQ 端口访问

## 后续优化方向

1. 添加消息持久化配置
2. 实现消息重试机制
3. 添加死信队列处理
4. 实现消息确认机制
5. 添加监控和日志记录

## 开源协议

MIT License 