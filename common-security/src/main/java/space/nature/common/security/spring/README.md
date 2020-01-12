# 备注
## 即便使用JWT，这里实现SecurityContext是个错误，应该实现HttpSession
## 对于web还是服务端保存session更好而非JWT这种令牌，暂且将选择基于redis这种分布式的缓存服务器的Spring Session 方案