package org.apache.ibatis.wyjdemo;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({@Signature(type = Executor.class,
        method = "query",
        args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class }
)})
public class MyExecutorPlugin2 implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 执行目标方法的前置处理
        System.out.println("我是executor2前置消息");

        Object returnObject = invocation.proceed();
        // 执行目标方法的后置处理
        System.out.println("我是executor2后置消息");
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
