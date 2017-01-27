package com.ices.yangengzhe.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.api.IInformation;
import com.ices.yangengzhe.service.persistence.IUserService;
import com.ices.yangengzhe.util.factory.WebChatFactory;
import com.ices.yangengzhe.util.pojo.JsonResult;
import com.ices.yangengzhe.util.serializer.IJsonSerializer;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private IInformation information;

    @RequestMapping("/init")
    public void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String id  = request.getParameter("id");
        String key  = request.getParameter("key");
        JsonResult result = information.init(Integer.valueOf(id), key);
        sendResult(response,result);
    }
    
    public void sendResult(HttpServletResponse response,JsonResult result) throws IOException{
        response.setCharacterEncoding("UTF-8");
        IJsonSerializer serializer = WebChatFactory.createSerializer();
        response.getWriter().write(serializer.toJSON(result));
    }
}