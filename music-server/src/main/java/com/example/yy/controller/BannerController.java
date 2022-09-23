package com.example.yy.controller;

import com.example.yy.common.R;
import com.example.yy.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 轮播图相关
 * @Author Ethereal
 * @Time : 2022/9/7
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;
    /**
     * TODO 用户前台调用 轮播图
     * 获取轮播图
     * */
    @GetMapping("/getAllBanner")
    public R getAllBanner(){
        return R.success("成功获取轮播图",bannerService.getAllBanner());
    }

}
