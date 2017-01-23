package com.ices.yangengzhe.util.pojo.message;


/**
 * 客户端发出的消息： 发出方的信息
 * //mine的结构如下：
{
  avatar: "avatar.jpg" //我的头像
  ,content: "你好吗" //消息内容
  ,id: "100000" //我的id
  ,mine: true //是否我发送的消息
  ,username: "纸飞机" //我的昵称
}
 * @date 2017年1月22日 下午2:01:10
 * @author yangengzhe
 *
 */
public class ToServerMessageMine {
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String avatar;
    private int id;
    private String content;
    private String username;
}
