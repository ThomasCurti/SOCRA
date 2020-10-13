package com.mti.profile;

import com.mti.profile.persistence.model.ProfileModel;
import com.mti.profile.persistence.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Class used to add all default data
 * */
@Component
public class DemoData {

    @Autowired
    private ProfileRepository profileRepository;

    private static String[] names = {
            "Clement",
            "Thomas",
            "Vincent",
            "Kong",
            "Lascar",
            "Masturbin",
            "Nicolai",
            "Aroul",
            "Lemettre",
            "Balcani",
            "Masson",
            "Dedenis",
            "Curti",
            "Piro",
            "Le joyeux",
            "Le peureux",
            "Sergio",
            "De la chapelle",
            "Chatain",
            "Bogoss",
            "Corbier",
    };

    private static String[] firstnames = {
            "Clement",
            "Thomas",
            "Vincent",
            "Jeremie",
            "Isabelle",
            "Marine",
            "Jean",
            "Marie",
            "Agathe",
            "Antoine",
            "Keith",
            "Arnaud",
            "Patrick",
            "Joe",
            "Susan",
            "Yohan",
            "Paul",
            "Theo",
            "Mehdi",
            "Valentin",
            "Charlotte",
            "Morgane",
            "Stephane",
            "Nadine",
            "Fabien",
            "Sebastien",
            "Tom",
            "Dorian",
            "Ferdinand",
            "Audrey"
    };

    private String[] towns = {
            "Paris",
            "Berlin",
            "Madrid",
            "Barcelone",
            "Bucarest",
            "Budapest",
            "Londres",
            "Lisbonne",
            "Marseille",
            "Dubai"
    };

    private static String[] titles = {
            "Developpeur java",
            "Developpeur C",
            "Architecte",
            "Chef de projet",
            "Developpeur Javascript",
            "Developpeur Go",
            "Developpeur Php",
            "CTO"
    };

    private static String[] studies = {
            "Epita",
            "Efrei",
            "Universite de la sorbonne",
            "Telecom Paris",
            "Polytechnique",
            "IPSA",
            "Epitech",
    };

    private static String[] experiences = {
            "Stage chez Ubisoft",
            "Stage chez Renault",
            "5 ans chez la Societe Generale",
            "5 ans chez Accenture",
            "Stage chez 3IE",
            "Stage chez Shadow",
            "Lead technique chez Algolia",
            "Stage chez Docker"
    };

    private static String[] formations = {
            "Multimedia",
            "IA",
            "Cybersecurite",
            "Reseau",
            "Developpement",
            "Marketing",
            "Gestion de projet",
            "Finance",
            "BioChimie"
    };

    private static String[] skills = {
            "Java",
            "JavaScript",
            "Html / Css",
            "Python",
            "C",
            "C++",
            "C#",
            "Rust",
            "Haskel",
            "Lisp"
    };

    /**
     * Called when the server is started
     * @param event event received when the server is started
     * */
    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            String name = names[rand.nextInt(names.length)];
            String firstname = firstnames[rand.nextInt(firstnames.length)];
            String town = towns[rand.nextInt(towns.length)];
            String title = titles[rand.nextInt(titles.length)];
            String experience = experiences[rand.nextInt(experiences.length)];
            String formation = formations[rand.nextInt(formations.length)];
            String skill = skills[rand.nextInt(skills.length)];
            String study = studies[rand.nextInt(studies.length)];

            ProfileModel model = new ProfileModel(0, name, firstname, town, title, experience, formation, skill, study);

            profileRepository.save(model);
        }
    }
}
