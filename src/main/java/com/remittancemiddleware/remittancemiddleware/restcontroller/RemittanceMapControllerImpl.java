package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import com.remittancemiddleware.remittancemiddleware.service.RemittanceMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class RemittanceMapControllerImpl implements RemittanceMapController {

    private final RemittanceMapService remittanceMapService;

    @Autowired
    public RemittanceMapControllerImpl(RemittanceMapService theRemittanceMapServiceImpl) {
        this.remittanceMapService = theRemittanceMapServiceImpl;
    }

    @GetMapping("/remittancemap/{userId}/{destCountry}")
    public CustomResponse getMappingByCountry(@PathVariable(value = "userId") int userId, @PathVariable(value = "destCountry") String destCountry) {
        Map<String, String> remittanceHashMap = remittanceMapService.findMapByCountry(userId, destCountry);
        CustomResponse result = new CustomResponse<>();
        if (remittanceHashMap.isEmpty()) {
            List<String> requiredFields = remittanceMapService.getRequiredFields(destCountry);
            result = new CustomResponse(requiredFields);
        } else {
            result = new CustomResponse(remittanceHashMap);
        }
        return result;
    }

    @PostMapping(value = "/createremittancemap/{userId}/{destCountry}")
    public CustomResponse createRemittanceMap(@PathVariable(value = "userId") int userId, @PathVariable(value = "destCountry") String destCountry, @RequestBody Map<String, String> mappingDetails) {
        CustomResponse result = new CustomResponse(remittanceMapService.save(userId, destCountry, mappingDetails));

        return result;
    }

    @PutMapping(value = "/updateremittancemap/{userId}/{destCountry}")
    public CustomResponse updateRemittanceMap(@PathVariable(value = "userId") int userId, @PathVariable(value = "destCountry") String destCountry, @RequestBody Map<String, String> mappingDetails) {
        CustomResponse result = new CustomResponse(remittanceMapService.update(userId, destCountry, mappingDetails));

        return result;
    }

}
