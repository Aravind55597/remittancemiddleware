package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import com.remittancemiddleware.remittancemiddleware.entity.Company;

import com.remittancemiddleware.remittancemiddleware.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyControllerImpl implements CompanyController {
    private final CompanyServiceImpl companyServiceImpl;

    @Autowired
    public CompanyControllerImpl(CompanyServiceImpl theCompanyServiceImpl) {
        this.companyServiceImpl = theCompanyServiceImpl;
    }
    @GetMapping("/company")
    public CustomResponse findAll() {

        return new CustomResponse(companyServiceImpl.findAll());
    };

    @GetMapping("/company/{companyId}")
    public CustomResponse findById(@PathVariable int companyId) {
        return new CustomResponse(companyServiceImpl.findById(companyId));
    };

}
