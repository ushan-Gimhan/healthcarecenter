package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.AdminDAO;
import com.service.Project.HealthCare.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    private final FactoryConfiguration config=FactoryConfiguration.getInstance();
    @Override
    public Admin findByuserNAme(String username) {
        Session session=config.getSession();
        Query<Admin> query = session.createQuery("FROM Admin WHERE name = :username", Admin.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public List<Admin> findAll() {
        return List.of();
    }

    @Override
    public Admin find(String id) {
        return null;
    }

    @Override
    public boolean save(Admin entity) {
        Session session=config.getSession();
        Transaction tx=session.beginTransaction();
        if(entity!=null){
            session.persist(entity);
            tx.commit();
            return true;
        }else {
            tx.rollback();
            return false;
        }
    }

    @Override
    public boolean update(Admin entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx=session.beginTransaction();

        if(entity!=null){
            session.merge(entity);
            tx.commit();
            return true;
        }else {
            tx.rollback();
            return false;
        }


    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx=session.beginTransaction();

        if(id!=null){
            session.remove(session.get(Admin.class, id));
            tx.commit();
            return true;
        }else {
            tx.rollback();
            return false;
        }
    }
}
