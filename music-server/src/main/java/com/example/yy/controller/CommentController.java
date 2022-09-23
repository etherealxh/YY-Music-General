package com.example.yy.controller;

import com.example.yy.common.R;
import com.example.yy.model.request.CommentRequest;
import com.example.yy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * 评论相关
 *  @Author Ethereal
 *  @Time : 2022/9/7
 * */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 增加评论
     * */
    @PostMapping("/comment/add")
    public R addComment(@RequestBody CommentRequest addCommentRequest){
        return commentService.addComment(addCommentRequest);
    }
    /**
     * 删除评论
     * */
    @GetMapping("/comment/delete")
    public R deleteComment(@RequestParam Integer id){
        return commentService.deleteComment(id);
    }
    /**
     * 歌曲评论
     * */
    @GetMapping("/comment/song/detail")
    public R commentOfSongId(@RequestParam Integer songId){
        return commentService.commentOfSongId(songId);
    }
    /**
     * 歌单评论
     * */
    @GetMapping("/comment/songList/detail")
    public R commentOfSongListId(@RequestParam Integer songListId){
        return commentService.commentOfSongListId(songListId);
    }
    /**
     * 点赞
     * */
    @PostMapping("/comment/like")
    public R commentOfLike(@RequestParam CommentRequest upCommentRequest){
        return commentService.updateCommentMsg(upCommentRequest);
    }


}
