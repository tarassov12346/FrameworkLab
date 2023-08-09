@search
Feature:The Internet http://shop.bugred.ru/ Website

  Scenario Outline: By search request a user can get the items of given names
    Given I am on the page thru "chrome"
    When I enter <search_request> and press search button
    Then I should see items with names corresponding to <search_request>

    Examples:
      | search_request |
      | Пальто         |
      | Шорты          |
      | Товар          |