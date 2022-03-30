package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import com.remittancemiddleware.remittancemiddleware.service.RemittanceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RemittanceCompanyControllerImpl implements RemittanceCompanyController{
    private final RemittanceCompanyService remittanceCompanyService;

    @Autowired
    public RemittanceCompanyControllerImpl(RemittanceCompanyService theRemittanceCompanyService) {
        this.remittanceCompanyService = theRemittanceCompanyService;
    }

    @GetMapping("/remittanceCompany")
    public CustomResponse findAll() {
        List<RemittanceCompany> remittanceCompany = remittanceCompanyService.findAll();
        CustomResponse result = new CustomResponse (remittanceCompany);
        return result;
    };
}
