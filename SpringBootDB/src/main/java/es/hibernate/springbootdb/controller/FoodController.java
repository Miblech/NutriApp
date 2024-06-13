package es.hibernate.springbootdb.controller;

import es.hibernate.springbootdb.entity.Food;
import es.hibernate.springbootdb.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @PostMapping
    public Food createFood(@RequestBody Food food) {
        return foodRepository.save(food);
    }

    @GetMapping("/category/{category}")
    public List<Food> getFoodsByCategory(@PathVariable String category) {
        return foodRepository.findByCategory(category);
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

    @GetMapping("/categories")
    public List<String> getDistinctCategories() {
        return foodRepository.findDistinctCategories();
    }

}
