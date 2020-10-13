Feature: lister les profils ordonnés en fonction des mots-clés donnés par l'utilisateur

  Scenario: Le client fait un appel vers /profile/filter/ avec une methode POST et obtient les profils ordonnés par pertinence selon les mots-clés.
    Given The user provided "Lamartine" and "poète"
    Given The database already contains relevant data
    When I make a POST Request at /profile/filter/
    Then I receive status code of 200 and data sorted by pertinence