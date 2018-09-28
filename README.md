
## TopTalk 论坛 -- IT圈友们的休闲度假之地. 

TopTalk是一个类似于CSDN简化后的中大型专业IT交流论坛，使用了最新的Spring全家桶技术栈开发的前后端分离的微服务项目;
   
##  项目说明

这是一个采用了最新的Spring家族全家桶技术开发的项目;

所有模块使用SpringBoot框架来简化组件配置,提高开发效率;

使用了SpringCloud简化了分布式系统基础设施的开发以及全局的服务治理;

项目采用前后端分离的系统架构，遵循了RESTful架构规范；    
                      
开发环境全部基于docker来搭建Linux端环境，开发工具IDEA，使用Maven管理jar包；

MySql数据库使用了MyCat分片部署，主从分离，读写分离；

非关系型数据库使用了MongoDb;

使用Redis实现了缓存功能;

使用了分布式搜索引擎Elasticsearch，使用Logstash实现Elasticsearch与MySql数据同步；

使用了RabbitMQ作为消息中间件；


## ********** 开发记录 ***********

2018/09/28:

1 : 增加了密码加密;

2 : 部分模块的部分方法增加了部分角色权限验证,例如发布文章,发布吐槽,发布评论等;

3 : 配置了SpringCloud环境,现在除common模块外均以微服务注册到Eureka中;

2018/09/27:

1 : 完成了搜索模块的搭建,配置了linux端的elasticsearch环境;

2 : 完成了用户注册模块的搭建;

3 : 完成了短信验证模块的搭建;

4 : 完成了Linux端的rabbitMQ环境搭建,测试成功实现消息消费;

2018/09/26:

1 : 完成了求职模块的搭建;

2 : 完成了问答模块的搭建;

3 : 完成了文章模块的搭建;

4 : 完成了活动模块的搭建;

5 : 完成了吐槽模块的搭建;

5 : 完成了redis缓存功能的实现;

6 : 完成了MongoDB数据库的搭建;

2018/09/25:

1 : 配置开发环境,创建数据库,执行sql语句文件;

2 : 创建父模块toptalk_parent,公共模块toptalk_common,基础模块toptalk_base,求职模块toptalk_recruit的基础业务,执行模块测试;

3 : 初次运行报错:
        Caused by: java.lang.ClassNotFoundException: javax.xml.bind.JAXBException;
        
        原因 : 这是一个常见错误,因为java 9 之后移除了四个java ee级别的jar包,所以需要在父模块中pom中加入四个包:
        
        <!--下面四个jar包是由于java9移除了这些jarEE包导致hibernate运行时抛出
        javax.xml.bind.JAXBException  ,  注意如果某个包下载不了可以换个版本号-->
                    <dependency>
                        <groupId>javax.xml.bind</groupId>
                        <artifactId>jaxb-api</artifactId>
                        <version>2.3.0</version>
                    </dependency>
                    <dependency>
                        <groupId>com.sun.xml.bind</groupId>
                        <artifactId>jaxb-impl</artifactId>
                        <version>2.3.0</version>
                    </dependency>
                    <dependency>
                        <groupId>com.sun.xml.bind</groupId>
                        <artifactId>jaxb-core</artifactId>
                        <version>2.3.0</version>
                    </dependency>
                    <dependency>
                        <groupId>javax.activation</groupId>
                        <artifactId>activation</artifactId>
                        <version>LATEST</version>
                    </dependency>
                    
4 : 在postman或浏览器中中输入http://localhost:9001/label/1   测试成功,返回json字符串
            {
                "flag": true,
                "code": 20000,
                "message": "查询成功",
                "data": {
                    "id": "1",
                    "labelname": "java",
                    "state": "1",
                    "count": null,
                    "fans": null,
                    "recommend": null
                }
            }