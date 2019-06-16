package com.exec.api.execapi.core;

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

    public static Object run(String folderName, String[] args) throws Exception {
        File file = new File(FileUtility.getCompileCodeFile(folderName));
        URL url = file.toURI().toURL();
        URL[] urls = new URL[] { url };

        ClassLoader cl = new URLClassLoader(urls);
        Class<?> c;
        c = Class.forName("External", false, cl);

        Object[] param = { getArgs(args) };

        Method m = c.getMethod("ex", param.getClass());
        Object obj = m.invoke(null, param);

        return obj;
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
