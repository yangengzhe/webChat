package com.ices.yangengzhe.service.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.api.IGroup;
import com.ices.yangengzhe.service.persistence.IGroupService;
import com.ices.yangengzhe.util.enums.ResponseType;
import com.ices.yangengzhe.util.pojo.JsonResult;


/**
 * @date 2017年1月27日 上午10:03:27
 * @author yangengzhe
 *
 */
@Service
public class Group implements IGroup {
    @Autowired
    IGroupService groupService;
    
    @Override
    public int addGroup(Integer uid,String name) {
        return groupService.insertGroup(uid, name);
    }

    @Override
    public int addGroup(Integer uid,String name, List<Integer> uids) {
        int groupId = groupService.insertGroup(uid, name);
        for (Integer integer : uids) {
            groupService.addMember(groupId, integer);
        }
        return groupId;
    }

    @Override
    public void addMemberToGroup(Integer gid, Integer uid) {
        groupService.addMember(gid, uid);
    }

    @Override
    public List<HashMap<String, Object>> getAllGroup(Integer uid) {
        List<com.ices.yangengzhe.persistence.pojo.Group> groups = groupService.selectGroup(uid);
        List<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
        for (com.ices.yangengzhe.persistence.pojo.Group group : groups) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("groupname", group.getName());
            map.put("id", group.getId());
            map.put("avatar", group.getHeadphoto());
            result.add(map);
        }
        return result;
    }

    @Override
    public JsonResult getGroupMembers(Integer gid) {
        
        HashMap<String, Object> data = new HashMap<String, Object>();
        //owner
        HashMap<String, Object> owner =new HashMap<String, Object>();        
        User user = groupService.getGroupOwner(gid);
        owner.put("username", user.getName());
        owner.put("id", user.getUid());
        owner.put("avatar", user.getHeadphoto());
        owner.put("sign", user.getSign());
        data.put("owner", owner);
        
        //list
        List<HashMap<String, Object>> memberslist = new ArrayList<HashMap<String,Object>>();
        List<User> userlist = groupService.getAllMember(gid);
        for (User user2 : userlist) {
            HashMap<String, Object> userMap =new HashMap<String, Object>();
            userMap.put("username", user2.getName());
            userMap.put("id", user2.getUid());
            userMap.put("avatar", user2.getHeadphoto());
            userMap.put("sign", user2.getSign());
            memberslist.add(userMap);
        }
        data.put("list", memberslist);
        return new JsonResult(ResponseType.SUCCESS,data);
    }

}
