package org.demo.reports;

import org.demo.enums.LogType;
import org.demo.exceptions.FrameWorkExceptions;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;


/**
 * @author "Pon Vignesh"
 * SeleniumFramework
 * Nov , 2002
 * @version 1.0
 */

public final class FrameWorkLogger {

    private FrameWorkLogger(){}
    private static final Consumer<String> PASS = ExtentLogger::pass; // message need to pass
    private static final Consumer<String> FAIL = ExtentLogger::fail;
    private static final Consumer<String> SKIP = ExtentLogger::skip;
    private static final Consumer<String> INFO = ExtentLogger::info;
    private static final Consumer<String> CONSOLE = ($)-> System.out.println("INFO---->"+$);

    private static final Consumer<String> EXTENTANDCONSOLE = INFO.andThen(CONSOLE);
    private static final Map<LogType, Consumer<String>> LOGSMAP = new EnumMap(LogType.class);

    static {

        LOGSMAP.put(LogType.PASS, PASS);
        LOGSMAP.put(LogType.FAIL, FAIL);
        LOGSMAP.put(LogType.SKIP, SKIP);
        LOGSMAP.put(LogType.INFO, INFO);
        LOGSMAP.put(LogType.CONSOLE, CONSOLE);
        LOGSMAP.put(LogType.EXTENTANDCONSOLE, EXTENTANDCONSOLE);


    }

    public static void log(LogType status, String message){
        LOGSMAP.get(status).accept(message);
    }

    public static void log(LogType status, String message, boolean screenShotNeeded){
        switch (status){
            case PASSWITHSCREENSHOT:
                ExtentLogger.pass(message, screenShotNeeded);
                break;
            case FAILWITHSCREENSHOT:
                ExtentLogger.fail(message, screenShotNeeded);
                break;
            default:
                throw new FrameWorkExceptions("Please select a valid option !");

        }

    }

}
