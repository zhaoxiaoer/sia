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
