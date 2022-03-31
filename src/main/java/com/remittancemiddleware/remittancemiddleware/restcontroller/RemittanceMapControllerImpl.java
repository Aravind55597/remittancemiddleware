package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomNotFoundException;
import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.RemittanceMap;
import com.remittancemiddleware.remittancemiddleware.service.RemittanceMapServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class RemittanceMapControllerImpl implements RemittanceMapController {

    private final RemittanceMapServiceImpl remittanceMapServiceImpl;

    @Autowired
    public RemittanceMapControllerImpl(RemittanceMapServiceImpl theRemittanceMapServiceImpl) {
        this.remittanceMapServiceImpl = theRemittanceMapServiceImpl;
    }

    @GetMapping("/remittancemap/{userId}/{destCountry}")
    public CustomResponse getMappingByCountry(@PathVariable(value="userId") int userId, @PathVariable(value="destCountry") String destCountry) {
        RemittanceMap theRemittanceMap = remittanceMapServiceImpl.findMapByCountry(userId, destCountry);
        CustomResponse result = new CustomResponse<>();
        if (theRemittanceMap == null) {
            List<String> list = remittanceMapServiceImpl.getRequiredFields(destCountry);
            result = new CustomResponse(list);
        }
        else {
            result = new CustomResponse(theRemittanceMap);
        }
        return result;
    }

    @PostMapping(value="/remittancemap/{userId}/{destCountry}")
    public CustomResponse createRemittanceMap(@PathVariable(value="userId") int userId, @PathVariable(value="destCountry") String destCountry, @RequestBody Map<String,String> mappingDetails) {
        CustomResponse result = new CustomResponse(remittanceMapServiceImpl.save(userId, destCountry, mappingDetails));

        return result;
    }

}
