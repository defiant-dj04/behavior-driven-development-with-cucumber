package com.actitime.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    // Page objects
    @FindBy(id = "username")
    private WebElement usernameTF;

    @FindBy(name = "pwd")
    private WebElement passwordTF;

    @FindBy(xpath = "//div[text()='Login ']")
    private WebElement loginBtn;

    // Actions
    public void enterUsername(String username) {
        usernameTF.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordTF.sendKeys(password);
    }

    public void clickOnLoginBtn() {
        loginBtn.click();
    }
}
