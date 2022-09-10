package me.zuif.module3.config;

import org.flywaydb.core.Flyway;

public class FlywayConfig {
    private static final String URL = "jdbc:postgresql://ec2-52-207-15-147.compute-1.amazonaws.com:5432/dqsueodc8vrn4";
    private static final String USER = "paojnxmahyyjzn";
    private static final String PASSWORD = "22705ebb51cb71ce08e5fea0ec3e3b99436bf3b7ef5ff8e5dc994ac74e007a92";
    private static final String SCHEMA = "db";
    private static final String LOCATION = "db/migration";

    public static Flyway getInstance() {
        return Flyway.configure()
                .dataSource(URL, USER, PASSWORD)
                .baselineOnMigrate(true)
                .schemas(SCHEMA)
                .locations(LOCATION)
                .load();
    }
}
