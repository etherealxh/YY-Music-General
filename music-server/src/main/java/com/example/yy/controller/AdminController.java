package com.example.yy.controller;

import com.example.yy.common.R;
import com.example.yy.model.request.AdminRequest;
import com.example.yy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
/**
* 后台管理相关
 * @Author Ethereal
 * @Time : 2022/9/7
 */
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    /**
     * 判断是否登录成功
     * */
    @PostMapping("/admin/login/status")
    public R loginStatus(@RequestBody AdminRequest adminRequest, HttpSession session){
        return adminService.verityPassword(adminRequest,session);
    }
}
