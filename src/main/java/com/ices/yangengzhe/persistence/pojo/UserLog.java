package com.ices.yangengzhe.persistence.pojo;

import java.util.Date;

public class UserLog {
    private Integer id;

    private Integer uid;

    private String ip;

    private Date addtime;

    private String log;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log == null ? null : log.trim();
    }
}