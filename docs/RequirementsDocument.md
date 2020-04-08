# Requirements Document 

Authors:

Date: 08/04/2020

Version: 1

# Contents

- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
	
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	+ [Functional Requirements](#functional-requirements)
	+ [Non functional requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	+ [Use case diagram](#use-case-diagram)
	+ [Use cases](#use-cases)
    	+ [Relevant scenarios](#relevant-scenarios)
- [Glossary](#glossary)
- [System design](#system-design)
- [Deployment diagram](#deployment-diagram)


# Stakeholders

| Stakeholder name  | Description | 
| ----------------- |:-----------:|
| Administrator		|Manages profiles on the app, and enables people to modify prices in case of errors | 
| Map API			|Third party server used to implement the map on the application so that people can locate gas stations easily | 
| User				|Use the application directly. They are interested in finding gas station and share the new ones they find in the platform | 

# Context Diagram and interfaces

## Context Diagram

```plantuml
@startuml

left to right direction
actor "Administrator" as a
actor "Map API" as c
actor "Vehicle Owner/Driver" as d
a -- (EZGas) 
(EZGas) -- c
(EZGas) -- d

@enduml
```

## Interfaces

| Actor | Logical Interface | Physical Interface |
| ------------- |:-------------:| -----:|
| Administrator	| GUI			| Screen, keyboard, smartphone |
| User			| GUI			|Screen, keyboard, smartphone|
| Maps API		|Web Services API |Internet Connection|

# Stories and personas

Jack is a father of 2, each morning drives the kids to school and then goes to work. Every week on Saturday goes to the gas station to fill the tank, but he notices that his usual gas station has increased the price. Since the situation at work is not the best he tries to find a cheaper station so he goes on the app EZGas, creates a profile, and search for the best price station in the area of his work place. 

Alice is a bank officer, a quite wealthy one. She usually does the same route to work every morning, but today her usual gas station has close due to repairs. She wants to find the closest station to fill her tank, regardless of the price. To do so uses the app EZGas. After that she goes to work happy because she saved a lot of time and came into work before her shift.

Maja is a police officer and is very diligent towards precision and correctness in documents. She usually drives to the local gas station to fill her car, but looking at the app that uses she notices that the prices are not correct. Following her usual thoughts in this cases, she reports that the prices are incorrect and after the enabling of the operation from the administrator of the platform she modifies and correct the prices.

Robert is the administrator of the platform. Every day at the office he must check whether the users of the platform perform in conformance to the requirements that the developer has set. This means that prices inserted are inside a specific range, must check wheather someone tries to access the platform without an active account and must enable people that report an error in prices.

Jackson is the owner of a gas station. He is struggling in the last period since he opened a new station and doesn't have a lot of clients since people don't know where it is. One of his friends uses frequently the app EZGas and tells it to Jackson to help him with his job. Jackson, after creating an account, inserts his new gas station onto the application map and inserts all his fuel prices. He notices that his price was lower than the ones in the area and in the next few days a lot of clients go to him to fill their tanks.

# Functional and non functional requirements

## Functional Requirements

| ID        | Description  |
| ---------	|:-------------:|
| FR1		| Record the prices of fuel into a specific gas station |  
| FR2		| Look for cheapest gas station in a certain area |
| FR3		| Ability to report issues |
| FR4		| Manage Accounts |
| FR5		| Look for closest gas station in the area |
| FR6 		| Let user up vote or down vote the fuel price |
| FR7		| Track user with position | 
| FR8		| Manage insertion and position of gas stations |

## Non Functional Requirements

| ID        | Type (efficiency, reliability, ..)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
| NFR1		| Usability | Application should be used without any training | All FR |
| NFR2		| Performance | All functions should complete in < 1 sec  | All FR |
<!-- TODO: fix NFR3, more specific browsers, also for mobile -->
| NFR3		| Portability | The application runs on Browser from Windows 7 on | All FR |
| NFR4		| Localisation | Decimal numbers use . (dot) as decimal separator | All FR|

# Use case diagram and use cases

## Use case diagram


```plantuml
@startuml
left to right direction

Actor User as U
Actor Authenticated_user as AU
Actor Administrator as A
Actor Web_API as WA

U <|-- AU
rectangle EZGas{
  (search gas stations) --> (show map and gas stations)
  AU --> (FR6 vote a gas station)
  U --> (insert search params)
  AU --> (insert new gas station)
  AU --> (enter new price)
  (enter new price) <-- WA
  (insert new gas station) --> (FR8 accept new gas station request)
  AU --> (FR3 notify an issue)
  (insert search params) --> (search gas stations)
  A --> (FR4 manage accounts)
  A --> (FR8 accept new gas station request)
  A <-- (FR7 send report)
  WA --> (FR7 send report)
  (show map and gas stations) --> WA
}

@enduml
```

<!-- TODO: remove this model -->
### Use case 1, UC1
| Actors Involved        |  |
| ------------- |:-------------:| 
|  Precondition     | \<Boolean expression, must evaluate to true before the UC can start> |  
|  Post condition     | \<Boolean expression, must evaluate to true after UC is finished> |
|  Nominal Scenario     | \<Textual description of actions executed by the UC> |
|  Variants     | \<other executions, ex in case of errors> |
<!-- TODO: ends here -->

<!-- TODO: are use cases title okay? They are different  from the model above -->
### Use case 1, UC1 - FR1  Record price of fuel

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | Active account, Log in operation, Knowledge of prices |  
|  Post condition     | Prices inserted, Log out |
|  Nominal Scenario     | User logs into the app with his/her account. Inserts a new station inside the map and all its fuel prices|
|  Variants     | Gas station is already fresent but missing some prices, so the user can insert them |

### Use case 2, UC2 - FR2 Look for cheapest station

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | Active account, Log in operation |  
|  Post condition     |  Best priced gas station found, Log out |
|  Nominal Scenario     | User logs into the app with his/her account. Looks for the best priced gas station in the area and finds the right one|
|  Variants     |  |

### Use case 3, UC3 - FR3 Report a error in the prices

| Actors Involved        | User, Administrator |
| ------------- |:-------------:| 
|  Precondition     | Active account, Log in operation, Knowledge of prices |  
|  Post condition     | Prices modified, Log out  |
|  Nominal Scenario     | User reports that a gas station has the wrong prices. The administrator enables the user account to modify the prices and after this operation the prices will be correct|
|  Variants     |  |

### Use case 4, UC4 - FR4.1 Log in

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | Active account|  
|  Post condition     | Log out |
|  Nominal Scenario     | The person inserts the credential: username and password and access the platform|
|  Variants     | The person has problem accessing so it contacts the administrator |

### Use case 5, FR4.2 Log out

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | Active account, Log in|  
|  Post condition     | Not access to the platform|
|  Nominal Scenario     | The person logs out from the app when the operation he/she needed to do is finished|
|  Variants     | |

### Use case 6, FR4.3 Create Account

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | Defining what the credentials will be|  
|  Post condition     | Being able to perform operation on the application|
|  Nominal Scenario     | The person creates a new account by registering his/hers credentials on the platform and after logs in|
|  Variants     | |

### Use case 7, FR6 Find cheapest gas station

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | Active account, Log in operation |  
|  Post condition     |  Log out |
|  Nominal Scenario     | User logs into the app with his/her account. Looks for the cheapest gas station in the area and finds the righ

##### Scenario 1.1
<!-- TODO: set all scenarios with this title format -->

<!-- TODO: remove this comments -->
\<describe here scenarios instances of UC1>

\<a scenario is a sequence of steps that corresponds to a particular execution of one use case>

\<a scenario is a more formal description of a story>

\<only relevant scenarios should be described>
<!-- TODO: ends here -->

## Scenario 1

| Scenario ID: SC1        | Corresponds to UC1  |
| ------------- |:-------------| 
| Description | Record prices of fuel|
| Precondition | The application contains a certain amount of gas station and prices connected to them|
| Postcondition | A new gas station and its prices are inserted |
| Step#        |  Step description   |
|  1     | User logs into the account| 
|  2	 | User inserts a new gas station iinto the application |  
|  3     | User fills out the prices of fuels related to that station|
|  4     | User logs out |

## Scenario 2

| Scenario ID: SC2        | Corresponds to UC2  |
| ------------- |:-------------| 
| Description | User has to search for the best prices in the area|
|Precondition | User must have an active account and must be inside|
|Postcondition | User has found the cheapestgas station for a specific fuel |
| Step#        | Step description  |
|  1     | User looks for a certain area in the map of the application |  
|  2     | User can see all the prices of the fuel he/she is looking for |
|  3     | User is able to select the best price  |
|  4     | User logs out |

## Scenario 3

| Scenario ID: SC3        | Corresponds to UC3  |
| ------------- |:-------------| 
| Description | Report of error in prices of fuel|
| Precondition | User must have knowledge of the prices of the gas station he's at. Must have an active account|
| Postcondition | User has changed the price of a gas station |
| Step#        |  Step description   |
|  1     | User is at a gas station whose prices don't match the ones in the application |  
|  2     |  User log into the application with his/her credential |
|  3     | Reports to the administrator of the app an error in the prices of fuel at a gas station |
| 4 | Administrator enables the user to change the prices|
| 5 | User changes the prices into the correct ones|
| 6 | User log out|

## Scenario 4

| Scenario ID: SC4        | Corresponds to UC7  |
| ------------- |:-------------| 
| Description | User has to search for the closest gas station in the area|
|Precondition | User must have an active account and must be inside|
|Postcondition | User has found the closest gas station for a specific fuel |
| Step#        | Step description  |
|  1     | User looks for a certain area in the map of the application |  
|  2     | User performs a search based on proximity |
|  3     | User is able to select the closest one since he can see the kilometers from his position  |
|  4     | User logs out |

| Scenario 1.1 | |
| ------------- |:-------------:| 
|  Precondition     | \<Boolean expression, must evaluate to true before the scenario can start> |
|  Post condition     | \<Boolean expression, must evaluate to true after scenario is finished> |
| Step#        | Description  |
|  1     |  |  
|  2     |  |
|  ...     |  |

##### Scenario 1.2

### Use case 2, UC2
..

### Use case
..



# Glossary

<!-- TODO: remove these comments -->
\<use UML class diagram to define important concepts in the domain of the system, and their relationships> 

\<concepts are used consistently all over the document, ex in use cases, requirements etc>
<!-- TODO: ends here -->


# System Design
\<describe here system design>

\<must be consistent with Context diagram>

# Deployment Diagram 

\<describe here deployment diagram >
