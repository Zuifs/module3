package me.zuif.module3;

import me.zuif.module3.config.FlywayConfig;
import me.zuif.module3.config.HibernateSessionFactoryUtil;
import me.zuif.module3.controller.Controller;
import org.flywaydb.core.Flyway;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        init();
        controller.run();
    }

    private static void init() {
        Flyway flyway = FlywayConfig.getInstance();
        flyway.clean();
        flyway.migrate();
        HibernateSessionFactoryUtil.getSessionFactory();

    }
}
