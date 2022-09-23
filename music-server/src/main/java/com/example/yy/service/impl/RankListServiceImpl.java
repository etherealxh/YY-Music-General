package com.example.yy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yy.common.R;
import com.example.yy.mapper.RankListMapper;
import com.example.yy.model.domain.RankList;
import com.example.yy.model.request.RankListRequest;
import com.example.yy.service.RankListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankListServiceImpl extends ServiceImpl<RankListMapper, RankList> implements RankListService {

    @Autowired
    private RankListMapper rankListMapper;


    @Override
    public R addRank(RankListRequest rankListAddRequest) {
        RankList rankList = new RankList();
        BeanUtils.copyProperties(rankListAddRequest,rankList);
        if (rankListMapper.insert(rankList)>0){
            return R.success("评价成功");
        }else {
            return R.error("评价失败");
        }
    }

    @Override
    public R rankOfSongListId(Long songListId) {
        //评分人数为0，返回0，否则返回均分
        QueryWrapper<RankList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        Long rankNum = rankListMapper.selectCount(queryWrapper);
        return R.success(null,(rankNum < 0) ? 0 :rankListMapper.selectScoreSum(songListId) / rankNum);
    }

    @Override
    public R getUserRank(Long consumerId, Long songListId) {
        return R.success(null,rankListMapper.selectUserRank(consumerId, songListId));
    }
}
