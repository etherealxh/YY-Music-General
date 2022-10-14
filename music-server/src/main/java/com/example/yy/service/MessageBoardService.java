package com.example.yy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yy.common.R;
import com.example.yy.model.domain.MessageBoard;
import com.example.yy.model.request.MessageBoardRequest;

public interface MessageBoardService extends IService<MessageBoard> {

    R addMessageBoard(MessageBoardRequest addMessageBoardRequest);

    R deleteMessageBoard(Integer id);

    R upMessageBoard(MessageBoardRequest upMessageBoardRequest);

    R allMessageBoard();

    R replyMessageBoard(MessageBoardRequest replyMessageBoardRequest);

    R getEnabledMessageBoard(Integer enabled);


}

