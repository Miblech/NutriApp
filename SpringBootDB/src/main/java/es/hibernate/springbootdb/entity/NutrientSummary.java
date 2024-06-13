package es.hibernate.springbootdb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class NutrientSummary {
    private float carbohydrate;
    private float protein;
    private float fat;
    private float fiber;
    private float sugarTotal;

    public void addNutrients(Food food, float weightFactor) {
        if (food.getCarbohydrate() != null) this.carbohydrate += food.getCarbohydrate() * weightFactor;
        if (food.getProtein() != null) this.protein += food.getProtein() * weightFactor;
        if (food.getTotalLipid() != null) this.fat += food.getTotalLipid() * weightFactor;
        if (food.getFiber() != null) this.fiber += food.getFiber() * weightFactor;
        if (food.getSugarTotal() != null) this.sugarTotal += food.getSugarTotal() * weightFactor;
    }

    public void addNutrients(NutrientSummary other) {
        this.carbohydrate += other.carbohydrate;
        this.protein += other.protein;
        this.fat += other.fat;
        this.fiber += other.fiber;
        this.sugarTotal += other.sugarTotal;
    }
}