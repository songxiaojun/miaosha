package com.hfuu.miaosha.service.impl;

import com.hfuu.miaosha.dao.MiaoshaUserDao;
import com.hfuu.miaosha.domain.MiaoshaUser;
import com.hfuu.miaosha.exception.GlobalException;
import com.hfuu.miaosha.redis.MiaoshaUserKey;
import com.hfuu.miaosha.redis.RedisService;
import com.hfuu.miaosha.result.CodeMsg;
import com.hfuu.miaosha.service.MiaoshaUserService;
import com.hfuu.miaosha.util.MD5Util;
import com.hfuu.miaosha.util.UUIDUtil;
import com.hfuu.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService {

    @Autowired
    private MiaoshaUserDao miaoshaUserDao;
    @Autowired
    RedisService redisService;
    @Override
    public List<MiaoshaUser> list() {
        return miaoshaUserDao.list();
    }

    /**
     * 登陆
     * @param response
     * @param loginVo
     * @return
     */
    @Override
    public String login(HttpServletResponse response, @Valid LoginVo loginVo) {
        if(loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        //todo md5 加密
        String calcPass =formPass;
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token= UUIDUtil.uuid();
        addCookie(response, token, user);
        return token;
    }
    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    private MiaoshaUser getById(long id) {
        //取缓存
        MiaoshaUser user = redisService.get(MiaoshaUserKey.getById, ""+id, MiaoshaUser.class);
        if(user != null) {
            return user;
        }
        //取数据库
        user = miaoshaUserDao.getById(id);
        if(user != null) {
            redisService.set(MiaoshaUserKey.getById, ""+id, user);
        }
        return user;
    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
