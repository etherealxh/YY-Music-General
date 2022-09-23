package com.example.yy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yy.model.domain.Singer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerMapper extends BaseMapper<Singer> {
    /**
     * 获取歌手头像路径
     * @param singerId
     * @return
     * */
    String selectSingerPic(@Param("singer_id") Long singerId);
}
