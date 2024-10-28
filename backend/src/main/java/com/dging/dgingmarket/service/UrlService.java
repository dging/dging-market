package com.dging.dgingmarket.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UrlService {

    private final HttpServletRequest request;

    @Autowired
    public UrlService(HttpServletRequest request) {
        this.request = request;
    }

    public String getServerBaseUrl() {
        String scheme = request.getScheme();              // http 또는 https
        String serverName = request.getServerName();      // 호스트 이름
        int serverPort = request.getServerPort();         // 포트 번호

        return scheme + "://" + serverName + (serverPort == 80 || serverPort == 443 ? "" : ":" + serverPort);
    }
}
