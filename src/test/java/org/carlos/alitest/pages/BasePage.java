package org.carlos.alitest.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;


import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

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

    @FindBy(xpath = "/html//div[@id='root']/div[@class='glosearch-wrap']/div[@class='page-content']//div[@class='product-pagination-wrap']")
    private WebElement pageSelection;

    @FindBy(css = "input#fm-login-id")
    private WebElementFacade loginAccount;

    @FindBy(css = "input#fm-login-password")
    private WebElementFacade loginPassword;

    public void enter_keywords(String keyword) {
        searchIcon.type(keyword);
    }

    public void lookup_terms() {
        skipAdvertise();
        ifLoginRequired();
        scrollToElement(lookupButton);
        lookupButton.click();
    }

    public void lookup_pageNumber(String pageN) {
        try {
            skipAdvertise();
            ifLoginRequired();
            scrollToElement(getDriver().findElement(By.className("list-pagination")));
            // here I made a work around , I know this is not the best solution
            // but for some reason is not taking any Rel xpath , absolute or css selector provided , include finding a way to focus the main WebElement an interact with inside.
            // if provided matches there are 3 possible solutions : pass the variable PageN to input box ,
            // click with a next button and make the assertion to stop on number pageN ,
            // or just simply select the page number 2 in the button by passing PageN.

            //getDriver().findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div[2]/div/div[3]/div/div[1]/div/div/button[2]")).click();

            //so then I did:
            String providedURL = "https://es.aliexpress.com/af/iphone.html?trafficChannel=af&d=y&CatId=0&SearchText=iphone&ltype=affiliate&SortType=default&page=" + pageN;
            getDriver().navigate().to(providedURL);

            Thread.sleep(100);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lookup_resultNumber(String resultNumber) {

        skipAdvertise();
        WebElement itemCard = getDriver().findElement(By.xpath("/html//div[@id='root']/div[@class='glosearch-wrap']//ul[@class='list-items']/li[" + resultNumber + "]"));
        scrollToElement(itemCard);
        WebElement itemImage = getDriver().findElement(By.xpath("//body/div[@id='root']/div[@class='glosearch-wrap']" +
                "/div[@class='page-content']/div[@class='main-content']/div[@class='right-menu']/div[@class='product-container']" +
                "/div[@class='list-wrap product-list']/ul[@class='list-items']/li[" + resultNumber + "]/div[1]/div[1]/a[1]"));
        String imageSrc = itemImage.getAttribute("href");
        System.out.println("image href source found:" + imageSrc);
        getDriver().navigate().to(imageSrc);
    }

    public void scrollToElement(WebElement webElement) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView()", webElement);
    }

    public void skipAdvertise() {
        if (advertise.isVisible()) {
            xClose.click();
        }
    }

    public void ifLoginRequired() {
        if (loginAccount.isVisible()) {
            System.out.println("login required to authenticate");
            loginAccount.clear();
            loginAccount.sendKeys("carlos.m.lopez@gmail.com");
            loginPassword.sendKeys("AutomationDummy1");
            try {
                System.out.println("Program is on Stand by for 1000 seconds, please complete captcha");
                getDriver().wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No login required");
        }
    }

    public void lookup_item_able_to_buy() {
        String count = getDriver().findElement(By.cssSelector(".product-quantity-tip > span")).getText();
        //regex to replace words
        String requestCount = count.replaceAll("([a-z])", "").trim();
        if (Integer.parseInt(requestCount) > 0) {
            System.out.println("Item Stock remaining: " + requestCount);
        } else {
            System.out.println("Cannot purchase , Out of Stock");
        }
    }
}