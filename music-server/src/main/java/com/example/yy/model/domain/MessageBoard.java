package com.example.yy.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@TableName(value = "message_board")
@Data
public class MessageBoard {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String message;

    private Integer up;

    private Date createTime;

    private Integer replied;

    private String replyName;

    private String replyContent;

    private Date updateTime;

    private Integer enabled;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
