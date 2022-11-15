package com.example.yy.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;


@TableName(value = "feedback")
@Data
public class Feedback {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String message;

    private Date createTime;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
