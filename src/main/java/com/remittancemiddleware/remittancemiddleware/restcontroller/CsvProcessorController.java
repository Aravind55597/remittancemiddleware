package com.remittancemiddleware.remittancemiddleware.restcontroller;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CsvProcessorController {
    List<Map<String,String>> processCsv(MultipartFile csvFile) throws Exception;
    List<String> processCsvHeaders(MultipartFile csvFile) throws Exception;
}
