package com.ices.yangengzhe.service.persistence;

import java.util.List;

import com.ices.yangengzhe.persistence.pojo.Friendgroup;

public interface IFriendgroupService {

    public List<Friendgroup> getFGByUID(int userUID);

    public void insertFG(String name,int userUID);
}
