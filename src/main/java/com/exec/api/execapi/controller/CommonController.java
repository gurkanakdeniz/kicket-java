package com.exec.api.execapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exec.api.execapi.core.CodeRunner;
import com.exec.api.execapi.core.FileUtility;
import com.exec.api.execapi.core.GuidGenerator;
import com.exec.api.execapi.request.CreateRequest;
import com.exec.api.execapi.request.RunRequest;
import com.exec.api.execapi.response.CreateResponse;
import com.exec.api.execapi.response.RunResponse;

@RestController
public class CommonController {

    @GetMapping(value = { "/" })
    public Object index() {
        return "return of the jedi";
    }

    @PostMapping(value = { "/create" })
    public CreateResponse create(@RequestBody CreateRequest request) {
        CreateResponse response = new CreateResponse();
        
        String uuid = "";
        try {
            uuid = GuidGenerator.generate();
            FileUtility.createFolder(uuid);
            FileUtility.createCode(request.getMethod(), request.getImports(), uuid);
            FileUtility.createClass(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            response.setUuid("FAIL: " + e.getMessage());
            return response;
        }

        response.setUuid(uuid);
        return response;
    }

    @PostMapping(value = { "/run/{guid}" })
    public RunResponse run(@PathVariable String guid, @RequestBody RunRequest request) {
        RunResponse response = new RunResponse();
        Object result = null;
        try {
            result = CodeRunner.run(guid, request.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponse("FAIL: " + e.getMessage());
            return response;
        }
        
        response.setResponse(result);
        return response;
    }

}
