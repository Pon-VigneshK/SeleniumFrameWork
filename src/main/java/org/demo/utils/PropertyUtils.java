package org.demo.utils;

import org.demo.exceptions.JsonExceptions;
import org.demo.constants.FrameWorkConstants;
import org.demo.enums.ConfigProperties;
import org.demo.exceptions.UtilsExceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Read the property file and store it in a HashMap for faster processing.
 * Users can prefer to use json instead of property file based on their requirement.
 *
 *
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0<br>
 */

public final class PropertyUtils extends HashMap<String,String>  {

    @Override
    public String put(String key, String value) {
        return super.put(key.toLowerCase(), value);
    }

    @Override
    public String get(Object key) {
        return super.get(key.toString().toLowerCase());
    }


	/**
	 * Private constructor to avoid external instantiation
	 */
    private PropertyUtils(){
    }
    //var dec
    private static Properties properties = new Properties();

    public static Map<String,String> getConfigmap() {
        return CONFIGMAP;
    }

    private static final Map<String,String> CONFIGMAP = new PropertyUtils();

    /**
	 * Static block is used to read the property file from given directory
     * Nov , 2022
     * @author Pon Vignesh
	 */
    static {
        // no need of finally block
        try(FileInputStream file = new FileInputStream(FrameWorkConstants.getConfigfilepath())) {

            properties.load(file);
//            for(Map.Entry<Object,Object> entry: properties.entrySet()){
//                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
//            }
            // with lambda expression
            properties.entrySet().forEach(objectEntry -> getConfigmap().put(String.valueOf(objectEntry.getKey()), String.valueOf(objectEntry.getValue()).trim()));
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
	 * Receives the {@link org.demo.enums.ConfigProperties},converts to lowercase , return the corresponding value
	 * for the key from the HashMap
     * Nov , 2022
     * @author Pon Vignesh
	 * @param key To be fetched from property file
	 * @return corresponding value for the requested key if found else {@link UtilsExceptions}
	 */
    public static String getValue(ConfigProperties key) {
        try {
            if (Objects.isNull(getConfigmap().get(key.name().toLowerCase())) || Objects.isNull(key)) {

            }
            return getConfigmap().get(key.name().toLowerCase());
        } catch (Exception e) {
            throw  new UtilsExceptions("Property name " + key + " is not found. Please check the property file");
        }

    }



    // hashtable-- little slow, thread safe //HashMap- faster
    // converting a property to hashmap need some time
//    public static String getValue(String key) throws Exception {
//        if(Objects.isNull(properties.getProperty(key.toLowerCase())) || Objects.isNull(key)){
//            throw new Exception("Property name "+ key +"is not found. Please check the property file");
//        }
//        return properties.getProperty(key.toLowerCase());
//
//    }
}
