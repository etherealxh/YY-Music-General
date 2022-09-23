package com.example.yy.controller;

import com.example.yy.common.R;
import com.example.yy.model.request.UserSupportRequest;
import com.example.yy.service.UserSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 用户点赞相关
 * @Author Ethereal
 * @Time : 2022/9/8
 * */
@RestController
@RequestMapping("/userSupport")
public class UserSupportController {
    @Autowired
    UserSupportService userSupportService;

    /**
    * 判断是否点赞
    * */
    @PostMapping("/test")
    public R isUserSupportComment(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.isUserSupportComment(userSupportRequest);
    }

    /**
     * 添加记录
     * */
    @PostMapping("/insert")
    public R insertCommentSupport(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.insertCommentSupport(userSupportRequest);
    }

    /**
     * 取消记录
     * */
    @PostMapping("/delete")
    public R deleteCommentSupport(@RequestBody UserSupportRequest userSupportRequest) {
        return userSupportService.deleteCommentSupport(userSupportRequest);
    }
}
