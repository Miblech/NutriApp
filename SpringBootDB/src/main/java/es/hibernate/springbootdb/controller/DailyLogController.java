package es.hibernate.springbootdb.controller;

import es.hibernate.springbootdb.entity.DailyLog;
import es.hibernate.springbootdb.entity.NutrientSummary;
import es.hibernate.springbootdb.service.DailyLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
@Tag(name = "Daily Log API", description = "API for managing daily logs")
public class DailyLogController {

    @Autowired
    private DailyLogService dailyLogService;

    @Operation(summary = "Get all logs", description = "Get all logs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Not Authorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public List<DailyLog> getAllLogs() {
        return dailyLogService.getAllLogs();
    }

    @Operation(summary = "Get logs by date", description = "Get logs by date in the format yyyy-MM-dd")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/date/{date}")
    public List<DailyLog> getLogsByDate(@PathVariable String date) {
        LocalDate logDate = LocalDate.parse(date);
        return dailyLogService.getLogsByDate(logDate);
    }

    @Operation(summary = "Get logs by Id", description = "Get logs by Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/search/{id}")
    public DailyLog getDailyLogById(@PathVariable Long id) {
        return dailyLogService.getDailyLogById(id);
    }

    @Operation(summary = "Get user logs", description = "Get user logs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Not Authorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/user/get")
    public List<DailyLog> getUserLogs(@AuthenticationPrincipal UserDetails userDetails) {
        return dailyLogService.getLogsByUser(userDetails.getUsername());
    }

    @Operation(summary = "Get user logs by date", description = "Get user logs by date (yyyy-MM-dd)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Not Authorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/user/log/{date}")
    public List<DailyLog> getUserLogsByDate(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String date) {
        LocalDate logDate = LocalDate.parse(date);
        return dailyLogService.getLogsByUserAndDate(userDetails.getUsername(), logDate);
    }

    @Operation(summary = "Get user log summary", description = "Get user log summary")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Not Authorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/user/summary/{date}")
    public NutrientSummary getUserLogSummaryForDate(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String date) {
        LocalDate logDate = LocalDate.parse(date);
        return dailyLogService.getNutrientSummaryForDate(userDetails.getUsername(), logDate);
    }

    @Operation(summary = "Get user logs for today", description = "Get user logs for today")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Not Authorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/user/today")
    public List<DailyLog> getUserLogsForToday(@AuthenticationPrincipal UserDetails userDetails) {
        LocalDate today = LocalDate.now();
        return dailyLogService.getLogsByUserAndDate(userDetails.getUsername(), today);
    }

    @Operation(summary = "Get user logs count", description = "Get user logs count")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Not Authorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/user/logs/count")
    public long getUserLogsCount(@AuthenticationPrincipal UserDetails userDetails) {
        return dailyLogService.getLogsByUser(userDetails.getUsername()).size();
    }

    @Operation(summary = "Get log summary", description = "Get log summary")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Not Authorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/summary/{id}")
    public NutrientSummary getLogSummary(@PathVariable Long id) {
        return dailyLogService.getLogSummary(id);
    }

    @Operation(summary = "Get total nutrient summary for user", description = "Get total nutrient summary for user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Not Authorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/user/summary")
    public NutrientSummary getTotalNutrientSummaryForUser(@AuthenticationPrincipal UserDetails userDetails) {
        return dailyLogService.getTotalNutrientSummaryForUser(userDetails.getUsername());
    }

    @Operation(summary = "Get user log summary by period", description = "Get user log summary by period (yyyy-MM-dd, yyyy-MM-dd)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Not Authorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/user/summary/period/{period}")
    public NutrientSummary getUserLogSummaryByPeriod(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String period) {
        return dailyLogService.getUserLogSummaryByPeriod(userDetails.getUsername(), period);
    }

    @Operation(summary = "Get user logs by period", description = "Get user logs by period")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Not Authorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/user/logs/{period}")
    public List<DailyLog> getLogsByUserAndPeriod(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String period) {
        return dailyLogService.getLogsByUserAndPeriod(userDetails.getUsername(), period);
    }

    @Operation(summary = "Create a new log", description = "Create a new daily log for the authenticated user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Log created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid log data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public DailyLog createLog(
            @Parameter(description = "Daily log data", required = true) @RequestBody DailyLog log,
            @AuthenticationPrincipal UserDetails userDetails) {
        return dailyLogService.createLog(log, userDetails.getUsername());
    }

    @Operation(summary = "Add food to daily log", description = "Add food to daily log example : /api/dailylogs/1/addFood?foodId=1&weight=1.0")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Food added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid food data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{dailyLogId}/addFood")
    public DailyLog addFoodToDailyLog(@PathVariable Long dailyLogId, @RequestParam Long foodId, @RequestParam Float weight) {
        return dailyLogService.addFoodToDailyLog(dailyLogId, foodId, weight);
    }

    @Operation(summary = "Delete log", description = "Delete log")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Log deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteLog(@PathVariable Long id) {
        dailyLogService.deleteLog(id);
    }


    @Operation(summary = "Delete all user logs", description = "Delete all user logs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Log deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/user/delete")
    public void deleteAllUserLogs(@AuthenticationPrincipal UserDetails userDetails) {
        dailyLogService.deleteAllLogsByUser(userDetails.getUsername());
    }

}