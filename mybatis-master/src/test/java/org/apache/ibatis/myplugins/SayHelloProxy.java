package org.apache.ibatis.myplugins;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * @Author W.Y.J
 * @Date 2020/10/27 22:49
 */
public class SayHelloProxy implements InvocationHandler {

    private Object target;

    private MyInterceptor interceptor;

    public SayHelloProxy(Object target, MyInterceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyInvocation myInvocation = new MyInvocation(target, method, args);
        return interceptor.intercept(myInvocation);
    }

    public static Object warp(Object target, MyInterceptor interceptor) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new SayHelloProxy(target, interceptor));
    }
}
