package com.example.yy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yy.common.R;
import com.example.yy.mapper.BlackListMapper;
import com.example.yy.model.domain.BlackList;
import com.example.yy.model.domain.Consumer;
import com.example.yy.model.domain.Feedback;
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
        if (this.existBlackList(addBlackListRequest.getUsername())){
            return R.warning("用户已在黑名单中");
        }
        BlackList blackList = new BlackList();
        BeanUtils.copyProperties(addBlackListRequest,blackList);
        blackList.setCreateTime(new Date());
        if (blackListMapper.insert(blackList)>0){
            return R.success("加入黑名单成功");
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

    @Override
    public boolean existBlackList(String username) {
        QueryWrapper<BlackList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        if (blackListMapper.selectCount(queryWrapper)>0){
            return true;
        }else {
            return false;
        }
    }
}
