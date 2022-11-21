package org.demo.enums;

/**
 * Enums to restrict the users to choose an appropriate waiting strategy before operating an element.
 * 
 * 
 * Nov, 2022
 * @author Pon Vignesh
 * @version 1.0
 * @since 1.0<br>
 * @see org.demo.factories.ExplicitWaitFactory
 * @see org.demo.pages.basepage.BasePageActions
 */
public enum WaitStrategy {

    CLICKABLE,
    PRESENCE,
    VISIBLE,
    NONE
}
