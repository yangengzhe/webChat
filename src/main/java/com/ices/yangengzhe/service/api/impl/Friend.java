package com.ices.yangengzhe.service.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.pojo.Friendgroup;
import com.ices.yangengzhe.persistence.pojo.FriendgroupDetail;
import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.api.IFriend;
import com.ices.yangengzhe.service.persistence.IFriendgroupDetailService;
import com.ices.yangengzhe.service.persistence.IFriendgroupService;
import com.ices.yangengzhe.util.Global;
import com.ices.yangengzhe.util.security.Security;

@Service
public class Friend implements IFriend {

    @Autowired
    private IFriendgroupService       friendgroupService;
    @Autowired
    private IFriendgroupDetailService friendgroupDetailService;

    @Override
    public int addFriendgroup(Integer uid, String groupname) {
        return friendgroupService.insertFG(groupname, uid);
    }

    @Override
    public int addFriendgroup(Integer uid, String groupname, List<User> members) {
        int groupId = addFriendgroup(uid,groupname);
        for (User user : members) {
            addMemberToFG(groupId, user.getUid());
        }
        return groupId;
    }

    @Override
    public void addFriend(Integer user1, Integer user1_group, Integer user2, Integer user2_group) {
        addMemberToFG(user1_group, user2);
        addMemberToFG(user2_group, user1);
    }

    @Override
    public void addMemberToFG(Integer groupId, Integer uid) {
        friendgroupDetailService.insertFG(groupId, uid);
    }

    @Override
    public List<Friendgroup> getFriendgroupList(Integer uid) {
        List<Friendgroup> friendgroups = friendgroupService.getFGByUID(uid);
        return friendgroups;
    }

    @Override
    public List<HashMap<String, Object>> getFridengroupMember(Integer fid) {
        List<HashMap<String, Object>> friend = new ArrayList<HashMap<String, Object>>();

        List<FriendgroupDetail> friendList = friendgroupDetailService.getFGMemberByFID(fid);
        for (FriendgroupDetail friendgroupDetail : friendList) {
            HashMap<String, Object> friendgroupDetail_map = new HashMap<String, Object>();
            friendgroupDetail_map.put("username", friendgroupDetail.getUser().getName());
            friendgroupDetail_map.put("id", friendgroupDetail.getUser().getUid());
            friendgroupDetail_map.put("avatar", Global.URL+friendgroupDetail.getUser().getHeadphoto());
            friendgroupDetail_map.put("sign", friendgroupDetail.getUser().getSign());
            if (Security.isLogin(friendgroupDetail.getUser().getUid())) friendgroupDetail_map.put("status", "online");
            else friendgroupDetail_map.put("status", "offline");
            friend.add(friendgroupDetail_map);
        }
        return friend;
    }

    @Override
    public List<HashMap<String, Object>> getFriendgroupDetaillist(Integer uid) {
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

    
}
