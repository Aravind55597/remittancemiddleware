package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CsvProcessorController {
    CustomResponse processCsv(MultipartFile csvFile) throws Exception;
    CustomResponse processCsvHeaders(MultipartFile csvFile) throws Exception;
}
