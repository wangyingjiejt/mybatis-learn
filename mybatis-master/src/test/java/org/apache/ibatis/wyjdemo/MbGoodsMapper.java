package org.apache.ibatis.wyjdemo;

import org.apache.ibatis.annotations.Param;

public interface MbGoodsMapper {

    MbGoods selectById(@Param("id") String id);

}