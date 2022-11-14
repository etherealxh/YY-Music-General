package com.example.yy.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@TableName(value = "black_list")
@Data
public class BlackList {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String explain;

    private Date createTime;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}