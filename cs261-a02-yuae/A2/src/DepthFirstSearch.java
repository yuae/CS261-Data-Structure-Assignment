import java.util.Scanner;
import java.io.*;

public class DepthFirstSearch
{
    public static void main (String [] args)
    {
        DepthFirstSearch search = new DepthFirstSearch();
        search.start();
    }

    private char [][] map;//map array
    private int count = 0;//total baggage
    private int startCol = 1;
    private int startRow = 1;
    public DepthFirstSearch()//constructor
    {}
    public void start()//calling method
    {
        read();
        search(map,startCol,startRow);
    }
    private void read()//reading the file
    {
        System.out.print("Enter a map file (example map.txt): " );
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();
        try {
            File file = new File(fileName);
            Scanner fileScan = new Scanner(file);
            int cols = fileScan.nextInt();
            int rows = fileScan.nextInt();
            map = new char[cols][rows];//notice that column is x-axis for the map, row is y-axis for the map
            for(int row = 0; row < rows; row++) {
                String line = fileScan.next(); //reads in the next line of the map
                for(int col = 0; col < cols; col++) {
                    map[col][row] = line.charAt(col);
                    if(map[col][row]=='R')
                        startCol = col;
                        startRow = row;
                }
            }
            fileScan.close();
        }catch(IOException e){
            System.out.print("map file not formatted correctly\n");
        }
    }
    private void search(char [][] map, int col, int row)//searching method
    {
        if(visited(col,row))//check if visited
            return;
        if(isWall(col,row))//check if is wall
            return;
        if(hasBaggage(col,row))//if found give feedback
        {
            count++;//increment found dangerous baggage
            System.out.println("Located at column " + (col+1) + ", row " + (row+1));//location of baggage
            System.out.println("Pieces of dangerous luggage found: " + count);//total baggage
        }
        map[col][row] = 'O';//put marker
        search(map,col+1,row);//move East
        search(map,col,row+1);//move South
        search(map,col,row-1);//move North
        search(map,col-1,row);//move West
        if(count==0)//if no baggage found give feedback
            System.out.println("No dangerous luggage found.");
    }
    private boolean visited(int col, int row)
    {
        return map[col][row]=='O';//if equals marker
    }
    private boolean isWall(int col, int row)
    {
        return map[col][row]=='W';//if equals wall
    }
    private boolean hasBaggage(int col, int row)
    {
        return map[col][row]=='X';//if equals baggage marker
    }
}