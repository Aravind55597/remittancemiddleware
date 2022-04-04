package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import com.remittancemiddleware.remittancemiddleware.entity.SupportedCountry;
import com.remittancemiddleware.remittancemiddleware.service.SupportedCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SupportedCountryControllerImpl implements SupportedCountryController {
    private final SupportedCountryService supportedCountryService;

    @Autowired
    public SupportedCountryControllerImpl(SupportedCountryService theSupportedCountryService) {
        this.supportedCountryService = theSupportedCountryService;
    }

    @GetMapping("/countries")
    public CustomResponse findAll() {
        List<SupportedCountry> countries = supportedCountryService.findAll();
        CustomResponse result = new CustomResponse(countries);
        return result;
    }

}
