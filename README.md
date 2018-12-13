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
