package ru.oskin_di.spring_market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oskin_di.spring_market.utils.Statistics;

@RestController
@RequiredArgsConstructor
public class StatisticsController {

    @Autowired
    private Statistics statistics;

    @GetMapping("/stat")
    public Statistics getStatistics(){
        return statistics;
    }

}
