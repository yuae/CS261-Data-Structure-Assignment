import java.util.TreeMap;
import java.util.Deque;
import java.util.Scanner;
import java.io.*;

public class Driver { 

    /**
        For this task our Graph is a Collection of Cities which are stored in db
        A city object contains a collection of all the neighbouring cities    
    **/     
    public static void populateDB(TreeMap<String, City> db, Scanner scan) {
        while(scan.hasNext()) {
            String line = scan.nextLine(); 
            String [] fields = line.split(",");
            String start = fields[1];
            String finish = fields[2];
            
            City c = getOrCreate(db, start);
            //create a link between start and finish
            c.addNeighbour( getOrCreate(db, finish) );
        }
    }

    //if this city(string) is in the db return it 
    //otherwise create a City obj and then add it to the db and return it
    //this makes sure I don't have duplicates of the same city 
    public static City getOrCreate(TreeMap<String, City> db, String city) { 
        if(db.containsKey(city) ){
            return db.get(city);
        }
        else {
            City c = new City(city);
            db.put(city, c);
            return c;
        }
    }
    /*
        Perform the input and output with users
    */
    public static void askQuestions(TreeMap<String, City> db) {

        Router router = new Router(db);
        Scanner inputScan = new Scanner(System.in);
        System.out.println("Where are you departing from (airport code - \"exit\" to quit)?");
        String start = inputScan.next().toUpperCase();
        while(!start.equals("EXIT")) {

            System.out.println("Where are you going to (airport code)?");
            String finish = inputScan.next().toUpperCase();

            System.out.println("What is your maximum allowable connections?");
            int max = inputScan.nextInt();

            Iterable<String> route = router.route(start, finish, max);
            if (route == null) {
                System.out.println("Could not find a route from " + start + " to " + finish);

            }
            else {
                for(String city : route) {
                    System.out.print(city + " ");
                }
                System.out.println();
            }

            System.out.println("Where are you departing from (airport code - \"exit\" to quit)?");
            start = inputScan.next().toUpperCase();
        }
        
        System.out.println("exit");
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        //create a TreeMap to act as our database
        TreeMap<String, City> database = new TreeMap<>();
        File f = new File ("routes.txt");
        Scanner fileScan = new Scanner (f);      
        populateDB(database, fileScan);
        askQuestions(database);
    }
}
