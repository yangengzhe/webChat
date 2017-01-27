package com.ices.yangengzhe.service.api;

import java.util.HashMap;
import java.util.List;

import com.ices.yangengzhe.persistence.pojo.Friendgroup;
import com.ices.yangengzhe.persistence.pojo.User;

/**
 * @date 2017年1月24日 下午2:00:02
 * @author yangengzhe
 */
public interface IFriend {

    // 创建好友分组
    int addFriendgroup(Integer uid, String groupname);

    // 创建好友分组
    int addFriendgroup(Integer uid, String groupname, List<User> members);

    // 添加好友
    void addFriend(Integer user1,Integer user1_group,Integer user2,Integer user2_group);
    //向分组加成员
    void addMemberToFG(Integer groupId,Integer uid);

    // 获取分组列表
    public List<Friendgroup> getFriendgroupList(Integer uid);

    // 获取分组列表(含成员)
    public List<HashMap<String, Object>> getFriendgroupDetaillist(Integer uid);

    // 获取好友分组的成员
    public List<HashMap<String, Object>> getFridengroupMember(Integer fid);
}
