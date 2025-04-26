package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.SessionBO;
import com.service.Project.HealthCare.dao.DAOFactory;
import com.service.Project.HealthCare.dao.custom.Impl.SessionDAOImpl;
import com.service.Project.HealthCare.dao.custom.SessionDAO;
import com.service.Project.HealthCare.dto.PatientDTO;
import com.service.Project.HealthCare.dto.SessioonDTO;
import com.service.Project.HealthCare.entity.Patient;
import com.service.Project.HealthCare.entity.TherapySession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SessionBOImpl implements SessionBO {
    SessionDAO sessionDAO;

    {
        try {
            sessionDAO = (SessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.session);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SessioonDTO> findAll() {
        List<TherapySession> all = sessionDAO.findAll();
        List<SessioonDTO> sessions = new ArrayList<SessioonDTO>();
        for(TherapySession session : all) {
            SessioonDTO sessionDTO = new SessioonDTO(session.getSId(),session.getSessionCount(), session.getTime(), session.getDate(),session.getStatus(),session.getPatient(),session.getTheropist());
            sessions.add(sessionDTO);
        }
        return sessions;
    }

    @Override
    public SessioonDTO find(String id) {
        return null;
    }

    @Override
    public boolean save(SessioonDTO patient) {
        return sessionDAO.save(new TherapySession(patient.getSId(),patient.getSessionCount(),patient.getTime(),patient.getDate(), patient.getStatus(),patient.getPatient(),patient.getId()));
    }

    @Override
    public boolean update(SessioonDTO patient) {
        return sessionDAO.update(new TherapySession(patient.getSId(),patient.getSessionCount(),patient.getTime(),patient.getDate(), patient.getStatus(),patient.getPatient(),patient.getId()));
    }

    @Override
    public boolean delete(String id) {
        return sessionDAO.delete(id);
    }

    @Override
    public String generateId() {
        return sessionDAO.generateId();
    }
}
