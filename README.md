# protocolManager
modbus TCP、RTU etc...
#### 主要内容 ###
本文件主要分为一下模块：
* driverCore
* modbusRtu
* modbusTcp  

其中`driverCore`为协议主类，其他模块可单独打成jar包，然后将该jar包所在路径传递给`driverCore`中的`DriverReppository.driverCompile()`方法来加载
