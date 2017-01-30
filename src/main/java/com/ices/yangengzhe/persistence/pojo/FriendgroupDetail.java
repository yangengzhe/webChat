package com.ices.yangengzhe.persistence.pojo;

public class FriendgroupDetail {
    private Integer id;

    private Integer fidOwner;
    
    private Integer fid;

    private Integer uid;
    
    private User user;

    
    public User getUser() {
        return user;
    }

    
    public void setUser(User user) {
        this.user = user;
    }

    
    public Integer getFidOwner() {
        return fidOwner;
    }


    
    public void setFidOwner(Integer fidOwner) {
        this.fidOwner = fidOwner;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}