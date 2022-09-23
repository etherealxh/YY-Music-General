package com.example.yy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yy.model.domain.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}
