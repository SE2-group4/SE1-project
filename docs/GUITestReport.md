# GUI  Testing Documentation

Authors: Group 12

Date: 31/05/2020

Version: 1.0

# GUI testing

This part of the document reports about testing at the GUI level. Tests are end to end, so they should cover the Use Cases, and corresponding scenarios.

## Coverage of Scenarios and FR

###

| Scenario ID | Functional Requirements covered | GUI Test(s) |
| ----------- | ------------------------------- | ----------- |
| 1           | FR1.1                             | signUpTest.py, resetFormUserResgistrationAdminPanel.py, creationUserAccountAdminPanelTest.py            |
| 1           | FR2                               | signInAdminTest.py, resetFormSignUpNotLoggedTest.py           |
| 2           | FR1.1                             | modifyTest.py, modifyUserAccountAdminPanelTest.py           |
| 3           | FR1.2                             | deleteUserAccountAdminPanelTest.py, deleteUserAccountAdminPanelTest.py            |
| 4           | FR3.1                             | createNewGasStation.py, resetFormCreateGasStation.py            |
| 5           | FR3.1                             | modifyGasStation.py            |
| 6           | FR3.2                             | deleteGasStation.py            |
| 7           | FR5.1                             | insertPriceReportLoggedHomeTest.py, addReportAdminPanel.py           |
| 8           | FR4                               | searchGasStationLoggedTest.py, searchGasStationAdminPanel.py, resetFormSearchGasStationAdminPanel.py, sortGasStationLoggedUserTest.py, resetFormSearchGasStationLoggedUserTest.py            |
| 10          | FR5.3                             | increaseReputationLoggedHomeTest.py, decreaseReputationLoggedHomeTest.py, increaseReputationPanelTest.py, decreaseReputationPanelTest.py            |

# REST  API  Testing

This part of the document reports about testing the REST APIs of the back end. The REST APIs are implemented by classes in the Controller package of the back end.
Tests should cover each function of classes in the Controller package

## Coverage of Controller methods

| class.method name | Functional Requirements covered |REST  API Test(s) |
| ----------- | ------------------------------- | ----------- |
| GasStationControllerTests.testGetGasStationById           | FR4         | getGasStationById           |
| GasStationControllerTests.testSaveGasStation              | FR3.1       | saveGasStation              |
| GasStationControllerTests.testDeleteGasStation            | FR3.2       | deleteGasStation            |
| GasStationControllerTests.testGasStationByGasolineType    | FR4.5       | getGasStationByGasolineType |
| GasStationControllerTests.testGetGasStationByProximity    | FR4         | getGasStationByProximity    |
| GasStationControllerTests.testGetGasStationWithCoordinates| FR4         | getGasStationWithCoordinates|
| GasStationControllerTests.testSetGasStationReport         | FR5.1       | setGasStationReport         |
| UserControllerTests.testGetUserById                       | FR1.4       | getUserById                 |
| UserControllerTests.testSaveUser                          | FR1.1       | saveUser                    |
| UserControllerTests.testDeleteUser                        | FR1.2       | deleteUser                  |
| UserControllerTests.testIncreaseReputation                | FR5.3       | increaseReputation          |
| UserControllerTests.testDecreaseReputation                | FR5.3       | decreaseReputation          |
| HomeControllerTests.testLogin                             | FR2         | login                       |
