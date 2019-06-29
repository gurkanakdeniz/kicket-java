package com.kicket.api.kicketapi.request;

public class RunRequest extends BaseRequest {

    private String[] args;
    private String method;

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
