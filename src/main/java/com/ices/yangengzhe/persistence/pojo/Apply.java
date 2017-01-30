package com.ices.yangengzhe.persistence.pojo;

import java.util.Date;

public class Apply extends ApplyKey {
    private Integer fromuserFid;

    private Date addtime;

    private String msg;

    public Integer getFromuserFid() {
        return fromuserFid;
    }

    public void setFromuserFid(Integer fromuserFid) {
        this.fromuserFid = fromuserFid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }
}