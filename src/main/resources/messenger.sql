create database if not exists messenger default char set utf8;
use messenger;
grant all privileges on messenger.* to root identified by 'root';
SHOW VARIABLES LIKE 'character_set_database';