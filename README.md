# Duplicates counter
Duplicates counter is counting number of occurrences in given sorted array 
of letters.

## Installation
```
mvn clean package
```

## Usage
App is consuming data as a path to txt file with a sorted array:
```
java -jar duplicates_counter-0.1.0.jar test_data.txt
```

If argument is not provided then app is running with example data.

Example array:  
a,b,b,b,b,b,d,h,h,h,p

Example output:  
a=1, b=5, d=1, h=3, p=1

To generate data you can use data_generator.sh. It will create a sorted
array of letters with size as a passed parameter. Size is 10 by default.
Array is saved as test_data.txt.

## Description
App is using two public methods to count:
* countDuplicates - a simple for loop with adding letters to map string to 
occurrences (complexity: O(n)).
* countDuplicatesOptimized - multiple binary search for every letter. After 
finding number of occurrences of the letter is runs again with sublist reduced 
by previous results (complexity: O(log(n))).