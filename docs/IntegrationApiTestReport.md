# Integration and API Test Documentation

Authors: Group 12

Date: 21/05/2020

Version: 1.0

# Contents

- [Dependency graph](#dependency graph)

- [Integration approach](#integration)

- [Tests](#tests)

- [Scenarios](#scenarios)

- [Coverage of scenarios and FR](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



# Dependency graph

     <report the here the dependency graph of the classes in it/polito/Ezgas, using plantuml>

```plantuml

@startuml

hide circle
hide members

package "Frontend" {}

package "Backend" {

package "it.polito.ezgas.controller" {
    class GasStationController

    class UserController
}

package "it.polito.ezgas.service" {
   interface "GasStationService"

   interface "UserService"
}

package "it.polito.ezgas.repository" {
    class GasStationRepository

    class UserRepository
}

package "it.polito.ezgas.utility"{
    class Utility
}

package "it.polito.ezgas.converter" {
    class UserConverter

    class GasStationConverter
}

package "it.polito.ezgas.dto" {
    class GasStationDto

    class IdPw

    class LoginDto

    class UserDto
}

package "it.polito.ezgas.entity" {
    class GasStation

    class User
}

GasStationController -> GasStationService
UserController -> UserService
GasStationService -> GasStationRepository
GasStationService -> Utility
UserService -> UserRepository
GasStationService -> GasStationConverter
UserService -> UserConverter
GasStationConverter -> GasStationDto
GasStationConverter -> GasStation
UserConverter -> UserDto
UserConverter -> User
GasStationRepository -> GasStation
UserRepository -> User
UserService -> LoginDto
UserService -> IdPw
GasStationService -> User
GasStationService -> UserDto
GasStationConverter -> User
GasStationConverter -> UserDto

"it.polito.ezgas.controller" --[hidden]> "it.polito.ezgas.service"
"it.polito.ezgas.service" --[hidden]> "it.polito.ezgas.repository"
"it.polito.ezgas.service" --[hidden]> "it.polito.ezgas.converter"
"it.polito.ezgas.repository" -[hidden]> "it.polito.ezgas.converter"
"it.polito.ezgas.repository" --[hidden]> "it.polito.ezgas.entity"
"it.polito.ezgas.repository" --[hidden]> "it.polito.ezgas.dto"
"it.polito.ezgas.repository" --[hidden]> "it.polito.ezgas.utility"
"it.polito.ezgas.converter" --[hidden]> "it.polito.ezgas.dto"
"it.polito.ezgas.converter" --[hidden]> "it.polito.ezgas.entity"
"it.polito.ezgas.converter" --[hidden]> "it.polito.ezgas.utility"
"it.polito.ezgas.entity" -[hidden]> "it.polito.ezgas.dto"
"it.polito.ezgas.dto" -[hidden]> "it.polito.ezgas.utility"
"it.polito.ezgas.entity" -[hidden]> "it.polito.ezgas.utility"
}

"Frontend" --> "Backend"

@enduml

```

# Integration approach

    <Write here the integration sequence you adopted, in general terms (top down, bottom up, mixed) and as sequence
    (ex: step1: class A, step 2: class A+B, step 3: class A+B+C, etc)>
    <The last integration step corresponds to API testing at level of Service package>
    <Tests at level of Controller package will be done later>



#  Tests

   <define below a table for each integration step. For each integration step report the group of classes under test, and the names of
     JUnit test cases applied to them>

## Step 1
| Classes  | JUnit test cases |
|--|--|
|||


## Step 2
| Classes  | JUnit test cases |
|--|--|
|||


## Step n API Tests

   <The last integration step  should correspond to API testing, or tests applied to all classes implementing the APIs defined in the Service package>

| Classes  | JUnit test cases |
|--|--|
|||




# Scenarios


<If needed, define here additional scenarios for the application. Scenarios should be named
 referring the UC they detail>

## Scenario UCx.y

| Scenario |  name |
| ------------- |:-------------:|
|  Precondition     |  |
|  Post condition     |   |
| Step#        | Description  |
|  1     |  ... |
|  2     |  ... |



# Coverage of Scenarios and FR


<Report in the following table the coverage of  scenarios (from official requirements and from above) vs FR.
Report also for each of the scenarios the (one or more) API JUnit tests that cover it. >




| Scenario ID | Functional Requirements covered | JUnit  Test(s) |
| ----------- | ------------------------------- | ----------- |
|  ..         | FRx                             |             |
|  ..         | FRy                             |             |
| ...         |                                 |             |
| ...         |                                 |             |
| ...         |                                 |             |
| ...         |                                 |             |



# Coverage of Non Functional Requirements


<Report in the following table the coverage of the Non Functional Requirements of the application - only those that can be tested with automated testing frameworks.>


###

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|                            |           |


