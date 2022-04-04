package com.remittancemiddleware.remittancemiddleware.restcontroller;


import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import com.remittancemiddleware.remittancemiddleware.service.CsvProcessorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CsvProcessorControllerImpl implements CsvProcessorController {
    private final CsvProcessorServiceImpl csvProcessorServiceImpl;

    @Autowired
    public CsvProcessorControllerImpl(CsvProcessorServiceImpl theCsvProcessorServiceImpl) {
        this.csvProcessorServiceImpl = theCsvProcessorServiceImpl;
    }

    @PostMapping("/csvProcessor/processCsv")
    public CustomResponse processCsv(@RequestParam("csvFile") MultipartFile csvFile) throws Exception {

        try {
            List<Map<String, String>> mapList = csvProcessorServiceImpl.processCsv(csvFile);
            CustomResponse result = new CustomResponse(mapList);
            return result;
        } catch (Exception e) {
            throw e;
        }


    }

    @PostMapping("/csvProcessor/processCsvHeaders")
    public CustomResponse processCsvHeaders(@RequestParam("csvFile") MultipartFile csvFile) throws Exception {

        try {
            List<String> listOfHeaders = csvProcessorServiceImpl.processCsvHeaders(csvFile);
            CustomResponse result = new CustomResponse(listOfHeaders);
            return result;
        } catch (Exception e) {
            throw e;
        }

    }


}
