package com.ices.yangengzhe.service.persistence;

import java.util.List;

import com.ices.yangengzhe.persistence.pojo.Apply;

/**
* webChat
* @date 2017年1月28日 下午9:22:06
* @author gengzhe.ygz
* 
*/
public interface IApplyService {
    //增加
    void insertApply(int fromuser,int fromuser_fid,int uid,String msg) throws Exception;
    
    //删除
    void deleteApply(int fromuser,int uid);
    
    //根据目标人查找
    List<Apply> selectApplyByUid(int uid,int offset);
    
    //根据目标人查找数量
    int countApplyByUid(int uid);
    
    //验证是否存在
    boolean isExist(int fromuser,int uid);
    
}
