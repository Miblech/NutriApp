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
    List<Food> findByCarbohydrateGreaterThan(@Param("carbohydrate") Float carbohydrate);

    @Query("SELECT f FROM Food f WHERE f.protein > :protein")
    List<Food> findByProteinGreaterThan(@Param("protein") Float protein);

    @Query("SELECT f FROM Food f WHERE f.saturatedFat > :fat")
    List<Food> findByFatGreaterThan(@Param("fat") Float fat);

    @Query("SELECT f FROM Food f WHERE f.carbohydrate BETWEEN :minCarbohydrate AND :maxCarbohydrate")
    List<Food> findByCarbohydrateBetween(@Param("minCarbohydrate") Float minCarbohydrate, @Param("maxCarbohydrate") Float maxCarbohydrate);

    @Query("SELECT f FROM Food f WHERE f.protein BETWEEN :minProtein AND :maxProtein")
    List<Food> findByProteinBetween(@Param("minProtein") Float minProtein, @Param("maxProtein") Float maxProtein);

    @Query("SELECT f FROM Food f WHERE f.saturatedFat BETWEEN :minFat AND :maxFat")
    List<Food> findByFatBetween(@Param("minFat") Float minFat, @Param("maxFat") Float maxFat);

    @Query("SELECT f FROM Food f ORDER BY f.protein DESC")
    List<Food> findAllByOrderByProteinDesc();

    @Query("SELECT f FROM Food f ORDER BY f.carbohydrate DESC")
    List<Food> findAllByOrderByCarbohydrateDesc();

    @Query("SELECT f FROM Food f ORDER BY f.saturatedFat DESC")
    List<Food> findAllByOrderByFatDesc();

    @Query("SELECT f FROM Food f ORDER BY f.protein ASC")
    List<Food> findAllByOrderByProteinAsc();

    @Query("SELECT f FROM Food f ORDER BY f.carbohydrate ASC")
    List<Food> findAllByOrderByCarbohydrateAsc();

    @Query("SELECT f FROM Food f ORDER BY f.saturatedFat ASC")
    List<Food> findAllByOrderByFatAsc();

}