package com.hfuu.miaosha.service.impl;

import com.hfuu.miaosha.dao.MiaoshaUserDao;
import com.hfuu.miaosha.domain.MiaoshaUser;
import com.hfuu.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService {
    @Autowired
    private MiaoshaUserDao miaoshaUserDao;
    @Override
    public List<MiaoshaUser> list() {
        return miaoshaUserDao.list();
    }
}
