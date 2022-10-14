package com.example.yy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yy.common.R;
import com.example.yy.model.domain.Feedback;
import com.example.yy.model.request.FeedbackRequest;

public interface FeedbackService extends IService<Feedback> {

    R addFeedback(FeedbackRequest addFeedbackRequest);

    R deleteFeedback(Integer id);

    R allFeedback();


}
