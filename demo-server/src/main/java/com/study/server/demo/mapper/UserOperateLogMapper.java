package com.study.server.demo.mapper;

import com.study.server.demo.model.UserOperateLog;
import com.study.server.demo.tableshard.HashTableShardStrategy;
import com.study.server.demo.tableshard.TableShard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: UserOperateLogMapper
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/2 3:23 下午
 * @Version: v1.0
 **/
@TableShard(tableName = "t_user_operate_log", shardColumn = "userId", shardStrategy = HashTableShardStrategy.class)
public interface UserOperateLogMapper {

    @Insert("insert into t_user_operate_log(user_id, operate_desc, operate_result, handler_class, handler_method) " +
            "values(#{log.userId}, #{log.operateDesc}, #{log.operateResult}, #{log.handlerClass}, #{log.handlerMethod})")
    int insertUserOperateLog(@Param("log") UserOperateLog userOperateLog, @Param("userId") long userId);
}
