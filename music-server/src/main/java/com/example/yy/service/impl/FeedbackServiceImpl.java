package com.example.yy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yy.common.R;
import com.example.yy.mapper.FeedbackMapper;
import com.example.yy.model.domain.Feedback;
import com.example.yy.model.request.FeedbackRequest;
import com.example.yy.service.FeedbackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public R addFeedback(FeedbackRequest addFeedbackRequest) {
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(addFeedbackRequest,feedback);
        feedback.setCreateTime(new Date());
        if (feedbackMapper.insert(feedback)>0){
            return R.success("反馈成功");
        }else {
            return R.error("反馈失败");
        }
    }

    @Override
    public R deleteFeedback(Integer id) {
        if (feedbackMapper.deleteById(id)>0){
            return R.success("删除成功");
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R allFeedback() {
        return R.success(null,feedbackMapper.selectList(null));
    }
}
