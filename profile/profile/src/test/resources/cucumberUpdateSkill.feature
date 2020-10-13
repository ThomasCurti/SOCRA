Feature: Modifier une competence a un profil

  Scenario Outline: Le client fait un appel vers /skills/ avec une methode POST et un objet de skill en body
    Given The database already contains relevant profil data
    Given Create a new skill object containing "experienceTest", "formationTest", "technical_skillsTest", "studyTest"
    When I make a POST on /skills/ with this object
    Then I receive skill status code of 200
    Examples:
      | id  | experience | formation | technicalSkills | study |
      | 1   | UpExp      | UpForma   | UpTechnical     | UpStu |