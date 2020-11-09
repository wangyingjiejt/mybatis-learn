package org.apache.ibatis.binding;

import org.apache.ibatis.annotations.Select;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 模拟mapper代理实现
 * @author W.Y.J
 */
public class MockMapperProxyTest {




    @Test
    public void test01(){
        //生成一个MyMapper一个代理
        MyMapper myMapper = (MyMapper) Proxy.newProxyInstance(MockMapperProxyTest.class.getClassLoader(),
                new Class<?>[]{MyMapper.class}, (proxy, method, args) -> {
                    System.out.println(method.getName());
                    Select annotation = method.getAnnotation(Select.class);
                    if (null!=annotation){
                        System.out.println(annotation.value()[0]);
                    }
                    System.out.println(proxy.getClass());
                    return method;
                });

        myMapper.say();

    }



}


/**
 * 模拟定义一个mapper
 * @author W.Y.J
 */
interface MyMapper {

    @Select("select * from my_mapper")
    void say();
}