package es.hibernate.springbootdb.repository;

import es.hibernate.springbootdb.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}