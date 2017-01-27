package com.ices.yangengzhe.service.api.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.inject.New;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.pojo.Friendgroup;
import com.ices.yangengzhe.persistence.pojo.FriendgroupDetail;
import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.api.IFriend;
import com.ices.yangengzhe.service.api.IInformation;
import com.ices.yangengzhe.service.persistence.IFriendgroupDetailService;
import com.ices.yangengzhe.service.persistence.IFriendgroupService;
import com.ices.yangengzhe.service.persistence.IUserService;
import com.ices.yangengzhe.util.enums.ResponseType;
import com.ices.yangengzhe.util.factory.WebChatFactory;
import com.ices.yangengzhe.util.pojo.JsonResult;
import com.ices.yangengzhe.util.pojo.SocketUser;
import com.ices.yangengzhe.util.security.Security;
import com.ices.yangengzhe.util.serializer.IJsonSerializer;

/**
 * @date 2017年1月24日 上午10:16:49
 * @author yangengzhe
 *
 */
@Service
public class Information implements IInformation {
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IFriend  friend;

    @Override
    public ResponseType getMyInformation(Integer uid, String password,HashMap<String, Object> map) {
        if(!Security.isLogin(uid)) return ResponseType.LOGIN_NO;
        
        User user = userService.getUserByUID(uid);
        if(user == null) return ResponseType.USER_NOTFOUND;
//        if(!Security.authentication(uid, password)) return ResponseType.USER_WRONG;
        
        //个人信息
        HashMap<String, Object> mine = new HashMap<String, Object>();
        mine.put("username", user.getName());
        mine.put("id", user.getUid());
        mine.put("status", "online");
        mine.put("sign", user.getSign());
        mine.put("avatar", user.getHeadphoto());
        map.put("mine", mine);
        
        //好友分组
        List<HashMap<String, Object>> friendgrouplist =friend.getFriendgroupDetaillist(uid);        
        map.put("friend",friendgrouplist);
        
        //群
        map.put("group", "");
        return ResponseType.SUCCESS;
    }

   

    @Override
    public JsonResult init(Integer uid, String password) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        ResponseType responseType = getMyInformation(uid,password,data);
        return new JsonResult(responseType,data);
    }



    @Override
    public void addUser(Integer uid, String name, String photo, String sign) {
        userService.insertUser(uid,name,photo,sign);
    }



    @Override
    public User findUserByUid(Integer uid) {
        return userService.getUserByUID(uid);
    }

    

}
