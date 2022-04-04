package com.remittancemiddleware.remittancemiddleware.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CsvProcessorService {
    List<Map<String, String>> processCsv(MultipartFile csvFile) throws Exception;

    List<String> processCsvHeaders(MultipartFile csvFile) throws Exception;
}
