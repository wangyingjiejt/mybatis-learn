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

    private List<MyInterceptor> interceptors;

    public SayHelloProxy(Object target, List<MyInterceptor> interceptors) {
        this.target = target;
        this.interceptors = interceptors;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        interceptors.forEach(i->i.intercept());
        return method.invoke(target,args);
    }

    public static Object warp(Object target,List<MyInterceptor>  interceptors){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),new SayHelloProxy(target, interceptors));
    }
}
