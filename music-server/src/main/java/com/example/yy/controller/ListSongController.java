package com.example.yy.controller;

import com.example.yy.common.R;
import com.example.yy.model.request.ListSongRequest;
import com.example.yy.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 歌单歌曲相关
 * @Author Ethereal
 * @Time : 2022/9/7
 */
@RestController
public class ListSongController {
    @Autowired
    private ListSongService listSongService;
    /**
     * 给歌单添加歌曲
     * */
    @PostMapping("/listSong/add")
    public R addListSong(@RequestBody ListSongRequest addListSongRequest){
        return listSongService.addListSong(addListSongRequest);
    }
    /**
     * 删除歌单里的歌曲
     * */
    @GetMapping("/listSong/delete")
    public R deleteListSong(@RequestParam int songId){
        return listSongService.deleteListSong(songId);
    }
    /**
     * 返回歌单里指定歌单 ID 的歌曲
     * */
    @GetMapping("/listSong/detail")
    public R listSongOfSongId(@RequestParam int songListId) {
        return listSongService.listSongOfSongId(songListId);
    }
    /**
     * 更新歌单里面的歌曲信息
     * */
    @PostMapping("/listSong/update")
    public R updateListSongMsg(@RequestBody ListSongRequest updateListSongRequest){
        return listSongService.updateListSongMsg(updateListSongRequest);
    }
}
