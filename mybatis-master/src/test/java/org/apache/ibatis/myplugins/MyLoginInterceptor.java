package org.apache.ibatis.myplugins;

/**
 * @Author W.Y.J
 * @Date 2020/10/27 23:20
 */
public class MyLoginInterceptor implements MyInterceptor{
    @Override
    public void intercept() {
        System.out.println("登录");
    }
}
