### 安装
##### 启动EPEL源
 $ sudo yum install epel-release 
##### 安装erlang
 $ sudo yum install erlang 
##### 下载rabbitmq rpm包
$ wget http://www.rabbitmq.com/releases/rabbitmq-server/v3.6.6/rabbitmq-server-3.6.6-1.el7.noarch.rpm
##### 安装rpm包
$ yum install rabbitmq-server-3.6.6-1.el7.noarch.rpm 


###  服务启动
$ sudo chkconfig rabbitmq-server on # 添加开机启动RabbitMQ服务
$ sudo /sbin/service rabbitmq-server start # 启动服务
$ sudo /sbin/service rabbitmq-server status # 查看服务状态
$ sudo /sbin/service rabbitmq-server stop # 停止服务


###  用户配置
##### 查看当前所有用户
$ sudo rabbitmqctl list_users
##### 查看默认guest用户的权限
$ sudo rabbitmqctl list_user_permissions guest
##### 由于RabbitMQ默认的账号用户名和密码都是guest。为了安全起见, 先删掉默认用户
$ sudo rabbitmqctl delete_user guest
##### 添加新用户
$ sudo rabbitmqctl add_user username password
##### 设置用户tag
$ sudo rabbitmqctl set_user_tags username administrator
##### 赋予用户默认vhost的全部操作权限
$ sudo rabbitmqctl set_permissions -p / username ".*" ".*" ".*"
##### 查看用户的权限
$ sudo rabbitmqctl list_user_permissions username
##### 修改密码
$ sudo rabbitmqctl change_password {username} {newpassword}


###  其他配置
##### 开启web管理接口
##### http://xxx.xxx.xxx.xxx:15672/
$ sudo rabbitmq-plugins enable rabbitmq_management

##### rabbit最大占用内存比例
$ sudo rabbitmqctl set_vm_memory_high_watermark 0.5 

###  日志路径
RabbitMQ默认日志路径：/var/log/rabbitmq/

###  Connection In Application
5672 client连接端口