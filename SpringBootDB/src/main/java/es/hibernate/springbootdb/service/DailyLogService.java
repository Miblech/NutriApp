package es.hibernate.springbootdb.service;

import es.hibernate.springbootdb.entity.DailyLog;
import es.hibernate.springbootdb.entity.Food;
import es.hibernate.springbootdb.entity.MealItem;
import es.hibernate.springbootdb.repository.DailyLogRepository;
import es.hibernate.springbootdb.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DailyLogService {

    @Autowired
    private DailyLogRepository dailyLogRepository;

    @Autowired
    private FoodRepository foodRepository;

    public List<DailyLog> getAllLogs() {
        return dailyLogRepository.findAll();
    }

    public DailyLog createLog(DailyLog log) {
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
}