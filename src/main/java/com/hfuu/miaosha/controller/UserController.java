package com.hfuu.miaosha.controller;

import com.hfuu.miaosha.domain.MiaoshaUser;
import com.hfuu.miaosha.result.Result;
import com.hfuu.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
    private MiaoshaUserService userService;
	

	
    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {
        return Result.success(user);
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<MiaoshaUser> list(){
        List<MiaoshaUser> list = userService.list();
        return list;
    }
    
}
