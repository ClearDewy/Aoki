#!/bin/bash

mysql -uroot -p$MYSQL_ROOT_PASSWORD << EOF
system echo '================Start create database aoki====================';
source $WORK_PATH/$FILE_0;
system echo '================Start create database nacos==================';
source $WORK_PATH/$FILE_1;
system echo '=====================Databases is Created!=======================';
quit;
EOF
