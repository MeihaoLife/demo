package com.study.server.demo.tableshard;

/**
 * @ClassName: ITableShardStrategy
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/1 7:07 下午
 * @Version: v1.0
 **/
public interface ITableShardStrategy {

    String resolveTableName(String tableName, long shardColumnValue);
}
