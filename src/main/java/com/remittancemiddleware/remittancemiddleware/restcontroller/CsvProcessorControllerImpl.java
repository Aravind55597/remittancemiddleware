package com.remittancemiddleware.remittancemiddleware.restcontroller;


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
        public List<Map<String,String>> processCsv(@RequestParam("csvFile") MultipartFile csvFile) throws Exception {

            try {
                return csvProcessorServiceImpl.processCsv(csvFile);
            } catch (Exception e) {
                throw e;
            }

        };

    @PostMapping("/csvProcessor/processCsvHeaders")
    public List<String> processCsvHeaders(@RequestParam("csvFile") MultipartFile csvFile) throws Exception {

        try {
            return csvProcessorServiceImpl.processCsvHeaders(csvFile);
        } catch (Exception e) {
            throw e;
        }

    };


}
