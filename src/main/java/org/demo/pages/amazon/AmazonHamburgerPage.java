package org.demo.pages.amazon;

import com.gcit.utils.DynamicXpathUtils;
import org.demo.enums.WaitStrategy;
import org.demo.pages.basepage.BasePageActions;
import org.openqa.selenium.By;

/**
 * Page is used to do the web page actions based on the used requirements 
 * 
 * @author Pon Vignesh
 * Nov, 2022
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.pages.basepage.BasePageActions
 */
public final class AmazonHamburgerPage extends BasePageActions {

    //dynamic xpath handling
    private String linkSubMenu = "//a[text()='%s']";
    private String linkFirstSubMenu = "//div[text()='%s']";

    public AmazonHamburgerPage clickOnFirstSubMenu(String menuText, String textVerify){
        String dynamicPath = DynamicXpathUtils.getDynamicXpath(linkFirstSubMenu , menuText);
        click(By.xpath(dynamicPath), WaitStrategy.CLICKABLE, menuText);
        if (menuText.contains(textVerify)){
            return this;
        }
        else
            return null;
    }

    public AmazonLaptopsPage clickOnSubMenu(String submenuText, String textVerify){
        String dynamicPath = DynamicXpathUtils.getDynamicXpath(linkSubMenu , submenuText);
        click(By.xpath(dynamicPath), WaitStrategy.CLICKABLE, submenuText);
        if (submenuText.contains(textVerify)){
            return new AmazonLaptopsPage();
        }
        else
            return null;
    }
}
