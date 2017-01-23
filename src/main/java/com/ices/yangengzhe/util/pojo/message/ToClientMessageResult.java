package com.ices.yangengzhe.util.pojo.message;

import com.ices.yangengzhe.util.enums.ToClientMessageType;

/**
 * @date 2017年1月22日 下午2:22:30
 * @author yangengzhe
 *
 */
public class ToClientMessageResult {

    public ToClientMessageType getType() {
        return type;
    }

    public void setType(ToClientMessageType type) {
        this.type = type;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    private ToClientMessageType type;
    private Object msg;
}