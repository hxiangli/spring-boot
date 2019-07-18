# spring-boot
> 基于 springboot2.0 集成的工具与框架
## 后台代码分层说明：
### com.hlfc.springboot
+ beetl
+ cache (缓存)
   + redis
+ controller
   + nio(非阻塞)
      + websocket
   + security(登录)
   + IndexController （首页以及.html页面跳转）
   + RestfulController （rest服务）
+ db（数据库）  
   + mongodb
   + mybatisplus
+ git
+ http
   + client
+ log
   + log4j
+ mq
   + kafka
+ nio
  + netty 
  + websocket
+ security（安全机制）
+ zk（操作zk）
## 前台功能说明：
+ socket
+ 打印
+ 鼠标选中区
  
