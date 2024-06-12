package es.hibernate.springbootdb.repository;

import es.hibernate.springbootdb.entity.DailyLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long> {
}
