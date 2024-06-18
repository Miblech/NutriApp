package es.hibernate.springbootdb.controller;

import es.hibernate.springbootdb.entity.Food;
import es.hibernate.springbootdb.repository.FoodRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@Tag(name = "Food API", description = "API for managing foods")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @Operation(summary = "Get all foods", description = "Retrieve a list of all foods")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Operation(summary = "Get foods by category", description = "Retrieve foods by a specific category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods by category"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/category/{category}")
    public List<Food> getFoodsByCategory(@PathVariable String category) {
        return foodRepository.findByCategory(category);
    }

    @Operation(summary = "Get food by ID", description = "Retrieve a food item by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the food item"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable Long id) {
        return foodRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Search foods by description", description = "Search foods by a keyword in the description")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods matching the description"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search/{description}")
    public List<Food> searchFoodsByDescription(@PathVariable String description) {
        return foodRepository.findByDescriptionContainingIgnoreCase(description);
    }

    @Operation(summary = "Get foods by carbohydrate content", description = "Retrieve foods with carbohydrate content greater than the specified amount")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/carbohydrate")
    public List<Food> getFoodsByCarbohydrateContent(@RequestParam Float carbohydrate) {
        return foodRepository.findByCarbohydrateGreaterThan(carbohydrate);
    }

    @Operation(summary = "Get foods by protein content", description = "Retrieve foods with protein content greater than the specified amount")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/protein")
    public List<Food> getFoodsByProteinContent(@RequestParam Float protein) {
        return foodRepository.findByProteinGreaterThan(protein);
    }

    @Operation(summary = "Get foods by saturated fat content", description = "Retrieve foods with saturated fat content greater than the specified amount")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/saturated-fat")
    public List<Food> getFoodsBySaturatedFatContent(@RequestParam Float saturatedFat) {
        return foodRepository.findByFatGreaterThan(saturatedFat);
    }

    @Operation(summary = "Get foods by carbohydrate range", description = "Retrieve foods with carbohydrate content within the specified range")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/carbohydrate/range")
    public List<Food> getFoodsByCarbohydrateRange(@RequestParam Float minCarbohydrate, @RequestParam Float maxCarbohydrate) {
        return foodRepository.findByCarbohydrateBetween(minCarbohydrate, maxCarbohydrate);
    }

    @Operation(summary = "Get foods by protein range", description = "Retrieve foods with protein content within the specified range")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/protein/range")
    public List<Food> getFoodsByProteinRange(@RequestParam Float minProtein, @RequestParam Float maxProtein) {
        return foodRepository.findByProteinBetween(minProtein, maxProtein);
    }

    @Operation(summary = "Get foods by saturated fat range", description = "Retrieve foods with saturated fat content within the specified range")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/saturated-fat/range")
    public List<Food> getFoodsBySaturatedFatRange(@RequestParam Float minSaturatedFat, @RequestParam Float maxSaturatedFat) {
        return foodRepository.findByFatBetween(minSaturatedFat, maxSaturatedFat);
    }

    @Operation(summary = "Get foods sorted by protein content", description = "Retrieve foods sorted by protein content in descending order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/sort/protein/desc")
    public List<Food> getAllFoodsSortedByProteinDesc() {
        return foodRepository.findAllByOrderByProteinDesc();
    }

    @Operation(summary = "Get foods sorted by carbohydrate content", description = "Retrieve foods sorted by carbohydrate content in descending order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/sort/carbohydrate/desc")
    public List<Food> getAllFoodsSortedByCarbohydrateDesc() {
        return foodRepository.findAllByOrderByCarbohydrateDesc();
    }

    @Operation(summary = "Get foods sorted by saturated fat content", description = "Retrieve foods sorted by saturated fat content in descending order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/sort/saturated-fat/desc")
    public List<Food> getAllFoodsSortedBySaturatedFatDesc() {
        return foodRepository.findAllByOrderByFatDesc();
    }

    @Operation(summary = "Get foods sorted by protein content", description = "Retrieve foods sorted by protein content in ascending order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/sort/protein/asc")
    public List<Food> getAllFoodsSortedByProteinAsc() {
        return foodRepository.findAllByOrderByProteinAsc();
    }

    @Operation(summary = "Get foods sorted by carbohydrate content", description = "Retrieve foods sorted by carbohydrate content in ascending order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/sort/carbohydrate/asc")
    public List<Food> getAllFoodsSortedByCarbohydrateAsc() {
        return foodRepository.findAllByOrderByCarbohydrateAsc();
    }

    @Operation(summary = "Get foods sorted by saturated fat content", description = "Retrieve foods sorted by saturated fat content in ascending order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of foods"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/sort/saturated-fat/asc")
    public List<Food> getAllFoodsSortedBySaturatedFatAsc() {
        return foodRepository.findAllByOrderByFatAsc();
    }

    @Operation(summary = "Get distinct categories", description = "Retrieve a list of distinct food categories")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of categories"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/categories")
    public List<String> getDistinctCategories() {
        return foodRepository.findDistinctCategories();
    }

}