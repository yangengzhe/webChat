package com.ices.yangengzhe.socket.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;

import com.ices.yangengzhe.util.pojo.SocketUser;


/**
 * 管理登陆用户session
 * @date 2017年1月22日 下午1:09:11
 * @author yangengzhe
 *
 */
public class UserManager implements IUserManager {

    private static Map<String,SocketUser> socketUserMap;


    private OnLineUserManager onLineUserManager;

    private UserManager(){
        socketUserMap = new ConcurrentHashMap<String, SocketUser>();
        onLineUserManager = new OnLineUserManager();
    }

    private static UserManager manager = new UserManager();

    public static IUserManager getInstance(){
        return manager;
    }


    @Override
    public boolean addUser(SocketUser user) {

        String sessionId = user.getSession().getId();
        removeUser(sessionId);
        socketUserMap.put(sessionId, user);
        //加入在线列表缓存
        onLineUserManager.addUser(Integer.toString(user.getUserId()),sessionId);
        return true;
    }


    @Override
    public int removeUser(SocketUser user) {
        String sessionId =  user.getSession().getId();
        int userid = removeUser(sessionId);
        onLineUserManager.removeUser(Integer.toString(userid));
        return userid;
    }


    @Override
    public int getOnlineCount() {
        return socketUserMap.size();
    }

    @Override
    public SocketUser getUser(int userId){
        String key = Integer.toString(userId);
        Map map = onLineUserManager.getOnLineUsers();
        if(map!=null && map.containsKey(key)){
            return socketUserMap.get(map.get(key));
        }
        return new SocketUser();
    }

    private int removeUser(String sessionId) {
        SocketUser user = socketUserMap.get(sessionId);
        if(user!=null){
            socketUserMap.remove(sessionId);
            return user.getUserId();
        }else
            return 0;
    }


    @Override
    public List<SocketUser> getOnlineUser() {
        List<String> String = new ArrayList<String>(onLineUserManager.getOnLineUsers().values());
        List<SocketUser> result = new ArrayList<SocketUser>();
        for (String value : String) {
            result.add(socketUserMap.get(value));
        }
        return result;
    }


    @Override
    public void notifyOthers(SocketUser user, String message) {
        List<SocketUser> users = getOnlineUser();
        for (SocketUser socketUser : users) {
            if(user.equals(socketUser))
                continue;
            socketUser.getSession().getAsyncRemote().sendText(message);
        }
    }

}
