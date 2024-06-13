package es.hibernate.springbootdb.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MealItemKey implements Serializable {
    private Long logId;
    private Long foodItemId;
}
