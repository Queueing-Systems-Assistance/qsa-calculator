# QSA Calculator [![Build Status](https://travis-ci.com/Queueing-Systems-Assistance/qsa-calculator.svg?branch=master)](https://travis-ci.com/Queueing-Systems-Assistance/qsa-calculator)

### Project description

**QSA Calculator** is the main module for the Queueing Systems Assistance project. This module is responsible for calculating system features, report metrics, and logs.

- Calculate queue details
- Visualize queue properties

For those who want to help develop the application:
- [Git workflow](https://github.com/Queueing-Systems-Assistance/qsa-application/docs/git-workflow.md)
- Build the app pack:
  - Use `gradle clean build` to build the project
  - You can run the application, just start `Application.java` main entry point


The application contains several modules:

- qsa-calculator-domain
  - contains all domain objects (POJOs)
- qsa-calculator-implementation
  - here you can find the implementation of the math
  - resolves systems and values, exception messages
  - determines where is the request origin country
  - also validates the resources
- qsa-calculator-server
  - responsible for the access of the application
  - integration tests for the application

Contributors:

- Szilágyi Zoltán
- Szászi Szabolcs
