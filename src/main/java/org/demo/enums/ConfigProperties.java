package org.demo.enums;

/**
 * Enums to restrict the values used on Property files. Without using enums there can be create null pointer exceptions
 * because of typos.
 * <p>
 * Whenever a new value is added to property file, corresponding enum should be created here.
 * 
 * 
 * Nov , 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.utils.PropertyUtils
 */

public enum ConfigProperties {
    BROWSER,
    OVERRIDEREPORTS,
    PASSEDSTEPSCREENSHOTS,
    FAILEDSTEPSCREENSHOT,
    SKIPPEDSTEPSCREENSHOT,
    RETRYFAILEDTESTS,
    RUNMODE,
    SENDRESULTKIBANA,
    SELENIUMGRIDURL,
    ELASTICSEARCHURL,
    SELENOIDURL,
    HOSTNAME,
    PORT,
    SCHEMA,
    DBUSERNAME,
    DBPASSWORD,
    AMAZONURL
}
