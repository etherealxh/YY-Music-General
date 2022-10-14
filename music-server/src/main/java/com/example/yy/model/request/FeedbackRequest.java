package com.example.yy.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class FeedbackRequest {
    private Integer id;

    private String userId;

    private String message;

    private Date createTime;


}
