@Purchaseorder
Feature: Purcahse the order from e-commerce website

Background:
Given I Landed on Ecommerce Page

@submitingorder
Scenario Outline: positive test of submiting order
Given Logged in with "<email>" and "<password>"
When User add product "<productname>" to cart		
And Checkout and submit order "<productname>"
Then "Thankyou for the order." msg is display on confirmation Page

Examples:
|email               |password      |  productname     |
|Sujal123@gmail.com  |Sujal@14325     |  ADIDAS ORIGINAL |
