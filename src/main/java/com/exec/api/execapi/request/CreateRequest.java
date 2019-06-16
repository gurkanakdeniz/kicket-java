package com.exec.api.execapi.request;

public class CreateRequest {

    private String method;
    private String imports;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getImports() {
        return imports;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }

}
