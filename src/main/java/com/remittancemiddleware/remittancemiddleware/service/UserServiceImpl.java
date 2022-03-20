package com.remittancemiddleware.remittancemiddleware.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remittancemiddleware.remittancemiddleware.dao.CompanyDAO;
import com.remittancemiddleware.remittancemiddleware.dao.UserDAO;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.User;
import com.remittancemiddleware.remittancemiddleware.customexception.CustomNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private CompanyDAO companyDAO;

    @Autowired
    public UserServiceImpl(UserDAO theUserDAO, CompanyDAO theCompanyDAO) {
        this.userDAO = theUserDAO;
        this.companyDAO = theCompanyDAO;
    }

    @Override
    public User findById(int userId) {
        Optional<User> result = userDAO.findById(userId);
        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new CustomNotFoundException("Did not find user id - " + userId);
        }

        return theUser;
    }

    @Override
    @Transactional
    public User save(Map<String,String> creationDetails) {
        int companyId = Integer.parseInt(creationDetails.get("companyId"));
        Optional<Company> companyResult = companyDAO.findById(companyId);
        Company theCompany = null;
        if (companyResult.isPresent()) {
            theCompany = companyResult.get();
        } else {
            throw new CustomNotFoundException("Did not find company id - " + companyId);
        }

        String email = creationDetails.get("email");
        List<User> userResult = userDAO.findByEmail(email);
        User theUser = null;
        if (!userResult.isEmpty()) {
            throw new CustomNotFoundException("User creation failed, email already exists");
        }

        creationDetails.remove("companyId");
        ObjectMapper objectMapper = new ObjectMapper();
        theUser = objectMapper.convertValue(creationDetails, User.class);

        userDAO.save(theUser);
        theCompany.addUser(theUser);
        theUser.setCompany(theCompany);
        companyDAO.save(theCompany);

        return theUser;
    }

    @Override
    public User login(Map<String,String> loginDetails) {

        String email = loginDetails.get("email");
        String password = loginDetails.get("password");

        List<User> result = userDAO.findByEmailAndPassword(email, password);

        User theUser = null;
        if (!result.isEmpty()) {
            theUser = result.get(0);

        } else {
            // we didn't find the user
            throw new CustomNotFoundException("Could not find user, credentials invalid");
        }


        return theUser;
    }
}
