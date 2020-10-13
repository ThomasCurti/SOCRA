Feature: lister les profiles

  Scenario: Le client fait un appel vers /profile avec une methode GET et obtient les profils triés
    Given Nothing
    When I make a GET Request
    Then I receive status code of 200 and data sorted by name, firstname, town, title