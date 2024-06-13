package es.hibernate.springbootdb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String description;

    @Column(name = "nutrient_data_bank_number")
    private Integer nutrientDataBankNumber;

    @Column(name = "alpha_carotene")
    private Integer alphaCarotene;

    @Column(name = "beta_carotene")
    private Integer betaCarotene;

    @Column(name = "beta_cryptoxanthin")
    private Integer betaCryptoxanthin;

    private Float carbohydrate;

    private Integer cholesterol;

    private Float choline;

    private Float fiber;

    @Column(name = "lutein_zeaxanthin")
    private Integer luteinAndZeaxanthin;

    private Integer lycopene;

    private Float niacin;

    private Float protein;

    private Integer retinol;

    private Float riboflavin;

    private Float selenium;

    @Column(name = "sugar_total")
    private Float sugarTotal;

    private Float thiamin;

    private Float water;

    @Column(name = "monosaturated_fat")
    private Float monosaturatedFat;

    @Column(name = "polysaturated_fat")
    private Float polysaturatedFat;

    @Column(name = "saturated_fat")
    private Float saturatedFat;

    @Column(name = "total_lipid")
    private Float totalLipid;

    private Integer calcium;

    private Float copper;

    private Float iron;

    private Integer magnesium;

    private Integer phosphorus;

    private Integer potassium;

    private Integer sodium;

    private Float zinc;

    @Column(name = "vitamin_a_rae")
    private Integer vitaminARae;

    @Column(name = "vitamin_b12")
    private Float vitaminB12;

    @Column(name = "vitamin_b6")
    private Float vitaminB6;

    @Column(name = "vitamin_c")
    private Float vitaminC;

    @Column(name = "vitamin_e")
    private Float vitaminE;

    @Column(name = "vitamin_k")
    private Float vitaminK;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

