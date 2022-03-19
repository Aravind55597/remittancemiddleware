package com.remittancemiddleware.remittancemiddleware.service;

import com.remittancemiddleware.remittancemiddleware.dao.RemittanceTransactionDAO;
import com.remittancemiddleware.remittancemiddleware.dao.UserDAO;
import com.remittancemiddleware.remittancemiddleware.entity.Company;
import com.remittancemiddleware.remittancemiddleware.entity.User;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import com.remittancemiddleware.remittancemiddleware.enums.TransactionStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RemittanceTransactionServiceImpl implements RemittanceTransactionService{
    private RemittanceTransactionDAO remittanceTransactionDAO;
    private UserDAO userDAO;

    public RemittanceTransactionServiceImpl (RemittanceTransactionDAO theRemittanceTransactionDAO, UserDAO theUserDAO){
        this.remittanceTransactionDAO = theRemittanceTransactionDAO;
        this.userDAO = theUserDAO;
    }

    @Override
    public List<RemittanceTransaction> findByTransactionStatusAndCompanyId(TransactionStatus status, int userId) {

        Optional<User> result = userDAO.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the user
            throw new RuntimeException("Did not find user id - " + userId);
        }
        Company company = theUser.getCompany();
        int companyId = company.getId();

        return remittanceTransactionDAO.findByTransactionStatusAndCompanyId(status, companyId);
    }

    @Override
    public User findById(int userId) {
        Optional<User> result = userDAO.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find user id - " + userId);
        }

        return theUser;
    }

    @Override
    public List<RemittanceTransaction> findByCompanyId(int userId) {
        Optional<User> result = userDAO.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the user
            throw new RuntimeException("Did not find user id - " + userId);
        }
        Company company = theUser.getCompany();
        int companyId = company.getId();

        return remittanceTransactionDAO.findByCompanyId(companyId);
    }
}
