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

    public ScaledFoodDTO toScaledFoodDTO(Float weight) {

        return new ScaledFoodDTO(
                this.category,
                this.description,
                this.nutrientDataBankNumber,
                this.alphaCarotene != null ? Math.round(this.alphaCarotene * weight / 100) : null,
                this.betaCarotene != null ? Math.round(this.betaCarotene * weight / 100) : null,
                this.betaCryptoxanthin != null ? Math.round(this.betaCryptoxanthin * weight / 100) : null,
                this.carbohydrate != null ? this.carbohydrate * weight / 100 : null,
                this.cholesterol != null ? Math.round(this.cholesterol * weight / 100) : null,
                this.choline != null ? this.choline * weight / 100 : null,
                this.fiber != null ? this.fiber * weight / 100 : null,
                this.luteinAndZeaxanthin != null ? Math.round(this.luteinAndZeaxanthin * weight / 100) : null,
                this.lycopene != null ? Math.round(this.lycopene * weight / 100) : null,
                this.niacin != null ? this.niacin * weight / 100 : null,
                this.protein != null ? this.protein * weight / 100 : null,
                this.retinol != null ? Math.round(this.retinol * weight / 100) : null,
                this.riboflavin != null ? this.riboflavin * weight / 100 : null,
                this.selenium != null ? this.selenium * weight / 100 : null,
                this.sugarTotal != null ? this.sugarTotal * weight / 100 : null,
                this.thiamin != null ? this.thiamin * weight / 100 : null,
                this.water != null ? this.water * weight / 100 : null,
                this.monosaturatedFat != null ? this.monosaturatedFat * weight / 100 : null,
                this.polysaturatedFat != null ? this.polysaturatedFat * weight / 100 : null,
                this.saturatedFat != null ? this.saturatedFat * weight / 100 : null,
                this.totalLipid != null ? this.totalLipid * weight / 100 : null,
                this.calcium != null ? Math.round(this.calcium * weight / 100) : null,
                this.copper != null ? this.copper * weight / 100 : null,
                this.iron != null ? this.iron * weight / 100 : null,
                this.magnesium != null ? Math.round(this.magnesium * weight / 100) : null,
                this.phosphorus != null ? Math.round(this.phosphorus * weight / 100) : null,
                this.potassium != null ? Math.round(this.potassium * weight / 100) : null,
                this.sodium != null ? Math.round(this.sodium * weight / 100) : null,
                this.zinc != null ? this.zinc * weight / 100 : null,
                this.vitaminARae != null ? Math.round(this.vitaminARae * weight / 100) : null,
                this.vitaminB12 != null ? this.vitaminB12 * weight / 100 : null,
                this.vitaminB6 != null ? this.vitaminB6 * weight / 100 : null,
                this.vitaminC != null ? this.vitaminC * weight / 100 : null,
                this.vitaminE != null ? this.vitaminE * weight / 100 : null,
                this.vitaminK != null ? this.vitaminK * weight / 100 : null
        );
    }
}

