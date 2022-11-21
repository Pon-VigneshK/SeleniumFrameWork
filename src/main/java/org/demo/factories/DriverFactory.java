package org.demo.factories;

import org.demo.constants.FrameWorkConstants;
import org.demo.driver.DriverManager;
import org.demo.enums.ConfigProperties;
import org.demo.exceptions.FrameWorkExceptions;
import org.demo.utils.PropertyUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.demo.driver.DriverManager;
import org.demo.enums.ConfigProperties;
import org.demo.utils.PropertyUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

/**
 * Driver factory class used to generate the driver instance based on the config properties (Remote / Local) 
 * @author Pon Vignesh
 * Nov , 2022
 * @version 1.0
 * @since 1.0
 * @see org.demo.driver.DriverManager
 */
public final class DriverFactory {
    // cap options
    //desiredCapabilities.setCapability("network", true); // To enable network logs
    //desiredCapabilities.setCapability("visual", true); // To enable step by step screenshot
    //desiredCapabilities.setCapability("video", true); // To enable video recording
    //desiredCapabilities.setCapability("console", true); // To capture console logs
    //options.setPageLoadStrategy(PageLoadStrategy.EAGER);
    /**
     * Private constructor to avoid external instantiation
     */
    private DriverFactory() {
    }
    // use one parameter to setup multiple browser support
	/**
	 * 
	 *  @author Pon Vignesh
     *  Nov , 2022
	 * @param browserName
	 * @return
	 * @throws MalformedURLException
	 * @return WebDriver instance 
	 */
    public static WebDriver getDriver(String browserName) throws MalformedURLException {

        String runMode = PropertyUtils.getValue(ConfigProperties.RUNMODE);
        WebDriver driver= null;
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (Objects.isNull(DriverManager.getThreaddriver())) {
            switch (browserName.toLowerCase()){

                case "edge":
                    final EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--disable-dev-shm-usage");
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--window-size","1920,1080");

                    if (runMode.equalsIgnoreCase("remote")) {

                        desiredCapabilities.setCapability("browserName", "MicrosoftEdge");
                        desiredCapabilities.setCapability("browserVersion", "101.0");
                        desiredCapabilities.setCapability("screenResolution", "1920x1080x24");
                        desiredCapabilities.setCapability("selenoid:options", Map.<String, Object>of("enableVNC", true, "enableVideo", true)); // If this cap isn't specified, it will just get any available one
                        desiredCapabilities.setCapability("videoName", "EdgeRecorded_"+FrameWorkConstants.getTodayDateTime()+".mp4");
                        desiredCapabilities.setCapability("build", "Automation Test");
                        desiredCapabilities.setCapability("name", "Regression Test");
                        driver = new RemoteWebDriver(new URL(PropertyUtils.getValue(ConfigProperties.SELENOIDURL)), desiredCapabilities);

                    } else {
                        WebDriverManager.edgedriver().clearDriverCache();
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver(edgeOptions);
                    }
                    break;

                case "chrome":
                    final ChromeOptions chromeOptionsoptions = new ChromeOptions();
                    chromeOptionsoptions.addArguments("--disable-dev-shm-usage");
                    chromeOptionsoptions.addArguments("--disable-gpu");
                    chromeOptionsoptions.addArguments("--window-size","1920,1080");
                    if (runMode.equalsIgnoreCase("remote")) {
                        desiredCapabilities.setCapability("browserName", "chrome");
                        desiredCapabilities.setCapability("browserVersion", "102.0");
                        desiredCapabilities.setCapability("screenResolution", "1920x1080x24");
                        desiredCapabilities.setCapability("selenoid:options", Map.<String, Object>of("enableVNC", true, "enableVideo", true)); // If this cap isn't specified, it will just get any available one
                        desiredCapabilities.setCapability("videoName", "ChromeRecorded_"+FrameWorkConstants.getTodayDateTime()+".mp4");
                        desiredCapabilities.setCapability("build", "Automation Test");
                        desiredCapabilities.setCapability("name", "Regression Test");
                        driver = new RemoteWebDriver(new URL(PropertyUtils.getValue(ConfigProperties.SELENOIDURL)), desiredCapabilities);
                        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));

                    } else {
                        WebDriverManager.chromedriver().clearDriverCache();
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver(chromeOptionsoptions);
                    }
                    break;

                case "firefox":
                    final FirefoxOptions firefoxOptions = new FirefoxOptions();
                    final FirefoxProfile profile = new FirefoxProfile();

                    firefoxOptions.addArguments("--disable-web-security");
                    firefoxOptions.addArguments("--allow-running-insecure-content");
                    firefoxOptions.addArguments("--no-sandbox");
                    firefoxOptions.addArguments("--disable-dev-shm-usage");
                    firefoxOptions.addArguments("--disable-gpu");
                    firefoxOptions.addArguments("--window-size","1920,1080");
                    profile.setAcceptUntrustedCertificates(true);
                    profile.setAssumeUntrustedCertificateIssuer(false);
                    profile.setPreference("pageLoadStrategy", "normal");
                    //firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
                    //
                    if (runMode.equalsIgnoreCase("remote")) {
                        desiredCapabilities.setCapability("browserName", "firefox");
                        desiredCapabilities.setCapability("browserVersion", "100.0");
                        desiredCapabilities.setCapability("videoName", "FireFoxRecorded_"+ FrameWorkConstants.getTodayDateTime()+".mp4");
                        desiredCapabilities.setCapability("screenResolution", "1920x1080x24");
                        desiredCapabilities.setCapability("selenoid:options", Map.<String, Object>of("enableVNC", true, "enableVideo", true)); // If this cap isn't specified, it will just get any available one
                        desiredCapabilities.setCapability("build", "Automation Test");
                        desiredCapabilities.setCapability("name", "Regression Test");
                        driver = new RemoteWebDriver(new URL(PropertyUtils.getValue(ConfigProperties.SELENOIDURL)), desiredCapabilities);
                        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));

                    } else {
                        WebDriverManager.firefoxdriver().clearDriverCache();
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver(firefoxOptions);
                    }
                    break;

                default:
                    throw new FrameWorkExceptions("Browser name mismatch with available browser options, please choose correct one! ");

            }
            driver.manage().window().setSize(new Dimension(1920, 1080));
            return driver;
        }
        else throw new FrameWorkExceptions("Browser session already initiated! ");
    }
}
