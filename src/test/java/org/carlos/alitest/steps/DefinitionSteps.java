package org.carlos.alitest.steps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.carlos.alitest.steps.serenity.EndUserSteps;

public class DefinitionSteps {

    @Steps
    EndUserSteps endUser;

    @Given("the user is on the AliExpress home page")
    public void givenTheUserIsOnTheAliExpressHomePage() {
        endUser.is_the_home_page();
    }

    @When("the user looks up for the word '$word'")
    public void whenTheUserLooksUpTheWord(String word) {
        endUser.looks_for(word);
    }

    @When("the user navigates to <pageN>º page number")
    public void whenTheUserNavigatesToPageNumber(@Named("pageN") String pageN) {
        endUser.navigates_to_page(pageN);
    }

    @When("the user search for the <resultNumber> result")
    public void whenTheUserSearchForResultItemNumber(@Named("resultNumber") String resultNumber ) {
        endUser.lookup_resultNumber(resultNumber);
    }

    @Then("the user asserts that at least 1 item is able to buy")
    public void thenTheUserAssertThatIsAbleToBuy() {
        endUser.lookup_item_able_to_buy();
    }
}
