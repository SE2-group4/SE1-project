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

### **Class *User* - method *getUserId***

**Criteria for method *getUserId*:**
	
<!-- input space -->
 - id (int), as returned from the function

**Predicates for method *getUserId*:**

| Criteria | Predicate      |
| -------- | ---------      |
| id       | [min_int, 0]   |
|          | [1, max_int]   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| id       | min_int         |
|          | min_int +1      |
|          | -1              |
|          | 0               |
|          | 1               |
|          | 2               |
|          | max_int -1      |
|          | max_int         |

**Combination of predicates**:

| id            | Valid / Invalid   | Description of the test case  | JUnit test case           |
|-------|-------|-------|-------|-------|-------|
| [min_int, 0]  | invalid           | Spring works in this way...   | testGetUserId_returnId()  |
| [1, max_int]  | valid             | Spring works in this way...   | testGetUserId_returnId()  |


### **Class *User* - method *setUserId***

**Criteria for method *setUserId*:**
	
<!-- input space -->
 - id (int)

**Predicates for method *setUserId*:**

| Criteria | Predicate      |
| -------- | ---------      |
| id       | [min_int, 0]   |
|          | [1, max_int]   |

**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
| id       | min_int         |
|          | min_int +1      |
|          | -1              |
|          | 0               |
|          | 1               |
|          | 2               |
|          | max_int -1      |
|          | max_int         |

**Combination of predicates**:

| id            | Valid / Invalid   | Description of the test case  | JUnit test case           |
|-------|-------|-------|-------|-------|-------|
| [min_int, 0]  | valid             | omission in requirements      | testSetUserId()  |
| [1, max_int]  | valid             | compliant as Spring works     | testSetUserId()  |

### **Class *User* - method *getUserReputation***

**Criteria for method *getUserReputation*:**
	
<!-- input space -->
 - reputation (int), as returned from the function

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

| id            | Valid / Invalid   | Description of the test case  | JUnit test case           |
|-------|-------|-------|-------|-------|-------|
| [min_int, -6] | invalid           | as described in the glossary  | testGetUserReputation_returnReputation()  |
| [-5, 5]       | valid           | as described in the glossary  | testGetUserReputation_returnReputation()  |
| [6, max_int]  | invalid           | as described in the glossary  | testGetUserReputation_returnReputation()  |

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



