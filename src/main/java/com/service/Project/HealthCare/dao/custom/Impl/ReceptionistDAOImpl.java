package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.ReceptionistDAO;
import com.service.Project.HealthCare.entity.Receptionist;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ReceptionistDAOImpl implements ReceptionistDAO {
    private final FactoryConfiguration config=FactoryConfiguration.getInstance();
    @Override
    public Receptionist findByuserNAme(String username) {
        Session session=config.getSession();
        Query<Receptionist> query = session.createQuery("FROM Receptionist WHERE name = :username", Receptionist.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }
}
