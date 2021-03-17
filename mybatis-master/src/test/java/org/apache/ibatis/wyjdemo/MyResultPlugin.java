package org.apache.ibatis.wyjdemo;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({@Signature(type = ResultHandler.class,
        method = "handleResult",
        args = {ResultContext.class }
)})
public class MyResultPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 执行目标方法的前置处理
        System.out.println("我是ResultHandler前置消息");

        Object returnObject = invocation.proceed();
        // 执行目标方法的后置处理
        System.out.println("我是ResultHandler后置消息");
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
