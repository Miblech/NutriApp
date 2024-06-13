package es.hibernate.springbootdb.controller;

import es.hibernate.springbootdb.entity.DailyLog;
import es.hibernate.springbootdb.entity.NutrientSummary;
import es.hibernate.springbootdb.service.DailyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class DailyLogController {

    @Autowired
    private DailyLogService dailyLogService;

    @GetMapping
    public List<DailyLog> getAllLogs() {
        return dailyLogService.getAllLogs();
    }

    @GetMapping("/{date}")
    public List<DailyLog> getLogsByDate(@PathVariable String date) {
        LocalDate logDate = LocalDate.parse(date);
        return dailyLogService.getLogsByDate(logDate);
    }

    @GetMapping("/user")
    public List<DailyLog> getUserLogs(@AuthenticationPrincipal UserDetails userDetails) {
        return dailyLogService.getLogsByUser(userDetails.getUsername());
    }

    @GetMapping("/user/{date}")
    public List<DailyLog> getUserLogsByDate(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String date) {
        LocalDate logDate = LocalDate.parse(date);
        return dailyLogService.getLogsByUserAndDate(userDetails.getUsername(), logDate);
    }

    @GetMapping("/{id}/summary")
    public NutrientSummary getLogSummary(@PathVariable Long id) {
        return dailyLogService.getLogSummary(id);
    }

    @GetMapping("/user/{period}/summary")
    public NutrientSummary getUserLogSummaryByPeriod(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String period) {
        return dailyLogService.getUserLogSummaryByPeriod(userDetails.getUsername(), period);
    }

    @PostMapping
    public DailyLog createLog(@RequestBody DailyLog log, @AuthenticationPrincipal UserDetails userDetails) {
        return dailyLogService.createLog(log, userDetails.getUsername());
    }

    @PostMapping("/{dailyLogId}/addFood")
    public DailyLog addFoodToDailyLog(@PathVariable Long dailyLogId, @RequestParam Long foodId, @RequestParam Float weight) {
        return dailyLogService.addFoodToDailyLog(dailyLogId, foodId, weight);
    }

    @DeleteMapping("/{id}")
    public void deleteLog(@PathVariable Long id) {
        dailyLogService.deleteLog(id);
    }
}