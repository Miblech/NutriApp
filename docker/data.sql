CREATE DATABASE IF NOT EXISTS fooddb;

USE fooddb;

CREATE TABLE IF NOT EXISTS users (

    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    dob DATE NOT NULL,
    gender INT NOT NULL,
    height FLOAT NOT NULL,
    weight FLOAT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS foods (

    id INT NOT NULL AUTO_INCREMENT,
    category VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    nutrient_data_bank_number INT NOT NULL,
    alpha_carotene INT NOT NULL,
    beta_carotene INT NOT NULL,
    beta_cryptoxanthin INT NOT NULL,
    carbohydrate FLOAT NOT NULL,
    cholesterol INT NOT NULL,
    choline FLOAT NOT NULL,
    fiber FLOAT NOT NULL,
    lutein_zeaxanthin INT NOT NULL,
    lycopene INT NOT NULL,
    niacin FLOAT NOT NULL,
    protein FLOAT NOT NULL,
    retinol INT NOT NULL,
    riboflavin FLOAT NOT NULL,
    selenium FLOAT NOT NULL,
    sugar_total FLOAT NOT NULL,
    thiamin FLOAT NOT NULL,
    water FLOAT NOT NULL,
    monosaturated_fat FLOAT NOT NULL,
    polysaturated_fat FLOAT NOT NULL,
    saturated_fat FLOAT NOT NULL,
    total_lipid FLOAT NOT NULL,
    calcium INT NOT NULL,
    copper FLOAT NOT NULL,
    iron FLOAT NOT NULL,
    magnesium INT NOT NULL,
    phosphorus INT NOT NULL,
    potassium INT NOT NULL,
    sodium INT NOT NULL,
    zinc FLOAT NOT NULL,
    vitamin_a_rae INT NOT NULL,
    vitamin_b12 FLOAT NOT NULL,
    vitamin_b6 FLOAT NOT NULL,
    vitamin_c FLOAT NOT NULL,
    vitamin_e FLOAT NOT NULL,
    vitamin_k FLOAT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS daily_log (

    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    date DATE NOT NULL,
    timestamp DATETIME NOT NULL,
    meal_type VARCHAR(255) NOT NULL,
    food_item_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (food_item_id) REFERENCES foods(id)
);

CREATE TABLE IF NOT EXISTS meal_items (

    id INT NOT NULL AUTO_INCREMENT,
    log_id INT NOT NULL,
    food_item_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (log_id) REFERENCES daily_log(id),
    FOREIGN KEY (food_item_id) REFERENCES foods(id)
);


