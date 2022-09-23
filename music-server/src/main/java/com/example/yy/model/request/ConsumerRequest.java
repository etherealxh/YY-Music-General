package com.example.yy.model.request;

import lombok.Data;

import java.util.Date;
@Data
public class ConsumerRequest {
    private Integer id;

    private String username;

    private String oldPassword; //因为会用到用户旧密码 无所谓的对应

    private String password;

    private Byte sex;

    private String phoneNum;

    private String email;

    private Date birth;

    private String introduction;

    private String location;

    private String avatar;

    private Date createTime;
}
