package com.ices.yangengzhe.service.persistence.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.dao.FriendgroupDetailMapper;
import com.ices.yangengzhe.persistence.dao.FriendgroupMapper;
import com.ices.yangengzhe.persistence.pojo.Friendgroup;
import com.ices.yangengzhe.persistence.pojo.FriendgroupDetail;
import com.ices.yangengzhe.service.persistence.IFriendgroupDetailService;

@Service
public class FriendgroupDetailService implements IFriendgroupDetailService {

    @Resource
    private FriendgroupDetailMapper friendgroupDetailDao;
    @Resource
    private FriendgroupMapper friendgroupDao;

    @Override
    public List<FriendgroupDetail> getFGMemberByFID(int FID) {
        return friendgroupDetailDao.selectUsersByFID(FID);
    }

    @Override
    public void insertFG(int fid, int userUID) {
        FriendgroupDetail fd = new FriendgroupDetail();
        fd.setFid(fid);
        fd.setUid(userUID);
        Friendgroup fgroup = friendgroupDao.selectByPrimaryKey(fid);
        fd.setFidOwner(fgroup.getUid());
        friendgroupDetailDao.insert(fd);
    }

    @Override
    public boolean isFriend(int uidA, int uidB) {
        return friendgroupDetailDao.countfidOwnerUid(uidA, uidB) >0?true:false;
    }

}
