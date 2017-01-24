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

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private IInformation information;

    @RequestMapping("/init")
    public void init(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        
        int userId = Integer.parseInt(request.getParameter("id"));
        response.getWriter().write(information.init(userId));
    }
}