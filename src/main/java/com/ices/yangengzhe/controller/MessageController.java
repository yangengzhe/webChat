package com.ices.yangengzhe.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ices.yangengzhe.service.api.IMessage;
import com.ices.yangengzhe.util.factory.WebChatFactory;
import com.ices.yangengzhe.util.pojo.JsonPageResult;
import com.ices.yangengzhe.util.pojo.JsonResult;
import com.ices.yangengzhe.util.serializer.IJsonSerializer;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessage message;

    @RequestMapping("/applyAddFriend")
    public void applyAddFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer uid  = Integer.valueOf(request.getParameter("uid"));
        Integer toUid  = Integer.valueOf(request.getParameter("toUid"));
        Integer from_group  = Integer.valueOf(request.getParameter("from_group"));
        String remark  = request.getParameter("remark");
        JsonResult result = message.applyAddFriend(uid, toUid, from_group, remark);
        sendResult(response,result);
    }
    
    @RequestMapping("/getMsgbox")
    public void getMsgbox(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer uid  = Integer.valueOf(request.getParameter("uid"));
        Integer page  = Integer.valueOf(request.getParameter("page"));
        JsonPageResult result = message.getMessageBox(uid,page);
        sendResult(response,result);
    }
    
    @RequestMapping("/agreeFriend")
    public void agreeFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer uid  = Integer.valueOf(request.getParameter("uid"));
        Integer from_uid  = Integer.valueOf(request.getParameter("from_uid"));
        Integer group  = Integer.valueOf(request.getParameter("group"));
        JsonResult result = message.agreeAddFriend(uid, from_uid, group);
        sendResult(response,result);
    }
    
    @RequestMapping("/ignoreAddFriend")
    public void ignoreAddFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer uid  = Integer.valueOf(request.getParameter("uid"));
        Integer from_uid  = Integer.valueOf(request.getParameter("from_uid"));
        JsonResult result = message.ignoreAddFriend(uid, from_uid);
        sendResult(response,result);
    }
//    @RequestMapping("/searchUser")
//    public void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String keyword  = request.getParameter("keyword");
//        keyword =new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
//        JsonResult result = information.searchUserByKeyword(keyword);
//        sendResult(response,result);
//    }
    
    public void sendResult(HttpServletResponse response,Object result) throws IOException{
        response.setCharacterEncoding("UTF-8");
        IJsonSerializer serializer = WebChatFactory.createSerializer();
        response.getWriter().write(serializer.toJSON(result));
    }
}