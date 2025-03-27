package com.service.Project.HealthCare.config;
import com.service.Project.HealthCare.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration config;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() throws IOException {
        Configuration config = new Configuration().configure();

        try{
            InputStream inputStream = FactoryConfiguration.class.getClassLoader().getResourceAsStream("hibernate.properties");
        }catch (Exception e){
            e.printStackTrace();
        }

        config.addAnnotatedClass(User.class);
        config.addAnnotatedClass(Patient.class);
        config.addAnnotatedClass(Theropist.class);
        config.addAnnotatedClass(TherapySession.class);
        config.addAnnotatedClass(Registration.class);
        config.addAnnotatedClass(Programs.class);
        config.addAnnotatedClass(Payement.class);

        sessionFactory = config.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() throws IOException {
        if (config == null) {
            config = new FactoryConfiguration();
            return config;
        }
        return config;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
