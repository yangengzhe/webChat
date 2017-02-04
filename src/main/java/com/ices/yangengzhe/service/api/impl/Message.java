package com.ices.yangengzhe.service.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.pojo.Apply;
import com.ices.yangengzhe.persistence.pojo.MessageBox;
import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.api.IMessage;
import com.ices.yangengzhe.service.persistence.IApplyService;
import com.ices.yangengzhe.service.persistence.IFriendgroupDetailService;
import com.ices.yangengzhe.service.persistence.IMessageboxService;
import com.ices.yangengzhe.service.persistence.IUserService;
import com.ices.yangengzhe.util.Parse;
import com.ices.yangengzhe.util.enums.ResponseType;
import com.ices.yangengzhe.util.pojo.JsonPageResult;
import com.ices.yangengzhe.util.pojo.JsonResult;


/**
* webChat
* @date 2017年1月28日 下午10:13:40
* @author gengzhe.ygz
* 
*/
@Service
public class Message implements IMessage {
    @Autowired
    IApplyService applyService;
    @Autowired
    IFriendgroupDetailService friendgroupDetailService;
    @Autowired
    IMessageboxService messageService;
    @Autowired
    IUserService userService;
    
    @Override
    public JsonPageResult getMessageBox(int uid, int page) {
        final int pagesize = 5;
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
        int applyCount = applyService.countApplyByUid(uid);
        int msgCount = messageService.countMessageboxByUID(uid);
        int pages = (int) Math.ceil( (applyCount+msgCount)/Double.valueOf(pagesize) );
        //获得偏移量
        int offset = (page-1)*pagesize;
        //获得申请列表
        if(offset + pagesize <= applyCount)//全是申请列表
        {
            List<Apply> applyList = applyService.selectApplyByUid(uid, offset);
            for (Apply apply : applyList) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id", 0);
                map.put("content", "添加你为好友");
                map.put("uid", uid);
                map.put("from", apply.getFromuser());
                map.put("from_group", apply.getFromuserFid());
                map.put("remark", apply.getMsg());
                map.put("time", Parse.showTime(apply.getAddtime()));
                User user = userService.getUserByUID(apply.getFromuser());
                HashMap<String, Object> userMap = new HashMap<String, Object>();
                userMap.put("id", user.getUid());
                userMap.put("avatar", "../"+user.getHeadphoto());
                userMap.put("username", user.getName());
                userMap.put("sign", user.getSign());
                map.put("user", userMap);
                data.add(map);
            }
        }else if(offset <= applyCount){ //申请列表最后一页+历史第一页
            int count = 0;
            List<Apply> applyList = applyService.selectApplyByUid(uid, offset);
            for (Apply apply : applyList) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id", 0);
                map.put("content", "添加你为好友");
                map.put("uid", uid);
                map.put("from", apply.getFromuser());
                map.put("from_group", apply.getFromuserFid());
                map.put("remark", apply.getMsg());
                map.put("time", Parse.showTime(apply.getAddtime()));
                User user = userService.getUserByUID(apply.getFromuser());
                HashMap<String, Object> userMap = new HashMap<String, Object>();
                userMap.put("id", user.getUid());
                userMap.put("avatar", "../"+user.getHeadphoto());
                userMap.put("username", user.getName());
                userMap.put("sign", user.getSign());
                map.put("user", userMap);
                data.add(map);
                count++;
            }
            List<MessageBox> msgList = messageService.selectMessagebox(uid,0);
            for (MessageBox messageBox : msgList) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id", messageBox.getId());
                map.put("content", messageBox.getContent());
                map.put("uid", messageBox.getUid());
                map.put("time", Parse.showTime(messageBox.getAddtime()));
                data.add(map);
                if(++count == 5) break;
            }
        }else{//获得历史消息盒子
            offset -=applyCount;
            List<MessageBox> msgList = messageService.selectMessagebox(uid,offset);
            for (MessageBox messageBox : msgList) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id", messageBox.getId());
                map.put("content", messageBox.getContent());
                map.put("uid", messageBox.getUid());
                map.put("time", Parse.showTime(messageBox.getAddtime()));
                data.add(map);
            }
        }
        
        JsonPageResult result = new JsonPageResult(ResponseType.SUCCESS,pages,data);
        return result;
    }

    @Override
    public JsonResult applyAddFriend(int uid, int toUid, int from_group, String remark) {
        JsonResult jsonResult = null;
        if(friendgroupDetailService.isFriend(uid, toUid)){
            System.out.println("已经是好友");
            jsonResult = new JsonResult(ResponseType.APPLY_EXIST, null);
            return jsonResult;
        }
        try {
            applyService.insertApply(uid, from_group, toUid, remark);
            friendgroupDetailService.insertFG(from_group, toUid);
            //添加好友
            User user = userService.getUserByUID(toUid);
            messageService.insertMessagebox(uid, "您已添加 "+user.getName()+" 为好友");
            jsonResult = new JsonResult(ResponseType.SUCCESS, null);
        } catch (Exception e) {
            System.out.println("已经存在");
            jsonResult = new JsonResult(ResponseType.APPLY_REPERT, null);
        }
        return jsonResult;
    }

    @Override
    public JsonResult agreeAddFriend(int uid, int fromUid, int group) {
        JsonResult jsonResult = null;
        if(friendgroupDetailService.isFriend(uid, fromUid)){
            System.out.println("已经是好友");
            jsonResult = new JsonResult(ResponseType.APPLY_EXIST, null);
            return jsonResult;
        }
        try {
            friendgroupDetailService.insertFG(group, fromUid);
            User user = userService.getUserByUID(fromUid);
            messageService.insertMessagebox(uid, "您已添加 "+user.getName()+" 为好友");
            applyService.deleteApply(fromUid, uid);
            jsonResult = new JsonResult(ResponseType.SUCCESS, null);
        } catch (Exception e) {
            System.out.println("已经存在");
            jsonResult = new JsonResult(ResponseType.APPLY_REPERT, null);
        }
        return jsonResult;
    }

    @Override
    public JsonResult ignoreAddFriend(int uid, int fromUid) {
        User user = userService.getUserByUID(fromUid);
        messageService.insertMessagebox(uid, "您已忽略 "+user.getName()+" 的好友请求");
        applyService.deleteApply(fromUid, uid);
        return new JsonResult(ResponseType.SUCCESS, null);
    }

    @Override
    public Integer countUnreadMessage(int uid) {
//        return messageService.countMessageboxByUID(uid);
        return applyService.countApplyByUid(uid);
    }

}
