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

//       List<MyInterceptor> interceptors=new ArrayList<>();
//       interceptors.add(new MyLoginInterceptor());
//       interceptors.add(new MyLogOutInterceptor());
       System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); //设置系统属性
      HelloService target = new HelloServiceImpl();
       MyTransactionInterceptor myTransactionInterceptor = new MyTransactionInterceptor();
       target=(HelloService)myTransactionInterceptor.plugin(target);
       MyLoginInterceptor interceptor = new MyLoginInterceptor();
       target=(HelloService)interceptor.plugin(target);
      target.sayHello();
   }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); //设置系统属性
        HelloService target = new HelloServiceImpl();
        MyTransactionInterceptor myTransactionInterceptor = new MyTransactionInterceptor();
        target=(HelloService)myTransactionInterceptor.plugin(target);
        MyLoginInterceptor interceptor = new MyLoginInterceptor();
        target=(HelloService)interceptor.plugin(target);
        target.sayHello();
    }
}
