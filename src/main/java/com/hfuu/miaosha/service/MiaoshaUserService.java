package com.hfuu.miaosha.service;

import com.hfuu.miaosha.domain.MiaoshaUser;
import com.hfuu.miaosha.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

public interface MiaoshaUserService {
    public static final String COOKI_NAME_TOKEN = "token";
    List<MiaoshaUser> list();

    String login(HttpServletResponse response, @Valid LoginVo loginVo);
    public MiaoshaUser getByToken(HttpServletResponse response, String token);
}
