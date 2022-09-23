package com.example.yy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yy.model.domain.Consumer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerMapper extends BaseMapper<Consumer> {
    /**
     * 获取用户头像路径
     * @param consumerId
     * @return
     * */
    String selectConsumerAvatar(@Param("consumer_id") Long consumerId);
}
