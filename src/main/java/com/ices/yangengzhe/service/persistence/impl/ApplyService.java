package com.ices.yangengzhe.service.persistence.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.dao.ApplyMapper;
import com.ices.yangengzhe.persistence.pojo.Apply;
import com.ices.yangengzhe.persistence.pojo.ApplyKey;
import com.ices.yangengzhe.service.persistence.IApplyService;


/**
* webChat
* @date 2017年1月28日 下午9:28:15
* @author gengzhe.ygz
* 
*/
@Service
public class ApplyService implements IApplyService {
    
    @Autowired
    ApplyMapper applyDao;
    
    @Override
    public void insertApply(int fromuser, int fromuser_fid, int uid, String msg) throws Exception {
        Apply apply = new Apply();
        apply.setAddtime(new Date());
        apply.setFromuser(fromuser);
        apply.setFromuserFid(fromuser_fid);
        apply.setMsg(msg);
        apply.setUid(uid);
        try {
            applyDao.insert(apply);
        } catch (Exception e) {
            throw new Exception();
        }
        
    }

    @Override
    public void deleteApply(int fromuser, int uid) {
        ApplyKey applyKey = new ApplyKey();
        applyKey.setFromuser(fromuser);
        applyKey.setUid(uid);
        applyDao.deleteByPrimaryKey(applyKey);
    }

    @Override
    public List<com.ices.yangengzhe.persistence.pojo.Apply> selectApplyByUid(int uid,int offset) {
        offset = offset<0?0:offset;
        int count = 5;//每页五个
        return applyDao.selectByUID(uid, offset, count);
    }

    @Override
    public int countApplyByUid(int uid) {
        return applyDao.countByUID(uid);
    }

    @Override
    public boolean isExist(int fromuser, int uid) {
        return applyDao.countByFromUid(fromuser, uid) == 0 ?false:true;
    }

}
