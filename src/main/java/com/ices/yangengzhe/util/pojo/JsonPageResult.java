package com.ices.yangengzhe.util.pojo;

import com.ices.yangengzhe.util.enums.ResponseType;

/**
 * @date 2017年1月24日 上午10:19:44
 * @author yangengzhe
 */
public class JsonPageResult {

    private int    code;
    private int    pages;
    private Object data;

    public JsonPageResult(ResponseType type, Integer pages,Object obj){
        code = type.getCode();
        this.pages = pages;
        data = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    public int getPages() {
        return pages;
    }

    
    public void setPages(int pages) {
        this.pages = pages;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
