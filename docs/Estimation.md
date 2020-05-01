# Project Estimation

Authors: Group 12

Date: 22/04/2020

Version: 1.0

# Contents

- [Estimation approach](#estimation-approach)
- [Estimate by product decomposition](#estimate-by-product-decomposition)
- [Estimate by activity decomposition](#estimate-by-activity-decomposition)
    + [Gantt diagram](#gantt-diagram)

# Estimation approach

This is a formal approach. We can assume there are four main activities to end the project: requirements, design, coding, testing.
As we don't know how much time each activity could take, we can consider a uniformal distribution among them. So, the effort for each activity will be the same:

```
requirements effort = design effort = coding effort = testing effort
```

This project is quite small, so one module is enough to contain all parts. In this case, we do not have to perform the same activity more times. The overall project effort needed is:

```
overall effort = (requirements effort + design effort + coding effort + testing effort) * number of modules = coding effort * 4 * 1
```

The easiest effort to compute is the coding effort. We can assume we need 10 classes, each class is 120 LOC.

We also suppose a person can produce about 10 LOC per hour. These values considers writing requirements, designing, coding and testing (all the 4 main activities for the project). It is important that, computing the overall project effort, we consider not only the coding time, but even requirements, design and testing times.

# Estimate by product decomposition

###

|             | Estimate                        |
| ----------- | ------------------------------- |
| NC =  Estimated number of classes to be developed   | 10 |
| A = Estimated average size per class, in LOC        | 120 |
| S = Estimated size of project, in LOC (= NC * A) | 1200 * 4 = 4800 |
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)  | 4800 / 10 = 480 |
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 480 * 30 = 14400 |
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 3 |


# Estimate by activity decomposition

|         Activity name    | Estimated effort (person hours)   |
| ----------- | ------------------------------- |
| Requirements document | 80 |
| GUI prototype | 50 |
| Estimation document | 10 |
| Design document | 100 |
| Coding | 120 |
| Testing | 120 |

## Gantt diagram

```plantuml
@startuml

[Requirements document] lasts 80 days
[GUI prototype] lasts 50 days
[Estimation document] lasts 10 days
[Design document] lasts 100 days
[Code] lasts 120 days
[Test cases] lasts 120 days


[GUI prototype] starts at [Requirements document]'s end
[Estimation document] starts at [Requirements document]'s end
[Design document] starts at [Estimation document]'s end
[Code] starts at [Design document]'s end
[Test cases] starts at [Design document]'s end
[Time sheet] starts at [Design document]'s end

@enduml
```

