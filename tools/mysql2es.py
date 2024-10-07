import time
import os
from useMySQL import MySQLDatabase
from elasticsearch import Elasticsearch
from config import *

# 配置Elasticsearch
es = Elasticsearch([es_config['host']], http_auth=(es_config['user'], es_config['password']))
# 配置MySQL
db = MySQLDatabase(mysql_config)
check_column = "update_time"
select_sql = "select id, book_id, book_name, book_author, book_status, book_description, insert_time, update_time, insert_by, update_by, book_press, book_tags, is_deleted, is_like from book_book where 1=1"

# Elasticsearch索引映射
mapping = {
    "mappings": {
        "properties": {
            "id": {
                "type": "long"
            },
            "book_id": {
                "type": "keyword"
            },
            "book_name": {
                "type": "text",
                "analyzer": "ik_max_word",
                "fields": {
                    "keyword": {
                        "type": "keyword",
                        "ignore_above": 256
                    }
                }
            },
            "book_author": {
                "type": "text",
                "analyzer": "ik_max_word",
                "fields": {
                    "keyword": {
                        "type": "keyword",
                        "ignore_above": 256
                    }
                }
            },
            "book_status": {
                "type": "keyword"
            },
            "book_description": {
                "type": "text",
                "analyzer": "ik_max_word"
            },
            "insert_time": {
                "type": "date",
                "format": "yyyy-MM-dd HH:mm:ss||strict_date_optional_time||epoch_millis"
            },
            "update_time": {
                "type": "date",
                "format": "yyyy-MM-dd HH:mm:ss||strict_date_optional_time||epoch_millis"
            },
            "insert_by": {
                "type": "keyword"
            },
            "update_by": {
                "type": "keyword"
            },
            "book_press": {
                "type": "keyword"
            },
            "book_tags": {
                "type": "keyword"
            },
            "is_deleted": {
                "type": "boolean"
            },
            "is_like": {
                "type": "boolean"
            }
        }
    },
    "settings": {
        "number_of_shards": 1,
        "number_of_replicas": 1
    }
}


def check_exist(name):
    return os.path.exists(name)

# 获取上次同步的时间
if check_exist("SyncBookTime.txt"):
    with open("SyncBookTime.txt", 'r') as f:
        last_sync_time = f.read()
        select_sql = select_sql.replace("1=1", check_column + ">'" + last_sync_time + "' and update_time<NOW()")

# 更新同步时间
with open("SyncBookTime.txt", 'w') as f:
    f.write(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))

# 创建ES索引
if not es.indices.exists(index="book_book"):
    es.indices.create(index="book_book", body=mapping)
print(select_sql)
# 从MySQL中获取数据并同步到ES
results = db.execute(select_sql, fetch=True)
for result in results:
    update_body = {
        "id": result[0],
        "book_id": result[1],
        "book_name": result[2],
        "book_author": result[3],
        "book_status": result[4],
        "book_description": result[5],
        "insert_time": result[6],
        "update_time": result[7],
        "insert_by": result[8],
        "update_by": result[9],
        "book_press": result[10],
        "book_tags": result[11],
        "is_deleted": bool(result[12]),
        "is_like": bool(result[13])
    }
    es.index(index="book_book", id=result[0], body=update_body)