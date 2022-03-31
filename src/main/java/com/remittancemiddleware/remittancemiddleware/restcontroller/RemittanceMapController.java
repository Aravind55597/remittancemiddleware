package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;


public interface RemittanceMapController {
    CustomResponse getMappingByCountry(@PathVariable int userId, @PathVariable String destCountry);
//    CustomResponse createRemittanceMap(@RequestBody Map<String, String> mappingDetails);
}
