package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.TheropistBO;
import com.service.Project.HealthCare.dao.DAOFactory;
import com.service.Project.HealthCare.dao.custom.TheropistDAO;
import com.service.Project.HealthCare.dto.TheropistDTO;
import com.service.Project.HealthCare.entity.Theropist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TheropistBOImpl implements TheropistBO {
    TheropistDAO theropistDAO;

    {
        try {
            theropistDAO = (TheropistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Therapist);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TheropistDTO> findAll() {
        List<Theropist> all = theropistDAO.findAll();
        List<TheropistDTO> theropistDTOS = new ArrayList<TheropistDTO>();
        for (Theropist theropist : all) {
            TheropistDTO theropistDTO = new TheropistDTO(theropist.getId(),theropist.getName(),theropist.getGender(),theropist.getEmail(),theropist.getPhone(),theropist.getSpecialization(),theropist.getExperience(),theropist.getAge(),theropist.getStatus());
            theropistDTOS.add(theropistDTO);
        }
        return theropistDTOS;
    }

    @Override
    public TheropistDTO find(String id) {
        return null;
    }

    @Override
    public boolean save(TheropistDTO theropistDTO) {
        return theropistDAO.save(new Theropist(theropistDTO.getId(),theropistDTO.getName(),theropistDTO.getAge(),theropistDTO.getGender(),theropistDTO.getEmail(),theropistDTO.getPhone(),theropistDTO.getSpecialization(),theropistDTO.getExperience(),theropistDTO.getStaus()));
    }

    @Override
    public boolean update(TheropistDTO theropistDTO) {
        return theropistDAO.update(new Theropist(theropistDTO.getId(),theropistDTO.getName(),theropistDTO.getAge(),theropistDTO.getGender(),theropistDTO.getEmail(),theropistDTO.getPhone(),theropistDTO.getSpecialization(),theropistDTO.getExperience(),theropistDTO.getStaus()));
    }

    @Override
    public boolean delete(String id) {
        return theropistDAO.delete(id);
    }

    @Override
    public String generateId() {
        return theropistDAO.generateId();
    }

    @Override
    public List<String> getAllId() {
        return theropistDAO.getAllId();
    }

    @Override
    public Theropist getTheropistNameByID(String id) {
        return theropistDAO.getTheopistNameByid(id);
    }
}
