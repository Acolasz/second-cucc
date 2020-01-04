package hu.kukutyin.core.utils;

public class ThreadNameFromSessionId {

    private static final String THREAD_NAME_PREFIX_FILTER = "REST-";
    private static final String THREAD_NAME_PREFIX_JMS = "JMS-";

    public ThreadNameFromSessionId() {
        // Helper thread search
    }

    public static void set(String sessionId) {
        Thread currentThread = Thread.currentThread();
        currentThread.setName(THREAD_NAME_PREFIX_FILTER + sessionId);
    }

    public static void setJms(String sessionId) {
        Thread currentThread = Thread.currentThread();
        currentThread.setName(THREAD_NAME_PREFIX_JMS + sessionId);
    }

}
