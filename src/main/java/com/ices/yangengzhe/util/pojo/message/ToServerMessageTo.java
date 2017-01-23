package com.ices.yangengzhe.util.pojo.message;


/**
 * 客户端发出的信息： 接收者的信息
 * //to的结构如下：
{
  avatar: "avatar.jpg"
  ,id: "100001"
  ,name: "贤心"
  ,sign: "这些都是测试数据，实际使用请严格按照该格式返回"
  ,type: "friend" //聊天类型，一般分friend和group两种，group即群聊
  ,username: "贤心"
}
 * @date 2017年1月22日 下午1:59:57
 * @author yangengzhe
 *
 */
public class ToServerMessageTo {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    private int id;
    private String avatar;
    private String sign;
    private String type;
    private String name;
}
