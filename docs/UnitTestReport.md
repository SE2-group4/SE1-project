# Unit Testing Documentation

Authors:

Date:

Version:

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)




- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and (traceability) the correspondence with the JUnit test case writing the 
    class and method name that contains the test case>
    <JUnit test classes must be in src/test/java/it/polito/ezgas   You find here, and you can use,  class EZGasApplicationTests.java that is executed before 
    the set up of all Spring components
    >

<!-- START doc structure change -->

## **Class *User* - all tests*

# Formal and pratical approach

In this documents all setters and getters tests have been described in a formal way.
In the JUnit test, they have been tested in couples. At the begin, all values are setted through the class constructor (it is checked by Java compiler / runtime environment). Then, all getters are tested. Finally, all setters are tested and checked with the linked getter.
Using this approach, with getter tests both getter method and class constructor are checked.

In some cases (eg. userName string format, email string format...) no specific description have been found in requirements, so the test description test is "omission in requirements". For those cases, no JUnit test assert is coded.

<!-- END doc structure change -->

### **Class *User* - method *getUserId***

**Criteria for method *getUserId*:**
	
<!-- input space -->
 - userId (int), as returned from the method

**Predicates for method *getUserId*:**

| Criteria | Predicate      |
| -------- | ---------      |
| userId   | [min_int, 0]   |
|          | [1, max_int]   |

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

**Combination of predicates**:

| userId        | Valid / Invalid   | Description of the test case  | JUnit test case                       |
|-------|-------|-------|-------|-------|-------|
| [min_int, 0]  | invalid           | Spring works in this way...   | testGetUserId_ShouldReturnUserId()    |
| [1, max_int]  | valid             | Spring works in this way...   | testGetUserId_ShouldReturnUserId()    |

### **Class *User* - method *setUserId***

**Criteria for method *setUserId*:**
	
<!-- input space -->
 - userId (int)

**Predicates for method *setUserId*:**

| Criteria | Predicate      |
| -------- | ---------      |
| userId   | [min_int, 0]   |
|          | [1, max_int]   |

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

**Combination of predicates**:

| userId        | Valid / Invalid   | Description of the test case  | JUnit test case                   |
|-------|-------|-------|-------|-------|-------|
| [min_int, 0]  | valid             | omission in requirements      | testSetUserId_ShouldSetUserId()   |
| [1, max_int]  | valid             | compliant as Spring works     | testSetUserId_ShouldSetUserId()   |

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
| null               | invalid          | omission in requirements      | testGetUserName_ShouldReturnUserName()    |
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
| null               | invalid          | omission in requirements      | testSetUserName_ShouldSetUserName()       |
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
| null               | invalid          | omission in requirements      | testGetPassword_ShouldReturnPassword()    |
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
| null               | invalid          | omission in requirements      | testSetPassword_ShouldSetPassword()       |
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
| null               | invalid          | omission in requirements      | testGetEmail_ShouldReturnEmail()  |
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
| null               | invalid          | omission in requirements      | testSetEmail_ShouldSetEmail()     |
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

**Combination of predicates**:

| id            | Valid / Invalid   | Description of the test case  | JUnit test case                                   |
|-------|-------|-------|-------|-------|-------|
| [min_int, -6] | invalid           | as described in the glossary  | testGetUserReputation_ShouldReturnReputation()    |
| [-5, 5]       | valid             | as described in the glossary  | testGetUserReputation_ShouldReturnReputation()    |
| [6, max_int]  | invalid           | as described in the glossary  | testGetUserReputation_ShouldReturnReputation()    |

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

**Combination of predicates**:

| id            | Valid / Invalid   | Description of the test case  | JUnit test case                                           |
|-------|-------|-------|-------|-------|-------|
| [min_int, -6] | invalid           | reputation should be rounded up to the min accepted value, as no exception can be thrown  | testGetUserReputation_ShouldSetReputation()    |
| [-5, 5]       | valid             |                                                                                           | testGetUserReputation_ShouldSetReputation()       |
| [6, max_int]  | invalid           | reputation should be rounded off to the max accepted value, as no exception can be thrown | testGetUserReputation_ShouldSetReputation()    |

### **Class *User* - method *getAdmin***

**Criteria for method *getAdmin*:**

- admin (Boolean), as returned from the method

**Predicates for method *getAdmin*:**

| Criteria | Predicate |
| -------- | --------- |
| admin    | true      |
|          | false     |

**Boundaries**:

No boundaries.

**Combination of predicates**:

| admin | Valid / Invalid   | Description of the test case  | JUnit test case                   |
|-------|-------|-------|-------|-------|-------|
| true  | valid             |                               | testGetAdmin_ShouldReturnAdmin()  |
| false | valid             |                               | testGetAdmin_ShouldReturnAdmin()  |

### **Class *User* - method *setAdmin***

**Criteria for method *setAdmin*:**

- admin (Boolean)

**Predicates for method *setAdmin*:**

| Criteria | Predicate |
| -------- | --------- |
| admin    | true      |
|          | false     |
|          |           |
|          |           |

**Boundaries**:

No boundaries.

**Combination of predicates**:

| admin | Valid / Invalid   | Description of the test case  | JUnit test case                   |
|-------|-------|-------|-------|-------|-------|
| true  | valid             |                               | testSetAdmin_ShouldSetAdmin()     |
| false | valid             |                               | testSetAdmin_ShouldSetAdmin()     |


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


| Unit name | JUnit test case |
|--|--|
|||
|||
||||

### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >


### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||



