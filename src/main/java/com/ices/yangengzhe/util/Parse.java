package com.ices.yangengzhe.util;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ices.yangengzhe.persistence.pojo.Msg;
import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.persistence.IUserService;
import com.ices.yangengzhe.util.enums.ChatType;
import com.ices.yangengzhe.util.enums.ToClientMessageType;
import com.ices.yangengzhe.util.factory.WebChatFactory;
import com.ices.yangengzhe.util.pojo.message.ToClientMessageResult;
import com.ices.yangengzhe.util.pojo.message.ToClientTextMessage;

public class Parse {
    
    public static String getStringToClientMessage(User sender,Msg message) {

        ToClientTextMessage toClientTextMessage = new ToClientTextMessage();

        toClientTextMessage.setUsername(sender.getName());
        toClientTextMessage.setAvatar(sender.getHeadphoto());
        toClientTextMessage.setContent(message.getMsg());
        if(message.getGid()!=null && message.getGid()>0)
            toClientTextMessage.setType(ChatType.GROUP.getName());
        else
            toClientTextMessage.setType(ChatType.FRINED.getName());
        //群组和好友直接聊天不同，群组的id 是 组id，否则是发送人的id
        if (toClientTextMessage.getType().equals(ChatType.GROUP.getName())) {
            toClientTextMessage.setId(message.getGid());
        } else {
            toClientTextMessage.setId(message.getFromuser());
        }
        toClientTextMessage.setTimestamp(message.getAddtime().getTime());

        //返回统一消息接口
        ToClientMessageResult result = new ToClientMessageResult();
        result.setMsg(toClientTextMessage);
        result.setType(ToClientMessageType.TYPE_TEXT_MESSAGE);

        return WebChatFactory.createSerializer().toJSON(result);
    }
    
    public static List<String> getStringListToClientMessage(User sender,List<Msg> message) {
        List<String> resultlist = new ArrayList<String>();
        for(int i = message.size()-1;i>=0;i--)
            resultlist.add(getStringToClientMessage(sender, message.get(i)));
        return resultlist;
    }
}
