package org.demo.utils;


import org.demo.constants.FrameWorkConstants;
import org.demo.enums.ConfigProperties;
import org.demo.exceptions.JsonExceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static org.demo.utils.PropertyUtils.getConfigmap;
/**
 * Database connection class is used to establish the connection between local mysql server 
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0<br>
 */
public final class DataBaseConnection {
    private static Properties properties = new Properties();

    public static Connection getMyConn() {
        return myConn;
    }

    private static Connection myConn;
    /**
     * Static block is used to get the property utils from the property file for the dataBase configuration
     * Nov , 2022
     * @author Pon Vignesh
	 * @version 1.0
	 * @since 1.0<br> 
     */
    static {
        try(FileInputStream file = new FileInputStream(FrameWorkConstants.getDbconfigfilepath())) {
            properties.load(file);
            properties.entrySet().forEach(objectEntry -> getConfigmap().put(String.valueOf(objectEntry.getKey()), String.valueOf(objectEntry.getValue()).trim()));
        }
        catch (FileNotFoundException e){
            throw new JsonExceptions("Configuration json file not found in the given path, please check your path: "+FrameWorkConstants.getDbconfigfilepath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Static block is used to establish the MySql server connection  
     * Nov , 2022
     * @author Pon Vignesh
	 * @version 1.0
	 * @since 1.0<br> 
     */
    static {
        try {
            String hostName = PropertyUtils.getValue(ConfigProperties.HOSTNAME);
            String port = PropertyUtils.getValue(ConfigProperties.PORT);
            String schema = PropertyUtils.getValue(ConfigProperties.SCHEMA);
            String dbusername = PropertyUtils.getValue(ConfigProperties.DBUSERNAME);
            String password = PropertyUtils.getValue(ConfigProperties.DBPASSWORD);
            String url = "jdbc:mysql://" + hostName + ":" + port + "/" + schema + "?autoReconnect=true&useSSL=false";
            myConn = DriverManager.getConnection(url, dbusername, password);

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }


    }
    
    /**
	 * Private constructor to avoid external instantiation
	 */
    private DataBaseConnection() {
    }

}
