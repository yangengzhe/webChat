package com.ices.yangengzhe.socket.manager;

import java.util.List;

import com.ices.yangengzhe.util.cache.ChatCache;

/**
 * 群组成员管理
 * 根据群id（gid）获得该群中全部成员，若缓存没有 则从数据库中都到缓存
 * @date 2017年1月22日 下午1:28:56
 * @author yangengzhe
 *
 */
public class GroupUserManager {
    private static final String cacheName = "WEBCHAT_GROUP";
    private static final String cacheKey = "GM_";

    //每个群存一个
    private String getCacheKey(int groupId){
        return cacheKey + groupId;
    }

    //将某个群的用户id存入缓存  key=》list
    public boolean saveGroupMemeberIds(int groupId, List<String> userIds) {
        String key = getCacheKey(groupId);
        ChatCache.getInstance().setListCache(cacheName,key,userIds);
        return true;
    }

    //获得群成员的状态
    public List<String> getGroupMembers(int groupId){
        String key = getCacheKey(groupId);
        List<String> list = ChatCache.getInstance().getListCache(cacheName,key);
        if (list == null || list.size()==0) {
            System.out.println("缓存中没有数据，需要从数据库读取");
            
            //从数据库中读取群成员
//            LayIMDao dao = new LayIMDao();
//            List<String> memebers = dao.getMemberListOnlyIds(groupId);
//            saveGroupMemeberIds(groupId, memebers);
//            return memebers;
        }else{
            System.out.println("直接从缓存中读取出来");
        }
        return list;
    }
}
