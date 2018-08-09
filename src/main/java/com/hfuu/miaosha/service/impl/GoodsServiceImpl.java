package com.hfuu.miaosha.service.impl;

import com.hfuu.miaosha.dao.GoodsDao;
import com.hfuu.miaosha.service.GoodsService;
import com.hfuu.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    @Override
    public boolean reduceStock(GoodsVo goods) {
        return false;
    }

    @Override
    public void resetStock(List<GoodsVo> goodsList) {

    }
}
