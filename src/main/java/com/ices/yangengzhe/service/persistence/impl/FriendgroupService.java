package com.ices.yangengzhe.service.persistence.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.dao.FriendgroupMapper;
import com.ices.yangengzhe.persistence.pojo.Friendgroup;
import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.persistence.IFriendgroupService;

@Service
public class FriendgroupService implements IFriendgroupService {

    @Resource
    private FriendgroupMapper friendgroupDao;

    @Override
    public List<Friendgroup> getFGByUID(int userUID) {
        return friendgroupDao.selectByUID(userUID);
    }

    @Override
    public void insertFG(String name,int userUID) {
        Friendgroup friendgroup = new Friendgroup();
        friendgroup.setAddtime(new Date());
        friendgroup.setName(name);
        friendgroup.setSort(99);
        friendgroup.setUid(userUID);
        friendgroupDao.insert(friendgroup);
    }

}
