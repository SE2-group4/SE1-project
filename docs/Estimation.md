# Project Estimation

Authors: Group 12

Date: 22/04/2020

Version: 1.0

# Contents



- [Estimate by product decomposition]
- [Estimate by activity decomposition ]



# Estimation approach

<Consider the EZGas  project as described in YOUR requirement document, assume that you are going to develop the project INDEPENDENT of the deadlines of the course>

# Estimate by product decomposition



###

|             | Estimate                        |
| ----------- | ------------------------------- |
| NC =  Estimated number of classes to be developed   |          10 |
|  A = Estimated average size per class, in LOC       |          120 |
| S = Estimated size of project, in LOC (= NC * A) | 1200 |
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)  |          120                               |
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 3600 |
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) |  3                  |


# Estimate by activity decomposition



###

|         Activity name    | Estimated effort (person hours)   |
| ----------- | ------------------------------- |
| Requirements document | 60 |
| GUI prototype | 20 |
| Estimation document | 4 |
| Design document | 50 |
| Code | 100 |
| Test cases | 80 |
| Time sheet | 2 |

###
Insert here Gantt chart with above activities

```plantuml

@startuml

[Requirements document] lasts 80 days
[GUI prototype] lasts 40 days
[Estimation document] lasts 10 days
[Design document] lasts 10 days
[Code] lasts 100 days
[Test cases] lasts 100 days
[Time sheet] lasts 2 days


[GUI prototype] starts at [Requirements document]'s end
[Estimation document] starts at [Requirements document]'s end
[Design document] starts at [Estimation document]'s end
[Code] starts at [Design document]'s end
[Test cases] starts at [Design document]'s end
[Time sheet] starts at [Design document]'s end

@enduml

```

