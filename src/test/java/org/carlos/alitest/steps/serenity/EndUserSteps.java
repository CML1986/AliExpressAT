package org.carlos.alitest.steps.serenity;

import org.carlos.alitest.pages.BasePage;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    BasePage aliExpressPage;

    @Step
    public void enters(String keyword) {
        aliExpressPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        aliExpressPage.lookup_terms();
    }

    @Step
    public void navigates_to_page(String pageN) {
        aliExpressPage.lookup_pageNumber(pageN);
    }

    @Step
    public void lookup_resultNumber(String resultNumber) {
        aliExpressPage.lookup_resultNumber(resultNumber);
    }

    @Step
    public void is_the_home_page() {
        aliExpressPage.open();
    }

    @Step
    public void looks_for(String word) {
        enters(word);
        starts_search();
    }

    @Step
    public void lookup_item_able_to_buy() {
        aliExpressPage.lookup_item_able_to_buy();
    }
}