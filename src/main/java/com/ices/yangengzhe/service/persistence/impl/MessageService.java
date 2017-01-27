package com.ices.yangengzhe.service.persistence.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.dao.MsgMapper;
import com.ices.yangengzhe.persistence.dao.MsgUnreadMapper;
import com.ices.yangengzhe.persistence.dao.UserMapper;
import com.ices.yangengzhe.persistence.pojo.Msg;
import com.ices.yangengzhe.persistence.pojo.MsgUnread;
import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.persistence.IMessageService;
import com.ices.yangengzhe.util.Parse;
import com.ices.yangengzhe.util.enums.ChatType;
import com.ices.yangengzhe.util.pojo.message.ToServerTextMessage;

/**
 * @date 2017年1月26日 下午3:54:01
 * @author yangengzhe
 *
 */
@Service
public class MessageService implements IMessageService {

    @Autowired
    MsgMapper msgDao;
    @Autowired
    UserMapper userDao;
    @Autowired
    MsgUnreadMapper msgUnreadDao;
    
    @Override
    public void insertMessage(ToServerTextMessage message) {
        Msg msg = new Msg();
        msg.setAddtime(new Date());
        msg.setFromuser(message.getMine().getId());
        msg.setMsg(message.getMine().getContent());
        if(message.getTo().getType().equals("friend")){
            msg.setUid(message.getTo().getId());
        }else{
            msg.setGid(message.getTo().getId());
        }
        msgDao.insert(msg);
    }

    @Override
    public List<String> getMessageByUIDTop(int uid, int num) {
        List<Msg> msgList =  msgDao.selectByUIDTop(uid, num);
        User sender = null;
        if(msgList.size()>0)
            sender = userDao.selectByUID(msgList.get(0).getFromuser());
        return Parse.getStringListToClientMessage(sender,msgList);
    }
    
    @Override
    public List<String> getMessageByUIDTop(int fromuser,int uid, int num) {
        List<Msg> msgList =  msgDao.selectByFUIDTop(fromuser, uid, num);
        User sender = null;
        if(msgList.size()>0)
            sender = userDao.selectByUID(msgList.get(0).getFromuser());
        return Parse.getStringListToClientMessage(sender,msgList);
    }

    @Override
    public List<String> getMessageByGIDTop(int gid, int num) {
        List<Msg> msgList = msgDao.selectByGIDTop(gid, num);
        User sender = null;
        if(msgList.size()>0)
            sender = userDao.selectByUID(msgList.get(0).getFromuser());
        return Parse.getStringListToClientMessage(sender,msgList);
    }

    @Override
    public List<String> getMessageByFromIDTop(int fromuser, int num) {
        List<Msg> msgList = msgDao.selectByFromIDTop(fromuser, num);
        User sender = null;
        if(msgList.size()>0)
            sender = userDao.selectByUID(msgList.get(0).getFromuser());
        return Parse.getStringListToClientMessage(sender,msgList);
    }

    @Override
    public void offlineMessage(ToServerTextMessage message) {
        Integer id = message.getTo().getId();
        Integer gid = message.getTo().getType().equals(ChatType.FRINED.getName()) ? 0:1;
        Integer fromuser = gid.equals(1)? 0 : message.getMine().getId(); //如果是组 则发出者为0
        msgUnreadDao.countMsg(id, gid, fromuser);
    }

    @Override
    public List<List<String>> getFriendUnreadMessage(Integer id) {
         List<MsgUnread> list= msgUnreadDao.selectByGidId(ChatType.FRINED.getOrdinal(), id);
         List<List<String>> result = new ArrayList<List<String>>();
         for (MsgUnread msgUnread : list) {
            Integer fromuser = msgUnread.getFromuser();
            Integer uid = msgUnread.getId();
            Integer num = msgUnread.getMsgcount();
            List<String> result_list = getMessageByUIDTop(fromuser, uid, num);
            msgUnreadDao.deleteMsg(uid, ChatType.FRINED.getOrdinal(), fromuser);
            result.add(result_list);
        }
         return result;
    }

    //id为组id
    @Override
    public List<List<String>> getGroupUnreadMessage(Integer id) {
        List<MsgUnread> list= msgUnreadDao.selectByGidId(ChatType.GROUP.getOrdinal(), id);
        List<List<String>> result = new ArrayList<List<String>>();
        for (MsgUnread msgUnread : list) {
           Integer gid = msgUnread.getId();
           Integer num = msgUnread.getMsgcount();
           List<String> result_list = getMessageByGIDTop(gid, num);
           msgUnreadDao.deleteMsg(gid, ChatType.GROUP.getOrdinal(), 0);
           result.add(result_list);
       }
        return result;
    }

}
