package org.apache.ibatis.wyjdemo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * 缓存测试
 * @author W.Y.J
 * @Date 2021/4/27 11:02
 */
public class MyCacheTest {


     private static SqlSessionFactory sqlSessionFactory;

    @Before
    public void initCommonConfig() throws IOException {
        //创建SqlSessionFactory
        String resource = "org/apache/ibatis/wyjdemo/mybatis-config.xml";
        final Reader reader = Resources.getResourceAsReader(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();
    }

    @Test
    public void firstCacheTest(){

        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MbGoodsMapper mapper = sqlSession.getMapper(MbGoodsMapper.class);
        //第一次
        MbGoods goods = mapper.selectById("12");
        System.out.println(goods);
        //第二次使用同一个sqlSession
        MbGoodsMapper mapper2 = sqlSession.getMapper(MbGoodsMapper.class);
        MbGoods goods2 = mapper2.selectById("12");
        System.out.println(goods2);

        /**
         * 结果：
         * DEBUG [main] - Opening JDBC Connection
         * Tue Apr 27 11:34:04 CST 2021 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
         * DEBUG [main] - Created connection 905735620.
         * DEBUG [main] - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@35fc6dc4]
         * DEBUG [main] - ==>  Preparing: select * from mb_goods where id = ?
         * DEBUG [main] - ==> Parameters: 12(String)
         * DEBUG [main] - <==      Total: 1
         * MbGoods{id='12', name='商品名称12', price=120}
         * DEBUG [main] - Cache Hit Ratio [org.apache.ibatis.wyjdemo.MbGoodsMapper]: 0.0
         * MbGoods{id='12', name='商品名称12', price=120}
         *
         * 两次查询只查询了一次数据，第二次直接走了缓存
         */
    }

    @Test
    public void firstCacheTest2(){


        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MbGoodsMapper mapper = sqlSession.getMapper(MbGoodsMapper.class);
        //第一次
        MbGoods goods = mapper.selectById("12");
        System.out.println(goods);
        //第二次使用不同的sqlSession
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        MbGoodsMapper mapper2 = sqlSession2.getMapper(MbGoodsMapper.class);
        MbGoods goods2 = mapper2.selectById("12");
        System.out.println(goods2);

        /**
         * 结果：
         * DEBUG [main] - Created connection 2011482127.
         * DEBUG [main] - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@77e4c80f]
         * DEBUG [main] - ==>  Preparing: select * from mb_goods where id = ?
         * DEBUG [main] - ==> Parameters: 12(String)
         * DEBUG [main] - <==      Total: 1
         * MbGoods{id='12', name='商品名称12', price=120}
         * DEBUG [main] - Cache Hit Ratio [org.apache.ibatis.wyjdemo.MbGoodsMapper]: 0.0
         * DEBUG [main] - Opening JDBC Connection
         * Tue Apr 27 11:20:23 CST 2021 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
         * DEBUG [main] - Created connection 1461149300.
         * DEBUG [main] - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@57175e74]
         * DEBUG [main] - ==>  Preparing: select * from mb_goods where id = ?
         * DEBUG [main] - ==> Parameters: 12(String)
         * DEBUG [main] - <==      Total: 1
         * MbGoods{id='12', name='商品名称12', price=120}
         *
         *两次的查询各自查询一次，也就是说第二次并没有走缓存，可见一级缓存是在同一个sqlSession内共享的
         */
    }



    /**
     * 首先要开启二级缓存
     * @author W.Y.J
     * @Date 2021/4/29 15:07
     * @param
     * @return void
     */
    @Test
    public void secondCacheTest2(){


        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MbGoodsMapper mapper = sqlSession.getMapper(MbGoodsMapper.class);
        //第一次
        MbGoods goods = mapper.selectById("12");
        //关闭第一次的sqlSession，
        // 开启二级缓存的情况下，必须要关闭连接后，
        // 本次查询的结果才会添加到缓存中 sqlSession.close()—>cachingExecutor.close()->tcm.commit()->flushPendingEntries();
        //一级缓存是把结果立即放到缓存中
        sqlSession.close();
        System.out.println(goods);
        //第二次使用不同的sqlSession
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        MbGoodsMapper mapper2 = sqlSession2.getMapper(MbGoodsMapper.class);
        MbGoods goods2 = mapper2.selectById("12");
        System.out.println(goods2);

        /**
         * 结果：
         * DEBUG [main] - Created connection 2011482127.
         * DEBUG [main] - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@77e4c80f]
         * DEBUG [main] - ==>  Preparing: select * from mb_goods where id = ?
         * DEBUG [main] - ==> Parameters: 12(String)
         * DEBUG [main] - <==      Total: 1
         * MbGoods{id='12', name='商品名称12', price=120}
         * DEBUG [main] - Cache Hit Ratio [org.apache.ibatis.wyjdemo.MbGoodsMapper]: 0.0
         * DEBUG [main] - Opening JDBC Connection
         * Tue Apr 27 11:20:23 CST 2021 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
         * DEBUG [main] - Created connection 1461149300.
         * DEBUG [main] - Setting autocommit to false on JDBC Connection [com.mysql.jdbc.JDBC4Connection@57175e74]
         * DEBUG [main] - ==>  Preparing: select * from mb_goods where id = ?
         * DEBUG [main] - ==> Parameters: 12(String)
         * DEBUG [main] - <==      Total: 1
         * MbGoods{id='12', name='商品名称12', price=120}
         *
         *两次的查询各自查询一次，也就是说第二次并没有走缓存，可见一级缓存是在同一个sqlSession内共享的
         */
    }
}
