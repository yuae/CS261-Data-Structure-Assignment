# CS261-A02
Due date: Oct 21 23:59h.

The Charlottetown airport has a growing concern. Namely people are leaving their luggage unattended throughout the airport. On some occasions passengers are forgetting their luggage altogether and boarding their plane. This leaves a number of pieces of luggage in various places throughout the airport.

The airport has purchased a robot that can maneuver through the airport and analyze luggage to ensure it is not dangerous. The airport however did not purchase the software to help the robot navigate throughout the airport.

You job is to write software to help the robot search the entire airport and report back the **number** and **locations** of all dangerous luggage.

You will read in a map representing the airport and then use a DEPTH FIRST SEARCH algorithm to ensure the robot travels to all reachable squares in the map.

A sample map:
```
10 6
WWWWWWWWWW
WEREEWEXEW
WEEEEWEEEW
WEEEEWEEEW
WEEEEEEEEW
WWWWWWWWWW
```

The first two numbers are the columns and rows of the map. Followed by rows x columns characters such that a W indicates a wall (the robot **cannot** travel through walls), an R indicates the current location of the Robot, an X indicates a dangerous package (of course in real life we wouldn't know ahead of time where the X's are) and lastly an E indicates an open space that the robot can travel through. You may assume the robot can move through or occupy any tile that is not a W, i.e., an X doesn't block the robot.

For the above sample map the output might be:
```
Pieces of dangerous luggage found: 1
Locations of dangerous luggage: (1, 7)
```
The locations within the map start at (0,0) in the top left corner and are reported as (row, col). You can assume the map is closed, i.e., W's surround exterior row, col positions in the map.

The robot can move one square at a time in any of 4 directions, N, S, E(ast) or W(est) but not through walls.

A sample map is provided in file map.txt.

Tips:
1) Ensure you keep track of where the robot has been, to avoid going in circles.

2) Do not assume all dangerous luggage is reachable (some might be trapped behind walls)

3) in case you are curious here is how I might read in the map file from a string fileName (you are welcome to use this code):

```java

/** code provided as example of file reading **/
System.out.print("Enter a map file (example map.txt): " );
Scanner scan = new Scanner(System.in);
String fileName = scan.next();
try {
    File file = new File(fileName);
    Scanner fileScan = new Scanner(file);
    int cols = fileScan.nextInt();
    int rows = fileScan.nextInt();
    char[][] map = new char[rows][cols];
    for(int row = 0; row < rows; row++) {
        String line = fileScan.next(); //reads in the next line of the map
        for(int col = 0; col < cols; col++) {
            map[row][col] = line.charAt(col);
        }
    }
    fileScan.close();
}catch(IOException e){
    System.out.print("map file not formatted correctly\n");
}
```

4) If you find yourself writing tedious code like if map[row+1][col] == 'W' or row+1 == 'V', if map[row][col+1] == 'W' or map[row][col+1] =='V' it might be time to think about using classes and/or methods to make things more readable and robust.


Place your Solution in an Intellij project called A2 that sits within this Repository (you may like to copy the map.txt file into that project):
```
CS261-A02-YOURUSERNAME
|   README.md
|   .gitignore
|   map.txt
|___A2
    |   your
    |   intellij
    |   project files
```



