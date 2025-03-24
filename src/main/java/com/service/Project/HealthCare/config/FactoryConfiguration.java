package com.service.Project.HealthCare.config;
import com.service.Project.HealthCare.entity.Admin;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Receptionist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration config;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        org.hibernate.cfg.Configuration config = new Configuration().configure();

        config.addAnnotatedClass(Admin.class);
        config.addAnnotatedClass(Receptionist.class);
        config.addAnnotatedClass(Patient.class);

        sessionFactory = config.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() {
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
