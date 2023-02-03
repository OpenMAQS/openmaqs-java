Feature: Sample WebService Tests

  Scenario: Get a Product Name
    Given I Get Product Information for Product ID 2
    Then The Product Name is "Yo-yo"
