package org.apache.ibatis.myplugins;

/**
 * @Author W.Y.J
 * @Date 2020/10/27 23:20
 */
public class MyLoginInterceptor implements MyInterceptor{

    @Override
    public Object intercept(MyInvocation myInvocation) throws Exception {
        System.out.println("登录前");
        Object result = myInvocation.getMethod().invoke(myInvocation.getTarget(), myInvocation.getArgs());
        System.out.println("登录后");
        return result;
    }


    @Override
    public Object plugin(Object target) {
        return SayHelloProxy.warp(target,this);
    }
}
