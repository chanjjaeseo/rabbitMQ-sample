## RabbitMQ & SpringAMQP

### ConnectionFactory
如果没有注入，rabbitMQ将采用application.properties的设置自动注入一个ConnectionFactory

### RabbitAdmin
需要@Bean注入,并指定autoStartup为true

作用： 对MQ的Queue Exchange Binding操作  (如声明)

用法：

autoStartup必须设置为true,否则Spring容器不会加载RabbitAdmin类。

RabbitAdmin底层实现是从Spring容器中获取Exchange、Binding、RoutingKey以及Queue的Bean的声明。

然后使用RabbitTemplate的execute方法执行对应的修改、删除、声明等一系列RabbitMQ基础功能操作。

### RabbitTemplate

需要@Bean注入

作用: 发送消息

与SpringAMQP整合时候发送消息的关键类

提供了丰富的发送消息的方法，包括可靠性投递消息方法、回调监听消息接口ConfirmCallback、返回值确认接口ReturnCallback等等。

### SimpleMessageListenerContainer

作用: 消息监听容器，采用特定的策略异步消费消息

消息的消费者接收消息可以采用两种方式:

1、consumer.receive() 或 consumer.receive(int timeout);

2、注册一个MessageListener。

采用第一种方式,消息的接收者会一直等待下去,直到有消息到达,或者超时。后一种方式会注册一个监听器,当有消息到达的时候,会回调它的onMessage()方法

### MessageListenerAdapter

作用: 自定义消息监听器的适配器



### MessageConverter

作用: 消息转换器，把消息转换成指定的格式

SimpleMessageConverter(): 转化简单的消息格式

支持以下 Java 类型：

- String：contentType 设置为 text/plain

- Serializable：contentType 设置为 application/x-java-serialized-object，body 为对象序列化得到的 byte[]

除此之外的其他contentType用此转换器解码原样返回byte[]字节码


JacksonToJsonMessageConverter(): json格式转换成Java对象转换器

  一般用下面的消息转换器

  DefaultJsonToJaveTypeMapper(): json格式和java对象映射器

  (如果不在消息头内指定__TypeId__, JavaType默认转化为Object)


消息发送者调用toMessage方法把非Message的对象转换成Message, 消息接收者接收到消息调用fromMessage从获取消息并完成消费操作，如下。

发送者

RabbitTemplate => MessageConverter.toMessage();

接收者

MessageListenerContainer => MessageConvert.fromMessage();



### @RabbitListener

指定消息监听的方法，一般搭配@RabbitHandler实现

@RabbitListener依赖ListenerContainerFacotory，如果想指定listener的其他信息如：listner接收时消息是什么类型的，可用@Bean的方式用自己的
ListenerContainerFatory。

### @RabbitHandler
根据消息接受后解码的类型，采用不同的处理方法


### 生产环境的使用方法

发送端

一般用RabbitTempldate发送消息(注意指定MessageConvert和接收端一致)

接收端

指定MessageListenerContainer（设置符合自己需求配置）

然后用@RabbitListner 搭配@RabbitHander去处理消息
