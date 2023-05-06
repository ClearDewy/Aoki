FROM mysql:8

ENV TZ=Asia/Shanghai


#定义工作目录
ENV WORK_PATH /home

#定义会被容器自动执行的目录
ENV AUTO_RUN_DIR /docker-entrypoint-initdb.d

#定义sql文件名
ENV FILE_0 aoki.sql
ENV FILE_1 nacos.sql

#定义容器启动脚本文件名
ENV SERVE_START run.sh

COPY ./mysql.cnf /etc/mysql/conf.d/

COPY ./$FILE_0 $WORK_PATH/
COPY ./$FILE_1 $WORK_PATH/
COPY ./$SERVE_START $AUTO_RUN_DIR/

RUN chmod a+x $AUTO_RUN_DIR/$SERVE_START