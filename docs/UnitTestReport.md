# Unit Testing Documentation

Authors: Group 12

Date: 19/05/2020

Version: 1.0

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)




- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

<!--
    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and (traceability) the correspondence with the JUnit test case writing the 
    class and method name that contains the test case>
    <JUnit test classes must be in src/test/java/it/polito/ezgas   You find here, and you can use,  class EZGasApplicationTests.java that is executed before 
    the set up of all Spring components
    >
    -->

<!-- START doc structure change -->

### **Class *Utility* - method *checkCoordinates***
**Criteria for method *checkCoordinates*:**
	
<!-- input space -->
- lat (double)
- lon (double)

**Predicates for method *checkCoordinates*:**

| Criteria  | Predicate         |
| --------- | ------------------|
| lat       | [min_double, -90) |
|           | [-90, 90]         |
|           | (90, max_double]  |
| lon       | [min_double, -180)|
|           | [-180, 180]       |
|           | (180, max_double] |

**Boundaries**:

| Criteria  | Boundary values |
| --------- | --------------- |
| lat       | min_double      |
|           | min_double+0.001|
|           | -90.001         |
|           | -90             |
|           | -89.999         |
|           | 89.999          |
|           | 90              |
|           | 90.001          |
|           | max_double-0-001|
|           | max_double      |
| lon       | min_double      |
|           | min_double+0.001|
|           | -180.001        |
|           | -180            |
|           | -179.999        |
|           | 180             |
|           | 179.999         |
|           | 180.001         |
|           | max_double-0-001|
|           | max_double      |

**Combination of predicates**:

| lat | lon | Valid / Invalid   | Description of the test case  | JUnit test case    |
|-------|-------|-------|-------|-------|-------| ------- |
| [min_double, -90) | [min_double, -180) | Invalid | checkCoordinates(-90.1, -180.1) -> return false | CheckCoordinates.latUnderMinus90_returnFalse() |
| [min_double, -90) | [-180, 180] | Invalid | checkCoordinates(-90.01, 55) -> return false | CheckCoordinates.latUnderMinus90_returnFalse() |
| [min_double, -90) | (180, max_double] | Invalid | checkCoordinates(-90.001, 180.1) -> return false | CheckCoordinates.latUnderMinus90_returnFalse() |
| [-90, 90]         | [min_double, -180)| Invalid | checkCoordinates( 0, -180.001) -> return false |CheckCoordinates.latValidLonUnderMinus180_returnFalse() |
| [-90, 90]         | [180, 180]        | Valid   | checkCoordinates( 50.2, 41) -> return true | CheckCoordinates.latValidLonValid_returnTrue() |
| [-90, 90]         | (180, max_double] | Invalid | checkCoordinates( 0, 180.001) -> return false | CheckCoordinates.latValidLonOver180_returnFalse() |
| (90, max_double]  | [min_double, -180) | Invalid | checkCoordinates( 90.1, -180.01) -> return false | CheckCoordinates.latOver90_returnFalse() |
| (90, max_double]  | [-180, 180] | Invalid | checkCoordinates( 90.01, 0) -> return false | CheckCoordinates.latOver90_returnFalse() |
| (90, max_double]  | (180, max_double] | Invalid | checkCoordinates( 90.001, 180.01) -> return false | CheckCoordinates.latOver90_returnFalse() |

### **Class *Utility* - method *calculateDistanceInMeters***



**Criteria for method *name*:**
	
- lat1 (double)
- lon1 (double)
- lat2 (double)
- lon2 (double)

**Predicates for method *name*:**

| Criteria  | Predicate         |
| --------- | ------------------|
| lat1, lat2| [min_double, -90) |
|           | [-90, 90]         |
|           | (90, max_double]  |
| lon1, lon2| [min_double, -180)|
|           | [-180, 180]       |
|           | (180, max_double] |

**Boundaries**:

| Criteria  | Boundary values |
| --------  | --------------- |
| lat1, lat2| min_double      |
|           | min_double+0.001|
|           | -90.001         |
|           | -90             |
|           | -89.999         |
|           | 89.999          |
|           | 90              |
|           | 90.001          |
|           | max_double-0-001|
|           | max_double      |
| lon1, lon2| min_double      |
|           | min_double+0.001|
|           | -180.001        |
|           | -180            |
|           | -179.999        |
|           | 180             |
|           | 179.999         |
|           | 180.001         |
|           | max_double-0-001|
|           | max_double      |


**Combination of predicates**:


| lat1 | lon1 | lat2 | lon2 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|-|-|
| [min_double, -90) |/|/|/| Invalid | calculateDistanceInMeters(...) -> return -1 |lat1Invalid_returnMinus1()|
| (90, max_double] |/|/|/| Invalid | calculateDistanceInMeters(...) -> return -1 |lat1Invalid_returnMinus1()|
|/|[min_double, -180)|/|/| Invalid | calculateDistanceInMeters(...) -> return -1 |lon1Invalid_returnMinus1()|
|/|(180, max_double]|/|/| Invalid | calculateDistanceInMeters(...) -> return -1 |lon1Invalid_returnMinus1()|
|/|/| [min_double, -90)|/| Invalid | calculateDistanceInMeters(...) -> return -1 |lat2Invalid_returnMinus1()|
|/|/| (90, max_double] |/| Invalid | calculateDistanceInMeters(...) -> return -1 |lat2Invalid_returnMinus1()|
|/|/|/|[min_double, -180)| Invalid | calculateDistanceInMeters(...) -> return -1 |lon2Invalid_returnMinus1()|
|/|/|/|(180, max_double]| Invalid | calculateDistanceInMeters(...) -> return -1 |lon2Invalid_returnMinus1()|
|[-90, 90]|[-180, 180]|[-90, 90]|[-180, 180]|Valid| calculateDistanceInMeters(...) -> return value |correctCoordinates_returnDistance() |

### **Class *Utility* - method *trustCalculation***

**Criteria for method *trustCalculation*:**

- userRep (int)
- timestamp (String)
- trust (double)

**Predicates for method *trustCalculation*:**

| Criteria  | Predicate         |
| --------- | ------------------|
| userRep   | [min_int, -6]     |
|           | [-5, 5]           |
|           | [6, max_int       |
| timestamp | incorrect         |
|           | (-inf, today - 7 days)    |
|           | [today - 7 days, today]   |
|           | (today, inf)              |
| trust     | [min_double, -0.0001]     |
|           | [0, 100]                  |
|           | [100.0001, max_double]    |

**Boundaries**:

| Criteria  | Boundary values |
| --------  | --------------- |
| userRep   | min_int         |
|           | min_int +1      |
|           | -6              |
|           | -5              |
|           | 5               |
|           | 6               |
|           | max_int -1      |
|           | max_int         |
| timestamp | null            |
|           | today           |
|           | today + 7 days  |
| trust     | min_double            |
|           | min_double +0.0001    |
|           | -0.0001               |
|           | 0                     |
|           | 0.0001                |
|           | 99.9999               |
|           | 100                   |
|           | 100.0001              |
|           | max_double -0.0001    |
|           | max_double            |

**Combination of predicates**:

| userRep | timestamp | trust | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| [min_int, -6] | - | - | invalid | returned trust = 0 | invalidUserRep_ShouldReturn0() |
| [6, max_int]  | - | - | invalid | returned trust = 0 | invalidUserRep_ShouldReturn0() |
| [-5, 5]       | incorrect | - | invalid | returned trust = 0 | invalidTimestamp_ShouldReturn0() |
| [-5, 5]       | (-inf, today - 7 days)  | = 50 * (userRep +5)/10                      | valid   | as defined in the glossary | timestampLessThen7DaysAgo_ShouldReturnMoreThan0() |
| [-5, 5]       | (-inf, today - 7 days)  | != 50 * (userRep +5)/10                     | invalid |                            | timestampLessThen7DaysAgo_ShouldReturnMoreThan0() |
| [-5, 5]       | [today - 7 days, today] | = 50 * (userRep +5)/10 + 50 * (1 - (today - timestamp)/7))  | valid   | as defined in the glossary |  timestampLessEqualThanTodayMoreEqualThen7DaysAgo_ShouldReturnMoreThan0() |
| [-5, 5]       | [today - 7 days, today | != 50 * (userRep +5)/10 + 50 * (1 - (today - timestamp)/7)) | invalid |                            | timestampMoreThenToday_ShouldReturn0() |
| [-5, 5]       | (today, inf) | - | invalid | returned trust = 0 | timestampMoreThenToday_ShouldReturn0() |

## **Class *User* - all tests**

### Formal and pratical approach

In this documents all setters and getters tests have been described in a formal way.
In the JUnit test, they have been tested in couples. At the begin, all values are setted through the class constructor (it is checked by Java compiler / runtime environment). Then, all getters are tested. Finally, all setters are tested and checked with the linked getter.
Using this approach, with getter tests both getter method and class constructor are checked.

In some cases (eg. userName string format, email string format...) no specific description have been found in requirements, so the test description test is "omission in requirements". For those cases, no JUnit test assert is coded.

<!-- END doc structure change -->

### **Class *User* - method *getUserId***

**Criteria for method *getUserId*:**
	
<!-- input space -->
 - userId (Integer), as returned from the method

**Predicates for method *getUserId*:**

| Criteria | Predicate      |
| -------- | ---------      |
| userId   | [min_int, 0]   |
|          | [1, max_int]   |
|          | null           |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| userId   | min_int         |
|          | min_int +1      |
|          | -1              |
|          | 0               |
|          | 1               |
|          | 2               |
|          | max_int -1      |
|          | max_int         |
|          | null            |

**Combination of predicates**:

| userId        | Valid / Invalid   | Description of the test case  | JUnit test case                       |
|-------|-------|-------|-------|-------|-------|
| [min_int, 0]  | invalid           | Spring works in this way      | testGetUserId_ShouldReturnUserId()    |
| [1, max_int]  | valid             | Spring works in this way      | testGetUserId_ShouldReturnUserId()    |
| null          | invalid           |                               | testGetUserId_ShouldReturnUserId()    |

### **Class *User* - method *setUserId***

**Criteria for method *setUserId*:**
	
<!-- input space -->
 - userId (Integer)

**Predicates for method *setUserId*:**

| Criteria | Predicate      |
| -------- | ---------      |
| userId   | [min_int, 0]   |
|          | [1, max_int]   |
|          | null           |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| userId   | min_int         |
|          | min_int +1      |
|          | -1              |
|          | 0               |
|          | 1               |
|          | 2               |
|          | max_int -1      |
|          | max_int         |
|          | null            |

**Combination of predicates**:

| userId        | Valid / Invalid   | Description of the test case  | JUnit test case                   |
|-------|-------|-------|-------|-------|-------|
| [min_int, 0]  | valid             | omission in requirements      | testSetUserId_ShouldSetUserId()   |
| [1, max_int]  | valid             | compliant as Spring works     | testSetUserId_ShouldSetUserId()   |
| null          | invvalid          |                               | testSetUserId_ShouldSetUserId()   |

### **Class *User* - method *getUserName***

**Criteria for method *getUserName*:**

- userName (String), as returned from the method

**Predicates for method *getUserName*:**

| Criteria | Predicate          |
| -------- | ---------          |
| userName | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | any other string   |

**Boundaries**:

| Criteria | Boundary values    |
| -------- | ---------------    |
| userName | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | any other string   |

**Combination of predicates**:

| userName           | Valid / Invalid  | Description of the test case  | JUnit test case                           |
|-------|-------|-------|-------|-------|-------|
| "" (empty string)  | invalid          | omission in requirements      | testGetUserName_ShouldReturnUserName()    |
| null               | invalid          |                               | testGetUserName_ShouldReturnUserName()    |
| non trimmed string | invalid          | omission in requirements      | testGetUserName_ShouldReturnUserName()    |
| any other string   | valid            |                               | testGetUserName_ShouldReturnUserName()    |

### **Class *User* - method *setUserName***

**Criteria for method *setUserName*:**

- userName (String)

**Predicates for method *setUserName*:**

| Criteria | Predicate          |
| -------- | ---------          |
| userName | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | any other string   |

**Boundaries**:

| Criteria | Boundary values    |
| -------- | ---------------    |
| userName | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | any other string   |

**Combination of predicates**:

| userName           | Valid / Invalid  | Description of the test case  | JUnit test case                           |
|-------|-------|-------|-------|-------|-------|
| "" (empty string)  | invalid          | omission in requirements      | testSetUserName_ShouldSetUserName()       |
| null               | invalid          |                               | testSetUserName_ShouldSetUserName()       |
| non trimmed string | invalid          | omission in requirements. userName should be trimmed, as no exception can be thrown | testSetUserName_ShouldSetUserName()       |
| any other string   | valid            |                               | testSetUserName_ShouldSetUserName()       |

### **Class *User* - method *getPassword***

**Criteria for method *getPassword*:**

- password (String), as returned from the method

**Predicates for method *getPassword*:**

| Criteria | Predicate          |
| -------- | ---------          |
| userName | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | any other string   |

**Boundaries**:

| Criteria | Boundary values    |
| -------- | ---------------    |
| userName | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | any other string   |

**Combination of predicates**:

| password           | Valid / Invalid  | Description of the test case  | JUnit test case                           |
|-------|-------|-------|-------|-------|-------|
| "" (empty string)  | invalid          | omission in requirements      | testGetPassword_ShouldReturnPassword()    |
| null               | invalid          |                               | testGetPassword_ShouldReturnPassword()    |
| non trimmed string | invalid          | omission in requirements      | testGetPassword_ShouldReturnPassword()    |
| any other string   | valid            |                               | testGetPassword_ShouldReturnPassword()    |

### **Class *User* - method *setPassword***

**Criteria for method *setPassword*:**

- password (String)

**Predicates for method *setPassword*:**

| Criteria | Predicate          |
| -------- | ---------          |
| userName | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | any other string   |

**Boundaries**:

| Criteria | Boundary values    |
| -------- | ---------------    |
| userName | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | any other string   |

**Combination of predicates**:

| password           | Valid / Invalid  | Description of the test case  | JUnit test case                           |
|-------|-------|-------|-------|-------|-------|
| "" (empty string)  | invalid          | omission in requirements      | testSetPassword_ShouldSetPassword()       |
| null               | invalid          |                               | testSetPassword_ShouldSetPassword()       |
| non trimmed string | invalid          | omission in requirements      | testSetPassword_ShouldSetPassword()       |
| any other string   | valid            |                               | testSetPassword_ShouldSetPassword()       |

### **Class *User* - method *getEmail***

**Criteria for method *getEmail*:**

- email (String), as returned from the method

**Predicates for method *getEmail*:**

| Criteria | Predicate          |
| -------- | ---------          |
| email    | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | no email format    |
|          | any other string   |

**Boundaries**:

| Criteria | Boundary values    |
| -------- | ---------------    |
| userName | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | no email format    |
|          | any other string   |

**Combination of predicates**:

| password           | Valid / Invalid  | Description of the test case  | JUnit test case                   |
|-------|-------|-------|-------|-------|-------|
| "" (empty string)  | invalid          | omission in requirements      | testGetEmail_ShouldReturnEmail()  |
| null               | invalid          |                               | testGetEmail_ShouldReturnEmail()  |
| non trimmed string | invalid          | omission in requirements      | testGetEmail_ShouldReturnEmail()  |
| non email format   | invalid          | omission in requirements      | testGetEmail_ShouldReturnEmail()  |
| any other string   | valid            |                               | testGetEmail_ShouldReturnEmail()  |

### **Class *User* - method *setEmail***

**Criteria for method *setEmail*:**

- email (String)

**Predicates for method *setEmail*:**

| Criteria | Predicate          |
| -------- | ---------          |
| email    | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | no email format    |
|          | any other string   |

**Boundaries**:

| Criteria | Boundary values    |
| -------- | ---------------    |
| userName | "" (empty string)  |
|          | null               |
|          | non trimmed string |
|          | no email format    |
|          | any other string   |

**Combination of predicates**:

| password           | Valid / Invalid  | Description of the test case  | JUnit test case                   |
|-------|-------|-------|-------|-------|-------|
| "" (empty string)  | invalid          | omission in requirements      | testSetEmail_ShouldSetEmail()     |
| null               | invalid          |                               | testSetEmail_ShouldSetEmail()     |
| non trimmed string | invalid          | omission in requirements      | testSetEmail_ShouldSetEmail()     |
| non email format   | invalid          | omission in requirements      | testSetEmail_ShouldSetEmail()     |
| any other string   | valid            |                               | testSetEmail_ShouldSetEmail()     |

### **Class *User* - method *getUserReputation***

**Criteria for method *getUserReputation*:**
	
<!-- input space -->
 - reputation (Integer), as returned from the method

**Predicates for method *getUserReputation*:**

| Criteria      | Predicate     |
| --------      | ---------     |
| reputation    | [min_int, -6] |
|               | [-5, 5]       |
|               | [6, max_int]  |
|               | null          |


**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| id       | min_int         |
|          | min_int +1      |
|          | -6              |
|          | -5              |
|          | 5               |
|          | 6               |
|          | max_int -1      |
|          | max_int         |
|          | null            |

**Combination of predicates**:

| id            | Valid / Invalid   | Description of the test case  | JUnit test case                                   |
|-------|-------|-------|-------|-------|-------|
| [min_int, -6] | invalid           | as described in the glossary  | testGetUserReputation_ShouldReturnReputation()    |
| [-5, 5]       | valid             | as described in the glossary  | testGetUserReputation_ShouldReturnReputation()    |
| [6, max_int]  | invalid           | as described in the glossary  | testGetUserReputation_ShouldReturnReputation()    |
| null          | invalid           |                               | testGetUserReputation_ShouldReturnReputation()    |

### **Class *User* - method *setUserReputation***

**Criteria for method *setUserReputation*:**
	
<!-- input space -->
 - reputation (Integer)

**Predicates for method *setUserReputation*:**

| Criteria      | Predicate     |
| --------      | ---------     |
| reputation    | [min_int, -6] |
|               | [-5, 5]       |
|               | [6, max_int]  |
|               | null          |
|               | null          |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| id       | min_int         |
|          | min_int +1      |
|          | -6              |
|          | -5              |
|          | 5               |
|          | 6               |
|          | max_int -1      |
|          | max_int         |
|          | null            |

**Combination of predicates**:

| id            | Valid / Invalid   | Description of the test case  | JUnit test case                                           |
|-------|-------|-------|-------|-------|-------|
| [min_int, -6] | invalid           | reputation should be rounded up to the min accepted value, as no exception can be thrown  | testGetUserReputation_ShouldSetReputation()    |
| [-5, 5]       | valid             |                                                                                           | testGetUserReputation_ShouldSetReputation()       |
| [6, max_int]  | invalid           | reputation should be rounded off to the max accepted value, as no exception can be thrown | testGetUserReputation_ShouldSetReputation()    |
| null          | invalid           |                                                                                           | testGetUserReputation_ShouldSetReputation()    |

### **Class *User* - method *getAdmin***

**Criteria for method *getAdmin*:**

- admin (Boolean), as returned from the method

**Predicates for method *getAdmin*:**

| Criteria | Predicate |
| -------- | --------- |
| admin    | true      |
|          | false     |
|          | null      |

**Boundaries**:

No boundaries.

**Combination of predicates**:

| admin | Valid / Invalid   | Description of the test case  | JUnit test case                   |
|-------|-------|-------|-------|-------|-------|
| true  | valid             |                               | testGetAdmin_ShouldReturnAdmin()  |
| false | valid             |                               | testGetAdmin_ShouldReturnAdmin()  |
| null  | invalid             |                             | testGetAdmin_ShouldReturnAdmin()  |

### **Class *User* - method *setAdmin***

**Criteria for method *setAdmin*:**

- admin (Boolean)

**Predicates for method *setAdmin*:**

| Criteria | Predicate |
| -------- | --------- |
| admin    | true      |
|          | false     |
|          | null      |

**Boundaries**:

No boundaries.

**Combination of predicates**:

| admin | Valid / Invalid   | Description of the test case  | JUnit test case                   |
|-------|-------|-------|-------|-------|-------|
| true  | valid             |                               | testSetAdmin_ShouldSetAdmin()     |
| false | valid             |                               | testSetAdmin_ShouldSetAdmin()     |
| null  | invalid           |                               | testSetAdmin_ShouldSetAdmin()     |


## **Class *GasStation***

Testing getter, setter of the class gasStation.
Similar attributes, such as prices and boolean, have been grouped and treated the same way.

### **Class *GasStation* - Getters for integer ids**

**Method list:**
- getGasStationId
- getReportUser

**Criteria:**
	
<!-- input space -->
 - return value (int), as returned from the method

**Predicates:**

| Criteria      | Predicate      |
| ------------- | ---------      |
| return value  | [min_int, 0]   |
|               | [1, max_int]   |

**Boundaries**:

| Criteria      | Boundary values |
| ------------- | --------------- |
| return value  | min_int         |
|               | min_int +1      |
|               | -1              |
|               | 0               |
|               | 1               |
|               | 2               |
|               | max_int -1      |
|               | max_int         |

**Combination of predicates**:

| return value  | Valid / Invalid   | Description of the test case  | JUnit test case                       |
|-------|-------|-------|-------|-------|-------|
| [min_int, 0]  | invalid           | setter() -> return id (id should not be <= 0)   | getGasStationId__returnGasStationId(), getReportUSer__returnReportUSer()    |
| [1, max_int]  | valid             | setter() -> return id   | getGasStationId__returnGasStationId(), getReportUSer__returnReportUSer()    |

### **Class *GasStation* - Setters for integer ids**
	
**Method list:**
- setGasStationId
- setReportUser

**Criteria:**

<!-- input space -->
 - input (int)

**Predicates:**

| Criteria      | Predicate      |
| ------------- | ---------      |
| input         | [min_int, 0]   |
|               | [1, max_int]   |

**Boundaries**:

| Criteria      | Boundary values |
| ------------- | --------------- |
| input         | min_int         |
|               | min_int +1      |
|               | -1              |
|               | 0               |
|               | 1               |
|               | 2               |
|               | max_int -1      |
|               | max_int         |

**Combination of predicates**:

| input  | Valid / Invalid   | Description of the test case  | JUnit test case                   |
|-------|-------|-------|-------|-------|-------|
| [min_int, 0]  | invalid             | setter() -> modify id (id should not be <= 0>)      | setGasStationId__modifyGasStationId(), setReportUSer__modifyReportUSer()   |
| [1, max_int]  | valid             | setter() -> modify id     | setGasStationId__modifyGasStationId(), getReportUSer__modifyReportUSer()   |

### **Class *GasStation* - Getters for prices**

**Method list:**
- getDieselPrice
- getSuperPrice
- getSuperPlusPrice
- getGasPrice
- getMethanePrice

**Criteria:**
	
<!-- input space -->
 - price (double), as returned from the method

**Predicates:**

| Criteria  | Predicate      |
| --------- | ---------      |
| price     | [min_double, 0]|
|           | [0.001, max_double] |

**Boundaries**:

| Criteria  | Boundary values |
| --------- | --------------- |
| price     | min_double      |
|           | min_double+0.001|
|           | -0.001          |
|           | 0               |
|           | 0.001           |
|           | max_double-0-001|
|           | max_double      |

**Combination of predicates**:

| price    | Valid / Invalid   | Description of the test case  | JUnit test case                       |
|-------|-------|-------|-------|-------|-------|
| [min_double, 0]  | invalid           | getter()->return price (price should not be <= 0)  | getDieselPrice__returDieselPrice(), getSuperPrice__returnSuperPrice(), getSuperPlusPrice__returnSuperPlusPrice(), getGasPrice__returnSuperPlusPrice(), getMethanePrice__returnMethanePrice() |
| [0.001, max_double]  | valid             | getter()->return price   | getDieselPrice__returDieselPrice(), getSuperPrice__returnSuperPrice(), getSuperPlusPrice__returnSuperPlusPrice(), getGasPrice__returnSuperPlusPrice(), getMethanePrice__returnMethanePrice()    |

### **Class *GasStation* - Setters for prices**

**Method list:**
- setDieselPrice
- setSuperPrice
- setSuperPlusPrice
- setGasPrice
- setMethanePrice

**Criteria:**
	
<!-- input space -->
 - price (double)

**Predicates:**

| Criteria  | Predicate      |
| --------- | ---------      |
| price     | [min_double, 0]|
|           | [0.001, max_double] |

**Boundaries**:

| Criteria  | Boundary values |
| --------- | --------------- |
| price     | min_double      |
|           | min_double+0.001|
|           | -0.001          |
|           | 0               |
|           | 0.001           |
|           | max_double-0-001|
|           | max_double      |

**Combination of predicates**:

| price    | Valid / Invalid   | Description of the test case  | JUnit test case                       |
|-------|-------|-------|-------|-------|-------|
| [min_double, 0]  | invalid | setter()->modify price (price should not be <= 0)   | setDieselPrice__modifyDieselPrice(), setSuperPrice__modifySuperPrice(), setSuperPlusPrice__modifySuperPlusPrice(), setGasPrice__modifySuperPlusPrice(), setMethanePrice__modifyMethanePrice()  |
| [0.001, max_double]  | valid             | setter()->modify price   | setDieselPrice__modifyDieselPrice(), setSuperPrice__modifySuperPrice(), setSuperPlusPrice__modifySuperPlusPrice(), setGasPrice__modifySuperPlusPrice(), setMethanePrice__modifyMethanePrice()  |

### **Class *GasStation* - getLat **

**Criteria:**
	
<!-- input space -->
 - latitude (double), as returned from the method

**Predicates:**

| Criteria  | Predicate         |
| --------- | ------------------|
| latitude  | [min_double, -90) |
|           | [-90, 90]         |
|           | (90, max_double]  |

**Boundaries**:

| Criteria  | Boundary values |
| --------- | --------------- |
| price     | min_double      |
|           | min_double+0.001|
|           | -90.001         |
|           | -90             |
|           | -89.999         |
|           | 89.999          |
|           | 90              |
|           | 90.001          |
|           | max_double-0-001|
|           | max_double      |

**Combination of predicates**:

| price    | Valid / Invalid   | Description of the test case  | JUnit test case                       |
|-------|-------|-------|-------|-------|-------|
| [min_double, -90)  | invalid           | getter()->return latitude (latitude should not be < -90)  | getLat__returnLat() |
| [-90, 90]  | valid             | getter()->return latitude   | getLat__returnLat |
| (90, max_double)  | invalid           | getter()->return latitude (latitude should not be > 90]  | getLat__returnLat() |

### **Class *GasStation* - setLat**

**Criteria:**
	
<!-- input space -->
 - latitude (double)

**Predicates:**

| Criteria  | Predicate         |
| --------- | ------------------|
| latitude  | [min_double, -90) |
|           | [-90, 90]         |
|           | (90, max_double]  |

**Boundaries**:

| Criteria  | Boundary values |
| --------- | --------------- |
| latitude  | min_double      |
|           | min_double+0.001|
|           | -90.001         |
|           | -90             |
|           | -89.999         |
|           | 89.999          |
|           | 90              |
|           | 90.001          |
|           | max_double-0-001|
|           | max_double      |

**Combination of predicates**:

| price    | Valid / Invalid   | Description of the test case  | JUnit test case                       |
|-------|-------|-------|-------|-------|-------|
| [min_double, -90)  | invalid           | getter()->modify latitude (latitude should not be < -90)  | setLat__modifyLat() |
| [-90, 90]  | valid             | getter()->modify latitude   | setLat__modifyLat |
| (90, max_double)  | invalid           | getter()->modify latitude (latitude should not be > 90]  | setLat__modifyLat() |

### **Class *GasStation* - getLon **

**Criteria:**
	
<!-- input space -->
 - longitude (double), as returned from the method

**Predicates:**

| Criteria  | Predicate         |
| --------- | ------------------|
| longitude | [min_double, -180)|
|           | [-180, 180]       |
|           | (180, max_double] |

**Boundaries**:

| Criteria  | Boundary values |
| --------- | --------------- |
| longitude | min_double      |
|           | min_double+0.001|
|           | -180.001        |
|           | -180            |
|           | -179.999        |
|           | 180             |
|           | 179.999         |
|           | 180.001         |
|           | max_double-0-001|
|           | max_double      |

**Combination of predicates**:

| longitude    | Valid / Invalid   | Description of the test case  | JUnit test case                       |
|-------|-------|-------|-------|-------|-------|
| [min_double, -180)  | invalid           | getter()->return longitude (longitude should not be < -180)  | getLon__returnLon() |
| [-180, 180]  | valid             | getter()->return longitude   | getLon__returnLon() |
| (180, max_double)  | invalid           | getter()->return longitude (longitude should not be > 180]  | getLon__returnLon() |

### **Class *GasStation* - setLon**

**Criteria:**
	
<!-- input space -->
 - longitude (double)

**Predicates:**

| Criteria  | Predicate         |
| --------- | ------------------|
| longitude  | [min_double, -180)|
|           | [-180, 180]       |
|           | (180, max_double] |

**Boundaries**:

| Criteria  | Boundary values |
| --------- | --------------- |
| longitude     | min_double      |
|           | min_double+0.001|
|           | -180.001        |
|           | -180            |
|           | -179.999        |
|           | 180             |
|           | 179.999         |
|           | 180.001         |
|           | max_double-0-001|
|           | max_double      |

**Combination of predicates**:

| longitude    | Valid / Invalid   | Description of the test case  | JUnit test case                       |
|-------|-------|-------|-------|-------|-------|
| [min_double, -180)  | invalid           | getter()->modify longitude (longitude should not be < -180)  | setLon__modifyLon() |
| [-180, 180]  | valid             | getter()->modify longitude   | setLon__modifyLon() |
| (180, max_double)  | invalid           | getter()->modify longitude (longitude should not be > 180]  | setLon__modifyLon() |

### **Class *GasStation* - getReportDependability **

**Criteria:**
	
<!-- input space -->
 - reportDependability (double), as returned from the method

**Predicates:**

| Criteria  | Predicate         |
| --------- | ------------------|
| reportDependability | [min_double, 0)|
|           | [0, 100]       |
|           | (180, max_double] |

**Boundaries**:

| Criteria  | Boundary values |
| --------- | --------------- |
| reportDependability | min_double      |
|           | min_double+0.001|
|           | -0.001          |
|           | 0               |
|           | -0.001          |
|           | 99.999             |
|           | 100         |
|           | 100.001         |
|           | max_double-0-001|
|           | max_double      |

**Combination of predicates**:

| reportDependability    | Valid / Invalid   | Description of the test case  | JUnit test case                       |
|-------|-------|-------|-------|-------|-------|
| [min_double, 0)  | invalid           | getter()->return reportDependability (reportDependability should not be < 0)  | getReportDependability__returnReportDependability() |
| [0, 100]  | valid             | getter()->return reportDependability   | getReportDependability__returnReportDependability() |
| (100, max_double)  | invalid           | getter()->return reportDependability (reportDependability should not be > 100]  | getReportDependability__returnReportDependability() |

### **Class *GasStation* - setReportDependability **

**Criteria:**
	
<!-- input space -->
 - reportDependability (double)

**Predicates:**

| Criteria  | Predicate         |
| --------- | ------------------|
| reportDependability | [min_double, 0)|
|           | [0, 100]       |
|           | (180, max_double] |

**Boundaries**:

| Criteria  | Boundary values |
| --------- | --------------- |
| reportDependability | min_double      |
|           | min_double+0.001|
|           | -0.001          |
|           | 0               |
|           | -0.001          |
|           | 99.999             |
|           | 100         |
|           | 100.001         |
|           | max_double-0-001|
|           | max_double      |

**Combination of predicates**:

| reportDependability    | Valid / Invalid   | Description of the test case  | JUnit test case                       |
|-------|-------|-------|-------|-------|-------|
| [min_double, 0)  | invalid           | getter()->modify reportDependability (reportDependability should not be < 0)  | setReportDependability__modifyReportDependability() |
| [0, 100]  | valid             | getter()->modify reportDependability   | setReportDependability__modifyReportDependability() |
| (100, max_double)  | invalid           | getter()->modify reportDependability (reportDependability should not be > 100]  | setReportDependability__modifyReportDependability() |



### **Class *GasStation* - Getters for String**

**Method list:**
- getGasStationName
- getGasStationAddress
- getCarSharing
- getReportTimestamp

**Criteria:**

- return value (String), as returned from the method

**Predicates:**

| Criteria      | Predicate          |
| ------------- | ---------          |
| return value  | "" (empty string)  |
|               | null               |
|               | any other string   |

**Boundaries**:

| Criteria      | Boundary values    |
| ------------- | ---------------    |
| return value  | "" (empty string)  |
|               | null               |
|               | any other string   |

**Combination of predicates**:

| return value           | Valid / Invalid  | Description of the test case  | JUnit test case                           |
|-------|-------|-------|-------|-------|-------|
| "" (empty string)  | invalid          | getter() -> return string (string should not be empty) | getGasStationName__returnGasStationName(), getGasStationAddress__returnGasStationAddress(), getCarSharing__returnCarSharing(), getReportTimestamp__returnReportTimestamp()    |
| null               | invalid          | getter() -> return null | getGasStationName__returnGasStationName(), getGasStationAddress__returnGasStationAddress(), getCarSharing__returnCarSharing(), getReportTimestamp__returnReportTimestamp()    |
| any other string   | valid            | getter() -> return string | getGasStationName__returnGasStationName(), getGasStationAddress__returnGasStationAddress(), getCarSharing__returnCarSharing(), getReportTimestamp__returnReportTimestamp()    |

### **Class *GasStation* - Setters for String**

**Method list:**
- settGasStationName
- setGasStationAddress
- setCarSharing
- setReportTimestamp

**Criteria:**

- input (String)

**Predicates:**

| Criteria      | Predicate          |
| ------------- | ---------          |
| input         | "" (empty string)  |
|               | null               |
|               | any other string   |

**Boundaries**:

| Criteria      | Boundary values    |
| ------------- | ---------------    |
| input         | "" (empty string)  |
|               | null               |
|               | any other string   |

**Combination of predicates**:

| userName           | Valid / Invalid  | Description of the test case  | JUnit test case                           |
|-------|-------|-------|-------|-------|-------|
| "" (empty string)  | invalid          | setter() -> modify string (string should not be empty)      | setGasStationName__modifyGasStationName(), setGasStationAddress__modifyGasStationAddress(), setCarSharing__modifyCarSharing(), setReportTimestamp__modifyReportTimestamp()       |
| null               | invalid          | setter() -> modify string (string should not be null)      | setGasStationName__modifyGasStationName(), setGasStationAddress__modifyGasStationAddress(), setCarSharing__modifyCarSharing(), setReportTimestamp__modifyReportTimestamp()       |
| any other string   | valid            | setter() -> modify string | setGasStationName__modifyGasStationName(), setGasStationAddress__modifyGasStationAddress(), setCarSharing__modifyCarSharing(), setReportTimestamp__modifyReportTimestamp()       |

### **Class *GasStation* - Getters for boolean**

**Method list:**
- getHasDiesel
- getHasSuper
- getHasSuperPlus
- getHasGas
- getHasMethane

**Criteria:**

- return value (boolean), as returned from the method

**Predicates:**

| Criteria      | Predicate |
| ------------- | --------- |
| return value  | true      |
|               | false     |
|               | other     |

**Boundaries**:

| Criteria      | Boundary values |
| ------------- | --------------- |
| return value  | true            |
|               | false           |
|               | other           |

**Combination of predicates**:


| return value | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| true  | valid     | getter() -> return boolean |getHasDiesel__returnHasDiesel(), getHasSuper__returnHasSuper(), getHasSuperPlus__returnHasSuperPlus(), getHasGas__returnHasGas(), getHasMethane__returnHasMethane()|
| false | valid     | getter() -> return boolean |getHasDiesel__returnHasDiesel(), getHasSuper__returnHasSuper(), getHasSuperPlus__returnHasSuperPlus(), getHasGas__returnHasGas(), getHasMethane__returnHasMethane()|
| other | invalid   | getter() -> exception |getHasDiesel__returnHasDiesel(), getHasSuper__returnHasSuper(), getHasSuperPlus__returnHasSuperPlus(), getHasGas__returnHasGas(), getHasMethane__returnHasMethane()|

### **Class *GasStation* - Setters for boolean**

**Method list:**
- setHasDiesel
- setHasSuper
- setHasSuperPlus
- setHasGas
- setHasMethane

**Criteria:**

- input (boolean)

**Predicates:**

| Criteria  | Predicate |
| --------- | --------- |
| input     | true      |
|           | false     |
|           | other     |

**Boundaries**:

| Criteria  | Boundary values |
| --------- | --------------- |
| input     | true            |
|           | false           |
|           | other           |

**Combination of predicates**:


| input | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| true  | valid     | setter() -> value = true |setHasDiesel__modifyHasDiesel(), setHasSuper__modifyHasSuper(), setHasSuperPlus__modifyHasSuperPlus(), setHasGas__modifyHasGas(), setHasMethane__modifyHasMethane()|
| false | valid     | setter() -> value = true |setHasDiesel__modifyHasDiesel(), setHasSuper__modifyHasSuper(), setHasSuperPlus__modifyHasSuperPlus(), setHasGas__modifyHasGas(), setHasMethane__modifyHasMethane()|
| other | invalid   ||setHasDiesel__modifyHasDiesel(), setHasSuper__modifyHasSuper(), setHasSuperPlus__modifyHasSuperPlus(), setHasGas__modifyHasGas(), setHasMethane__modifyHasMethane()|

### **Class *GasStation* - getUser**

**Criteria:**

- return value

**Predicates:**

| Criteria      | Predicate |
| ------------- | --------- |
| return value  | user      |
|               | null      |

**Boundaries**:

| Criteria      | Boundary values |
| ------------- | --------------- |
| return value  | user            |
|               | null            |

**Combination of predicates**:


| return value | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| user  | valid     | getUser() -> return user |getUser__returnUser() |
| null  | valid     | getUser() -> return null |getUser__returnUser() |

### **Class *GasStation* - setUser**

**Criteria:**

- input

**Predicates:**

| Criteria      | Predicate |
| ------------- | --------- |
| input         | user      |
|               | null      |

**Boundaries**:

| Criteria      | Boundary values |
| ------------- | --------------- |
| input         | user            |
|               | null            |

**Combination of predicates**:


| input | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| user  | valid     | setUser() -> modify user |setUser__modifyUser() |
| null  | valid     | setUser() -> modify user |setUser__modifyUser() |

<!--
### **Class *class_name* - method *name***



**Criteria for method *name*:**
	

 - 
 - 





**Predicates for method *name*:**

| Criteria | Predicate |
| -------- | --------- |
|          |           |
|          |           |
|          |           |
|          |           |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|          |                 |
|          |                 |



**Combination of predicates**:


| Criteria 1 | Criteria 2 | ... | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
|||||||
|||||||
|||||||
|||||||
|||||||
-->




# White Box Unit Tests

### Test cases definition
    
    <JUnit test classes must be in src/test/java/it/polito/ezgas>
    <Report here all the created JUnit test cases, and the units/classes under test >
    <For traceability write the class and method name that contains the test case>


| Unit name     | JUnit test case |
|--|--|
| GasStation    | GasStationTest |
| Utility       | UtilityTest |
||||

### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >



<!--
### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
|||||| -->



