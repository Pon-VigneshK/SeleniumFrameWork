package org.demo.tests.google;

import org.demo.Base.BaseSetup;
import org.demo.driver.DriverManager;
import org.demo.enums.ConfigProperties;
import org.demo.utils.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public final class GoogleTest1 extends BaseSetup {
    private GoogleTest1(){}

    @Test
    public void googleTest() throws Exception {
        DriverManager.getThreaddriver().get(PropertyUtils.getValue(ConfigProperties.AMAZONURL));
        DriverManager.getThreaddriver().findElement(By.name("q")).sendKeys("Jeeva Anand", Keys.ENTER);
    }


}
