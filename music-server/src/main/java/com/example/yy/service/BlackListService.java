package com.example.yy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yy.common.R;
import com.example.yy.model.domain.BlackList;
import com.example.yy.model.request.BlackListRequest;

public interface BlackListService extends IService<BlackList> {

    R addBlackList(BlackListRequest addBlackListRequest);

    R deleteBlackList(Integer id);

    R allBlackList();

    boolean existBlackList(String username);
}
