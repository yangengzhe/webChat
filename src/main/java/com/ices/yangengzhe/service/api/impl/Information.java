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
import com.ices.yangengzhe.service.api.IInformation;
import com.ices.yangengzhe.service.persistence.IFriendgroupDetailService;
import com.ices.yangengzhe.service.persistence.IFriendgroupService;
import com.ices.yangengzhe.service.persistence.IUserService;
import com.ices.yangengzhe.util.enums.ResponseType;
import com.ices.yangengzhe.util.factory.WebChatFactory;
import com.ices.yangengzhe.util.pojo.SocketUser;
import com.ices.yangengzhe.util.serializer.IJsonSerializer;

@Service
public class Information implements IInformation {
    
    private final String key="ices-yangengzhe";
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IFriendgroupService  friendgroupService;
    @Autowired
    private IFriendgroupDetailService  friendgroupDetailService;

    @Override
    public boolean isLogin(Integer uid) {
        SocketUser user= WebChatFactory.createManager().getUser(uid);
        if(user.getSession()!= null)
            return true;
        else
            return false;
    }

    @Override
    public ResponseType getMyInformation(Integer uid, HashMap<String, Object> map) {
        if(!isLogin(uid)) return ResponseType.LOGIN_NO;
        
        User user = userService.getUserByUID(uid);
        if(user == null) return ResponseType.USER_NOTFOUND;
        //个人信息
        HashMap<String, Object> mine = new HashMap<String, Object>();
        mine.put("username", user.getName());
        mine.put("id", user.getUid());
        mine.put("status", "online");
        mine.put("sign", user.getSign());
        mine.put("avatar", user.getHeadphoto());
        map.put("mine", mine);
        
        //好友分组
        List<HashMap<String, Object>> friendgrouplist =getFriendgroupList(uid);        
        map.put("friend",friendgrouplist);
        
        //群
        map.put("group", "");
        return ResponseType.SUCCESS;
    }

    @Override
    public List<HashMap<String, Object>> getFriendgroupList(Integer uid) {
        List<HashMap<String, Object>> friendgrouplist = new ArrayList<HashMap<String, Object>>();
        
        List<Friendgroup> friendgroups = friendgroupService.getFGByUID(uid);
        for (Friendgroup friendgroup : friendgroups) {
            HashMap<String, Object> friendgroup_map = new HashMap<String, Object>();
            friendgroup_map.put("groupname", friendgroup.getName());
            friendgroup_map.put("id", friendgroup.getId());
            List<HashMap<String, Object>> friend = getFridengroupMember(friendgroup.getId());
            friendgroup_map.put("list", friend);
            friendgrouplist.add(friendgroup_map);
        }
        
        return friendgrouplist;
    }

    @Override
    public List<HashMap<String, Object>> getFridengroupMember(Integer fid) {
        List<HashMap<String, Object>> friend = new ArrayList<HashMap<String, Object>>();
        
        List<FriendgroupDetail> friendList = friendgroupDetailService.getFGMemberByFID(fid);
        for (FriendgroupDetail friendgroupDetail : friendList) {
            HashMap<String, Object> friendgroupDetail_map = new HashMap<String, Object>();
            friendgroupDetail_map.put("username", friendgroupDetail.getUser().getName());
            friendgroupDetail_map.put("id", friendgroupDetail.getUser().getUid());
            friendgroupDetail_map.put("avatar", friendgroupDetail.getUser().getHeadphoto());
            friendgroupDetail_map.put("sign", friendgroupDetail.getUser().getSign());
            if(isLogin(friendgroupDetail.getUser().getUid()))
                friendgroupDetail_map.put("status", "online");
            else
                friendgroupDetail_map.put("status", "offline");
            friend.add(friendgroupDetail_map);
        }
        return friend;
    }

    @Override
    public String init(Integer uid) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        ResponseType responseType = getMyInformation(uid,data);
        
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("code", responseType.getCode());
        result.put("msg", responseType.getMsg());
        result.put("data", data);
        
        IJsonSerializer serializer = WebChatFactory.createSerializer();
        return serializer.toJSON(result);
    }

    @Override
    public boolean authentication(Integer uid) {
        User user = userService.getUserByUID(uid);
        String password = uid+key;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            password = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        if(!user.getPassword().equals(password))
            return false;
        else
            return true;
    }

}
