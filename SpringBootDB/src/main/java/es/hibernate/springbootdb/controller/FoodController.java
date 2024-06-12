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
}
