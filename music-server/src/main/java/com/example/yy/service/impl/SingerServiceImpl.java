package com.example.yy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yy.common.R;
import com.example.yy.mapper.SingerMapper;
import com.example.yy.model.domain.Singer;
import com.example.yy.model.request.SingerRequest;
import com.example.yy.service.SingerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public R addSinger(SingerRequest addSingerRequest) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(addSingerRequest,singer);
        String pic = "/img/avatarImages/user.jpg";
        singer.setPic(pic);
        if (singerMapper.insert(singer)>0){
            return R.success("添加成功");
        }else {
            return R.error("添加失败");
        }
    }

    @Override
    public R updateSingerMsg(SingerRequest updateSingerRequest) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(updateSingerRequest,singer);
        if (singerMapper.updateById(singer)>0){
            return R.success("修改成功");
        }else {
            return R.error("修改失败");
        }
    }

    @Override
    public R updateSingerPic(MultipartFile avatarFile, int id) {
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic";
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        //删除原文件，减少内存负载
        String oldFilePath = System.getProperty("user.dir") +singerMapper.selectSingerPic((long) id);
        File file2= new File(oldFilePath);
        String defaultPic = "/img/avatarImages/user.jpg";
        if (defaultPic.equals(singerMapper.selectSingerPic((long) (id))) ){}
        else {
            if(file2.exists()){
                file2.delete();
            }
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/singerPic/" + fileName;
        try {
            avatarFile.transferTo(dest);
        }catch (IOException e){
            return R.fatal("上传失败"+e.getMessage());
        }
        Singer singer = new Singer();
        singer.setId(id);
        singer.setPic(imgPath);
        if (singerMapper.updateById(singer)>0){
            return R.success("上传成功");
        }else {
            return R.error("上传失败");
        }

    }

    @Override
    public R deleteSinger(Integer id) {
        if (singerMapper.deleteById(id) > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    @Override
    public R allSinger() {
        return R.success(null,singerMapper.selectList(null));
    }

    @Override
    public R singerOfName(String name) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return R.success(null,singerMapper.selectList(queryWrapper)) ;
    }

    @Override
    public R singerOfSex(Integer sex) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("sex",sex);
        return R.success(null,singerMapper.selectList(queryWrapper)) ;
    }
}
