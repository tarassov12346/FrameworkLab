@filter
Feature:The Internet http://shop.bugred.ru/ Website

  Background:
    Given I open the page thru "chrome"

  Scenario Outline: By ticking the color  a user can get the items of given colors

    When I tick color <color> and press lower search button
    Then I should see items with colors <color> corresponding to "<color_check>"

    Examples:
      | color | color_check   |
      | 1     | 0, 0, 0       |
      | 3     | 14, 82, 247   |
      | 12    | 224, 124, 11  |
      | 11    | 20, 235, 56   |
      | 2     | 254, 8, 8     |
      | 5     | 246, 244, 244 |

  Scenario Outline: By ticking the size  a user can get the items of given sizes

    When I tick size <size> and press lower search button
    Then I should see items with sizes <size> corresponding to <size_check>

    Examples:
      | size | size_check |
      | 5    | 50         |
      | 7    | 42         |
      | 3    | 30         |


  Scenario Outline: By entering lower and upper price rank  a user can get the items of given price rank

    When I enter price rank <lower_price> <upper_price> and press lower search button
    Then I should see items with price rank within <lower_price> <upper_price>

    Examples:
      | lower_price | upper_price |
      | 100         | 800         |
    