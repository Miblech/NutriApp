package es.hibernate.springbootdb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "meal_items")
public class MealItem {
    @EmbeddedId
    private MealItemKey id;

    @JsonIgnore
    @MapsId("logId")
    @ManyToOne
    @JoinColumn(name = "log_id")
    private DailyLog dailyLog;

    @MapsId("foodItemId")
    @ManyToOne
    @JoinColumn(name = "food_item_id")
    private Food food;

    private Float weight;

    public MealItem(DailyLog dailyLog, Food food, Float weight) {
        this.id = new MealItemKey(dailyLog.getLogId(), food.getId());
        this.dailyLog = dailyLog;
        this.food = food;
        this.weight = weight;
    }
}
