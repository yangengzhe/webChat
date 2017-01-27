package com.ices.yangengzhe.socket.manager;

import java.util.List;

import com.ices.yangengzhe.util.pojo.SocketUser;

/**
 * @date 2017年1月22日 下午1:06:43
 * @author yangengzhe
 *
 */
public interface IUserManager {

    boolean addUser(SocketUser user);

    int removeUser(SocketUser user);

    int getOnlineCount();

    SocketUser getUser(int userId);
    
    List<SocketUser> getOnlineUser();
    
    void notifyOthers(SocketUser user,String message);

}