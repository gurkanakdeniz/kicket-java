package com.kicket.api.kicketapi.response;

public class ExampleResponse extends BaseResponse {

    private static String exampleCode;
    private static String exampleRequest;

    static {
        exampleCode = "/* You can define multiple method definition, just change <ex> keyword with your method name. */\n /* If you changed <ex> keyword, you need to send your method name in request */\n/* ex. { method: <your method name> } */\n/* you shouldn't change this -> <External> */\n " +
                "public class External {\n" +
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
