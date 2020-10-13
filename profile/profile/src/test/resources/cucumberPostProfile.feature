Feature: publier mon profil

    Scenario Outline: Le client fait un appel vers /profile avec une methode POST et un objet de profile en body
      Given Create a new profile object containing "Curti" "Thomas", "Paris", "Student", "Android app", "Engineering", "Android", "Epita"
      When I make a POST on /profile/ with this object
      Then I receive status code of 200

      Examples:
      | name   | firstname   | town  | title   | experience | formation | technical_skills | study
      |Curti   |Thomas       | Paris | Student |Android app |Engineering|Android           | Epita