package com.exec.api.execapi.response;

public class ExampleResponse extends BaseResponse {

    private static String exampleCode;
    private static String exampleRequest;

    static {
        exampleCode = "public class External {\n" + 
                "    \n" + 
                "    public static String ex(Object[] args) {\n" + 
                "        \n return \"\";" + 
                "    }\n" + 
                "    \n" + 
                "}";
        exampleRequest = "{\n" + 
                "    \"args\" : [\"4\", \"2\"],\n" + 
                "    \"method\" : \"ex\"\n" + 
                "}";
    }

    public String getExampleCode() {
        return exampleCode;
    }

    public String getExampleRequest() {
        return exampleRequest;
    }
}
