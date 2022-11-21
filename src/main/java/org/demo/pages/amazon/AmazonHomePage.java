package org.demo.pages.amazon;

import org.demo.enums.WaitStrategy;
import org.demo.pages.basepage.BasePageActions;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

/**
 * Page is used to do the web page actions based on the used requirements 
 *
 *@author Pon Vignesh
  * Nov , 2022
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.pages.basepage.BasePageActions
 */
public final class AmazonHomePage extends BasePageActions {

    private final By linkHamburger  = By.id("nav-hamburger-menu");

    public @NotNull AmazonHamburgerPage clickHamburger(){
        click(linkHamburger, WaitStrategy.CLICKABLE, "Ham burger link");
        return new AmazonHamburgerPage();
    }
}
