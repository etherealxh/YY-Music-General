package com.example.yy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yy.common.R;
import com.example.yy.mapper.MessageBoardMapper;
import com.example.yy.model.domain.MessageBoard;
import com.example.yy.model.request.MessageBoardRequest;
import com.example.yy.service.MessageBoardService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageBoardServiceImpl extends ServiceImpl<MessageBoardMapper, MessageBoard> implements MessageBoardService {
    @Autowired
    private MessageBoardMapper messageBoardMapper;

    @Override
    public R addMessageBoard(MessageBoardRequest addMessageBoardRequest) {
        MessageBoard messageBoard= new MessageBoard();
        BeanUtils.copyProperties(addMessageBoardRequest,messageBoard);
        messageBoard.setCreateTime(new Date());
        if (messageBoardMapper.insert(messageBoard)>0){
           return R.success("留言成功");
        }else {
           return R.error("留言失败");
        }
    }

    @Override
    public R deleteMessageBoard(Integer id) {

        if (messageBoardMapper.deleteById(id)>0){
            return R.success("删除成功");
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R upMessageBoard(MessageBoardRequest upMessageBoardRequest) {
        MessageBoard messageBoard= new MessageBoard();
        BeanUtils.copyProperties(upMessageBoardRequest,messageBoard);
        if (messageBoardMapper.updateById(messageBoard)>0){
            return R.success("点赞成功");
        }else {
            return R.error("点赞失败");
        }
    }

    @Override
    public R allMessageBoard() {
        return R.success(null,messageBoardMapper.selectList(null));
    }

    @Override
    public R replyMessageBoard( MessageBoardRequest replyMessageBoardRequest) {
        MessageBoard messageBoard = new MessageBoard();
        BeanUtils.copyProperties(replyMessageBoardRequest,messageBoard);
        messageBoard.setUpdateTime(new Date());
        if (messageBoardMapper.updateById(messageBoard)>0) {
            return R.success("回复成功");
        }else {
            return R.error("回复失败");
        }
    }

    @Override
    public R getEnabledMessageBoard(Integer enabled) {
        QueryWrapper<MessageBoard> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("enabled",enabled);
        return R.success(null,messageBoardMapper.selectList(queryWrapper));
    }
}
