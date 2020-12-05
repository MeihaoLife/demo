package com.study.server.demo.tableshard;

import com.study.server.demo.common.SpringBeanUtils;
import com.study.server.demo.model.UserOperateLog;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName: TableShardInterceptor
 * @Description: mybatis分表拦截器
 * @Author: zhānghào
 * @Date: 2020/12/1 7:08 下午
 * @Version: v1.0
 **/
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class })})
@SuppressWarnings("uncheck")
public class TableShardInterceptor implements Interceptor {

    private static final ReflectorFactory defaultReflectorFactory = new DefaultReflectorFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                defaultReflectorFactory
        );

        // 从元数据中读取，映射陈述即当前执行SQL所在的MappedStatement对象
        MappedStatement mappedStatement = (MappedStatement)
                metaObject.getValue("delegate.mappedStatement");

        String id = mappedStatement.getId();
        id = id.substring(0, id.lastIndexOf('.'));
        Class clazz = Class.forName(id);

        // 获取TableShard注解
        TableShard tableShard = (TableShard)clazz.getDeclaredAnnotation(TableShard.class);
        if (tableShard != null) {
            // 获取表名
            String tableName = tableShard.tableName();
            // 分表字段
            String columnName = tableShard.shardColumn();
            Class<? extends ITableShardStrategy> strategyClazz = tableShard.shardStrategy();
            // 获取分表策略
            ITableShardStrategy strategy = SpringBeanUtils.getBean(strategyClazz);

            // 获取分表字段的值
            long columnValue = 0;
            Object params = statementHandler.getParameterHandler().getParameterObject();
            Class argClazz = params.getClass();

            // 从参数对象中获取分表字段的值
            // 多个参数，直接获取参数名称为userId参数
            if (argClazz.isAssignableFrom(UserOperateLog.class)) {
                Field field = argClazz.getDeclaredField(columnName);
                field.setAccessible(true);
                columnValue = (long) field.get(params);
            } else if (argClazz.isAssignableFrom(MapperMethod.ParamMap.class)) {
                Map<String, Object> paramsMap = (Map<String, Object>) params;
                columnValue = (long) paramsMap.get(columnName);
            }
            // 新表名称
            String newTableName = strategy.resolveTableName(tableName, columnValue);
            // 获取源sql
            String sql = (String)metaObject.getValue("delegate.boundSql.sql");
            // 用新sql代替旧sql, 完成sql rewrite
            metaObject.setValue("delegate.boundSql.sql", sql.replaceAll(tableName, newTableName));
        }
        // 传递给下一个拦截器处理
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身, 减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
