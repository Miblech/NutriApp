package es.hibernate.springbootdb.controller;


import es.hibernate.springbootdb.entity.MealItem;
import es.hibernate.springbootdb.repository.MealItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal-items")
public class MealItemController {
    @Autowired
    private MealItemRepository mealItemRepository;

    @GetMapping
    public List<MealItem> getAllMealItems() {
        return mealItemRepository.findAll();
    }

    @PostMapping
    public MealItem createMealItem(@RequestBody MealItem mealItem) {
        return mealItemRepository.save(mealItem);
    }
}
