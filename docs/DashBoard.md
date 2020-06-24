# Project Dashboard

The dashboard collects the key measures about the project.
To be used to control the project, and compare with others. These measures will not be used to graduate the project. <br>
We consider two phases: <br>
-New development: From project start (april 13) to delivery of version 1 fixed (june 9)  <br>
-Maintenance: implementation of CR4 and CR7 (june 9 to end)   <br>
Report effort figures from the timesheet or timesheetCR document, compute size from the source code.

## New development 
| Measure| Value |
|---|---|
|effort E (report here effort in person hours, for New development, from timesheet)  |427|
|size S (report here size in LOC of all code written, excluding test cases)  |2752|
|productivity = S/E |2752/427=6.44|
|defects after release D (number of defects found running official acceptance tests and fixed in CR0) |3 (on 40 tests)|
|defect density = D/S|3/2752=0.00109|
| effort for non-quality ENQ (effort for CR0, or effort to fix defects found running official acceptance tests, from timesheetCR) |8|
| effort for non quality, relative = ENQ / E |8/427=0.018|

## Maintenance

| Measure | Value|
|---|---|
| size S_CR4 = only lines added for CR4 = total size with CR4 - S |10|
| actual effort (from timesheetCR) AE_CR4 |6|
| productivity P_CR4 = S_CR4/ AE_CR4 |10/6=1.666|
| estimated effort (from estimationCR) EE_CR4 |30|
|estimation accuracy CR4 = EE_CR4/AE_CR4  |30/6=5|
|||
| size S_CR7 =only lines added for CR7 = total size with CR7 - S |38|
| actual effort (from timesheetCR) AE_CR7 |14|
| productivity P_CR7 = S_CR7/ AE_CR7 |38/14=2.714|
| estimated effort (from estimationCR) EE_CR7 |30|
|estimation accuracy CR7 = EE_CR7/AE_CR7  |30/14=2.14|