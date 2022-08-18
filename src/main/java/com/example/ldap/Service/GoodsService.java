package com.example.ldap.Service;

import com.example.ldap.Data.Goods;

import java.util.List;

public interface GoodsService {
    int updateGoods(Goods entity);
    Goods findById(Integer id);
    int saveGoods(Goods entity);
    int deleteById(Integer id);
    List<Goods> findGoods();
}

