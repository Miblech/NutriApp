package es.hibernate.springbootdb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScaledFoodDTO {

    private String category;
    private String description;
    private Integer nutrientDataBankNumber;
    private Integer alphaCarotene;
    private Integer betaCarotene;
    private Integer betaCryptoxanthin;
    private Float carbohydrate;
    private Integer cholesterol;
    private Float choline;
    private Float fiber;
    private Integer luteinAndZeaxanthin;
    private Integer lycopene;
    private Float niacin;
    private Float protein;
    private Integer retinol;
    private Float riboflavin;
    private Float selenium;
    private Float sugarTotal;
    private Float thiamin;
    private Float water;
    private Float monosaturatedFat;
    private Float polysaturatedFat;
    private Float saturatedFat;
    private Float totalLipid;
    private Integer calcium;
    private Float copper;
    private Float iron;
    private Integer magnesium;
    private Integer phosphorus;
    private Integer potassium;
    private Integer sodium;
    private Float zinc;
    private Integer vitaminARae;
    private Float vitaminB12;
    private Float vitaminB6;
    private Float vitaminC;
    private Float vitaminE;
    private Float vitaminK;

}