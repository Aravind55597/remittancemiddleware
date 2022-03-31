package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.customexception.CustomNotFoundException;
import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import com.remittancemiddleware.remittancemiddleware.entity.companyfieldmap.RemittanceMap;
import com.remittancemiddleware.remittancemiddleware.service.RemittanceMapServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            Map<String, Boolean> map = remittanceMapServiceImpl.getRequiredFields(destCountry);
            result = new CustomResponse(map);
        }
        else {
            result = new CustomResponse(theRemittanceMap);
        }
        return result;
    }

//    @PostMapping(value="/remittancemap")
//    public CustomResponse createRemittanceMap(@RequestBody Map<String,String> mappingDetails) {
//        CustomResponse result = new CustomResponse(remittanceMapServiceImpl.save(mappingDetails));
//
//        return result;
//    }

}
