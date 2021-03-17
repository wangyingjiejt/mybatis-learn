package org.apache.ibatis.wyjdemo;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class,
        method = "parameterize",
        args = {Statement.class }
)})
public class MyStatementPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 执行目标方法的前置处理
        System.out.println("我是StatementHandler前置消息");

        Object returnObject = invocation.proceed();
        // 执行目标方法的后置处理
        System.out.println("我是StatementHandler后置消息");
        return returnObject;
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
