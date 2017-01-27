package com.ices.yangengzhe.socket.manager;

import java.util.Map;

import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;

import com.ices.yangengzhe.util.cache.ChatCache;

/**
 * 利用缓存管理在线用户session
 * @date 2017年1月22日 下午1:10:11
 * @author yangengzhe
 *
 */
public class OnLineUserManager {
    static final String cacheName = "WEBCHAT";
    static final String cacheKey = "ONLINE_USER";

    public void addUser(String userId,String sessionId){
        Map map = ChatCache.getInstance().get(cacheName,cacheKey);
        if(map == null){
            map = new ConcurrentHashMap();
        }
        map.put(userId,sessionId);
        ChatCache.getInstance().set(cacheName,cacheKey,map);
    }

    public void removeUser(String userId){
        Map map = ChatCache.getInstance().get(cacheName,cacheKey);

        if (map == null){ return; }

        map.remove(userId);
        ChatCache.getInstance().set(cacheName,cacheKey,map);
    }

    public Map getOnLineUsers(){
        Map map = ChatCache.getInstance().get(cacheName,cacheKey);
        return map;
    }
    
    public int getOnlineUsersCount(){
        Map map = ChatCache.getInstance().get(cacheName, cacheKey);
        return map.size();
    }
    //
}
