package org.apache.ibatis.wyjdemo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class MyTest {

    private static SqlSessionFactory sqlSessionFactory;

    @Test
    public void test01() throws IOException {
        //1、创建SqlSessionFactory
        String resource = "org/apache/ibatis/wyjdemo/mybatis-config.xml";
        final Reader reader = Resources.getResourceAsReader(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();
        //2、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3、获取mapper
        MbGoodsMapper mapper = sqlSession.getMapper(MbGoodsMapper.class);
        //4、执行数据库操作，并处理结果集
        MbGoods goods = mapper.selectById("12");
        System.out.println(goods);
    }

    @Test
    public void test02() throws IOException {
        //1、创建SqlSessionFactory
        String resource = "org/apache/ibatis/wyjdemo/mybatis-config.xml";
        final Reader reader = Resources.getResourceAsReader(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();
        //2、获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Object o = sqlSession.selectOne("selectById","12");
        System.out.println(o);
    }
}