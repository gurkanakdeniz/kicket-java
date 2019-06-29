package com.kicket.api.kicketapi.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtility {

    private static final String ROOT_FOLDER = "codes";
    private static final String ROOT_FILE = "External.java";
    private static final String ROOT_COMPILE_FILE = "External.class";

    public static void createFolder(String name) throws Exception {
        Path path = Paths.get(ROOT_FOLDER).resolve(name);
        Files.createDirectories(path);
    }

    public static void createCode(String code, String folderName) throws Exception {
        generateCode(folderName, code);
    }
    
    public static void createClass(String folderName) throws Exception {
        String codeFile = getCodeFile(folderName);
        CodeRunner.compile(codeFile);
    }
    
    private static void generateCode(String folderName, String code) throws Exception {
        String codeFile = getCodeFile(folderName);

        FileOutputStream fos = null;
        try {
            File file = new File(codeFile);
            fos = new FileOutputStream(file);

            fos.write(code.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static String getCodeFile(String folderName) {
        return ROOT_FOLDER + File.separator + folderName + File.separator + ROOT_FILE;
    }
    
    public static String getCompileCodeFile(String folderName) {
        return ROOT_FOLDER + File.separator + folderName;// + File.separator + ROOT_COMPILE_FILE;
    }

}
