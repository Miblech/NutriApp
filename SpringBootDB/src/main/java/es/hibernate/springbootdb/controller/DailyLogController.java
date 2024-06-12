package es.hibernate.springbootdb.controller;

import es.hibernate.springbootdb.entity.DailyLog;
import es.hibernate.springbootdb.repository.DailyLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class DailyLogController {
    @Autowired
    private DailyLogRepository dailyLogRepository;

    @GetMapping
    public List<DailyLog> getAllLogs() {
        return dailyLogRepository.findAll();
    }

    @PostMapping
    public DailyLog createLog(@RequestBody DailyLog log) {
        return dailyLogRepository.save(log);
    }
}