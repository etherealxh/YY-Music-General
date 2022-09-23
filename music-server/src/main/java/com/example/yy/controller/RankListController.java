package com.example.yy.controller;

import com.example.yy.common.R;
import com.example.yy.model.request.RankListRequest;
import com.example.yy.service.RankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 评分相关
 * @Author Ethereal
 * @Time : 2022/9/8
 */
@RestController
public class RankListController {

    @Autowired
    private RankListService rankListService;
    /**
     * 提交评分
     * */
    @PostMapping("/rankList/add")
    public R addRank(@RequestBody RankListRequest rankListAddRequest){
        return rankListService.addRank(rankListAddRequest);
    }
    /**
     *获取指定歌单评分
     **/
    @GetMapping("/rankList")
    public R rankOfSongListId(@RequestParam Long songListId){
        return rankListService.rankOfSongListId(songListId);
    }
    /**
     * 获取指定用户歌单评分
     * */
    @GetMapping("/rankList/user")
    public R getUserRank(@RequestParam Long consumerId, @RequestParam Long songListId){
        return rankListService.getUserRank(consumerId,songListId);
    }
}
