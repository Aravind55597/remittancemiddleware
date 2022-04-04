package com.remittancemiddleware.remittancemiddleware.restcontroller;

import com.remittancemiddleware.remittancemiddleware.dataclass.custom.CustomResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CsvProcessorController {
    CustomResponse processCsv(MultipartFile csvFile) throws Exception;

    CustomResponse processCsvHeaders(MultipartFile csvFile) throws Exception;
}
