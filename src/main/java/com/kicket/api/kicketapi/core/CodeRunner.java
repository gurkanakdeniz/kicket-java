package com.kicket.api.kicketapi.core;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class CodeRunner {

    public static void compile(String file) throws Exception {
        try {
            ProcessBuilder pb = new ProcessBuilder("javac", file);
            Process process = pb.start();

            while (process.isAlive()) {
                // stay
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

//		// blocked :(
//        BufferedReader reader =
//                new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        int exitCode = process.waitFor();
//        System.out.println("\nExited with error code : " + exitCode);
    }

    public Object run(String folderName, String[] args, String method) throws Exception {
        File file = new File(FileUtility.getCompileCodeFile(folderName));
        URL url = file.toURI().toURL();
        URL[] urls = new URL[] { url };

        ClassLoader cl = new URLClassLoader(urls);
        Class<?> c;
        c = Class.forName("External", false, cl);

        Object[] param = { getArgs(args) };
        String runMethod = method;
        if (runMethod == null || runMethod.trim().length() <= 0) {
            runMethod = "ex";
        }
        
        Method m = c.getMethod(runMethod, param.getClass());
        TimeoutCodeRunner timeoutCodeRunner = new TimeoutCodeRunner(m, 8000);
        timeoutCodeRunner.run(null, param); // m.invoke(null, param);
        
        Exception exception = timeoutCodeRunner.getException();
        if (exception != null) {
            throw exception;
        }
        
        Object obj = timeoutCodeRunner.getResult();

        return obj;
    }

    public void test() {
        int i = 0;
        while (true) {
            System.out.println(i++);
        }
    }

    private static Object[] getArgs(String[] args) {
        Object[] response = null;
        if (args != null && args.length > 0) {
            response = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                response[i] = args[i];
            }
        } else {
            response = new Object[] { "" };
        }

        return response;
    }

}
