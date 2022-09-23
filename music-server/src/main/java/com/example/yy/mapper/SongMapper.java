package com.example.yy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yy.model.domain.Song;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SongMapper extends BaseMapper<Song> {
    /**
     * 获取歌曲图片路径
     * @param songId
     * @return
     * */
    String selectSongPic(@Param("song_id") Long songId);
    /**
     * 获mp3文件路径
     * @param songId
     * @return
     * */
    String selectSongUrl(@Param("song_id") Long songId);
}
