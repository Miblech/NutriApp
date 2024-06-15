package es.hibernate.springbootdb.repository;

import es.hibernate.springbootdb.entity.DailyLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {
    List<DailyLog> findByDate(LocalDate date);

    List<DailyLog> findByUserUserUsername(String username);

    List<DailyLog> findByUserUserUsernameAndDate(String username, LocalDate date);

    List<DailyLog> findByUserUserUsernameAndDateBetween(String username, LocalDate startDate, LocalDate endDate);

    void deleteByUserUserUsername(String username);
}
