# Hashtable Implementation

## Overview
Implementation of a hashtable with separate chaining. Depending on the current load factor, the insert operation might need to resize and rehash the entire hashtable.

## How to Run
Make sure that you specify the input file path and the output file path in the ProgramRunner.java file and then run the main method. There are eight valid commands:

* insert [target key] [target value]
    * Info: the insert operation will check if the target key exists in the table. If yes, it will add the target value to the existing value associated with the target key. If no, it will add the target key-value pair into the table.
* delete [target key]
    * Info: the delete operation will check if the target key exists in the table. If yes, it will delete the target key-value pair from the table. If no, it won't do anything. 
* increase [target key]
    * Info: the increase operation will check if the target key exists in the table. If yes, it will increment the existing value associated with the target key by one. If not, it will insert the target key with the value one into the table. 
* find [target key]
* keys
    * Info: the keys operation will return a list of all the existing keys.
* print
* save
    * Info: the save operation will write all the key-value pairs to a user-specified file.
* quit
