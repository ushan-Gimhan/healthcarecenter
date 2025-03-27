package com.service.Project.HealthCare.bo.custom.Impl;

import com.service.Project.HealthCare.bo.custom.UserBO;
import com.service.Project.HealthCare.dao.DAOFactory;
import com.service.Project.HealthCare.dao.custom.UserDAO;
import com.service.Project.HealthCare.dto.UserDTO;
import com.service.Project.HealthCare.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Admin);

    public UserBOImpl() throws IOException {
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> all = userDAO.findAll();
        List<UserDTO> Alladmin = new ArrayList<UserDTO>();

        for (User admmin : all) {
            UserDTO dto = new UserDTO(admmin.getId(),admmin.getName(),admmin.getEmail(),admmin.getPhone(),admmin.getUserName(),admmin.getPassword(),admmin.getRole());
            Alladmin.add(dto);
        }
        return Alladmin;
    }

    @Override
    public UserDTO find(String id) {
        return null;
    }

    @Override
    public boolean save(UserDTO entity) {
        return userDAO.save(new User(entity.getId(),entity.getName(),entity.getPhone(),entity.getPassword(),entity.getEmail(),entity.getUserNAme(),entity.getRole()));
    }

    @Override
    public boolean update(UserDTO entity) throws IOException {
        return userDAO.update(new User(entity.getId(),entity.getName(),entity.getPhone(),entity.getPassword(),entity.getEmail(),entity.getUserNAme(),entity.getRole()));
    }

    @Override
    public boolean delete(String id) {
        return userDAO.delete(id);
    }

    @Override
    public String genareateID() {
        return userDAO.generateId();
    }
}
