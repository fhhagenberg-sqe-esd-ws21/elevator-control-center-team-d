# Elevator Controll Center with JavaFX

![](elevator_dudes.jpg)

This project contains a GUI for an Elevator Controll Center that is implemented in JavaFx.

## Prerequisites

- [x] Java 13 SDK (e.g. Oracle or OpenJDK).
- [x] The Elevator Simulator (https://github.com/winterer/elevator/releases)

## How To Download And Run The Application
1. The latest version of the Elevator Controll Center can be found in the releases section of the repository. Simply download jar file from the latest release.
2. To Run the application two steps are needed:
   1. Start the Simulator and setup the building as you like
   2. Double Click on the ECC.jar
3. Congratulations! Now you can play around and feel like a real elevator manager.  

## How the project was managed
To ensure quality, we establishes a few measures.
First of all, we established automatic builds. A build only passes, if all the Unit Tests  and also the automatic analysis in Sonarcloud are successfull.

The Masterbranch is also protected against merges. It is only possible to merge a branch to the master trough a pull request. 
Our process for pull requests was as follows: As soon as somebody finished a feature and the Unit Tests and SonarCloud analysis passed, he created a pull request.
Then an other Team member made a Code review (usually together with the developer). 
If issues were found, they had to be fixed and otherwise the pull request was accepted. 

## Build it yourself
`mvn clean package`
