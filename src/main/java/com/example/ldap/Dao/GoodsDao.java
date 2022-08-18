package com.example.ldap.Dao;

import com.example.ldap.Data.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

    @Mapper
    public interface GoodsDao {
        @Update("update goods set name=#{name},remark=#{remark} where id=#{id}")
        int updateGoods(Goods entity);
        @Select("select * from goods where id=#{id}")
        Goods findById(Integer id);
        @Insert("insert into goods(name,remark,createdTime) values(#{name},#{remark},GETDate())")
        int insertGoods(Goods entity);
        /**
         *Delete based on ID
         * @param id
         * @return
         */
        @Delete("delete from goods where id=#{id}")
        int deleteById(Integer id);
        /**
         *Query all product information
         *@ return all products
         *There are two ways to define SQL mapping in the mybatis framework
         *1. Annotation (simple SQL mapping)
         *2. Using XML (realizing complex SQL mapping)
         */
        @Select("select * from goods")
        List<Goods> findGoods();
}
