package com.ices.yangengzhe.service.persistence.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.dao.GroupDetailMapper;
import com.ices.yangengzhe.persistence.dao.GroupMapper;
import com.ices.yangengzhe.persistence.dao.UserMapper;
import com.ices.yangengzhe.persistence.pojo.Group;
import com.ices.yangengzhe.persistence.pojo.GroupDetail;
import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.persistence.IGroupService;


/**
* webChat
* @date 2017年1月27日 上午10:23:48
* @author gengzhe.ygz
* 
*/
@Service
public class GroupService implements IGroupService {
    
    @Resource 
    GroupMapper groupDao;
    @Resource
    GroupDetailMapper groupDetailDao;
    @Resource
    UserMapper userDao;

    @Override
    public int insertGroup(Integer uid, String name) {
        Group group = new Group();
        group.setAddtime(new Date());
        group.setHeadphoto("");
        group.setName(name);
        group.setOwnerUid(uid);
        groupDao.insert(group);
        addMember(group.getId(), uid);
        return group.getId();
    }

    @Override
    public void addMember(Integer gid, Integer uid) {
        GroupDetail groupDetail = new GroupDetail();
        groupDetail.setAddtime(new Date());
        groupDetail.setGid(gid);
        groupDetail.setUid(uid);
        groupDetailDao.insert(groupDetail);
    }

    @Override
    public List<Group> selectGroup(Integer uid) {
        List<GroupDetail> groupDetails = groupDetailDao.selectByUID(uid);
        List<Group> result = new ArrayList<Group>();
        for (GroupDetail groupDetail : groupDetails) {
            Group group = groupDao.selectByPrimaryKey(groupDetail.getGid());
            result.add(group);
        }
        return result;
    }

    @Override
    public List<User> getAllMember(Integer gid) {
        List<GroupDetail> groupDetails = groupDetailDao.selectByGID(gid);
        List<User> result = new ArrayList<User>();
        for (GroupDetail groupDetail : groupDetails) {
            User user = userDao.selectByUID(groupDetail.getUid());
            result.add(user);
        }
        return result;
    }

    @Override
    public Group selectGroupById(Integer gid) {
        return groupDao.selectByPrimaryKey(gid);
    }

    @Override
    public User getGroupOwner(Integer gid) {
        return userDao.selectByUID(selectGroupById(gid).getOwnerUid());
    }

    @Override
    public List<String> getAllMemberToString(Integer gid) {
        List<GroupDetail> groupDetails = groupDetailDao.selectByGID(gid);
        List<String> result = new ArrayList<String>();
        for (GroupDetail groupDetail : groupDetails) {
            result.add(String.valueOf(groupDetail.getUid()));
        }
        return result;
    }

}
