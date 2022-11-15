package com.example.yy.model.request;

import lombok.Data;

import java.util.Date;
@Data
public class BlackListRequest {
    private Integer id;

    private String username;

    private String reason;

    private Date createTime;
}
