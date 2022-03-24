package com.remittancemiddleware.remittancemiddleware.service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class CsvProcessorServiceImpl implements CsvProcessorService {

    @Autowired
    public CsvProcessorServiceImpl () {

    }

    public List<Map<String,String>> processCsv(MultipartFile csvFile) throws Exception {


        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            csvFile.getInputStream()
                    )
            );
            CSVReader csvReader = new CSVReader(reader);

            List<String> columnNameArray = null;
            ArrayList<Map<String,String>>arrayOfCsvRowObjects = new ArrayList<Map<String,String>>();

            String[] currentRow;
            while((currentRow = csvReader.readNext()) != null) {

                // assign first excel row as an array containing a list of column names to create object later e.g. firstName, lastName
                if (columnNameArray == null) {
                    // add column names to array
                    columnNameArray = processCsvHeaders(csvFile);
                    continue;
                }

                // if first column at current row no longer has content --> no more valid data --> break loop
                boolean hasAnymoreContent = currentRow[0] != "";
                if (!hasAnymoreContent) {
                    break;
                }

                // create new hashmap using given column labels in columnNameArray
                int columnIdx = 0;
                Map<String,String> currentObject = new HashMap<String, String>();
                for (String columnValue : currentRow) {
                    String columnName = columnNameArray.get(columnIdx);
                    currentObject.put(columnName, columnValue);
                    columnIdx++;
                }
                arrayOfCsvRowObjects.add(currentObject);
            }
            reader.close();
            csvReader.close();

            // return array of objects
            return arrayOfCsvRowObjects;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public List<String> processCsvHeaders(MultipartFile csvFile) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            csvFile.getInputStream()
                    )
            );
            CSVReader csvReader = new CSVReader(reader);

            ArrayList<String> columnNameArray = new ArrayList<String>();
            String[] currentRow;
            if ((currentRow=csvReader.readNext()) != null) {
                for (String columnName: currentRow) {
                    String cleanedColumnName = columnName.replaceAll("[^a-zA-Z0-9]", ""); // clean column name of special characters
                    columnNameArray.add(cleanedColumnName);
                }
            }
            reader.close();
            csvReader.close();

            return columnNameArray;
        }
        catch (Exception e) {
            throw e;
        }
    }

}
