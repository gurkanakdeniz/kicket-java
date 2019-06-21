package com.exec.api.execapi.response;

public class CommonResponse extends BaseResponse {

    Object response;

    public CommonResponse(Object response) {
        super();
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

}
