package com.exec.api.execapi.response;

public class CreateResponse extends BaseResponse {

    private String platform;
    private String endpoint;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

}
