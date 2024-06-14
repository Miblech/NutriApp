package es.hibernate.springbootdb.controller;

import es.hibernate.springbootdb.entity.Food;
import es.hibernate.springbootdb.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @GetMapping("/category/{category}")
    public List<Food> getFoodsByCategory(@PathVariable String category) {
        return foodRepository.findByCategory(category);
    }

    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable Long id) {
        return foodRepository.findById(id).orElse(null);
    }

    @GetMapping("/search")
    public List<Food> searchFoodsByDescription(@RequestParam String description) {
        return foodRepository.findByDescriptionContainingIgnoreCase(description);
    }

    @GetMapping("/carbohydrate")
    public List<Food> getFoodsByCarbohydrateContent(@RequestParam Float carbohydrate) {
        return foodRepository.findByCarbohydrateGreaterThan(carbohydrate);
    }

    @GetMapping("/protein")
    public List<Food> getFoodsByProteinContent(@RequestParam Float protein) {
        return foodRepository.findByProteinGreaterThan(protein);
    }

    @GetMapping("/saturated-fat")
    public List<Food> getFoodsBySaturatedFatContent(@RequestParam Float saturatedFat) {
        return foodRepository.findByFatGreaterThan(saturatedFat);
    }

    @GetMapping("/carbohydrate/range")
    public List<Food> getFoodsByCarbohydrateRange(@RequestParam Float minCarbohydrate, @RequestParam Float maxCarbohydrate) {
        return foodRepository.findByCarbohydrateBetween(minCarbohydrate, maxCarbohydrate);
    }

    @GetMapping("/protein/range")
    public List<Food> getFoodsByProteinRange(@RequestParam Float minProtein, @RequestParam Float maxProtein) {
        return foodRepository.findByProteinBetween(minProtein, maxProtein);
    }

    @GetMapping("/saturated-fat/range")
    public List<Food> getFoodsBySaturatedFatRange(@RequestParam Float minSaturatedFat, @RequestParam Float maxSaturatedFat) {
        return foodRepository.findByFatBetween(minSaturatedFat, maxSaturatedFat);
    }

    @GetMapping("/sort/protein/desc")
    public List<Food> getAllFoodsSortedByProteinDesc() {
        return foodRepository.findAllByOrderByProteinDesc();
    }

    @GetMapping("/sort/carbohydrate/desc")
    public List<Food> getAllFoodsSortedByCarbohydrateDesc() {
        return foodRepository.findAllByOrderByCarbohydrateDesc();
    }

    @GetMapping("/sort/saturated-fat/desc")
    public List<Food> getAllFoodsSortedBySaturatedFatDesc() {
        return foodRepository.findAllByOrderByFatDesc();
    }

    @GetMapping("/sort/protein/asc")
    public List<Food> getAllFoodsSortedByProteinAsc() {
        return foodRepository.findAllByOrderByProteinAsc();
    }

    @GetMapping("/sort/carbohydrate/asc")
    public List<Food> getAllFoodsSortedByCarbohydrateAsc() {
        return foodRepository.findAllByOrderByCarbohydrateAsc();
    }

    @GetMapping("/sort/saturated-fat/asc")
    public List<Food> getAllFoodsSortedBySaturatedFatAsc() {
        return foodRepository.findAllByOrderByFatAsc();
    }

    @GetMapping("/categories")
    public List<String> getDistinctCategories() {
        return foodRepository.findDistinctCategories();
    }

}
