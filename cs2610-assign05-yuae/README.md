CS 2610 Assignment 5

Due December 3, 23:59h

Find in this repository a roughly accurate database of every Westjet flight currently offered.

The database is a plain text file `routes.txt` containing 3 columns. The first column is the airline in this case it is always WS which is Westjet's airline code. The second column is the departure city's airport code and the third column is the arrival city's airport code.

For instance if you look in the file you'll notice that Westjet offers a robust selection of flights into and out of Charlottetown (airport code YYG) namely one flight in (from Toronto YYZ) and one flight out (to Toronto YYZ). That doesn't mean however that the only place you can get to is Toronto from Charlottetown, because you can connect in Toronto to many other flights.

Your task is to write a program that given a departure airport code and a desired destination will print out either a possible series of flights eventually leading from the departure airport to the destination airport or an indication that no such path is possible.

You've been provided with code that implements a simplistic Adjacency List inside the `City.java` class. I.e. Given a city object `myCity` you can obtain all the other cities that have an incoming flight from `myCity` by calling `myCity.getNeighbours()`

You've also been provided with a Driver class that loads a database of all the cities: `TreeMap<String, City>  db`. A TreeMap is a red-black tree, you can obtain for instance the City object for Charlottetown by calling `db.get('yyg')`; The cities are coded by airport codes and `yyg` is Charlottetown.  

Your contribution is to complete the Router class and specifically: `Iterable<String> route(String start, String finish, int maxSteps)` which will perform either a depth first or breadth first search through the flights offered by Westjet (as stored in the `TreeMap<String, City) db`; to determine **any** possible path (sequence of cities with flights between them) from start to finish.

Note:

- We have not learned algorithms that find efficient routes yet (that comes next year), we have however learned about exhaustive searches like depth first and breadth first search that will tell you if something is possible. You should implement one of the known searches depth first or breadth first search (watch out for cycles) and so you may find a long collection of flights leading to the eventual destination, or perhaps find no path at all. Make sure you terminate any search that has surpassed the specified maxSteps.

- There is a sample intellij project provided in folder A5. You can open folder A5 in Intellij and complete the existing Router class.

Sample program:
```
Where are you departing from? ('exit') YYG
Where are you going to? ABV
Sorry there is no combination of flights leading from YYG to ABV.

Where are you departing from? ('exit' to quit) YYG
Where are you going to? YYT
The following flights lead from YYG to YYT: YYG to YYZ, YYZ to MCO and MCO to YYT.
```
Translating the above that reads that to get from Charlottetown you could travel from Charlottetown to Toronto, then Toronto to Orlando and finally Orlando to St. John's. Which is an example of the potential solution a depth or breadth first search will provide (not optimal ... ).




