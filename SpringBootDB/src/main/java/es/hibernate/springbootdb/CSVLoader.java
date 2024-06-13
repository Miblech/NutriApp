package es.hibernate.springbootdb;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import es.hibernate.springbootdb.entity.Food;
import es.hibernate.springbootdb.repository.FoodRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVLoader {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void loadCSVData() throws IOException{
        if (foodRepository.count() == 0) {
            try (CSVReader reader = new CSVReader(new InputStreamReader(
                    resourceLoader.getResource("classpath:food.csv").getInputStream()))) {
                String[] line;
                List<Food> foods = new ArrayList<>();

                reader.readNext();

                while((line = reader.readNext()) != null){

                    Food food = new Food();
                    food.setCategory(line[0]);
                    food.setDescription(line[1]);
                    food.setNutrientDataBankNumber(Integer.parseInt(line[2]));
                    food.setAlphaCarotene(Integer.parseInt(line[3]));
                    food.setBetaCarotene(Integer.parseInt(line[4]));
                    food.setBetaCryptoxanthin(Integer.parseInt(line[5]));
                    food.setCarbohydrate(Float.parseFloat(line[6]));
                    food.setCholesterol(Integer.parseInt(line[7]));
                    food.setCholine(Float.parseFloat(line[8]));
                    food.setFiber(Float.parseFloat(line[9]));
                    food.setLuteinAndZeaxanthin(Integer.parseInt(line[10]));
                    food.setLycopene(Integer.parseInt(line[11]));
                    food.setNiacin(Float.parseFloat(line[12]));
                    food.setProtein(Float.parseFloat(line[13]));
                    food.setRetinol(Integer.parseInt(line[14]));
                    food.setRiboflavin(Float.parseFloat(line[15]));
                    food.setSelenium(Float.parseFloat(line[16]));
                    food.setSugarTotal(Float.parseFloat(line[17]));
                    food.setThiamin(Float.parseFloat(line[18]));
                    food.setWater(Float.parseFloat(line[19]));
                    food.setMonosaturatedFat(Float.parseFloat(line[20]));
                    food.setPolysaturatedFat(Float.parseFloat(line[21]));
                    food.setSaturatedFat(Float.parseFloat(line[22]));
                    food.setTotalLipid(Float.parseFloat(line[23]));
                    food.setCalcium(Integer.parseInt(line[24]));
                    food.setCopper(Float.parseFloat(line[25]));
                    food.setIron(Float.parseFloat(line[26]));
                    food.setMagnesium(Integer.parseInt(line[27]));
                    food.setPhosphorus(Integer.parseInt(line[28]));
                    food.setPotassium(Integer.parseInt(line[29]));
                    food.setSodium(Integer.parseInt(line[30]));
                    food.setZinc(Float.parseFloat(line[31]));
                    food.setVitaminARae(Integer.parseInt(line[32]));
                    food.setVitaminB12(Float.parseFloat(line[33]));
                    food.setVitaminB6(Float.parseFloat(line[34]));
                    food.setVitaminC(Float.parseFloat(line[35]));
                    food.setVitaminE(Float.parseFloat(line[36]));
                    food.setVitaminK(Float.parseFloat(line[37]));
                    foods.add(food);
                }

                foodRepository.saveAll(foods);

            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Database already loaded");
        }
    }
}
