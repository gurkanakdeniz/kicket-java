package com.kicket.api.kicketapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kicket.api.kicketapi.core.CodeRunner;
import com.kicket.api.kicketapi.core.FileUtility;
import com.kicket.api.kicketapi.core.GuidGenerator;
import com.kicket.api.kicketapi.request.CreateRequest;
import com.kicket.api.kicketapi.request.RunRequest;
import com.kicket.api.kicketapi.response.CreateResponse;
import com.kicket.api.kicketapi.response.ExampleResponse;
import com.kicket.api.kicketapi.response.RunResponse;

@RestController
public class CommonController {
    
    private static final String platform = "java";

    @GetMapping(value = { "/" })
    public Object index() {
        return "return of the jedi";
    }

    @PostMapping(value = { "/create" })
    public CreateResponse create(@RequestBody CreateRequest request) {
        CreateResponse response = new CreateResponse();
        response.setPlatform(platform);;
        
        String uuid = request.getUuid();
        try {
            if (uuid == null || uuid.trim().length() <= 0) {
                uuid = GuidGenerator.generate();    
            }
            
            FileUtility.createFolder(uuid);
            FileUtility.createCode(request.getCode(), uuid);
            FileUtility.createClass(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            response.setEndpoint("FAIL: " + e.getMessage());
            return response;
        }

        response.setEndpoint(uuid);
        return response;
    }

    @PostMapping(value = { "/run/{guid}" })
    public RunResponse run(@PathVariable String guid, @RequestBody RunRequest request) {
        RunResponse response = new RunResponse();
        Object result = null;
        try {
            result = new CodeRunner().run(guid, request.getArgs(), request.getMethod());
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponse("FAIL: " + e.getMessage());
            return response;
        }
        
        response.setResponse(result);
        return response;
    }
    
    @GetMapping(value = { "/run/{guid}" })
    public RunResponse run(@PathVariable String guid) {
        RunResponse response = new RunResponse();
        Object result = null;
        try {
            result = new CodeRunner().run(guid, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponse("FAIL: " + e.getMessage());
            return response;
        }
        
        response.setResponse(result);
        return response;
    }
    
    @GetMapping(value = { "/example" })
    public ExampleResponse examples() {
        return new ExampleResponse();
    }

}
