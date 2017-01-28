package com.ices.yangengzhe.service.persistence;

import java.util.List;

import com.ices.yangengzhe.persistence.pojo.FriendgroupDetail;

public interface IFriendgroupDetailService {

    public List<FriendgroupDetail> getFGMemberByFID(int FID);

    public void insertFG(int fid,int userUID);
}
