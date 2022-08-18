package com.example.ldap.Service;

import com.example.ldap.Dao.GoodsDao;
import com.example.ldap.Data.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService{
    private static final Logger log= LoggerFactory.getLogger(GoodsServiceImpl.class);
    @Autowired
    private GoodsDao goodsDao;
    @Override
    public int updateGoods(Goods entity) {
        return goodsDao.updateGoods(entity);
    }
    @Override
    public Goods findById(Integer id) {
        return goodsDao.findById (id);// In the future, the query results can be stored in the cache in the business logic layer;
    }
    @Override
    public int saveGoods(Goods entity) {
        return goodsDao.insertGoods(entity);
    }
    @Override
    public int deleteById(Integer id) {
        int rows=goodsDao.deleteById(id);
        return rows;
    }
    @Override
    public List<Goods> findGoods() {
        long t1=System.currentTimeMillis();
        List<Goods> list=goodsDao.findGoods();
        long t2=System.currentTimeMillis();
        log.info ("findGoods time-> {}", t2-t1);//{} Good for placeholders
        return list;
    }
}
