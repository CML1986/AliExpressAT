package org.carlos.alitest.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Managed;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


@DefaultUrl("https://www.aliexpress.com/")
public class BasePage extends PageObject {


    @FindBy(id = "search-key")
    private WebElementFacade searchIcon;

    @FindBy(css = ".search-button")
    private WebElementFacade lookupButton;

    @FindBy(xpath = "/html/body/div[6]/div[2]/div/div/div/div")
    private WebElementFacade advertise;

    @FindBy(className = "next-dialog-close")
    private WebElementFacade xClose;

    @FindBy(css = ".product-pagination-wrap input")
    private WebElementFacade pageSearchInput;

    @FindBy(css = ".list-pagination")
    private WebElementFacade pageSelection;

    @FindBy(css = "input#fm-login-id")
    private WebElementFacade loginAccount;

    @FindBy(css = "input#fm-login-password")
    private WebElementFacade loginPassword;

    @FindBy(css = "div.glosearch-wrap div.page-content div.main-content div.right-menu:nth-child(2) div.product-container div.list-wrap.product-list ul.list-items li.list-item.packaging_sale:nth-child(2) > div.list.product-card")
    private WebElementFacade itemSelector;

    public void enter_keywords(String keyword) {
        searchIcon.type(keyword);
    }

    public void lookup_terms() {
        skipAdvertise();
        ifLoginRequired();
        lookupButton.click();
    }

    public void lookup_pageNumber(String pageN) {

        skipAdvertise();
        ifLoginRequired();
        new Actions(getDriver()).moveToElement(getDriver().findElement(By.cssSelector(".next-pagination-list > button:nth-of-type(" + pageN + ")"))).perform();
        try {
            Thread.sleep(100);
            //element.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lookup_resultNumber(String resultNumber) {

        skipAdvertise();

        getDriver().findElement(By.xpath("//li[" + resultNumber + "]//div[1]//div[1]//a[1]//img[1]")).click();

    }

    public void skipAdvertise() {
        if (advertise.isVisible()) {
            xClose.click();
        }

    }

    public void ifLoginRequired() {
        if (loginAccount.isVisible()) {
            System.out.println("login required to authemticate");
            loginAccount.clear();
            loginAccount.sendKeys("carlos.m.lopez@gmail.com");
            loginPassword.sendKeys("AutomationDummy1");
            getDriver().findElement(By.cssSelector(".password-login")).click();
        } else {
            System.out.println("no login required");
        }

    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.tagName("ol"));
        return definitionList.findElements(By.tagName("li")).stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }
}