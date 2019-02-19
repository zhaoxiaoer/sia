# Spring in Action

## 安装部署
### 安装jdk11
下载jdk-11.0.1_linux-x64_bin.tar.gz，并解压:tar -zxvf jdk-11.0.1_linux-x64_bin.tar.gz<br>
编辑~/.bashrc或者/etc/profile文件，添加如下内容:<br>
JAVA_HOME=...  <br>
CLASSPATH=$JAVA_HOME/lib/  <br>
PATH=$PATH:$JAVA_HOME/bin  <br>
export JAVA_HOME CLASSPATH PATH  <br>
<br>
命令: source ~/.bashrc 让 .bashrc 文件立即生效

### 安装tomcat-9.0.13
下载apache-tomcat-9.0.13.tar.gz，并解压<br>
cd apache-tomcat-9.0.13<br>
./bin/startup.sh 启动tomcat

## jar文件说明
### 阿里支付相关jar
alipay-sdk-java-3.3.2.jar, commons-logging-1.1.1.jar

### http客户端相关jar
httpclient-4.5.6.jar, httpcore-4.4.10.jar

### json相关jar
fastjson-1.2.51.jar

### xml解析相关jar
dom4j-2.1.1.jar, jaxen-1.1.6.jar

### 自定义 约束校验注解，以及 Hibernate 约束校验注解
validation-api-2.0.1.Final.jar  依赖  hibernate-validator-6.0.13.Final.jar <br />
hibernate-validator-6.0.13.Final.jar  又依赖  jboss-logging-3.3.2.Final.jar、javax.el-3.0.1-b09.jar、classmate-1.3.4.jar

### multipart 格式的支持及相关jar
form表单，默认的编码格式为 application/x-www-form-urlencoded <br />
通过添加 multipartResolver bean，spring 可以支持 multipart/form-data 编码格式 <br />
相关jar: commons-fileupload-1.3.3.jar, 该包又依赖于 commons-io-2.6.jar

### jdbc相关jar包
Spring JDBC 扩展自 jdbc, 因此要想使用Spring JDBC，必须添加 mysql-connector-java-8.0.12.jar <br />
一般情况下，创建数据库连接所消耗的时间大于SQL执行的时间，因此要使用缓冲池: c3p0-0.9.5.2.jar、mchange-commons-java-0.2.11.jar

### OXM相关jar包
使用 Spring 的 OXM 包，需要javax.activation-api-1.2.0.jar, jaxb-api-2.4.0-b180830.0359.jar <br />
jaxb-core-2.3.0.1.jar, jaxb-impl-2.4.0-b180830.0438.jar, istack-commons-runtime-3.0.7.jar <br />
当前仅使用 jaxb 的 marshal, unmarshal 时需要添加 CDATA, 所以暂时没有使用

### JPA相关jar包
使用JPA，需要导入javax.persistence-api-2.2.jar，而该包又依赖于：<br />
hibernate-core-5.3.7.Final.jar <br />
dom4j-2.1.1.jar <br />
javax.activation-api-1.2.0.jar <br />
classmate-1.3.4.jar <br />
jandex-2.0.5.Final.jar <br />
jboss-transaction-api_1.2_spec-1.1.1.Final.jar <br />
byte-buddy-1.8.17.jar <br />
javassist-3.23.1-GA.jar <br />
jboss-logging-3.3.2.Final.jar <br />
hibernate-commons-annotations-5.0.4.Final.jar <br />
antlr-2.7.7.jar <br />

### log4j 相关jar包
log4j-core-2.11.1.jar log4j-api-2.11.1.jar

### shiro 相关jar包
shiro-core-1.4.0.jar 使用 slf4j 接口 -- slf4j-api-1.8.0-beta2.jar <br />
其又依赖 commons-beanutils-1.9.3.jar <br />
slf4j的实现使用 log4j2 -- log4j-slf4j18-impl-2.11.1.jar
### shiro 网络相关jar包
shiro-web-1.4.0.jar 通过servlet使用shiro时，需要添加此包
### shiro ehcache相关jar包
shiro-ehcache-1.4.0.jar ehcache-2.10.6.jar commons-collections-3.2.2.jar
