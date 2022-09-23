package com.example.yy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yy.model.domain.SongList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SongListMapper extends BaseMapper<SongList> {
    /**
     * 获取歌单头像路径
     * @param songListId
     * @return
     * */
    String selectSongListPic(@Param("song_list_id") Long songListId);
}
