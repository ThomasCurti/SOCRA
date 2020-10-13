# new feature
# Tags: optional

Feature: Récupérer les profils au format excel

  Scenario: Le client fait un appel vers /excel avec une methode GET et obtient les profils triés dans un excel
    Given Profiles in the database
    When I make a GET Request for the excel
    Then I receive status code of 200 and a response containing the excel file in bytes