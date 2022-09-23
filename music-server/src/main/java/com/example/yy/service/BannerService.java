package com.example.yy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yy.model.domain.Banner;

import java.util.List;

public interface BannerService extends IService<Banner> {
    List<Banner> getAllBanner();
}
