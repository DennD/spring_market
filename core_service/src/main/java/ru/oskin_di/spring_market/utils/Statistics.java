package ru.oskin_di.spring_market.utils;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.oskin_di.spring_market.dtos.StatisticsItemDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Data
public class Statistics {

    private List<StatisticsItemDto> statisticsItemDtos = new ArrayList<>();

    @Around("execution(public * ru.oskin_di.spring_market.services..*.*(..))")
    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        addTime(Arrays.stream(proceedingJoinPoint.getTarget().getClass().getInterfaces()).findFirst().get().getSimpleName(), duration);
        return out;
    }


    private void addTime(String serviceName, long time) {
        for (StatisticsItemDto statisticsItemDto: statisticsItemDtos){
            if (statisticsItemDto.getServiceName().equals(serviceName)) {
                statisticsItemDto.setTime(statisticsItemDto.getTime() + time);
                return;
            }
        }
        statisticsItemDtos.add(new StatisticsItemDto(serviceName,time));
    }

}
