package com.ices.yangengzhe.socket;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebListener()
public class SocketServletListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request=(HttpServletRequest) sre.getServletRequest();
        HttpSession session=request.getSession();
        session.setAttribute("ClientIPadd", sre.getServletRequest().getRemoteAddr());//把HttpServletRequest中的IP地址放入HttpSession中，关键字可任取，此处为ClientIP
    }
}