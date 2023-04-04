# Project 3: Experiments with Hashing

* Author: Gavin Wale
* Class: CS321 Section #002
* Semester: Spring 2023

## Overview

The classes in this program take advantage of Java's inheritance hierarchy  
to maintain organization and implement two different types of hashing. Both     
basic linear probing and double hashing are thoroughly implmented and observed  
during runtime. A user can use command line arguments to perform various    
experiments with the two hashing algorithms.    

## Reflection

This project at its core was quite simple. I was able to complete around 90-95%     
of the code in under an hour. However, after this, it was pretty much impossible    
to get my output to pass the ```diff``` in the runtests.sh file. So many small  
off-by-one errors and small problems made this go from a 2 hour project to a   
triple late day, 10 hours a day project. I was not expecting this, and I am not     
completely satisfied with the result. The project became more of a "I'm trying to   
make my output match the desired output" as opposed to "I'm trying to make and  
implement a hash table" which I think is just perfect academia.

## Compiling and Using

Running this program will require to have a terminal open in the project directory

Run the following command to compile all the java files:    
```javac *.java```

After this, the main method can be run through HashtableTest.java using:    
```java HashtableTest <dataSource> <loadFactor> [<debugLevel>]```

If any errors are made when trying to run, the program's usage will print to the terminal.

## Results 

Input type: Integer

| Load Factor | Avg. Lin. Probes | Avg. Dbl. Probes |
|-------|-------|-------|
| 0.5   | 1.50  | 1.38  |
| 0.6   | 1.74  | 1.53  |
| 0.7   | 2.16  | 1.72  |
| 0.8   | 3.03  | 2.01  |
| 0.9   | 5.96  | 2.57  |
| 0.95  | 10.13 | 3.18  |
| 0.99  | 59.99 | 4.67  |

Input type: Long

| Load Factor | Avg. Lin. Probes | Avg. Dbl. Probes |
|-------|--------|--------|
| 0.5   | 1.08    | 1.13    |
| 0.6   | 1.09    | 1.17    |
| 0.7   | 1.09    | 1.23    |
| 0.8   | 1.15    | 1.44    |
| 0.9   | 1.47    | 2.21    |
| 0.95  | 1.86    | 2.86    |
| 0.99  | 3.54    | 4.54    |

Input type: Word-List

| Load Factor | Avg. Lin. Probes | Avg. Dbl. Probes |
|-------|--------|--------|
| 0.5   | 1.60  | 1.39  |
| 0.6   | 2.15  | 1.53  |
| 0.7   | 3.60  | 1.72  |
| 0.8   | 6.71  | 2.02  |
| 0.9   | 19.81 | 2.57  |
| 0.95  | 110.59| 3.19  |
| 0.99  | 471.67| 4.70  |

## Sources used

https://www.geeksforgeeks.org/wildcards-in-java/
https://math.hws.edu/eck/cs124/javanotes4/c12/ex-12-3-answer.html