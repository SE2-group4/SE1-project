# Design Document


Authors:

Date:

Version:


# Contents

- [High level design](#package-diagram)
- [Low level design](#class-diagram)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)

# Instructions

The design must satisfy the Official Requirements document (see EZGas Official Requirements.md ). <br>
The design must comply with interfaces defined in package it.polito.ezgas.service (see folder ServicePackage ) <br>
UML diagrams **MUST** be written using plantuml notation.

# High level design

The style selected is client - server. Clients can be smartphones, tablets, PCs.
The choice is to avoid any development client side. The clients will access the server using only a browser.

The server has two components: the frontend, which is developed with web technologies (JavaScript, HTML, Css) and is in charge of collecting user inputs to send requests to the backend; the backend, which is developed using the Spring Framework and exposes API to the front-end.
Together, they implement a layered style: Presentation layer (front end), Application logic and data layer (back end).
Together, they implement also an MVC pattern, with the V on the front end and the MC on the back end.



```plantuml
@startuml
package "Backend" {

}

package "Frontend" {

}


Frontend -> Backend
@enduml


```


## Front End

The Frontend component is made of:

Views: the package contains the .html pages that are rendered on the browser and that provide the GUI to the user.

Styles: the package contains .css style sheets that are used to render the GUI.

Controller: the package contains the JavaScript files that catch the user's inputs. Based on the user's inputs and on the status of the GUI widgets, the JavaScript controller creates REST API calls that are sent to the Java Controller implemented in the back-end.


```plantuml
@startuml
package "Frontend" {

    package "it.polito.ezgas.resources.views" {

    }


package "it.polito.ezgas.resources.controller" {

    }


package "it.polito.ezgas.resources.styles" {

    }



it.polito.ezgas.resources.styles -down-> it.polito.ezgas.resources.views

it.polito.ezgas.resources.views -right-> it.polito.ezgas.resources.controller


}
@enduml

```

## Back End

The backend  uses a MC style, combined with a layered style (application logic, data).
The back end is implemented using the Spring framework for developing Java Entrerprise applications.

Spring was selected for its popularity and relative simplicity: persistency (M and data layer) and interactions are pre-implemented, the programmer needs only to add the specific parts.

See in the package diagram below the project structure of Spring.

For more information about the Spring design guidelines and naming conventions:  https://medium.com/the-resonant-web/spring-boot-2-0-project-structure-and-best-practices-part-2-7137bdcba7d3



```plantuml
@startuml
package "Backend" {

package "it.polito.ezgas.service"  as ps {
   interface "GasStationService"
   interface "UserService"
}


package "it.polito.ezgas.controller" {

}

package "it.polito.ezgas.converter" {

}

package "it.polito.ezgas.dto" {

}

package "it.polito.ezgas.entity" {

}

package "it.polito.ezgas.repository" {

}


}
note "see folder ServicePackage" as n
n -- ps
@enduml
```



The Spring framework implements the MC of the MVC pattern. The M is implemented in the packages Entity and Repository. The C is implemented in the packages Service, ServiceImpl and Controller. The packages DTO and Converter contain classes for translation services.



**Entity Package**

Each Model class should have a corresponding class in this package. Model classes contain the data that the application must handle.
The various models of the application are organised under the model package, their DTOs(data transfer objects) are present under the dto package.

In the Entity package all the Entities of the system are provided. Entities classes provide the model of the application, and represent all the data that the application must handle.




**Repository Package**

This package implements persistency for each Model class using an internal database.

For each Entity class, a Repository class is created (in a 1:1 mapping) to allow the management of the database where the objects are stored. For Spring to be able to map the association at runtime, the Repository class associated to class "XClass" has to be exactly named "XClassRepository".

Extending class JpaRepository provides a lot of CRUD operations by inheritance. The programmer can also overload or modify them.



**DTO package**

The DTO package contains all the DTO classes. DTO classes are used to transfer only the data that we need to share with the user interface and not the entire model object that we may have aggregated using several sub-objects and persisted in the database.

For each Entity class, a DTO class is created (in a 1:1 mapping).  For Spring the Dto class associated to class "XClass" must be called "XClassDto".  This allows Spring to find automatically the DTO class having the corresponding Entity class, and viceversa.




**Converter Package**

The Converter Package contains all the Converter classes of the project.

For each Entity class, a Converter class is created (in a 1:1 mapping) to allow conversion from Entity class to DTO class and viceversa.

For Spring to be able to map the association at runtime, the Converter class associated to class "XClass" has to be exactly named "XClassConverter".




**Controller Package**

The controller package is in charge of handling the calls to the REST API that are generated by the user's interaction with the GUI. The Controller package contains methods in 1:1 correspondance to the REST API calls. Each Controller can be wired to a Service (related to a specific entity) and call its methods.
Services are in packages Service (interfaces of services) and ServiceImpl (classes that implement the interfaces)

The controller layer interacts with the service layer (packages Service and ServieImpl)
 to get a job done whenever it receives a request from the view or api layer, when it does it should not have access to the model objects and should always exchange neutral DTOs.

The service layer never accepts a model as input and never ever returns one either. This is another best practice that Spring enforces to implement  a layered architecture.



**Service Package**


The service package provides interfaces, that collect the calls related to the management of a specific entity in the project.
The Java interfaces are already defined (see file ServicePackage.zip) and the low level design must comply with these interfaces.


**ServiceImpl Package**

Contains Service classes that implement the Service Interfaces in the Service package.

# Low level design

```plantuml
package "Backend" {

package "it.polito.ezgas.repository" {
    interface UserRepository{
        + updateUserName(userId, userName): User
        + updateEmail(userId, email): User
        + updatePassword(userId, password): User
        + updateReputation(userId, reputation): User
    }
    interface GasStationRepository{
        + updateGasStationName(gasStationId, gasStationName): GasStation
        + updateGasStationAddress(gasStationId, gasStationAddress): GasStation
        + updateBrand(gasStationId, brand): GasStation
        + updateCarSharing(gasStationId, carSharing): GasStation
        + findByGasolineType(gasolinetype): List<GasStation>
        + findByProximity(GeoPoint): List<GasStation>
        + findByGeoPoint(GeoPoint, gasolinetype, carsharing): List<GasStation>
        + findWithoutGeoPoint(gasolinetype, carsharing): List<GasStation>
        + findByCarSharing(carsharing): List<GasStation>
        + updateReport(gasStationId, priceReportId): void
    }
    interface PriceReportRepository
}

package "it.polito.ezgas.entity" {
    class GasStation{
        - gasStationId
        - gasStationName
        - gasStationAddress
        - brand
        - carSharing
        - priceReportId
        - geoPoint
        + Getter()
        + Setter()
    }
    class User{
        - userId
        - userName
        - password
        - email
        - reputation
        - isAdmin
        + Getter()
        + Setter()
    }
    class PriceReport{
        - priceReportId
        - userId
        - dieselPrice
        - superPrice
        - superPlusPrice
        - gasPrice
        - time_tag
        + Getter()
        + Setter()
    }
    class GeoPoint{
        - latitude
        - longitude
        + Getter()
        + Setter()
    }

}

package "it.polito.ezgas.dto" {
    class IdPw{
        - user
        - pw
        + Getter()
        + Setter()
    }
    class LoginDto{
        userId
        userName
        token
        email
        reputation
        admin
        + Getter()
        + Setter()
    }
    class UserDto{
        - userId
        - userName
        - password
        - email
        - reputation
        - isAdmin
        + Getter()
        + Setter()
    }
    class GasStationDto{
        - gasStationId
        - gasStationName
        - gasStationAddress
        - brand
        - carSharing
        - priceReport
        - geoPoint
        + Getter()
        + Setter()
    }
    class PriceReportDto{
        - priceReportId
        - user
        - dieselPrice
        - superPrice
        - superPlusPrice
        - gasPrice
        - time_tag
        + Getter()
        + Setter()
        + getTrustLevel()
    }
    class GeoPointDto{
        - latitude
        - longitude
        + Getter()
        + Setter()
    }
}

package "it.polito.ezgas.converter" {
    class UserConverter{
        + toUserDto(User): UserDto
    }
    class GasStationConverter{
        + toGasStationDto(GasStation): GasStationDto
    }
    class PriceReportConverter{
        + toPriceReportConverter(PriceReport): PriceReportDto
    }
    class GeoPointConverter{
        + toGeoPointConverter(GeoPoint): GeoPointDto
    }
}

package "it.polito.ezgas.service" {
   interface "GasStationService"{
       + modifyGasStationName(gasStationId, gasStationName): GasStationDto
       + modifyGasStationAddress(gasStationId, gasStationAddress): GasStationDto
       + modifyBrand(gasStationId, brand): GasStationDto
       + modifyCarSharing(gasStationId, carSharing): GasStationDto
       + getGasStationById(gasStationId): GasStationDto
       + saveGasStation(GasStationDto): GasStationDto
       + getAllGasStations(): List<GasStationDto>
       + deleteGasStation(gasStationId): Boolean
       + getGasStationsByGasolineType(gasolinetype):  
       + getGasStationsByProximity(GeoPoint): List<GasStationDto>
       + getGasStationsWithCoordinates(Geopoint, gasolinetype, carsharing): List<GasStationDto>
       + getGasStationsWithoutCoordinates(gasolinetype, carsharing): List<GasStationDto>
       + setReport(gasStationId, priceReportId): void
       + getGasStationByCarSharing(carSharing): List<GasStation>
       + savePriceReport(dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId): PriceReport
   }
   interface "UserService" {
       + modifyUserName(userId, userName): UserDto
       + modifyEmail(userId, email): UserDto
       + modifyPassword(userId, password): UserDto
       + getUserById(userId): UserDto 
       + saveUser(userDto): UserDto 
       + getAllUsers(): List<UserDto>
       + deleteUser(userId): Boolean
       + login(credentials): LoginDto
       + increaseUserReputation(userId): Integer
       + decreaseUserReputation(userId): Integer
   }
}

package  "it.polito.ezgas.controller" {
    class GasStationController{
        - gasStationService
        + getGasStationById(gasStationId): GasStationDto 
        + getAllGasStations(): List<GasStationDto>
        + saveGasStation(gasStationDto): GasStationDto 
        + deleteGasStation(gasStationId): Boolean 
        + getGasStationsByGasolineType(gasolineType): List<GasStationDto>
        + getGasStationsByProximity(myLat, myLon): List<GasStationDto>
        + getGasStationsWithCoordinates(myLat, myLon, gasolineType, carSharing): List<GasStationDto>
        + setGasStationReport(gasStationId, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, userId): void
    }

    class UserController{
        - userService
        + modifyGasStationName(gasStationId, gasStationName): GasStationDto
        + modifyGasStationAddress(gasStationId, gasStationAddress): GasStationDto
        + modifyBrand(gasStationId, brand): GasStationDto
        + modifyCarSharing(gasStationId, carSharing): GasStationDto
        + modifyUserName(userId, userName): UserDto
        + modifyEmail(userId, email): UserDto
        + modifyPassword(userId, password): UserDto
        + getUserById(userid): UserDto
        + getAllUsers(): List<UserDto>
        + saveUser(UserDto): UserDto
        + deleteUser(userId): Boolean
        + increaseUserReputation(userId): Integer
        + decreaseUserReputation(userId): Integer
        + login(IdPw): LoginDto
    }
}

it.polito.ezgas.controller -[hidden]-> it.polito.ezgas.service
it.polito.ezgas.service -[hidden]-> it.polito.ezgas.repository
it.polito.ezgas.repository -[hidden]> it.polito.ezgas.converter
}

```
# Verification traceability matrix

Only classes and interfaces from the following packages have been considered in the traceability matrix:

- it.polity.ezgas.entity
- it.polito.ezgas.service
- it.polito.ezgas.repository
- it.polito.ezgas.controller

Others are support classes (or interfaces) and do not perform operations to directly implement functional requirements.

|       | User | GasStation | GeoPoint | PriceList | UserService | GasStationService | UserController | GasStationController | UserRepository | GasStationRepository | PriceListRepository |
| :-    | :-:  | :-:        | :-:      | :-:       | :-:         | :-:               | :-:            | :-:                  | :-:            | :-:                  | :-: |
| FR1.1 | x    |            |          |           | x           |                   | x              |                      | x              |                      |     |
| FR1.2 | x    |            |          |           | x           |                   | x              |                      | x              |                      |     |
| FR1.3 | x    |            |          |           | x           |                   | x              |                      | x              |                      |     |
| FR1.4 | x    |            |          |           | x           |                   | x              |                      | x              |                      |     |
| FR2   | x    |            |          |           | x           |                   | x              |                      |                |                      |     |
| FR3.1 | x    |            |          |           | x           |                   | x              | x                    |                | x                    | x   |
| FR3.2 | x    |            |          |           | x           |                   | x              | x                    |                | x                    |     |
| FR3.3 | x    | x          |          |           | x           | x                 | x              | x                    |                | x                    |     |
| FR4.1 | x    | x          | x        |           | x           | x                 | x              | x                    |                | x                    |     | 
| FR4.2 | x    | x          | x        |           | x           | x                 | x              | x                    |                | x                    |     | 
| FR4.3 | x    | x          | x        | x         | x           | x                 | x              | x                    |                | x                    | x   | 
| FR4.4 | x    | x          |          | x         | x           | x                 | x              | x                    |                | x                    | x   | 
| FR4.5 | x    | x          |          | x         | x           | x                 | x              | x                    |                | x                    | x   | 
| FR5.1 | x    | x          |          | x         | x           | x                 | x              | x                    |                |                      | x   |
| FR5.2 | x    | x          |          | x         | x           | x                 | x              | x                    |                |                      | x   |
| FR5.3 | x    | x          |          | x         | x           | x                 | x              | x                    |                |                      | x   | 

# Verification sequence diagrams

### Use case 1, UC1 - Create User Account

```plantuml
@startuml
hide footbox
skinparam shadowing false

participant UserController as UC
participant UserService as US
participant UserConverter as UCV
participant UserRepository as UR
participant H2Database as H2

activate UC
UC -> US: 1: saveUser()
activate US

US -> UR: 2: saveUser()
activate UR

UR -> H2: 3: save()

activate H2
H2 --> UR: 4: return User
deactivate H2

UR --> US: 5: return User
deactivate UR

US -> UCV: 6: toUserDto()
activate UCV
UCV --> US: 7: return UserDto
deactivate UCV
US --> UC: 8: return UserDto
deactivate US
deactivate UC
@enduml
```

### Use case 2, UC2 - Modify user account (userName)

```plantuml
@startuml
hide footbox
skinparam shadowing false

participant UserController as UC
participant UserService as US
participant UserConverter as UCV
participant UserRepository as UR
participant H2Database as H2

activate UC
UC -> US: 1: modifyUserName()
activate US

US -> UR: 2: modifyUserName()
activate UR

UR -> H2: 3: updateUserName()

activate H2
H2 --> UR: 4: return User
deactivate H2


UR --> US: 5: return User
deactivate UR

US -> UCV: 6: toUserDto()
activate UCV
UCV --> US: 7: return UserDto
deactivate UCV
US --> UC: 8: return UserDto
deactivate US
deactivate UC
@enduml
```

### Use case 3, UC3 - Delete user account

```plantuml
@startuml
hide footbox
skinparam shadowing false

participant UserController as UC
participant UserService as US
participant UserRepository as UR
participant H2Database as H2

activate UC
UC -> US: 1: deleteUser()
activate US

US -> UR: 2: deleteUser()
activate UR

UR -> H2: 3: deleteById()

activate H2
H2 --> UR: 4: return Boolean
deactivate H2

UR --> US: 5: return Boolean
deactivate UR
US --> UC: 8: return Boolean
deactivate US
deactivate UC
@enduml
```

### Use case 4, UC4 - Create Gas Station

```plantuml
@startuml
hide footbox
skinparam shadowing false

participant GasStationController as GC
participant GasStationService as GS
participant GasStationRepository as GR
participant GasStationConverter as GCV
participant H2Database as H2

activate GC
GC -> GS: 1: saveGasStation()
activate GS

GS -> GR: 2: saveGasStation()
activate GR

GR -> H2: 3: saveById()

activate H2
H2 --> GR: 4: return GasStation
deactivate H2

GR --> GS: 5: return GasStation
deactivate GR

GS -> GCV: 6: toGasStationDto()
activate GCV
GCV --> GS: 7: return GasStationDto
deactivate GCV
GS --> GC: 8: return GasStationDto
deactivate GS
deactivate GC
@enduml
```
### Use case 5, UC5 - Modify Gas Station Information (gasStationName)

```plantuml
@startuml
hide footbox
skinparam shadowing false

participant GasStationController as GC
participant GasStationService as GS
participant GasStationConverter as GCV
participant GasStationRepository as GR
participant H2Database as H2

activate GC
GC -> GS: 1: modifyGasStationName()
activate GS

GS -> GR: 2: modifyGasStation()
activate GR

GR -> H2: 3: updateGasStation()

activate H2
H2 --> GR: 4: return GasStation
deactivate H2

GR --> GS: 5: return GasStation
deactivate GR

GS -> GCV: 6: toGasStationDto()
activate GCV
GCV --> GS: 7: return GasStationDto
deactivate GCV

GS --> GC: 8: return GasStationDto
deactivate GS
deactivate GC
@enduml
```

### Use case 6, UC6 - Delete Gas Station

```plantuml
@startuml
hide footbox
skinparam shadowing false

participant GasStationController as GC
participant GasStationService as GS
participant GasStationRepository as GR
participant PriceListRepository as PR
participant H2Database as H2

activate GC
GC -> GS: 1: deleteGasStation()
activate GS

GS -> GR: 2: deleteGasStation()
activate GR

GS -> PR: 2: deleteById()
activate PR

GR -> H2: 3: deleteById()

activate H2
PR -> H2: 3: deleteById()

H2 --> PR: 4: return Boolean
deactivate PR

H2 --> GR: 4: return Boolean
deactivate H2


GR --> GS: 5: return Boolean
deactivate GR

GS --> GC: 8: return Boolean
deactivate GS
deactivate GC
@enduml
```
### Use case 7, UC7 - Report fuel price for a gas station

```plantuml
@startuml
hide footbox
skinparam shadowing false

participant GasStationController as GC
participant GasStationService as GS
participant GasStationRepository as GR
participant PriceReportRepository as PRR
participant PriceReport as PR
participant H2Database as H2

activate GC
GC -> GS: 1: setGasStationReport()
deactivate GC

activate GS
GS -> PRR: 2: savePriceReport()
activate PRR
PRR -> H2: 3: save()
activate H2
H2 --> PRR: 4: return PriceReport
deactivate H2
PRR --> GS: 5: return PriceReport
deactivate PRR
GS -> GR: 6: setReport()
deactivate GS
activate GR 
GR -> H2: updateReport()
deactivate GR
activate H2
deactivate H2

@enduml
```

### Use case 8, UC8 - Obtain price of fuel for gas stations in a certain geographic area

```plantuml
@startuml
hide footbox
skinparam shadowing false

participant GasStationController as GC
participant GasStationService as GS
participant GasStationConverter as GCV
participant GasStationRepository as GR
participant H2Database as H2

activate GC
GC -> GS: 1: getGasStationsByProximity()
activate GS


GS -> GR: 2: getGasStationsByProximity()
activate GR

GR -> H2: 3: findByProximity()

activate H2
H2 --> GR: 4: return List<GasStation>
deactivate H2

GR --> GS: 5: return List<GasStation>
deactivate GR

GS -> GCV: 6: for each(toGasStationDto())
activate GCV
GCV --> GS: 7: return GasStationDto
deactivate GCV

GS --> GC: 8: return List<GasStationDto>
deactivate GS
deactivate GC
@enduml
```

### Use case 9, UC9 - Update trust level of price list
This use case doesn't have a sequence diagram since the trust level is computed by the getTrustLevel() function in the PriceReportDto and is returned to the Gas station.

### Use Case 10, UC10 - Evaluate price
#### Scenario 10.1 - Price is correct (UC.10)

```plantuml
@startuml
hide footbox
skinparam shadowing false

participant UserController as UC
participant UserService as US
participant UserConverter as UCV
participant UserRepository as UR
participant H2Database as H2

activate UC
UC -> US: 1: increaseUserReputation()
activate US

US -> UR: 2: increaseUserReputation()
activate UR

UR -> H2: 3: updateReputation()

activate H2
H2 --> UR: 4: return User
deactivate H2


UR --> US: 5: return User
deactivate UR

US -> UCV: 6: toUserDto()
activate UCV
UCV --> US: 7: return UserDto
deactivate UCV

US --> UC: 8: return Integer
deactivate US
deactivate UC
@enduml
```

#### Scenario 10.2 - Price is wrong (UC.10)

```plantuml
@startuml
hide footbox
skinparam shadowing false

participant UserController as UC
participant UserService as US
participant UserConverter as UCV
participant UserRepository as UR
participant H2Database as H2

activate UC
UC -> US: 1: decreaseUserReputation()
activate US

US -> UR: 2: decreaseUserReputation()
activate UR

UR -> H2: 3: updateReputation()

activate H2
H2 --> UR: 4: return User
deactivate H2

UR --> US: 5: return User
deactivate UR

US -> UCV: 6: toUserDto()
activate UCV
UCV --> US: 7: return UserDto
deactivate UCV

US --> UC: 8: return Integer
deactivate US
deactivate UC
@enduml
```