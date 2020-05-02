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

As described in the glossary in the requirements document, we suppose to have about 10 classes (at least the most important ones).

The project will be implemented using the Spring framework, so functionalities and data from each class will be splitted among many classes or interfaces in different modules.

We have not considered all those design details in these estimations, but classes (as taken from the glossary) have an higher number of LOC as they implement all functionalities and data.

# Estimate by product decomposition

###

|             | Estimate                        |
| ----------- | ------------------------------- |
| NC =  Estimated number of classes to be developed   | 10 |
| A = Estimated average size per class, in LOC        | 200 |
| S = Estimated size of project, in LOC (= NC * A) | 200 |
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)  | 2000 / 10 = 200 |
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 200 * 30 = 6000 |
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 1.25 (about 1 week and 2 days) |


# Estimate by activity decomposition

|         Activity name    | Estimated effort (person hours)   |
| ----------- | ------------------------------- |
| Requirements document | 80 |
| GUI prototype | 50 |
| Estimation document | 10 |
| Design document | 80 |
| Coding | 120 |
| Testing | 120 |

## Gantt diagram

```plantuml
@startgantt
scale 1.5

Project starts the 2020/04/22

saturday are closed
sunday are closed
2020/04/13 is closed

[Requirements document] lasts 3 days
[GUI prototype] lasts 2 days
[Estimation document] lasts 1 days
[Design document] lasts 4 days
[Coding] lasts 5 days
[Testing] lasts 5 days
[Time sheet] lasts 1 days


[GUI prototype] starts at [Requirements document]'s end
[Estimation document] starts at [Requirements document]'s end
[Design document] starts at [Estimation document]'s end
[Coding] starts at [Design document]'s end
[Testing] starts at [Design document]'s end
[Time sheet] starts at [Design document]'s end

@endgantt
```
