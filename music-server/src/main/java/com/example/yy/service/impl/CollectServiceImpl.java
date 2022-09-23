package com.example.yy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yy.common.R;
import com.example.yy.mapper.CollectMapper;
import com.example.yy.model.domain.Collect;
import com.example.yy.model.request.CollectRequest;
import com.example.yy.service.CollectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Override
    public R addCollection(CollectRequest addCollectRequest) {
        Collect collect = new Collect();
        BeanUtils.copyProperties(addCollectRequest,collect);
        if (collectMapper.insert(collect)>0){
            return R.success("收藏成功",true);
        }else {
            return R.error("收藏失败");
        }

    }

    @Override
    public R existSongId(CollectRequest isCollectRequest) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",isCollectRequest.getUserId());
        queryWrapper.eq("song_id",isCollectRequest.getSongId());
        if (collectMapper.selectCount(queryWrapper)>0){
            return R.success("",true);
        }else {
            return R.success("",false);
        }
    }

    @Override
    public R deleteCollect(Integer userId, Integer songId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_id",songId);
        if (collectMapper.delete(queryWrapper) > 0) {
            return R.success("取消收藏", false);
        } else {
            return R.error("取消收藏失败");
        }
    }

    @Override
    public R collectionOfUser(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return R.success("用户收藏",collectMapper.selectList(queryWrapper));
    }
}
