package com.example.yy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.yy.common.R;
import com.example.yy.constant.Constants;
import com.example.yy.mapper.ConsumerMapper;
import com.example.yy.model.domain.Consumer;
import com.example.yy.model.request.ConsumerRequest;
import com.example.yy.service.ConsumerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.example.yy.constant.Constants.SALT;

@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public R addUser(ConsumerRequest registryRequest) {
        if (this.existUser(registryRequest.getUsername())){
            return R.warning("用户名已注册");
        }
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(registryRequest,consumer);
        //使用MD5加密
        String password = DigestUtils.md5DigestAsHex((SALT+registryRequest.getPassword()).getBytes(StandardCharsets.UTF_8));
        consumer.setPassword(password);
        if (StringUtils.isBlank(consumer.getPhoneNum())){
            consumer.setPhoneNum(null);
        }
        if ("".equals(consumer.getEmail())){
            consumer.setEmail(null);
        }
        String avatar="img/avatarImages/user.jpg";
        consumer.setAvatar(avatar);
        try {
            if (consumerMapper.insert(consumer)>0){
                return R.success("注册成功");
            }else {
                return R.error("注册失败");
            }
        }catch (DuplicateKeyException e){
            return R.fatal(e.getMessage());
        }
    }

    @Override
    public R updateUserMsg(ConsumerRequest updateRequest) {
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(updateRequest,consumer);
        if (consumerMapper.updateById(consumer)>0){
            return R.success("修改成功");
        }else {
            return R.error("修改失败");
        }
    }

    @Override
    public R updateUserAvatar(MultipartFile avatarFile, int id) {
        String fileName = System.currentTimeMillis() + avatarFile.getOriginalFilename();
        //文件路径 根据系统获取对应文件分隔符
        String filePath = Constants.PROJECT_PATH + System.getProperty("file.separator")+"img"+System.getProperty("file.separator")+"avatarImages";
        File file = new File(filePath);
        if (!file.exists() && file.mkdir()){
            return R.fatal("创建文件失败");
        }

        //删除原文件，减少内存负载
        String oldFilePath = System.getProperty("user.dir") +consumerMapper.selectConsumerAvatar((long) id);
        File file2= new File(oldFilePath);
        String defaultPic = "/img/avatarImages/user.jpg";
        if (defaultPic.equals(consumerMapper.selectConsumerAvatar((long) (id))) ){}
        else {
            if(file2.exists()){
                file2.delete();
            }
        }
        File dest = new File(filePath + System.getProperty("file.separator")+fileName);
        String imgPath = "/img/avatarImages/" + fileName;
        try {
            avatarFile.transferTo(dest);
        }catch (IOException e){
            return R.fatal("上传失败" + e.getMessage());
        }
        Consumer consumer = new Consumer();
        consumer.setId(id);
        consumer.setAvatar(imgPath);
        if (consumerMapper.updateById(consumer)>0){
            return R.success("上传成功",imgPath);
        }else {
            return R.error("上传失败");
        }
    }

    @Override
    public R updatePassword(ConsumerRequest updatePasswordRequest) {
        if (!this.verityPasswd(updatePasswordRequest.getUsername(),updatePasswordRequest.getOldPassword())){
            return R.error("密码输入错误");
        }
        Consumer consumer = new Consumer();
        consumer.setId(updatePasswordRequest.getId());
        String secretPassword = DigestUtils.md5DigestAsHex((SALT + updatePasswordRequest.getPassword()).getBytes(StandardCharsets.UTF_8));
        consumer.setPassword(secretPassword);
        if (consumerMapper.updateById(consumer)>0){
            return R.success("密码修改成功");
        }else {
            return R.error("密码修改失败");
        }

    }

    @Override
    public boolean existUser(String username) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return consumerMapper.selectCount(queryWrapper)>0;
    }

    @Override
    public boolean verityPasswd(String username, String password) {
       QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
       queryWrapper.eq("username",username);
       String secretPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes(StandardCharsets.UTF_8));
       queryWrapper.eq("password",secretPassword);
       return consumerMapper.selectCount(queryWrapper)>0;
    }

    @Override
    public R deleteUser(Integer id) {
       if (consumerMapper.deleteById(id)>0){
           return R.success("删除成功");
       }else {
           return R.error("删除失败");
       }
    }

    @Override
    public R allUser() {
        return R.success(null,consumerMapper.selectList(null));
    }

    @Override
    public R userOfId(Integer id) {
        QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return R.success(null,consumerMapper.selectList(queryWrapper));
    }

    @Override
    public R loginStatus(ConsumerRequest loginRequest, HttpSession session) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (this.verityPasswd(username,password)){
            session.setAttribute("username",username);
            Consumer consumer = new Consumer();
            consumer.setUsername(username);
            QueryWrapper<Consumer> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",username);
            return R.success("登录成功",consumerMapper.selectList(queryWrapper));
        }else {
            return R.error("用户名或密码错误");
        }

    }
}
