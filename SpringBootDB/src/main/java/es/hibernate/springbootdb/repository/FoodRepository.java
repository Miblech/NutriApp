package es.hibernate.springbootdb.repository;

import es.hibernate.springbootdb.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("SELECT DISTINCT f.category FROM Food f")
    List<String> findDistinctCategories();
    List<Food> findByCategory(String category);

    @Query("SELECT f FROM Food f WHERE LOWER(f.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Food> findByDescriptionContainingIgnoreCase(@Param("description") String description);

    @Query("SELECT f FROM Food f WHERE f.carbohydrate > :carbohydrate")
    List<Food> findByCarbohydrateGreaterThan(Float carbohydrate);

    @Query("SELECT f FROM Food f WHERE f.protein > :protein")
    List<Food> findByProteinGreaterThan(Float protein);

}