#!/bin/bash
#
#chkconfig:   - 20 80
# description: Starts and stops the App.
# author:vakinget
APP_NAME=spring-boot

APP_HOME=`pwd`
dirname $0|grep "^/" >/dev/null
if [ $? -eq 0 ];then
   APP_HOME=`dirname $0`
else
    dirname $0|grep "^\." >/dev/null
    retval=$?
    if [ $retval -eq 0 ];then
        APP_HOME=`dirname $0|sed "s#^.#$APP_HOME#"`
    else
        APP_HOME=`dirname $0|sed "s#^#$APP_HOME/#"`
    fi
fi

if [ ! -d "$APP_HOME/logs" ];then
  mkdir $APP_HOME/logs
fi

LOG_PATH=$APP_HOME/logs/$APP_NAME.out
JAR_FILE=$APP_HOME/$APP_NAME.jar
pid=0
start(){
  checkpid
  if [ ! -n "$pid" ]; then
    nohup java -jar $JAR_FILE> $LOG_PATH 2>&1 &
    echo "---------------------------------"
    echo "启动完成，按CTRL+C退出日志界面即可>>>>>"
    echo "---------------------------------"
    sleep 2s
    tail -f $LOG_PATH
  else
      echo "$APP_NAME is runing PID: $pid"   
  fi

}


status(){
   checkpid
   if [ ! -n "$pid" ]; then
     echo "$APP_NAME not runing"
   else
     echo "$APP_NAME runing PID: $pid"
   fi 
}

checkpid(){
    pid=`ps -ef |grep $JAR_FILE |grep -v grep |awk '{print $2}'`
}

stop(){
    checkpid
    if [ ! -n "$pid" ]; then
     echo "$APP_NAME not runing"
    else
      echo "$APP_NAME stop..."
      kill -9 $pid
    fi 
}

restart(){
    stop 
    sleep 1s
    start
}

case $1 in  
          start) start;;  
          stop)  stop;; 
          restart)  restart;;  
          status)  status;;   
              *)  echo "require start|stop|restart|status"  ;;  
esac
