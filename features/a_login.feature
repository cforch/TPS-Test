Feature: TPS Web Login
Scenario: TPS Login Test Scenario

Given user is already on Login Page
When login title is Login NEW TPS WEB
Then user enters Username and Password
And user enters image code
And user clicks on login button
Then user selects data bases
And user clicks on connect button
And user is on home page
