package com.example.yy.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class FeedbackRequest {
    private Integer id;

    private String username;

    private String message;

    private Date createTime;


}
