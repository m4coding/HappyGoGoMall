#!/usr/bin/env bash

Port=8081
JarName=mall-foreground-1.0.0.jar
LogsPatch=./mall-foreground_logs_$Port

export JAVA_HOME=/opt/java/jdk1.8.0_201
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar


ID=`ps -ef | grep $Port | grep -v "grep" | awk '{print $2}'`
echo $ID
echo "---------------"
for id in $ID
do
kill -s 9 $id
echo "killed $id"
done
echo "---------------"

rm -rf $LogsPatch
mkdir $LogsPatch

export LANG=zh_CN.UTF-8

set -m

nohup java -jar -Dspringfox.documentation.swagger.v2.host=www.m4coding.xyz/happygo/fg -Dlogging.path=$LogsPatch  $JarName>$LogsPatch/catlina.out 2>&1 &

tail -f $LogsPatch/catlina.out