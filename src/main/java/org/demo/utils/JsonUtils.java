package org.demo.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.constants.FrameWorkConstants;
import org.demo.exceptions.JsonExceptions;
import org.demo.exceptions.SQLConnectionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static org.demo.utils.DataBaseConnection.getMyConn;

/**
 * Json-Utility class to read or write json.
 *
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.listeners.MethodInterceptor
 * @see DataProviderUtils
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public final class JsonUtils {

	/**
	 * Private constructor to avoid external instantiation
	 */
    private JsonUtils(){}
    
	private static List<Map<String, Object>> finaltestcaselist = new ArrayList();
    
	private static HashMap<String, Object> queriesList= new HashMap();
    private static FileInputStream fileInputStream;
    private static LinkedHashMap<String , LinkedHashMap<String, ArrayList<HashMap<String, Object>>>> testDataHashMap= new LinkedHashMap();
    private static String envName;
    private static List<Map<String, Object>> finalDatalist= new ArrayList<>();
    private static LinkedHashMap<String, ArrayList<HashMap<String, Object>>> finalMap = new LinkedHashMap();

    //test case runner list map
    private static LinkedHashMap<String, ArrayList<HashMap<String, Object>>> finalTestList= new LinkedHashMap();
    private static LinkedHashMap<String , LinkedHashMap<String, ArrayList<HashMap<String, Object>>>> testRunnerHashMap= new LinkedHashMap();


    public static LinkedHashMap<String , LinkedHashMap<String, ArrayList<HashMap<String, Object>>>> getTestDataHashMap() {
        return testDataHashMap;
    }

    
//    public static List<Map<String, Object>> getTestDetails(String highLevelKeyName, String nestedKeyName){
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            fileInputStream= new FileInputStream(FrameWorkConstants.getTestCaseJsonPath());
//            HashMap<String, HashMap> jsontestcasemap = objectMapper.readValue(fileInputStream, HashMap.class);
//            
//			ArrayList<Map<String, Object>> jsontestcaselist = (ArrayList) jsontestcasemap.get(highLevelKeyName).get(nestedKeyName);
//
//            for (Map<String, Object> finaltestcasemap : jsontestcaselist) {
//
//                finaltestcaselist.add(finaltestcasemap);
//            }
//
//            System.out.println(finaltestcaselist);
//        }
//        catch (FileNotFoundException e){
//            throw new JsonExceptions("Test case run list json file not found in the given path, please check your path: "+FrameWorkConstants.getTestCaseJsonPath());
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//
//            if(Objects.nonNull(fileInputStream)){
//                try {
//                    fileInputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return finaltestcaselist;
//    }

    /**
	 * Encapsulates all the value from the mentioned json and store it in
	 * List holding HashMaps. 
	 *
     * Nov , 2022
     * @author Pon Vignesh
	 * @param highLevelKeyName used to get the data from test runner json
	 * @param nestedKeyName used to get the data from test runner json
	 * @return List where each index holds a map and Each map holds the details about the test
	 */
    public static List<Map<String, Object>> getTestDetails(String highLevelKeyName, String nestedKeyName){
        List<Map<String, Object>> lst=new ArrayList<>();
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            fileInputStream= new FileInputStream(FrameWorkConstants.getTestCaseJsonPath());
            HashMap<String, HashMap> jsontestcasemap = objectMapper.readValue(fileInputStream, HashMap.class);
            HashMap<String, ArrayList<Map<String, Object >>> jsontestcaselist = (HashMap<String, ArrayList<Map<String, Object>>>) jsontestcasemap.get(highLevelKeyName); //.get(nestedKeyName);

            for (Map.Entry<String, ArrayList<Map<String,Object>>> mapval: jsontestcaselist.entrySet()){

                for (Map<String, Object> val: mapval.getValue()){
                    lst.add(val);
                }

            }
        }
        catch (FileNotFoundException e){
            throw new JsonExceptions("Test case run list json file not found in the given path, please check your path: "+FrameWorkConstants.getTestCaseJsonPath());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {

            if(Objects.nonNull(fileInputStream)){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return lst;
    }

    /**
	 * Encapsulates all the value from the mentioned json and store it in
	 * List holding HashMaps. 
	 *
     * Nov , 2022
     * @author Pon Vignesh
	 * @return List where each index holds a map and Each map holds the details about the test
	 */
    public static List<Map<String, Object>> getTestDataDetails(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            fileInputStream= new FileInputStream(FrameWorkConstants.getTestDataJsonFilePath());
            HashMap<String, HashMap> jsonTestDataMap = objectMapper.readValue(fileInputStream, HashMap.class);
            LinkedHashMap<String ,Object> jsonTestDataLinkedMap = (LinkedHashMap) jsonTestDataMap.get(FrameWorkConstants.getEnvironment());

            for (Map.Entry<String, Object> finaltestcasemap : jsonTestDataLinkedMap.entrySet()) {
                finalDatalist.addAll( (ArrayList) finaltestcasemap.getValue());
            }

        }
        catch (FileNotFoundException e){
            throw new JsonExceptions("Test data json file not found in the given path, please check your path: "+FrameWorkConstants.getTestDataJsonFilePath());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {

            if(Objects.nonNull(fileInputStream)){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return finalDatalist;
    }

    /**
	 * Encapsulates all the value from the mentioned json and store it in
	 * HashMap holding Objects. 
	 *
     * Nov , 2022
     * @author Pon Vignesh
	 * @param queryType is used to get the current query type from the user parameter
	 * @return List where each index holds a map and Each map holds the details about the test
	 */
    public static HashMap<String, Object> getQueryDetails(String queryType){

        try {
            String keyname = queryType.toLowerCase()+"queries";
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            fileInputStream= new FileInputStream(FrameWorkConstants.getSqlQueryjsonfilepath());
            HashMap<String, Object> jsontestcasemap = objectMapper.readValue(fileInputStream, HashMap.class);
            queriesList = (HashMap<String, Object>) jsontestcasemap.get(keyname);
        }
        catch (FileNotFoundException e){
            throw new JsonExceptions("SQL Query list json file not found in the given path, please check your path: "+FrameWorkConstants.getSqlQueryjsonfilepath());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {

            if(Objects.nonNull(fileInputStream)){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return queriesList;
    }

    /**
	 * Generate the test data for the current test suite from the database and write it in
	 * Test data json. 
	 *
     * Nov , 2022
     * @author Pon Vignesh
	 * @return List where each index holds a map and Each map holds the details about the test
	 */
    public static void generateTestDataJson() {
        try {

            Statement st =getMyConn().createStatement();
            HashMap<String, Object> queryDetails = getQueryDetails("select");


            for (Map.Entry<String, Object> mapdata : queryDetails.entrySet()) {

                ResultSet resultSet = st.executeQuery((String) mapdata.getValue());
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columns = resultSetMetaData.getColumnCount();
                HashMap<String, Object> rowDatas;
                ArrayList<HashMap<String, Object>> testDataList = new ArrayList();

                while (resultSet.next()) {
                    rowDatas = new HashMap(columns);
                    for (int i = 1; i <= columns; ++i) {
                        rowDatas.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
                        envName = resultSet.getNString("environment");
                    }
                    testDataList.add(rowDatas);
                }
                finalMap.put(mapdata.getKey(), testDataList);
            }
            testDataHashMap.put(envName,finalMap);
            FrameWorkConstants.setEnvironment(envName);

            ObjectMapper mapper = new ObjectMapper();
            FrameWorkConstants.setTestDataJsonFilePath(envName);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FrameWorkConstants.getTestDataJsonFilePath()), testDataHashMap);

        } catch (SQLException e) {
            throw new SQLConnectionException("Unable to execute the quires, please check the query configurations");
        } catch (JsonMappingException e) {
            throw new JsonExceptions("Json mapping exception");
        } catch (JsonGenerationException e) {
            throw new JsonExceptions("Json generation exception");
        } catch (FileNotFoundException e) {
            throw new JsonExceptions("Unable to write the test data json file, test data json file path/directory missing!");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (Objects.nonNull(getMyConn())){
                try {
                    getMyConn().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
	 * Generate the test runner data for the current test suite from the database and write it in
	 * Test runner list json. 
	 *
     * Nov , 2022
     * @author Pon Vignesh
	 * @return List where each index holds a map and Each map holds the details about the test
	 */
    public static void generateRunnerListJsonData() {
        try {

            Statement st =getMyConn().createStatement();
            HashMap<String, Object> queryDetails = getQueryDetails("runnerlist");
            for (Map.Entry<String, Object> mapdata : queryDetails.entrySet()) {

                ResultSet resultSet = st.executeQuery((String) mapdata.getValue());
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columns = resultSetMetaData.getColumnCount();
                HashMap<String, Object> rowDatas;
                ArrayList<HashMap<String, Object>> testDataList = new ArrayList();

                while (resultSet.next()) {
                    rowDatas = new HashMap(columns);
                    for (int i = 1; i <= columns; ++i) {
                        rowDatas.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));

                    }
                    testDataList.add(rowDatas);
                }
                finalTestList.put(mapdata.getKey(), testDataList);
            }
            testRunnerHashMap.put(FrameWorkConstants.getRunmanager() ,finalTestList);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FrameWorkConstants.getTestCaseJsonPath()), testRunnerHashMap);
        } catch (SQLException e) {
            throw new SQLConnectionException("Unable to execute the runner list quires, please check the query configurations");
        } catch (JsonMappingException e) {
            throw new JsonExceptions("Json mapping exception");
        } catch (JsonGenerationException e) {
            throw new JsonExceptions("Json generation exception");
        } catch (FileNotFoundException e) {
            throw new JsonExceptions("Unable to write the test runner list in json file, test runner list json file path/directory missing!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
