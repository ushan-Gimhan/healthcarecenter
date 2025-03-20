package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.ReceptionistDAO;
import com.service.Project.HealthCare.entity.Admin;
import com.service.Project.HealthCare.entity.Receptionist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.awt.geom.RectangularShape;
import java.util.List;

public class ReceptionistDAOImpl implements ReceptionistDAO {
    private final FactoryConfiguration config=FactoryConfiguration.getInstance();
    @Override
    public Receptionist findByuserNAme(String username) {
        Session session=config.getSession();
        Query<Receptionist> query = session.createQuery("FROM Receptionist WHERE name = :username", Receptionist.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public List<Receptionist> findAll() {
        return List.of();
    }

    @Override
    public Receptionist find(String id) {
        return null;
    }

    @Override
    public boolean save(Receptionist entity) {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction tx = session.beginTransaction();
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
    public boolean update(Receptionist entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        if(entity!=null){
            session.update(entity);
            tx.commit();
            return true;
        }
        else {
            tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        if(id!=null){
            session.remove(session.get(Receptionist.class, id));
            tx.commit();
            return true;
        }else {
            tx.rollback();
            return false;
        }
    }

    @Override
    public String genarateId() {
        return "";
    }
}
