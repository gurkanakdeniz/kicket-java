package com.kicket.api.kicketapi.core;

import java.lang.reflect.Method;

public class TimeoutCodeRunner {

    private Method method;
    private long timeout = 5000;
    private Object result;
    private Exception exception;
    private Throwable throwable;

    public TimeoutCodeRunner(Method method) {
        this.method = method;
    }

    public TimeoutCodeRunner(Method method, long timeout) {
        this.method = method;
        this.timeout = timeout;
    }

    public void run(Object obj, Object... args) throws Exception {
        ThreadGroup threadGroup = new ThreadGroup("threadGroup");
        Thread runThread = new Thread(threadGroup, new Runnable() {

            public void run() {
                try {
                    result = method.invoke(obj, args);
                } catch (Exception e) {
                    e.printStackTrace();
                    exception = e;
                } catch (Throwable e) {
                    e.printStackTrace();
                    throwable = e;
                }
            }
        });
        runThread.setDaemon(true);

        Thread timeThread = new Thread(threadGroup, new Runnable() {

            @SuppressWarnings("deprecation")
            public void run() {

                try {
                    Thread.sleep(timeout);
                    // INFO:: this method deprecated, i know but not work other ways
                    runThread.stop();
                    runThread.interrupt();
                    threadGroup.interrupt();
                    threadGroup.destroy();
                } catch (Exception e) {
                    return;
                }
            }
        });

        runThread.start();
        timeThread.start();
        timeThread.join();
    }

    public Object getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
