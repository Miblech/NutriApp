package es.hibernate.springbootdb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float calories;
    private Float servingSizeG;
    private Float fatTotalG;
    private Float fatSaturatedG;
    private Float proteinG;
    private Integer sodiumMg;
    private Integer potassiumMg;
    private Integer cholesterolMg;
    private Float carbohydratesTotalG;
    private Float fiberG;
    private Float sugarG;

}
