# Estimation proposal

## Estimation approach

This is a formal approach. We can assume there are 4 main activities to end the project: requirements, design, coding, testing.
As we don't know how much time each activity could take, we can consider a uniformal distribution among them. So, the effort for each activity will be the same:

```
requirements effort = design effort = coding effort = testing effort
```

The overall project effort needed is:

```
overall effort = requirements effort + design effort + coding effort + testing effort = coding effort * 4
```

The easiest effort to compute is the coding effort. We can assume we need 10 classes, each class is 120 LOC.

As proposed by the professor, a person can produce about 10 LOC per hour. These values considers writing requirements, designing, coding and testing (all the 4 main activities for the project). It is important that, computing the overall project effort, we consider not only the coding time, but even requirements, design and testing times.

## Estimate by product decomposition

| | Estimate |
| - | - |
| NC =  Estimated number of classes to be developed | 10 |
| A = Estimated average size per class, in LOC | 120 |
| SC = Estimated size ONLY FOR THE CODING ACTIVITY, in LOC (= NC * A) | 1200 | 
| S = Estimated size the whole project (consider the 4 main activities previously described), in LOC | 1200 * 4 = 4800 |
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour) | 4800 / 10 = 480|
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro) | 480 * 30 = 14400 |
| Estimated effort FOR EACH TEAM PERSON, in person hour | 480 / 4 = 120 | 
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 15 days, 3 weeks |