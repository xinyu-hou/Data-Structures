# Skiplist Implementation
## Overview
Implementation of a randomized skip list. The insert and delete operations might need to rearrange the entire list. For populate level process the insert operation, each node is promoted to the next higher level with a probability of 1/2.

### Operation Time Complexity

| Operation      | Average case | Worst case |
| ----------- | ----------- | ----------- |
| Insert      | O(log n)       | O(n)       |
| Delete   | O(log n)        | O(n)       |
| Search   | O(log n)        | O(n)       |

## How to Run
Run the main method in the ProgramRunner.java file. There are four valid commands: 
* insert [target]
* delete [target]
* lookup [target]
* quit

After performing each operation, the program will print out the current skiplist.

## Example Run
![valid commands](https://github.com/xinyu-hou/Data-Structures/blob/main/Skiplist/res/commands.png)
insert 12
![insert 12](https://github.com/xinyu-hou/Data-Structures/blob/main/Skiplist/res/insert_12.png)
insert 11
![insert 11](https://github.com/xinyu-hou/Data-Structures/blob/main/Skiplist/res/insert_11.png)
insert 98
![insert 98](https://github.com/xinyu-hou/Data-Structures/blob/main/Skiplist/res/insert_98.png)
lookup 11
![lookup 11](https://github.com/xinyu-hou/Data-Structures/blob/main/Skiplist/res/lookup_11.png)
delete 11
![delete 11](https://github.com/xinyu-hou/Data-Structures/blob/main/Skiplist/res/delete_11.png)
lookup 11
![lookup deleted 11](https://github.com/xinyu-hou/Data-Structures/blob/main/Skiplist/res/lookup_deleted_11.png)
quit
![quit](https://github.com/xinyu-hou/Data-Structures/blob/main/Skiplist/res/quit.png)

## Resources
1. Skip List Visualization Tool: https://cmps-people.ok.ubc.ca/ylucet/DS/SkipList.html

