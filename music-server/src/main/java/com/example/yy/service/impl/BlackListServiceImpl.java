package com.example.yy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yy.common.R;
import com.example.yy.mapper.BlackListMapper;
import com.example.yy.model.domain.BlackList;
import com.example.yy.model.request.BlackListRequest;
import com.example.yy.service.BlackListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlackListServiceImpl extends ServiceImpl<BlackListMapper, BlackList> implements BlackListService {

    @Autowired
    private BlackListMapper blackListMapper;
    @Override
    public R addBlackList(BlackListRequest addBlackListRequest) {
        BlackList blackList = new BlackList();
        BeanUtils.copyProperties(addBlackListRequest,blackList);
        blackList.setCreateTime(new Date());
        if (blackListMapper.insert(blackList)>0){
            return R.success("成功加入黑名单");
        }else {
            return R.error("加入失败");
        }
    }

    @Override
    public R deleteBlackList(Integer id) {
        if (blackListMapper.deleteById(id)>0){
            return R.success("移除成功");
        }else{
            return R.error("移除失败");
        }
    }

    @Override
    public R allBlackList() {
        return R.success(null,blackListMapper.selectList(null));
    }
}
