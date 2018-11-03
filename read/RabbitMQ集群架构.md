### 主备模式 Warren

适用场景： 并发和数据量不高的情况

主节点挂了，从节点提供服务（只有主节点提供服务，从节点不提供服务）

利用HAProxy 做主备负载均衡

HAProxy配置：

listen rabbitmq_cluster

bind 0.0.0.0:5672

[ tcp代理 ]

mode tcp

[ 均衡模式 ]

balance roundrobin

[ inter: 5秒检查 2次正确则处于可用 2次失败则挂掉 ]

server server_name_1 192.168.11.76:5672 check inter 5000 rise 2 fall 2

[ 备用节点: 除加上backup关键字，其他配置与主节点保持一致 ]

server server_name_2 192.168.11.77:5672 backup check inter 5000 rise 2 fall 2

### 远程模式 Shovel

适用场景：实现双活

我们可以把消息进行不同的数据中心复制, 可以跨地域让两个mq集群互联

使用shovel模式需要启动rabbitmq插件:

* rabbitmq-plugins enable amqp_client

* rabbitmq-plugins enable rabbitmq_shovel

* 创建rabbitmq.config : touch /etc/rabbitmq/rabbitmq.config

* 在源服务器和目的地服务器使用相同的配置文件
 
### 镜像模式 Mirror

适用场景：保障数据100%不丢失，实际工作用的较多
                                          

##### 多活模式 Federation：

实现异地数据复制的主流模式，因为Shovel模式配置比较复杂，所以一般实现异地集群都是

实现多活模式的，这种模型需要依赖rabbitmq的federation插件可以实现持续可靠的amqp通信。

Federation 插件是一个不需要构建Cluster而在Broker之间传输消息的高性能插件，

Federation插件可以在Brokers或者Cluster之间传输消息,连接的双方可以使用不同的users和

virtual hosts，双方也可以使用版本不同的RabbitMQ和Erlang。Federation插件使用AMQP

协议通讯，可以接收不连续的传输

