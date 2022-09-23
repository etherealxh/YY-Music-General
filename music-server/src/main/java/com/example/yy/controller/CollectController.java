package com.example.yy.controller;

import com.example.yy.common.R;
import com.example.yy.model.request.CollectRequest;
import com.example.yy.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
* 收藏相关
 *  @Author Ethereal
 *  @Time : 2022/9/7
 * */
@RestController
public class CollectController {

    @Autowired
    private CollectService collectService;
    /**
     * 添加收藏的歌曲、歌单
     * */
    @PostMapping("/collection/add")
    public R addCollection(@RequestBody CollectRequest addCollectRequest){
        return collectService.addCollection(addCollectRequest);
    }
    /**
     * 取消收藏的歌曲、歌单
     * */
    @DeleteMapping("/collection/delete")
    public R deleteCollection(@RequestParam Integer userId,@RequestParam Integer songId){
        return  collectService.deleteCollect(userId, songId);
    }
    /**
     * 是否收藏歌曲、歌单
     * */
    @PostMapping("/collection/status")
    public R isCollection(@RequestBody CollectRequest isCollectRequest){
        return  collectService.existSongId(isCollectRequest);
    }
    /**
     * 返回指定用户ID收藏的列表
     * */
    @GetMapping("/collection/detail")
    public R collectionOfUser(@RequestParam Integer userId){
        return  collectService.collectionOfUser(userId);
    }
}
