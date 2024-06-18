package es.hibernate.springbootdb.service;


import es.hibernate.springbootdb.entity.DailyLog;
import es.hibernate.springbootdb.entity.Food;
import es.hibernate.springbootdb.entity.MealItem;
import es.hibernate.springbootdb.entity.NutrientSummary;
import es.hibernate.springbootdb.entity.User;
import es.hibernate.springbootdb.repository.DailyLogRepository;
import es.hibernate.springbootdb.repository.FoodRepository;
import es.hibernate.springbootdb.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DailyLogServiceTest {

    @Mock
    private DailyLogRepository dailyLogRepository;

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DailyLogService dailyLogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllLogs() {
        DailyLog log1 = new DailyLog();
        DailyLog log2 = new DailyLog();
        when(dailyLogRepository.findAll()).thenReturn(Arrays.asList(log1, log2));

        List<DailyLog> logs = dailyLogService.getAllLogs();
        assertNotNull(logs);
        assertEquals(2, logs.size());
    }

    @Test
    void getLogsByDate() {
        LocalDate date = LocalDate.now();
        DailyLog log = new DailyLog();
        when(dailyLogRepository.findByDate(date)).thenReturn(Arrays.asList(log));

        List<DailyLog> logs = dailyLogService.getLogsByDate(date);
        assertNotNull(logs);
        assertEquals(1, logs.size());
    }

    @Test
    void getLogsByUser() {
        String username = "user1";
        DailyLog log = new DailyLog();
        when(dailyLogRepository.findByUserUserUsername(username)).thenReturn(Arrays.asList(log));

        List<DailyLog> logs = dailyLogService.getLogsByUser(username);
        assertNotNull(logs);
        assertEquals(1, logs.size());
    }

    @Test
    void getLogsByUserAndDate() {
        String username = "user1";
        LocalDate date = LocalDate.now();
        DailyLog log = new DailyLog();
        when(dailyLogRepository.findByUserUserUsernameAndDate(username, date)).thenReturn(Arrays.asList(log));

        List<DailyLog> logs = dailyLogService.getLogsByUserAndDate(username, date);
        assertNotNull(logs);
        assertEquals(1, logs.size());
    }

    @Test
    void getNutrientSummaryForDate() {
        String username = "user1";
        LocalDate date = LocalDate.now();
        DailyLog log = new DailyLog();
        NutrientSummary summary = new NutrientSummary();
        when(dailyLogRepository.findByUserUserUsernameAndDate(username, date)).thenReturn(Arrays.asList(log));

        NutrientSummary result = dailyLogService.getNutrientSummaryForDate(username, date);
        assertNotNull(result);
    }

    @Test
    void getDailyLogById() {
        Long id = 1L;
        DailyLog log = new DailyLog();
        when(dailyLogRepository.findById(id)).thenReturn(Optional.of(log));

        DailyLog result = dailyLogService.getDailyLogById(id);
        assertNotNull(result);
    }

    @Test
    void getLogSummary() {
        Long logId = 1L;
        DailyLog log = new DailyLog();
        NutrientSummary summary = new NutrientSummary();
        when(dailyLogRepository.findById(logId)).thenReturn(Optional.of(log));

        NutrientSummary result = dailyLogService.getLogSummary(logId);
        assertNotNull(result);
    }

    @Test
    void getTotalNutrientSummaryForUser() {
        String username = "user1";
        DailyLog log = new DailyLog();
        when(dailyLogRepository.findByUserUserUsername(username)).thenReturn(Arrays.asList(log));

        NutrientSummary result = dailyLogService.getTotalNutrientSummaryForUser(username);
        assertNotNull(result);
    }

    @Test
    void createLog() {
        String username = "user1";
        DailyLog log = new DailyLog();
        User user = new User();
        when(userRepository.findByUserUsername(username)).thenReturn(user);
        when(dailyLogRepository.save(log)).thenReturn(log);

        DailyLog result = dailyLogService.createLog(log, username);
        assertNotNull(result);
    }

    @Test
    void addFoodToDailyLog() {
        Long dailyLogId = 1L;
        Long foodId = 1L;
        Float weight = 100.0f;

        DailyLog dailyLog = new DailyLog();
        Food food = new Food();

        when(dailyLogRepository.findById(dailyLogId)).thenReturn(Optional.of(dailyLog));
        when(foodRepository.findById(foodId)).thenReturn(Optional.of(food));
        when(dailyLogRepository.save(any(DailyLog.class))).thenReturn(dailyLog);

        DailyLog result = dailyLogService.addFoodToDailyLog(dailyLogId, foodId, weight);

        assertNotNull(result, "Result should not be null");
        assertFalse(result.getMealItems().isEmpty(), "Meal items should not be empty");
        MealItem mealItem = result.getMealItems().get(0);
        assertEquals(food, mealItem.getFood(), "Food should be the same");
        assertEquals(weight, mealItem.getWeight(), "Weight should be the same");

        verify(dailyLogRepository, times(1)).findById(dailyLogId);
        verify(foodRepository, times(1)).findById(foodId);
        verify(dailyLogRepository, times(1)).save(dailyLog);
    }

    @Test
    void deleteLog() {
        Long id = 1L;
        doNothing().when(dailyLogRepository).deleteById(id);
        dailyLogService.deleteLog(id);
        verify(dailyLogRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteAllLogsByUser() {
        String username = "user1";
        doNothing().when(dailyLogRepository).deleteByUserUserUsername(username);
        dailyLogService.deleteAllLogsByUser(username);
        verify(dailyLogRepository, times(1)).deleteByUserUserUsername(username);
    }

    @Test
    void getLogsByUserAndPeriod() {
        String username = "user1";
        String period = "week";
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.minusWeeks(1);
        DailyLog log = new DailyLog();
        when(dailyLogRepository.findByUserUserUsernameAndDateBetween(username, startDate, now)).thenReturn(Arrays.asList(log));

        List<DailyLog> logs = dailyLogService.getLogsByUserAndPeriod(username, period);
        assertNotNull(logs);
        assertEquals(1, logs.size());
    }

    @Test
    void getUserLogSummaryByPeriod() {
        String username = "user1";
        String period = "week";
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.minusWeeks(1);
        DailyLog log = new DailyLog();
        when(dailyLogRepository.findByUserUserUsernameAndDateBetween(username, startDate, now)).thenReturn(Arrays.asList(log));

        NutrientSummary result = dailyLogService.getUserLogSummaryByPeriod(username, period);
        assertNotNull(result);
    }
}