package com.example.yy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yy.model.domain.RankList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RankListMapper extends BaseMapper<RankList> {

    /**
     * 查询总分
     * @param songListId
     * @return
     * */
    int selectScoreSum(Long songListId);
    /**
     * 查询指定用户评分
     * @param consumerId
     * @param songLstId
     * @return
     * */
    int selectUserRank(@Param("consumer_id") Long consumerId,@Param("song_list_id") Long songLstId);

}
