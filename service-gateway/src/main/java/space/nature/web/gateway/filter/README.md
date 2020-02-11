# 反向代理注意点
1. `WebFlux`无应用上下文名，如反向代理服务器接收客户端发送的`/api/v1/**`请求时需要去除`/api/v1`  
2. 相应的`spring-cloud-gateway`的代理设置时URI已经不存在`/api/v1`该节