package ru.oskin_di.spring_market.dtos;

public class StatisticsItemDto {

    private String serviceName;

    private long time;

    public StatisticsItemDto() {
    }

    public StatisticsItemDto(String serviceName, long time) {
        this.serviceName = serviceName;
        this.time = time;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
