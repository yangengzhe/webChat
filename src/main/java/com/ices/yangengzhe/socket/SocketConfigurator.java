package com.ices.yangengzhe.socket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;
/**
 * @date 2017年1月27日 上午8:37:38
 * @author yangengzhe
 *
 */
public class SocketConfigurator extends Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        config.getUserProperties().put("ClientIP", httpSession.getAttribute("ClientIPadd"));//把HttpSession中保存的ClientIP放到ServerEndpointConfig中，关键字可以跟之前不同
    }
}