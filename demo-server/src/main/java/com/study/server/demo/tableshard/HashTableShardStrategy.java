package com.study.server.demo.tableshard;

import org.springframework.stereotype.Component;

/**
 * @ClassName: DateTableShardStrategy
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/1 7:07 下午
 * @Version: v1.0
 **/
@Component
public class HashTableShardStrategy implements ITableShardStrategy {

    @Override
    public String resolveTableName(String tableName, long shardColumnValue) {
        return tableName + "_" + shardColumnValue % 100;
    }
}
