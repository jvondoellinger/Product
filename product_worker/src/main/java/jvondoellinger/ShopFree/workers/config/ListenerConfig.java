package jvondoellinger.ShopFree.workers.config;

public abstract class ListenerConfig {
    protected String region;
    protected String queueUrl;
    protected Integer maxMessage;
    protected Integer waitTime;

    public String getQueueUrl() {
        return queueUrl;
    }

    public String getRegion() {
        return region;
    }

    public Integer getMaxMessage() {
        return maxMessage;
    }

    public Integer getWaitTime() {
        return waitTime;
    }
}
