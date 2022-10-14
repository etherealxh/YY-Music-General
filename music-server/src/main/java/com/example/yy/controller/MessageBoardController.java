package com.example.yy.controller;

import com.example.yy.common.R;
import com.example.yy.model.request.MessageBoardRequest;
import com.example.yy.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 留言相关
 *  @Author Ethereal
 *  @Time : 2022/9/30
 * */
@RestController
public class MessageBoardController {
    @Autowired
    private MessageBoardService messageBoardService;

    /**
     * 添加留言
     * */
    @PostMapping("/message/add")
    public R addMessageBoard(@RequestBody MessageBoardRequest addMessageBoardRequest){
        return messageBoardService.addMessageBoard(addMessageBoardRequest);
    }

    /**
     * 删除留言
     * */
    @GetMapping("/message/delete")
    public R  deleteMessageBoard(@RequestParam Integer id){
        return messageBoardService.deleteMessageBoard(id);
    }

    /**
     * 点赞
     * */
    @PostMapping("/message/like")
    public R upMessageBoard(@RequestParam MessageBoardRequest upMessageBoardRequest){
        return messageBoardService.upMessageBoard(upMessageBoardRequest);
    }


    /**
     * 返回所有留言记录
     * */
    @GetMapping("/message")
    public R allMessageBoard() {
        return messageBoardService.allMessageBoard();
    }

    /**
    * 回复留言
    * */
    @PostMapping("/message/reply")
    public R replyMessageBoard(@RequestBody MessageBoardRequest replyMessageBoardRequest) {
        return messageBoardService.replyMessageBoard(replyMessageBoardRequest);
    }
    /**
     * 获取展示留言
     * */
    @GetMapping("/message/enabled/detail")
    public R getEnabledMessageBoard(@RequestParam int enabled){
        return messageBoardService.getEnabledMessageBoard(enabled);
    }
}
