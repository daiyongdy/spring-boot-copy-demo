多喜爱 spring boot demo

<1> 使用profile 区分环境
	java -jar spring-boot-demo.jar --spring.profiles.active=prod 会默认读取application-prod.properties 文件 找不到的属性会去application.properties公共配置文件读取