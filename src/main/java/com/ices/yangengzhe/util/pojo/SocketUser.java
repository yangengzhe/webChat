package com.ices.yangengzhe.util.pojo;

import javax.websocket.Session;

/**
 * @date 2017年1月22日 下午1:08:37
 * @author yangengzhe
 *
 */
public class SocketUser {

    private Session session;
    private int     userId;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isExist() {
        return this.userId > 0;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return session.getId() + "_" + userId;
    }
}
