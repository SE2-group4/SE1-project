# Requirements Document

Authors: Group 12

Date: 30/04/2020

Version: 3

# Contents
- [Abstract](#abstract)
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
- [Deployment diagram](#deployment-diagram)

# Abstract

*Gas station* positions are very important for *drivers*, expecially for who travels a lot for his/her job. Finding a gas station can be an expensive time activity and depends on each single person preferences.

Some people would like to know which gas station has the best prices of fuel in the area to save some money, others which one is closer to save some time.

The *"EZGas" browser application* has been created for those people, to allow them to perform these searches and share with the community inside the app the prices of gas stations. Each person that finds a new gas station or notices that a gas station is missing some prices can go to the application EZGas and inserts the new data discovered.

A voting system allows users to inform which prices are correct or not by just upvoting a price and automatically  downvoting all the others connected to that fuel. The votes will be up for 24 hours and will be used to check oscillation in prices in order to have always a correct price on the app.

Only people that have created an account on the app can perform insertion of prices and vote them, but that doesn't mean that if you don't create a profile you won't be able to perform your favourite searches.
EZGas thought of you. That's why searches are opened to everyone.

# Stakeholders

| Stakeholder name  | Description |
| ----------------- |:-----------:|
| Administrator     | Accepts a user request to insert a gas station |
| Google Maps API      |Third party server used to implement the map on the application so that people can locate gas stations easily|
| User              |Uses the application directly. They are interested in finding gas station, share the new ones they find in the platform and update the fuel prices|

# Context Diagram and interfaces

## Context Diagram

```plantuml
@startuml

left to right direction
actor "Administrator" as a
actor "Google Maps" as c
actor "User" as d
rectangle System{
	a --> (EZGas)
	(EZGas) -- c
	(EZGas) <-- d
}

@enduml
```

## Interfaces

| Actor | Logical Interface | Physical Interface |
| ------------- |:-------------:| -----:|
| Administrator	| GUI			| Screen, keyboard, smartphone |
| User			| GUI			|Screen, keyboard, smartphone|
| Google Maps		|Google Maps API |Internet Connection|

# Stories and personas

Jack is a father of 2, each morning drives the kids to school and then goes to work. Every week on Saturday goes to the gas station to fill the tank, but he notices that his usual gas station has increased the price. Since the situation at work is not the best he tries to find a cheaper station, but he doesn't want to create an account to do so, so he goes on the app EZGas and searches for the best price station in the area of his work place.

Alice is a bank officer, a quite wealthy one. She usually does the same route to work every morning, but today her usual gas station has closed due to repairs. She wants to find the closest station to fill her tank, regardless of the price. To do so uses the app EZGas, chooses proximity to her position as parameter of the search, and finds what she was looking for. Because of that she can go to work happy because she saved a lot of time and came into work before her shift.

Maja is a police officer and is very diligent towards precision and correctness in documents. She usually drives to the local gas station to fill her car, but looking at the app EZGas that usually uses, she notices that the prices are not correct. Since she has a steady account on the app, she can vote that the price displayed at the top of the list of prices of her fuel is not correct. To do so she can just upvote one of the other prices as the correct one, or insert the correct one through her account. She doesn't want people to have the wrong prices!

Robert is the administrator of the platform. Every day at the office he must check whether the users of the platform perform in conformance to the requirements that the developer has set. He can accept an user request to insert a new gas station.

Jackson is the owner of a gas station. He is struggling in the last period since he opened a new station and doesn't have a lot of clients since people don't know where it is. One of his friends uses frequently the app EZGas and tells it to Jackson to help him with his job. Jackson, after creating an account, inserts his new gas station onto the application map and inserts all his fuel prices. He notices that his price was lower than the ones in the area and in the next few days a lot of clients go to him to fill their tanks.

# Functional and non functional requirements

## Functional Requirements

| ID        | Description  |
| ------------- |:-------------:|
|  **FR1**	 | Record the prices of fuel into a specific gas station|
|  **FR2**   | Search Gas Stations |
|  FR2.1 | Search for cheapest gas station in a certain area|
|  FR2.2 | Search for closest gas station in a certain area|
|  **FR3**   | Track user with position|
|  **FR4** 	 | Let user votes the fuel price|
|  **FR5**	 | Manage Accounts|
|  FR5.1 | Create Account|
|  FR5.2 | Log in|
|  FR5.3 | Log Out|
|  **FR6** 	 | Manage insertion and position of a gas station|
|  FR6.1 | Insert a new gas station |
|  FR6.2 | Accept request of insertion of a gas station and insert parameters|

## Non Functional Requirements

| ID        | Type (efficiency, reliability, ..)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NFR1     | Usability | Application should be used without any training | All FR |
|  NFR2     | Performance | All functions should complete in < 1 sec  | All FR |
|  NFR3     | Portability | The application runs on all most used browsers (Chrome v.80 or more, Firefox v.72 or more, Safari v.13 or more, Microsoft Edge v.80 or more) | All FR |
|  NFR4     | Localisation | Decimal numbers use . (dot) as decimal separator | All FR|

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
  U -> (FR5 Manage Account)
  U -> (FR2 Insert search params)

  AU --> (FR4 Vote a fuel price)
  AU --> (FR6.1 Insert new gas station)
  A --> (FR6.2 Accept new gas station request)
  AU --> (FR1 Enter new price)

  (FR2 Show map and gas stations) <-- WA
  (FR6.1 Insert new gas station) <-- (FR6.2 Accept new gas station request)
  (FR2 Insert search params) --> (FR2 FR3 Search gas stations)
  (FR2 FR3 Search gas stations) --> (FR2 Show map and gas stations)
  WA --> (FR1 Enter new price)

  (FR5 Manage Account) .> (FR5.1 Create Account) : include
  (FR5 Manage Account) .> (FR5.2 Log in) : include
  (FR5 Manage Account) .> (FR5.3 Log Out) : include
}

@enduml
```

## Use Cases
## Relevant scenarios

### Use case 1, UC1 - FR1 Record price of fuel

| Actors Involved        | Authenticated_user |
| ------------- |:-------------:|
|  Precondition     | Active account, log in operation, distance of user from gas station < 200 m |
|  Post condition     | Price inserted |
|  Nominal Scenario     | User logs into the app with his/her account. Inserts fuel price for the selected fuel type.|
|  Variants     | If the fuel is not already present in the list of fuels present at the gas station he/she can insert a new fuel and its connected price.|

## Scenario 1

| Scenario ID: SC1        | Corresponds to UC1  |
| ------------- |:-------------|
| Description | Record prices of fuel|
| Precondition | User must have an active account and is logged in, User must have GPS activated|
| Postcondition | Prices are inserted |
| Step#        |  Step description   |
|  1	 | User selects a gas station|
|  2   | The application through web API verifies the position of the user which must be in 200 m from the gas station|
|  3	 |	User selects the fuel type |
|  4	 |	User inserts the new prices of fuel into a gas station|

### Use case 2, UC2 - FR2 Search for gas stations with certain properties

| Actors Involved        | User, Google Maps API|
| ------------- |:-------------:|
|  Precondition     | User selected a search type (distance, price, type of fuel) |
|  Post condition     |  Gas stations shown on the map |
|  Nominal Scenario     | Looks for gas stations based on the search type inserted. On the Map will be shown all of the gas stations within a certain range, decided by the user through a slider|
|  Variants     | |

## Scenario 2.1

| Scenario ID: SC2        | Corresponds to UC2  |
| ------------- |:-------------|
| Description | Search the cheapest gas station in a certain radius|
|Precondition | User must have GPS activated|
|Postcondition | Gas stations that fit the requirement are shown on the map |
| Step#        | Step description  |
|  1     | User must fill out the parameters required: fuel type, distance. He can choose to show the gas station in price order or distance order |
|  2     | The application gets the user's position from the Map API and performs the search |
|  3     | The application calculates which are the gas stations with the lower prices for the fuel type inserted and shows them on the map|

## Scenario 2.2

| Scenario ID: SC2        | Corresponds to UC2  |
| ------------- |:-------------|
| Description | Search the closest gas station in a certain radius|
|Precondition | User must have GPS activated|
|Postcondition | Gas stations that fit the requirement are shown on the map |
| Step#        | Step description  |
|  1     | User must fill out the parameters required: fuel type, distance. He can choose to show the gas station in price order or distance order |
|  2     | The application gets the user's position from the Map API and performs the search |
|  3     |The application calculates which are the gas stations that are closest to the user position and shows them on the map|

### Use case 3, UC3 - FR4 Vote the price of a gas station

| Actors Involved        | Authenticated_user |
| ------------- |:-------------:|
|  Precondition     | Active account, Log in operation, Distance of user from gas station < 200 m, price selected |
|  Post condition     | Vote active for 24h |
|  Nominal Scenario     | User votes only the correct fuel price. Each user can vote only once in 24 h the fuel price. |
|  Variants     | A user can change his/her vote during the 24 hours, but cannot add another vote|

## Scenario 3

| Scenario ID: SC3        | Corresponds to UC3  |
| ------------- |:-------------|
| Description | Vote the price of a fuel|
| Precondition | User must have an active account, must be logged in, must have GPS activated|
| Postcondition | A fuel price has an extra vote |
| Step#        |  Step description   |
|  1     | The application through web API verifies the position of the user which must be in 200 m from the gas station|
|  2     | User can upvote the correct fuel price among those displayed in the list connected to that gas station and fuel type|


### Use case 4, UC4 - FR5.1 Create Account

| Actors Involved        | User |
| ------------- |:-------------:|
|  Precondition     | User has not yet an active account. Defines what the credentials (e-mail, username and password) will be|
|  Post condition     | Being able to perform operation (searching and insertion) on the application|
|  Nominal Scenario     | The person creates a new account by registering his/hers credentials on the platform and, after, logs in|
|  Variants     | |

## Scenario 4

| Scenario ID: SC4        | Corresponds to UC4  |
| ------------- |:-------------|
| Description | Creation of an account|
|Precondition | There shouldn't be an account already logged in the application |
|Postcondition | User has created an account |
| Step#        | Step description  |
|  1     | User enters the app|
|  2     | User fills out a form with his/her credential (username, email, password)|
|  3     | User can log in |


### Use case 5, UC5 - FR6 Insertion of a new gas station

| Actors Involved        | User, Administrator |
| ------------- |:-------------:|
|  Precondition     | User has an active account, must be logged in|
|  Post condition     | Gas station is inserted and visible on the map|
|  Nominal Scenario     | User reports the existance of a new gas station in a determined position. The administrator must verify if the gas station really exist and if it does accept the request and insert the brand of that gas station|
|  Variants     | If the gas station is not in the position sent by the user the administrator can refuse the request and the gas station is not inserted |

## Scenario 5

| Scenario ID: SC5        | Corresponds to UC5  |
| ------------- |:-------------|
| Description | Insertion of a new gas station|
|Precondition | User must have an active account, must be logged in, must have active GPS|
|Postcondition | Gas station is inserted on the map |
| Step#        | Step description  |
|  1     | The authenticated user inserts the address of the new gas station |
|  2     | The administrator checks whether the gas station is really present in the location |
|  3     | The administrator accepts the request and inserts the brand of the gas station|


# Glossary

```plantuml
@startuml
class EZGas

class User

class "Authenticated User"{
  + userID
  + username
  + e-mail
  + password
}

class Administrator{
  + AdministratorID
  + username
  + e-mail
  + password
}

class "Gas station insert request"{
  + requestID
  + date_of_request
  + outcome: {approved, rejected}
}

class "Gas station"{
  + gasStationID
  + brand
  + date_of_insertion
}

class "Fuel type"{
  + name
  + acronym
}

class Price{
  + €_per_Lt
  + date_of_insertion
}

class Vote{
  + date
  + type: {up, down}
}

class Position{
	+ latitude
	+ longitude
	+ address*
}

User - Position
User <|-- "Authenticated User"
EZGas -- "*" User
EZGas -- "*" "Gas station"
EZGas -- "*" Administrator
Price "*" - "Fuel type"
"Gas station" -- "*" Price : has list of >
"Gas station" - Position
"Authenticated User" -- "*" Vote
"Authenticated User" -- "*" Price : inserts >
"Authenticated User" -- "*" "Gas station insert request" : requests >
"Gas station insert request" -- "0..1" "Gas station" : refers to >
"Gas station insert request" -- Position
"Gas station insert request" "*" -- Administrator : < approve/reject
Vote "*" -- Price

note "A User is a person that uses\nthe application to search\na gas station" as N1
N1 .. User

note "Only Authenticated User can add price,\ngas station, vote price. The user has an\nactive account if he/she can log into the\napplication and perform all the extra\noperation that authentication allows" as N2
N2 .. "Authenticated User"

' fake hidden relationships for positioning
' -[hidden]> : left to right
' --[hidden]> : top to bottom
EZGas --[hidden]> User
User --[hidden]> "Authenticated User"

"Authenticated User" --[hidden]> "Gas station insert request"
"Authenticated User" --[hidden]>  "Gas station"
"Gas station insert request" -[hidden]> Vote
Vote --[hidden]> Price
"Gas station" --[hidden]> "Administrator"
"Gas station" -[hidden]> Price
"Gas station insert request" --[hidden]> "Gas station"
Price -[hidden]> "Fuel type"
Position -[hidden]> "Gas station insert request"

@enduml
```

# Deployment Diagram

```plantuml
@startuml

node "Application Server" as AS {
  artifact "Search and Insertion Engine" as SE
  artifact "Votes Management" as AM
}

node "Web Server" as W {
  artifact "Web Site"
}

node "DB Server" as DB {
    database Accounts as ACC
    database Votes AS VO
    database "Gas Stations" AS GS
}

node Client as C {
artifact Browser as B
}

node "Google Maps API" as MA {

}

C -- W : internet
W -- AS
AS -- DB
AS - MA

' positioning

ACC -[hidden]> VO
VO -[hidden]> GS

@enduml
