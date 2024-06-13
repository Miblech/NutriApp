package es.hibernate.springbootdb.service;

import es.hibernate.springbootdb.entity.*;
import es.hibernate.springbootdb.repository.DailyLogRepository;
import es.hibernate.springbootdb.repository.FoodRepository;
import es.hibernate.springbootdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DailyLogService {

    @Autowired
    private DailyLogRepository dailyLogRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    public List<DailyLog> getAllLogs() {
        return dailyLogRepository.findAll();
    }

    public List<DailyLog> getLogsByDate(LocalDate date) {
        return dailyLogRepository.findByDate(date);
    }

    public List<DailyLog> getLogsByUser(String username) {
        return dailyLogRepository.findByUserUserUsername(username);
    }

    public List<DailyLog> getLogsByUserAndDate(String username, LocalDate date) {
        return dailyLogRepository.findByUserUserUsernameAndDate(username, date);
    }

    public NutrientSummary getLogSummary(Long logId) {
        Optional<DailyLog> logOptional = dailyLogRepository.findById(logId);
        if (logOptional.isPresent()) {
            return logOptional.get().getNutrientSummary();
        } else {
            throw new RuntimeException("DailyLog not found");
        }
    }

    public NutrientSummary getUserLogSummaryByPeriod(String username, String period) {
        List<DailyLog> logs;
        LocalDate now = LocalDate.now();

        switch (period.toLowerCase()) {
            case "day":
                logs = dailyLogRepository.findByUserUserUsernameAndDate(username, now);
                break;
            case "week":
                logs = dailyLogRepository.findByUserUserUsernameAndDateBetween(username, now.minusWeeks(1), now);
                break;
            case "month":
                logs = dailyLogRepository.findByUserUserUsernameAndDateBetween(username, now.minusMonths(1), now);
                break;
            default:
                throw new IllegalArgumentException("Invalid period: " + period);
        }

        NutrientSummary summary = new NutrientSummary();
        for (DailyLog log : logs) {
            summary.addNutrients(log.getNutrientSummary());
        }
        return summary;
    }

    public DailyLog createLog(DailyLog log, String username) {
        System.out.println("Username: " + username);
        User user = userRepository.findByUserUsername(username);
        if (user == null) {
            System.out.println("User not found with username : " + username);
            throw new RuntimeException("User not found");
        }
        log.setUser(user);
        return dailyLogRepository.save(log);
    }

    public DailyLog addFoodToDailyLog(Long dailyLogId, Long foodId, Float weight) {
        Optional<DailyLog> dailyLogOptional = dailyLogRepository.findById(dailyLogId);
        Optional<Food> foodOptional = foodRepository.findById(foodId);

        if (dailyLogOptional.isPresent() && foodOptional.isPresent()) {
            DailyLog dailyLog = dailyLogOptional.get();
            Food food = foodOptional.get();

            MealItem mealItem = new MealItem(dailyLog, food, weight);
            dailyLog.getMealItems().add(mealItem);

            return dailyLogRepository.save(dailyLog);
        } else {
            throw new RuntimeException("DailyLog or Food not found");
        }
    }

    public void deleteLog(Long id) {
        dailyLogRepository.deleteById(id);
    }
}