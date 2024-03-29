package com.example.yy.controller;

import com.example.yy.common.R;
import com.example.yy.model.request.ConsumerRequest;
import com.example.yy.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
/**
 * 用户相关
 * @Author Ethereal
 * @Time : 2022/9/7
 */
@RestController
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;
    /**
     * 前台页面调用 注册
     * 用户注册
     * */
    @PostMapping("/user/add")
    public R addUser(@RequestBody ConsumerRequest registryRequest){
         return consumerService.addUser(registryRequest);
    }
    /**
     * 用户前台页面调用 登录
     * 用户登录判断
     * */
    @PostMapping("/user/login/status")
    public R loginStatus(@RequestBody ConsumerRequest loginRequest, HttpSession session){
        return consumerService.loginStatus(loginRequest,session);
    }
    /**
     * 管理员后台页面调用
     * 返回所有用户
     * */
    @GetMapping("/user")
    public R allUser(){
        return consumerService.allUser();
    }
    /**
     * 用户前台页面调用
     * 返回指定ID的用户
     * */
    @GetMapping("/user/detail")
    public R userOfId(@RequestParam int id){
        return consumerService.userOfId(id);
    }
    /**
     * 管理员后台页面调用
     * 删除用户
     * */
    @GetMapping("/user/delete")
    public R deleteUser(@RequestParam int id){
        return consumerService.deleteUser(id);
    }
    /**
     * 前后台页面调用
     * 更新用户信息
     * */
    @PostMapping("/user/update")
    public R updateUserMsg(@RequestBody ConsumerRequest updateRequest){
        return consumerService.updateUserMsg(updateRequest);
    }
    /**
     * 前后台页面调用
     * 更新用户密码
     * */
    @PostMapping("/user/updatePassword")
    public R updatePassword(@RequestBody ConsumerRequest updatePasswordRequest){
        return consumerService.updatePassword(updatePasswordRequest);
    }
    /**
     * 前后台页面调用
     * 更新用户头像
     * */
    @PostMapping("/user/avatar/update")
    public R updateUserPic(@RequestParam("file") MultipartFile avatarFile,@RequestParam("id") int id){
        return consumerService.updateUserAvatar(avatarFile,id);
    }
}
