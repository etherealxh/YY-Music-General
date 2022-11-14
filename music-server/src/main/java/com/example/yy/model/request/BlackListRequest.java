package com.example.yy.model.request;

import lombok.Data;

import java.util.Date;
@Data
public class BlackListRequest {
    private Integer id;

    private String userId;

    private String explain;

    private Date createTime;
}
