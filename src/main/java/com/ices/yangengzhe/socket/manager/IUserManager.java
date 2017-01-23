package com.ices.yangengzhe.socket.manager;

import com.ices.yangengzhe.util.pojo.SocketUser;

/**
 * @date 2017年1月22日 下午1:06:43
 * @author yangengzhe
 *
 */
public interface IUserManager {

    boolean addUser(SocketUser user);

    boolean removeUser(SocketUser user);

    int getOnlineCount();

    SocketUser getUser(int userId);

}