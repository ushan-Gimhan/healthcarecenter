package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.AdminDAO;
import com.service.Project.HealthCare.entity.Admin;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class AdminDAOImpl implements AdminDAO {
    private final FactoryConfiguration config=FactoryConfiguration.getInstance();
    @Override
    public Admin findByuserNAme(String username) {
        Session session=config.getSession();
        Query<Admin> query = session.createQuery("FROM Admin WHERE name = :username", Admin.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }
}
