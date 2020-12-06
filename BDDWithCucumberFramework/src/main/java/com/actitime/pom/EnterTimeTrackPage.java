package com.actitime.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EnterTimeTrackPage {
    // Page objects
    @FindBy(id = "logoutLink")
    private WebElement logoutLink;

    // Actions
    public void clickOnLogoutBtn() {
        logoutLink.click();
    }
}

