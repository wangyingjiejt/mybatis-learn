package org.apache.ibatis.myplugins;

import org.junit.Test;
import java.util.*;

/**
 * @Author W.Y.J
 * @Date 2020/10/27 22:55
 */
public class PluginTest {

   @Test
    public void test(){

       List<MyInterceptor> interceptors=new ArrayList<>();
       interceptors.add(new MyLoginInterceptor());
       interceptors.add(new MyLogOutInterceptor());
       HelloService helloService = (HelloService)SayHelloProxy.warp(new HelloServiceImpl(),interceptors);
       helloService.sayHello();
   }
}
