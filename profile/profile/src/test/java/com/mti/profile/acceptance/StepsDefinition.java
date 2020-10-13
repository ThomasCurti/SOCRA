package com.mti.profile.acceptance;

import com.mti.profile.controller.dto.*;

import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class StepsDefinition {

    @LocalServerPort
    private int port;

    //Feature 1 post profile
    private TestRestTemplate restTemplate = new TestRestTemplate();

    AddProfileDtoProfileRequest addProfileDtoProfileRequest;
    ResponseEntity<String> response;
    ResponseEntity<Resource> resourceResponseEntity;

    @Given("Create a new profile object containing {string} {string}, {string}, {string}, " +
            "{string}, {string}, {string}, {string}")
    public void createANewProfileObjectContaining(String arg0,
                                                  String arg1,
                                                  String arg2,
                                                  String arg3,
                                                  String arg4,
                                                  String arg5,
                                                  String arg6,
                                                  String arg7) {
        addProfileDtoProfileRequest = new AddProfileDtoProfileRequest(0, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }



    @When("I make a POST on \\/profile\\/ with this object")
    public void iMakeAPOSTOnProfileWithThisObject() {
        HttpEntity<AddProfileDtoProfileRequest> request = new HttpEntity<>(addProfileDtoProfileRequest);
        response = restTemplate.postForEntity("http://127.0.0.1:" + port + "/profile/",
                                                    request, String.class);
    }

    @Then("I receive status code of {int}")
    public void iReceiveStatusCodeOf(int arg0) {
        Assert.assertEquals(arg0 ,response.getStatusCodeValue());
    }



    //Feature 3.4 get profile
    @Given("Nothing")
    public void nothing() {
    }

    @When("I make a GET Request")
    public void iMakeAGETRequest() {
        response = restTemplate.getForEntity("http://127.0.0.1:" + port + "/profile/", String.class);
    }

    @Then("I receive status code of {int} and data sorted by name, firstname, town, title")
    public void iReceiveStatusCodeOfAndDataSortedByNameFirstnameTownTitle(int arg0) {
        Assert.assertEquals(arg0 ,response.getStatusCodeValue());

        Gson g = new Gson();
        GetProfileDtoResponse p = g.fromJson(response.getBody(), GetProfileDtoResponse.class);
        for (int i = 0; i < p.profiles.size(); i++) {
            if (i + 1 < p.profiles.size()) {
                Assert.assertTrue(p.profiles.get(i).compareTo(p.profiles.get(i + 1)) <= 0);
            }
        }
    }

    // Feature 7
    @Autowired
    ProfileRepository profileRepository;
    GetProfilesByKeywordsDtoRequest getProfilesByKeywordsDtoRequest;

    @Given("The user provided {string} and {string}")
    public void theUserProvidedAnd(String arg0, String arg1) {
        getProfilesByKeywordsDtoRequest = new GetProfilesByKeywordsDtoRequest(List.of(arg0, arg1));
    }

    @Given("The database already contains relevant data")
    public void theDatabaseAlreadyContainsRelevantData() {
        profileRepository.deleteAll();
        ProfileModel profile = new ProfileModel(0, "Hugo", "Victor", "Besançon", "Poète", "", "", "", "");
        ProfileModel profile2 = new ProfileModel(0, "Lamartine", "Alphonse", "Mâcon", "Poète", "", "", "", "");
        ProfileModel profile3 = new ProfileModel(0, "Lamartine", "Victor", "Paris", "Poète", "", "", "", "");
        ProfileModel profile1 = new ProfileModel(0, "Pierre", "Paul", "Marseille", "Ecrivain", "", "", "", "");
        profileRepository.saveAll(List.of(profile, profile1, profile3, profile2));
    }

    @When("I make a POST Request at \\/profile\\/filter\\/")
    public void iMakeAPOSTRequestAtProfileFilter() {
        HttpEntity<GetProfilesByKeywordsDtoRequest> request = new HttpEntity<>(getProfilesByKeywordsDtoRequest);
        response = restTemplate.postForEntity("http://127.0.0.1:" + port + "/profile/filter/",
                request, String.class);
    }

    @Then("I receive status code of {int} and data sorted by pertinence")
    public void iReceiveStatusCodeOfAndDataSortedByPertinence(int arg0) {
        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
        Gson g = new Gson();
        GetProfilesByKeywordsDtoResponse r = g.fromJson(response.getBody(), GetProfilesByKeywordsDtoResponse.class);
        Assert.assertNotNull(r.profiles);
        Assert.assertEquals(4, r.profiles.size());
        Assert.assertEquals("Paris", r.profiles.get(0).town);
        Assert.assertEquals("Marseille", r.profiles.get(3).town);

    }
    //Feature 2 POST skill

    private UpdateSkillsDtoSkillsRequest skill = null;

    @Given("The database already contains relevant profil data")
    public void Thedatabasealreadycontainsrelevantprofildata()
    {
       // profileRepository.deleteAll();
        ProfileModel profileModel = new ProfileModel(0, "Hugo", "Victor", "Besançon", "Poète", "test", "test", "test", "test");
        profileRepository.save(profileModel);
    }

    @Given("Create a new skill object containing {string}, {string}, {string}, {string}")
    public void givenCreateanewskillobjectcontaining(String arg0, String arg1, String arg2, String arg3)
    {
        var test = profileRepository.findAll();
        Assert.assertTrue(test.iterator().next() instanceof ProfileModel);
        ProfileModel p = test.iterator().next();
        skill = new UpdateSkillsDtoSkillsRequest(p.getId(), arg0, arg1, arg2, arg3);
    }


    @When("I make a POST on \\/skills\\/ with this object")
    public void imakeaPOSTonskills() {
        HttpEntity<UpdateSkillsDtoSkillsRequest> request = new HttpEntity<>(skill);
        response = restTemplate.postForEntity("http://127.0.0.1:" + port + "/skills/",
                request, String.class);

    }

    @Then("I receive skill status code of {int}")
    public void iReceiveStatusCodeOfSkill(int arg0) {
        Assert.assertEquals(arg0 ,response.getStatusCodeValue());

        Gson g = new Gson();
        UpdateSkillsDtoSkillsResponse r = g.fromJson(response.getBody(), UpdateSkillsDtoSkillsResponse.class);

        Assert.assertEquals("experienceTest", r.experience);
        Assert.assertEquals("formationTest", r.formation);
        Assert.assertEquals("technical_skillsTest", r.technicalSkills);
        Assert.assertEquals("studyTest", r.study);
        profileRepository.deleteAll();

    }
    //Feature 3.5 get profiles as Excel
    @Given("Profiles in the database")
    public void profiles_in_the_database() {
    }

    @When("I make a GET Request for the excel")
    public void i_make_a_get_request_at_excel() {
        resourceResponseEntity = restTemplate.getForEntity("http://127.0.0.1:" + port + "/excel/", Resource.class);
    }

    @Then("I receive status code of {int} and a response containing the excel file in bytes")
    public void i_receive_status_code_of_200_and_response_containing_excel_in_body(int arg0) {
        Assert.assertEquals(arg0 ,resourceResponseEntity.getStatusCodeValue());

        Resource byteArrayResource = resourceResponseEntity.getBody();
        Assert.assertNotNull(byteArrayResource);
        Assert.assertEquals("profiles.xls", byteArrayResource.getFilename());
    }

    private GetProfileByMailDtoRequest getProfileByMailDtoRequest;

    //Feature 3.6 Send profils as excel by mail
    @Given("An email")
    public void anEmail() {
        getProfileByMailDtoRequest = new GetProfileByMailDtoRequest("socraprojectapi@gmail.com");
    }

    @When("I make a POST Request at \\/mail\\/")
    public void iMakeAPOSTRequestAtMail() {
        HttpEntity<GetProfileByMailDtoRequest> request = new HttpEntity<>(getProfileByMailDtoRequest);
        response = restTemplate.postForEntity("http://127.0.0.1:" + port + "/mail/",
                request, String.class);
    }

    @Then("I receive status code of {int} and a response saying the mail has been sent")
    public void iReceiveStatusCodeOfAndAResponseSayingTheMailHasBeenSent(int arg0) {
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(response.getBody().contains("Mail sent"));
    }
}
