package es.hibernate.springbootdb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "meal_items")
public class MealItem {
    @EmbeddedId
    private MealItemKey id;

    @MapsId("logId")
    @ManyToOne
    @JoinColumn(name = "log_id")
    private DailyLog dailyLog;

    @MapsId("foodItemId")
    @ManyToOne
    @JoinColumn(name = "food_item_id")
    private Food food;
}

