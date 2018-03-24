package com.apri.common.context;

import javax.servlet.http.HttpServletRequest;

public class RequestContext implements Context {
    protected HttpServletRequest request;

    public RequestContext(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getSessionId() {
        return request.getSession().getId();
    }
}
