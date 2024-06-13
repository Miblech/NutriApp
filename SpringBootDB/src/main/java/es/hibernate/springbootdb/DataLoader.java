package es.hibernate.springbootdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CSVLoader csvLoader;

    @Override
    public void run(String... args) throws Exception {
        csvLoader.loadCSVData();
    }
}
