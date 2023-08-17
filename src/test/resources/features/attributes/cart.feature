Feature: Cart

  @Cart
  Scenario: Item can be added to cart
    Given main page is open
    When item is added to cart
    And cart is opened
    Then item is visible in cart
   
  @Cart
  Scenario: Item can be removed from cart
    Given cart has an item
    When item is deleted
    Then item is removed from cart
  
  @Cart
  Scenario: Item cannot be booked with partial credentials (only with phone number)
    Given cart has an item
    When item is booked with phone number only
    Then item is not booked
  
  @Cart
  Scenario: Item can be booked with full credentials
    Given cart has an item
    When item is booked
    Then successful booking number is displayed
    
  @Cart
  Scenario: Booked items are stored in account profile
    Given login is performed
    When item is added to cart
    And cart is opened   
    And item is booked
    Then booked item is visible in account profile
