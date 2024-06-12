package es.hibernate.springbootdb.repository;

import es.hibernate.springbootdb.entity.MealItem;
import es.hibernate.springbootdb.entity.MealItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealItemRepository extends JpaRepository<MealItem, MealItemKey> {
}
