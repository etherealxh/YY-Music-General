package com.example.yy.controller;

import com.example.yy.common.R;
import com.example.yy.model.request.FeedbackRequest;
import com.example.yy.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 反馈相关
 *  @Author Ethereal
 *  @Time : 2022/9/30
 * */
@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    /**
     * 添加反馈
     * */
    @PostMapping("/feedback/add")
    public R addFeedback(@RequestBody FeedbackRequest addFeedbackRequest){
        return feedbackService.addFeedback(addFeedbackRequest);
    }

    /**
     * 删除反馈
     * */
    @GetMapping("/feedback/delete")
    public R deleteFeedback(@RequestParam Integer id){
        return feedbackService.deleteFeedback(id);
    }

    /**
     * 返回所有反馈记录
     * */
    @GetMapping("/feedback")
    public R allFeedback() {
        return feedbackService.allFeedback();
    }


}
