package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import com.remittancemiddleware.remittancemiddleware.entity.RemittanceCompany;
import com.remittancemiddleware.remittancemiddleware.service.RemittanceCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RemittanceCompanyControllerImpl implements RemittanceCompanyController {
    private final RemittanceCompanyService remittanceCompanyService;

    @Autowired
    public RemittanceCompanyControllerImpl(RemittanceCompanyService theRemittanceCompanyService) {
        this.remittanceCompanyService = theRemittanceCompanyService;
    }

    @GetMapping("/remittanceCompany/cId")
    public CustomResponse findRemittanceCompanyById(@RequestParam int cId) {
        List<RemittanceCompany> rc = remittanceCompanyService.findRemittanceCompanyById(cId);
        CustomResponse result = new CustomResponse(rc);
        return result;
    }

}
