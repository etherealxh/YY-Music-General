package com.example.yy.controller;

import com.example.yy.common.R;
import com.example.yy.model.request.BlackListRequest;
import com.example.yy.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 黑名单相关
 *
 * @Author Ethereal
 * @Time : 2022/11/14
 */
@RestController
public class BlackListController {

    @Autowired
    private BlackListService blackListService;

    /**
     * 加入黑名单
     * */
    @PostMapping("/blacklist/add")
    public R addBlackList(@RequestBody BlackListRequest addBlackListRequest) {
        return blackListService.addBlackList(addBlackListRequest);
    }

    /**
     *移除黑名单
     * */
    @GetMapping("/blacklist/delete")
    public  R deleteBlackList(@RequestParam Integer id) {
        return blackListService.deleteBlackList(id);
    }

    /**
     * 返回黑名单记录
     * */
    @GetMapping("/blacklist")
    public R allBlackList(){return blackListService.allBlackList();}

}
