package com.ices.yangengzhe.persistence.pojo;

import java.util.Date;

public class MsgUnread {
    private Integer id;

    private Integer uid;

    private Integer fromuser;

    private Integer gid;

    private Date updatetime;

    private Integer msgcount;

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

    public Integer getFromuser() {
        return fromuser;
    }

    public void setFromuser(Integer fromuser) {
        this.fromuser = fromuser;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getMsgcount() {
        return msgcount;
    }

    public void setMsgcount(Integer msgcount) {
        this.msgcount = msgcount;
    }
}