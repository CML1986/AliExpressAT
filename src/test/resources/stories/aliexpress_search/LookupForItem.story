Lookup for item
Narrative:
As a Customer we want to see if the second Iphone related ad from the second results page from www.aliexpress.com has at least 1 item to be bought.

Scenario: custom search and validation

Given the user is on the AliExpress home page
When the user looks up for the word 'Iphone'
And the user navigates to <pageN>º page number
And the user search for the <resultNumber> result
Then the user asserts that at least 1 item is able to buy

Examples:
|pageN|resultNumber|
|2|2|
