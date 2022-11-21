package org.demo.constants;
import org.demo.enums.ConfigProperties;
import org.demo.utils.PropertyUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * <p>Framework Constants holds all the constant values used within the framework. If some value needs to be changed
 * or modified often, then it should be stored in the property files
 * </p>
 *
 * Nov 2022
 * @author Pon Vignesh
 * @version 1.0<br>
 * @since 1.0
 * @see org.demo.utils.PropertyUtils
 */
public final class FrameWorkConstants {

    private static final Date d = new Date();
    private static final String TODAYDATETIME = new SimpleDateFormat("MMM-dd-yyyy_HH_mm_ss_SSS").format(d);
    private static final String MAINRESOURCEROOTPATH = System.getProperty("user.dir") + "/src/main/resources";
    private static final String TESTRESOURCEROOTPATH = System.getProperty("user.dir") + "/src/test/resources";

    private static final String CHROMEDRIVERPATH = TESTRESOURCEROOTPATH + "/Drivers/chromedriver.exe";
    private static final int EXPLICITWAIT = 30;

    private static final String REPORTFOLDERPATH = System.getProperty("user.dir") + "/src/test/reports/";
    private static final String TESTCASEJSONPATH = System.getProperty("user.dir") + "/src/main/resources/Configuration/RunList.json";
    private static final String TESTDATAEXCELPATH = TESTRESOURCEROOTPATH + "/DataFiles/Excel/TestData.xlsx";
    private static final String SELECTQUERYJSONFILEPATH = System.getProperty("user.dir") + "/src/main/resources/Configuration/SqlQueries.json";
    private static final String CONFIGFILEPATH = TESTRESOURCEROOTPATH + "/Configuration/config.properties";
    private static final String DBCONFIGFILEPATH = MAINRESOURCEROOTPATH + "/Configuration/DataBaseConfig.properties";
    private static final String RUNMANAGER = "RunManager";
    private static final String TESTCASELIST = "testCaseLists";
    private static String TESTDATAJSONFILEPATH = TESTRESOURCEROOTPATH + "/DataFiles/Json/OutPutJson";
    private static String reportFilepath = "";



    private static String reportClassName = "";
    private static String ENVIRONMENT = System.getenv("Environment");
    private static boolean flag= false;

    /**
     * Private constructor to avoid external instantiation
     */
    private FrameWorkConstants() {
    }

    public static String getRunmanager() {
        return RUNMANAGER;
    }

    public static String getTestcaselist() {
        return TESTCASELIST;
    }

    public static String getEnvironment() {
        return ENVIRONMENT;
    }



    public static void setEnvironment(String Environment) {
        if (Objects.isNull(ENVIRONMENT)) {
            FrameWorkConstants.ENVIRONMENT = Environment;
        }


    }

    public static void setReportClassName(String reportClassName) {
        FrameWorkConstants.reportClassName = reportClassName;
    }

    public static String getReportFilepath()  {
        if (FrameWorkConstants.reportFilepath.isEmpty()) {
            FrameWorkConstants.reportFilepath = createReportPath();
        }
        return FrameWorkConstants.reportFilepath;
    }

    private static String createReportPath() {
        if (PropertyUtils.getValue(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")) {
            return REPORTFOLDERPATH + getTodayDateTime() + "/" +FrameWorkConstants.reportClassName+ "_Automation Report.html";
        } else {
            return REPORTFOLDERPATH + "/" +FrameWorkConstants.reportClassName+ "_Automation Report.html";
        }
    }

    public static String getTodayDateTime() {
        return TODAYDATETIME;
    }


    public static String getTestDataJsonFilePath() {
        return TESTDATAJSONFILEPATH;
    }

    public static void setTestDataJsonFilePath(String environmentName) {
        if(!flag) {
            if (Objects.isNull(environmentName))
                TESTDATAJSONFILEPATH = TESTDATAJSONFILEPATH + "/" + environmentName.toUpperCase() + "_TestData.json";
            else
                TESTDATAJSONFILEPATH = TESTDATAJSONFILEPATH + "/" + getEnvironment().toUpperCase() + "_TestData.json";
            flag =true;
        }

    }

    public static String getTestCaseJsonPath() {
        return TESTCASEJSONPATH;
    }

    public static String getTestCaseExcelPath() {
        return TESTDATAEXCELPATH;
    }

    public static String getSqlQueryjsonfilepath() {
        return SELECTQUERYJSONFILEPATH;
    }

    public static String getDbconfigfilepath() {
        return DBCONFIGFILEPATH;
    }


    public static String getConfigfilepath() {
        return CONFIGFILEPATH;
    }

    public static int getExplicitwait() {
        return EXPLICITWAIT;
    }




}
