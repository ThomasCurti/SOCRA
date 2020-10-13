# SOCRA Profile project
This project is an API that exposes endpoints to handle profiles.<br/>
Those profiles are composed of name, firstname, town, title, experience, formation, technical skills, study texts<br/>
This file should be read by every maintainer of this application

## About this project
#### Authors

This project was created by a group of 4 students for a course of Software Craftmanship<br/>
This project should only be used in educative purpose<br/>
**Authors' login:**<br/>
* **clement.dedenis**<br/>
* **piro_j**<br/>
* **thomas.curti**
* **vincent1.masson**

NB: The project was **initialized** by Thomas with the **help of everyone**.<br/>
Thus, around 1000 lines of code were written **by everyone** under the git name of *Thomas* during this initialization

Everyone made their part.

#### How it was made

This project was made using **TDD methodology**. Acceptance tests using cucumber and Integration Test are also implemented 
in this project.<br/>
A pipeline has been used during the creation of the whole project in order to facilitate the 
Continuous Integration.

#### Docker

This project is also dockerized.<br/>
Nevertheless, be careful about Docker. Docker is **not** the same on Windows or Mac or Linux.<br/>
*for example docker localhost network on windows is docker.for.win.localhost and on mac it is host.docker.internal*.<br/>
Since the docker-compose create its own network, it **should** work on every Operating System.<br/>
It has been tested on a **Windows 10 pro**, **Windows 10 family**, on **Ubuntu** and on **Kali Linux**.<br/>
If you have any issue to start the docker, **feel free to send an email at Thomas Curti.**

## How to run this

#### For developers
**requirements**: You must have at least java 14 and the last maven version installed <br/>
**NB**: If you don't have maven, you can use at least the mvnw executable file present in the git repository
 
* Start Intellij Idea
* Open the pom.xml *as a project*
* You can use mvn command lines or simply run the main function

#### For Users (Docker deployment)
* Start a terminal and your docker deamon
* Move to the root of the project (where the docker-compose.yml file is located)
* Run *docker-compose build*
* Run *docker-compose up*
* When the server finishes to boot, the API can be used at localhost:8080

**If it doesn't work:**
* **Check that you docker deamon is started  and that you can run *docker run hello-world***
* **Check that your ports 3306 and 8080 are not busy**
* **Change paths in the docker-compose.yml file to match your operating's system path norms**

## Features
All features of this project are documented here:

* Publish my profile
* Edit my profile
* List profiles sorted by name then firstname then town then title
* Export this list of profiles in excel format
* Send a profile by email
* Search for a profile using keyword

All features' implementation are described below in the *features implementation* part.

## API description
**NB** be careful to use the exact url here. URL often end by a /, do not forget it

#### Publish my profile
Method **POST**
*localhost:8080/profile/*
```
Header:
* Basic http headers
* Content-Type: application/json

Body:
{
    "name": "<name>",
    "firstname": "<firstname>",
    "town": "<town>",
    "title": "<title>",
    "experience": "<experience>",
    "formation": "<formation>",
    "technicalSkills": "<technicalSkills>",
    "study": "<study>"
}
```
**name** should not be null<br/>
**firstname** should not be null<br/>
**town** should not be null<br/>
**title** should not be null<br/>
**Other values** are nullable<br/>

#### Edit the skills of my profile
Method **POST**
*localhost:8080/skills/*
```
Header:
* Basic http headers
* Content-Type: application/json

Body:
{
    "id": "<id>",
    "experience": "<experience>",
    "formation": "<formation>",
    "technicalSkills": "<technicalSkills>",
    "study": "<study>"
}
```
**id** should not be null<br/>
**experience** should not be null<br/>
**formation** should not be null<br/>
**technicalSkills** should not be null<br/>
**study** should not be null<br/>

#### List profiles sorted by name then firstname then town then title
Method **GET**
*localhost:8080/profile/*
```
Header:
* Basic http headers

Body:
```
#### Export this list of profiles in excel format
Method **GET**
*localhost:8080/excel/*  

With basic headers, the calling this endPoint triggers a download client site of an excel file containing the list of all profiles, thus this endpoint should be called from a navigator.

#### Send a profile by email
Method **POST**
*localhost:8080/mail/*
```
Header:
* Basic http headers
Content-Type: application/json

Body:
{
    "email" : "<email>"
}
```
#### Search for a profile using keyword
Method **POST**
*localhost:8080/profile/*
```
Header:
* Basic http headers
Content-Type: application/json

Body:
{
    "keywords" : [
        "keyword1",
        "keyword2",
        "keyword3"
    ]
}
```
## Architecture
This project use a basic Spring-Boot layered architecture.<br/>
#### Persistence layer
It is the lowest layer that discuss directly with the database.<br/>
It has:
* model package: all database models using Hibernate JPA
* repository package: all repositories

All model are entities and use the javax.persistence package to be linked with the database model

All repositories extend *CrudRepository* that implements CRUD basic methods

#### Domain Layer
It is the middle layer that implement all business logic.
It has:
* entity package: all entities used by the domain layer then sent to a controller or to a repository
* service package: all services that implements business logic

/!\ Domain Entity model is **not** necessarly the same as Persistence Model. It can be whatever you want.

Services should **only** implements business logic.<br/>
A service can *talk* with another service, be careful with circular dependencies.

#### Controller Layer
It is the highest layer that discuss directly with the user.<br/>
It has:
* dto package: all Model that will be exchanged with the user
* view package: all controllers where endpoints are implemented

/!\ Dto are model that will be exposed directly to the user. Do **not** put any sensitive information in a dto model.

Every controller has its own DTO file model for the request and another DTO file for the response.<br/>
Routes are made using SpringFramework web annotations.


#### Utils Layer
It has all class, method or functions that can be used by a developer to help in the development of the application.


#### How it is connected
The main function starts the SpringApplication runner.<br/>
The configuration of the project can be found in RestProfilesConfiguration. The whole project is configured automatically
by SpringFramework using the annotations.

All controllers are created automatically by SpringFramework (because of the @RestController annotation).<br/>
NB: all controllers are Singleton

All services are injected in controllers automatically by SpringFramework (because of @Service annotation) when controllers are constructed.
NB: all services are Singleton

All repositories are injected in services automatically by SpringFramework when services are constructed
NB: all repository are Singleton

You can choose what service/repository you want to inject in your code in the constructor of your class.

## Features implementation
This part explain the business logic.

#### Publish my profile
The method get a ProfileEntity constructed by the controller.<br/>
If this entity doesn't have any name/firstname/town/title/experience/formation/technicalSkills/study, it throws a IllegalArgumentException.<br/>
Else it send it to the repository to save it then returns the resulting entity.

#### Edit the skills of my profile
This method get a SkillEntity  constructed by the controller.</br>
If this entity have no Id/experience/formation/technicalSkills/study, it's a IllegalArgumentException.<br/>
Else it update the skills of the profile which match to the id then returns the resulting entity.

#### List profiles sorted by name then firstname then town then title
This method get all ProfileEntity in the database using the repository.<br/>
After getting all entities, it sort them then return them. In order to sort them properly, this entity implements Comparable.

#### Export this list of profiles in excel format
This method get all ProfileEntity in the database using the repository.  
After getting all entities, it sort them.  
It then format a HSSFWorkBook (excel in java) and add the profiles in it.  
The excel file is then converted to Bytes and returned by the controller.

#### Send a profile by email
This method calls the getProfilesAsExcel method.
After retrieving the excel it create an email, fill the To, From and Subject data before adding the excel file in attachment to the mail.  
The controller retrieve the email in the request and send the file to this email on success, nothing on fail.

#### Search for a profile using keyword
This endpoint takes a list of keywords submitted by the user and returns all profiles orderer by pertinence.
The list of keywords can not be empty or null.
