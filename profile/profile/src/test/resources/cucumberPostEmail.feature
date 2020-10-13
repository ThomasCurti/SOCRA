# new feature
# Tags: optional

Feature: Récupérer les profils au format excel dans un mail

  Scenario: Le client fait un appel vers /mail/ avec une methode POST contenant un email et reçoit a cet email un document excel contenant la liste des profils
    Given An email
    When I make a POST Request at /mail/
    Then I receive status code of 200 and a response saying the mail has been sent