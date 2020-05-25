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

We adopted MIXED way for the integration tests. We used Mockito for the Service Tests to Mock the Repository, but we don't use it to Mock the Converter.

Sequence:

step1:  GasStationTest.java + UserTest.java +
        GasStationDtoTest.java + UserDtoTest.java
step2:  GasStationConverterTest.java + UserConverterTest.java +
        GasStationRepositoryTest.java + UserRepositoryTests.java +
stepN:  GasStationServiceTest.java + UserServiceTest.java

#  Tests

   <define below a table for each integration step. For each integration step report the group of classes under test, and the names of
     JUnit test cases applied to them>

## Step 1
| Classes  | JUnit test cases |
|--|--|
| GasStationTest.java | constructor__returnGasStation |
| GasStationTest.java | getGasStationId__returnGasStationId |
| GasStationTest.java | setGasStationId__modifyGasStationId |
| GasStationTest.java | getGasStationName__returnGasStationName |
| GasStationTest.java | setGasStationName__modifyGasStationName |
| GasStationTest.java | getGasStationAddress__returnGasStationAddress |
| GasStationTest.java | setGasStationAddress__modifyGasStationAddress |
| GasStationTest.java | getReportDependability__returnReportDependability |
| GasStationTest.java | setReportDependability__modifyReportDependability |
| GasStationTest.java | getReportUSer__returnReportUser |
| GasStationTest.java | setReportUSer__modifyReportUser |
| GasStationTest.java | getReportTimestamp__returnReportTimestamp |
| GasStationTest.java | setReportTimestamp__modifyReportTimestamp |
| GasStationTest.java | getHasDiesel__returnHasDiesel |
| GasStationTest.java | setHasDiesel__modifyHasDiesel |
| GasStationTest.java | getHasSuper__returnHasSuper |
| GasStationTest.java | setHasSuper__modifyHasSuper |
| GasStationTest.java | getHasSuperPlus__returnHasSuperPlus |
| GasStationTest.java | setHasSuperPlus__modifyHasSuperPlus |
| GasStationTest.java | getHasGas__returnHasGas |
| GasStationTest.java | setHasGas__modifyHasGas |
| GasStationTest.java | getHasMethane__returnHasMethane |
| GasStationTest.java | setHasMethane__modifyHasMethane |
| GasStationTest.java | getLat__returnLat |
| GasStationTest.java | setLat__modifyLat |
| GasStationTest.java | getLon__returnLon |
| GasStationTest.java | setLon__modifyLon |
| GasStationTest.java | getDieselPrice__returnDieselPrice |
| GasStationTest.java | setDieselPrice__modifyDieselPrice |
| GasStationTest.java | getSuperPrice__returnSuperPrice |
| GasStationTest.java | setSuperPrice__modifySuperPrice |
| GasStationTest.java | getSuperPlusPrice__returnSuperPlusPrice |
| GasStationTest.java | setSuperPlusPrice__modifySuperPlusPrice |
| GasStationTest.java | getGasPrice__returnGasPrice |
| GasStationTest.java | setGasPrice__modifyGasPrice |
| GasStationTest.java | getMethanePrice__returnMethanePrice |
| GasStationTest.java | setMethanePrice__modifyMethanePrice |
| GasStationTest.java | getUser__returnUser |
| GasStationTest.java | setUser__modifyUser |
| GasStationTest.java | getCarSharing__returnCarSharing |
| GasStationTest.java | setCarSharing__modifyCarSharing |
| UserTest.java       | testUser_ShouldCreateObject |
| UserTest.java       | testGetUserId_ShouldReturnUserId |
| UserTest.java       | testSetUserId_ShouldSetUserId |
| UserTest.java       | testGetUserName_ShouldReturnUserName |
| UserTest.java       | testSetUserName_ShouldSetUserName |
| UserTest.java       | testGetPassword_ShouldReturnPassword |
| UserTest.java       | testSetPassword_ShouldSetPassword |
| UserTest.java       | testGetEmail_ShouldReturnEmail |
| UserTest.java       | testSetEmail_ShouldSetEmail |
| UserTest.java       | testGetReputation_ShouldReturnReputation |
| UserTest.java       | testSetReputation_ShouldSetReputation |
| UserTest.java       | testGetAdmin_ShouldReturnAdmin |
| UserTest.java       | testSetAdmin_ShouldSetAdmin |
| GasStationDtoTest.java | setHasSuper_ShouldHaveUpdateValue |
| GasStationDtoTest.java | setHasSuperPlus_ShouldHaveUpdateValue |
| GasStationDtoTest.java | setHasGas_ShouldHaveUpdateValue |
| UserDtoTest.java       | notInitializedAdmin_ShouldBeFalse |

## Step 2
| Classes  | JUnit test cases |
|--|--|
| GasStationConverterTest.java  | testGasStationConvertToGasStationDto  |
| GasStationConverterTest.java  | testGasStationDtoConvertToGasStation  |
| UserConverterTest.java        | testUserConvertToUserDto              |
| UserConverterTest.java        | testUserDtoConvertToUser              |
| GasStationRepositoryTest.java | testFindByGasStationId                                    |
| GasStationRepositoryTest.java | testFindByHasMethaneTrueOrderByMethanePriceDesc           |
| GasStationRepositoryTest.java | testFindByCarSharing                                      |
| GasStationRepositoryTest.java | testFindByHasDieselTrueOrderByDieselPriceDesc             |
| GasStationRepositoryTest.java | testFindByHasSuperTrueOrderBySuperPriceDesc               |
| GasStationRepositoryTest.java | testFindByHasSuperPlusTrueOrderBySuperPlusPriceDesc       |
| GasStationRepositoryTest.java | testFindByHasGasTrueOrderByGasPriceDesc                   |
| UserRepositoryTests.java      | testFindByEmailAndPassword                                |
| UserRepositoryTests.java      | testFindByUserId                                          |

## Step n API Tests

   <The last integration step  should correspond to API testing, or tests applied to all classes implementing the APIs defined in the Service package>

| Classes  | JUnit test cases |
|--|--|
| GasStationServiceTest.java    | existingId_returnCorrespondingGasStationDto  |
| GasStationServiceTest.java    | nonExistingId_returnNull  |
| GasStationServiceTest.java    | negativeId_InvalidGasStationExceptionThrown  |
| GasStationServiceTest.java    | existingId_returnCorrespondingGasStationDtoWithUserAndTimestampInserted  |
| GasStationServiceTest.java    | validGasStationDto_returnGasStationDto  |
| GasStationServiceTest.java    | negativeDiesel_PriceExceptionThrown  |
| GasStationServiceTest.java    | negativeSuper_PriceExceptionThrown  |
| GasStationServiceTest.java    | negativeSuperPlusPrice_PriceExceptionThrown  |
| GasStationServiceTest.java    | negativeGasPrice_PriceExceptionThrown  |
| GasStationServiceTest.java    | negativeMethanePrice_PriceExceptionThrown  |
| GasStationServiceTest.java    | invalidLatitude_GPSDataExceptionThrown  |
| GasStationServiceTest.java    | invalidLongitude_GPSDataExceptionThrown  |
| GasStationServiceTest.java    | _returnEmptyList  |
| GasStationServiceTest.java    | _returnGasStationDtoList  |
| GasStationServiceTest.java    | existingId_returnTrue  |
| GasStationServiceTest.java    | nonExistingId_returnFalse  |
| GasStationServiceTest.java    | negativeId_InvalidGasStationExceptionThrown  |
| GasStationServiceTest.java    | diesel_returnListGasStationDtoSortedByPrice  |
| GasStationServiceTest.java    | super_returnListGasStationDtoSortedByPrice  |
| GasStationServiceTest.java    | superPlus_returnListGasStationDtoSortedByPrice  |
| GasStationServiceTest.java    | gas_returnListGasStationDtoSortedByPrice  |
| GasStationServiceTest.java    | methane_returnListGasStationDtoSortedByPrice  |
| GasStationServiceTest.java    | diesel_returnEmptyList  |
| GasStationServiceTest.java    | emptyString_InvalidGasTypeExceptionThrown  |
| GasStationServiceTest.java    | null_InvalidGasTypeExceptionThrown  |
| GasStationServiceTest.java    | invalidString_InvalidGasTypeExceptionThrown  |
| GasStationServiceTest.java    | invalidLatValidLon_GPSDataExceptionThrown  |
| GasStationServiceTest.java    | validLatInvalidLon_GPSDataExceptionThrown  |
| GasStationServiceTest.java    | invalidLatInvalidLon_GPSDataExceptionThrown  |
| GasStationServiceTest.java    | validLatValidLon_returnListGasStationDtoSortedByDistance  |
| GasStationServiceTest.java    | validLatValidLon_returnListEmptyList  |
| GasStationServiceTest.java    | dieselCarcompanyA_returnListGasStationDtoSortedByPrice  |
| GasStationServiceTest.java    | superCarcompanyB_returnListGasStationDtoSortedByPrice  |
| GasStationServiceTest.java    | dieselNonExistingCarCompany_returnEmptyList  |
| GasStationServiceTest.java    | wrongLat_throwGPSDataException  |
| GasStationServiceTest.java    | emptyGasolinetype_throwGPSDataException  |
| GasStationServiceTest.java    | validLatValidLonDieselCarA_returnGasStationListSortedByDistance  |
| GasStationServiceTest.java    | validLatValidLonNullCarA_returnGasStationListSortedByDistance  |
| GasStationServiceTest.java    | validLatValidLonNullNull_returnGasStationListSortedByDistance  |
| GasStationServiceTest.java    | validLatValidLonMethaneNull_returnGasStationListSortedByDistance  |
| GasStationServiceTest.java    | invalidUserId_ShouldThrowException  |
| GasStationServiceTest.java    | invalidGasStationId_ShouldThrowException  |
| GasStationServiceTest.java    | notExistingUser_ShouldThrowException  |
| GasStationServiceTest.java    | notExistingGasStation_ShouldThrowException  |
| GasStationServiceTest.java    | invalidGasTypePrice_ShouldThrowException  |
| GasStationServiceTest.java    | correctParams_ShouldSetNewReport  |
| GasStationServiceTest.java    | validCarSharing_ShouldReturnGasStationList  |
| GasStationServiceTest.java    | wrongCarSharing_ShouldReturnEmptyGasStationList  |
| UserServiceTest.java          | getUser_UserId_ShouldReturnUser  |
| UserServiceTest.java          | getUser_NotExistingUserId_ShouldThrowException  |
| UserServiceTest.java          | saveNewUser_ShouldHaveReputationEq0  |
| UserServiceTest.java          | saveTwoUser_ShouldNotHaveSameEmail  |
| UserServiceTest.java          | testDelete  |
| UserServiceTest.java          | testInvalidUserException  |
| UserServiceTest.java          | nonExistingId  |
| UserServiceTest.java          | _returnEmptyList  |
| UserServiceTest.java          | _returnUserDtoList  |
| UserServiceTest.java          | invalidEmail_ShouldThrowException  |
| UserServiceTest.java          | invalidPassword_ShouldThrowException  |
| UserServiceTest.java          | correctIdPw_ShouldReturnLogin  |
| UserServiceTest.java          | testMaxValueIncrease  |
| UserServiceTest.java          | testIncrease  |
| UserServiceTest.java          | testInvalidUserException  |
| UserServiceTest.java          | nonExistingId  |
| UserServiceTest.java          | testDecrease  |
| UserServiceTest.java          | testMinValueDecrease  |
| UserServiceTest.java          | testInvalidUserException  |
| UserServiceTest.java          | nonExistingId  |

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


