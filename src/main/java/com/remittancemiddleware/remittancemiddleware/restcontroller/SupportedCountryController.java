package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.entity.SupportedCountry;
import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;

import java.util.List;

public interface SupportedCountryController {
    CustomResponse findAll();
}
