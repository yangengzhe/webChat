package com.ices.yangengzhe.service.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.api.IFriend;
import com.ices.yangengzhe.service.api.IGroup;
import com.ices.yangengzhe.service.api.IInformation;
import com.ices.yangengzhe.service.persistence.IUserService;
import com.ices.yangengzhe.util.Global;
import com.ices.yangengzhe.util.enums.ResponseType;
import com.ices.yangengzhe.util.pojo.JsonResult;
import com.ices.yangengzhe.util.security.Security;

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
    @Autowired
    private IGroup group;

    @Override
    public ResponseType getMyInformation(Integer uid, String password,HashMap<String, Object> map) {
        if(!Security.isLogin(uid)) return ResponseType.LOGIN_NO;
        
        User user = userService.getUserByUID(uid);
        if(user == null) return ResponseType.USER_NOTFOUND;
        //if(!Security.authentication(uid, password)) return ResponseType.USER_WRONG;
        
        //个人信息
        HashMap<String, Object> mine = new HashMap<String, Object>();
        mine.put("username", user.getName());
        mine.put("id", user.getUid());
        mine.put("status", "online");
        mine.put("sign", user.getSign());
        mine.put("avatar", Global.URL+user.getHeadphoto());
        map.put("mine", mine);
        
        //好友分组
        List<HashMap<String, Object>> friendgrouplist =friend.getFriendgroupDetaillist(uid);        
        map.put("friend",friendgrouplist);
        
        //群
        List<HashMap<String, Object>> grouplist = group.getAllGroup(uid);
        map.put("group", grouplist);
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



    @Override
    public JsonResult searchUserByKeyword(String keyword) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        List<User> users = userService.searchUsersByKeyword(keyword);
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
        for (User user : users) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("username", user.getName());
            map.put("id", user.getUid());
            map.put("avatar", Global.URL+user.getHeadphoto());
            map.put("sign", user.getSign());
            map.put("status", "online");
            list.add(map);
        }
        data.put("list", list);
        return new JsonResult(ResponseType.SUCCESS,data);
    }

    

}
