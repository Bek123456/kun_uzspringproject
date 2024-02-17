package org.example.springkunuz.service;


import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private InitService initService;
    @Autowired
    private DataSource dataSource;
    @Override
    public void run(String... args) throws Exception {
        //initService.initAdmin();4
        Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
    }

}
