package com.ices.yangengzhe.service.api;

import java.util.HashMap;
import java.util.List;

import com.ices.yangengzhe.persistence.pojo.Friendgroup;
import com.ices.yangengzhe.persistence.pojo.FriendgroupDetail;
import com.ices.yangengzhe.util.enums.ResponseType;

/**
 * @date 2017年1月23日 下午10:47:37
 * @author yangengzhe
 */
public interface IInformation {

    public boolean authentication(Integer uid);

    public boolean isLogin(Integer uid);

    public ResponseType getMyInformation(Integer uid, HashMap<String, Object> map);

    public List<HashMap<String, Object>> getFriendgroupList(Integer uid);

    public List<HashMap<String, Object>> getFridengroupMember(Integer fid);

    public String init(Integer uid);
}
