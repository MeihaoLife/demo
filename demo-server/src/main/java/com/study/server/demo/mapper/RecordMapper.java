package com.study.server.demo.mapper;

//import com.study.demo.tableshard.DateTableShardStrategy;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RecordMapper
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/1 7:11 下午
 * @Version: v1.0
 **/
//@TableShard(tableName = "tb_record", shardStrategy = DateTableShardStrategy.class)
public interface RecordMapper {

    @Insert("INSERT INTO tb_record(logs) VALUES(#{logs})")
    int addRecord(@Param("logs") String logs);

}
