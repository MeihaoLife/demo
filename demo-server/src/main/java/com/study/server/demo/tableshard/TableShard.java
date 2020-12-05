package com.study.server.demo.tableshard;

import java.lang.annotation.*;

/**
 * @ClassName: TableShard
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/1 7:07 下午
 * @Version: v1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableShard {

    // 要替换的表名
    String tableName();

    // 分表的字段
    String shardColumn();

    // 对应的分表策略类
    Class<? extends ITableShardStrategy> shardStrategy();
}
