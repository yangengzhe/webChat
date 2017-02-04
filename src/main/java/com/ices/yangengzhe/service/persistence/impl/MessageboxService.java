package com.ices.yangengzhe.service.persistence.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.dao.MessageBoxMapper;
import com.ices.yangengzhe.persistence.pojo.MessageBox;
import com.ices.yangengzhe.service.persistence.IMessageboxService;


/**
* webChat
* @date 2017年1月29日 下午5:24:31
* @author gengzhe.ygz
* 
*/
@Service
public class MessageboxService implements IMessageboxService {
    @Autowired
    MessageBoxMapper messageboxDao;

    @Override
    public void insertMessagebox(int uid, String content) {
        MessageBox messageBox = new MessageBox();
        messageBox.setAddtime(new Date());
        messageBox.setContent(content);
        messageBox.setUid(uid);
        messageboxDao.insert(messageBox);
    }

    @Override
    public int countMessageboxByUID(int uid) {
        return messageboxDao.countByUid(uid);
    }

    @Override
    public List<MessageBox> selectMessagebox(int uid,int offset) {
        int pageSize = 5;
        return messageboxDao.selectByUid(uid,offset,pageSize);
    }

}
