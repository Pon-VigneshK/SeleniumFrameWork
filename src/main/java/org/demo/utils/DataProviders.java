package org.demo.utils;


import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.constants.FrameWorkConstants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class DataProviders {

    @Test(dataProvider = "getJsonDataHashMap")
    void test1(Map map){
        System.out.println("Gokul");
    }

    // if simple test data we can use this method
    @DataProvider
    public Object[][] getExcelDataObjectArray() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FrameWorkConstants.getTestCaseExcelPath());
        //first need to mention Work book
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet xssfSheet = workbook.getSheet("Dev");
        // use for loop for read the cell and column
        int row = xssfSheet.getLastRowNum();
        int lastcellnumber= xssfSheet.getRow(row).getLastCellNum();
        Object[][] objarray= new Object[row][lastcellnumber];

        for (int i=1;i<=row;i++){
            for (int j=0; j<=lastcellnumber-1;j++){
                objarray[i-1][j]  = xssfSheet.getRow(i).getCell(j).getStringCellValue();
            }
        }
        workbook.close();
        fileInputStream.close();
        return objarray;

    }

    // for map use this method
    @DataProvider
    public Object[][] getExcelDataHashMap() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(FrameWorkConstants.getTestCaseExcelPath());
        //first need to mention Work book
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet xssfSheet = workbook.getSheet("Dev");
        // use for loop for read the cell and column
        int row = xssfSheet.getLastRowNum();
        int lastcellnumber= xssfSheet.getRow(row).getLastCellNum();
        // store it in map
        Object[] data = new Object[row];
        Map<String,String> datamap;

        for (int i=1;i<=row;i++){
            datamap =new HashMap<>();
            for (int j=0; j<=lastcellnumber-1;j++){

                String key = xssfSheet.getRow(0).getCell(j).getStringCellValue();
                String value= xssfSheet.getRow(i).getCell(j).getStringCellValue();
                datamap.put(key,value);
                data[i-1] = datamap;

            }
        }
        workbook.close();
        fileInputStream.close();
        return (Object[][]) data;

    }

    @DataProvider//parallel=true in suite xml --> data-provider-thread-count="4"
    public Object[][] getJsonDataHashMap() throws IOException, DatabindException {

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HashMap<String,HashMap> datamap = objectMapper.
                readValue(new File(FrameWorkConstants.getTestDataJsonFilePath()),
                        HashMap.class);
        int size = datamap.size();
        Object[][] dataToUse = new Object[size][1];
        int i = 0;
        for (Map.Entry<String, HashMap> entry : datamap.entrySet()) {
//            Map<String, HashMap> localMap = new HashMap<>();
//            localMap.put(entry.getKey(), entry.getValue());
//            System.out.println(entry.getValue());
            dataToUse[i++] = new Object[]{entry.getValue()};
        }
        return dataToUse;
    }


}
