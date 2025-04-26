package com.service.Project.HealthCare.dao.custom.Impl;

import com.service.Project.HealthCare.config.FactoryConfiguration;
import com.service.Project.HealthCare.dao.custom.PaymentDAO;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.Payement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    FactoryConfiguration config;

    {
        try {
            config = FactoryConfiguration.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Payement> findAll() {
        try (Session session = config.getSession()) {
            return session.createQuery("from Payement", Payement.class).list();
        }
    }

    @Override
    public Payement find(String id) {
        try (Session session = config.getSession()) {
            return session.get(Payement.class, id);
        }
    }

    @Override
    public boolean save(Payement entity) {
        Session session = config.getSession();

        Transaction tx = session.beginTransaction();

        try {
            session.persist(entity);
            tx.commit();
            return true;
        }catch (Exception e) {
            tx.rollback();
            return false;
        }finally {
            if(session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public boolean update(Payement entity) {
        Session session = config.getSession();
        Transaction tx = session.beginTransaction();

        try {
            session.merge(entity);
            tx.commit();
            return true;
        }catch (Exception e) {
            tx.rollback();
            return false;
        }finally {
            if(session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String id) {
        Transaction transaction = null;
        try (Session session = config.getSession()) {
            transaction = session.beginTransaction();
            Payement payement = session.get(Payement.class, id);
            if (payement != null) {
                session.delete(payement);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String generateId() {
        String newPaymentId = "P001"; // Default ID if no records exist

        try (Session session = config.getSession()) {
            // Get the last patient ID
            Query<String> query = session.createQuery("SELECT id FROM Payement  s ORDER BY s.id DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.uniqueResult();

            if (lastId != null && lastId.length() > 1) {
                // Extract the numeric part of the ID
                String numericPart = lastId.substring(1);
                try {
                    int lastNumericId = Integer.parseInt(numericPart);
                    newPaymentId = String.format("P%03d", lastNumericId + 1);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPaymentId;
    }
}
