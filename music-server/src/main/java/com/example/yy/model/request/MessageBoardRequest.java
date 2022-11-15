package com.example.yy.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class MessageBoardRequest {

    private Integer id;

    private String username;

    private String message;

    private Integer up;

    private Date createTime;

    private Integer replied;

    private String replyName;

    private String replyContent;

    private Date updateTime;

    private Integer enabled;

}
