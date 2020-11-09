package org.apache.ibatis.myplugins;

/**
 * @Author W.Y.J
 * @Date 2020/10/27 23:19
 */
public interface MyInterceptor {

    /**
     * 具体拦截后的业务处理
     * @param myInvocation
     * @return
     * @throws Exception
     */
    Object intercept(MyInvocation myInvocation) throws Exception;

    Object plugin(Object target);
}
