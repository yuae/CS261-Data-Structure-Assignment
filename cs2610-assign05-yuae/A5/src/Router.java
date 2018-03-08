import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//is it possible to get from start to end
public class Router {
    private Map<String,City>db;
    public Router (Map<String, City> database) {
        //probably save that database off as a class member variable
        db = database;
    }

    //search to find a route from start to finish in maxSteps or less steps
    public Iterable <String> route(String start, String finish, int maxSteps) { 

        /* Implement this method and any other method that might help in the task of finding a route from start to finish */

        ArrayList<String> step = new ArrayList<>();
        dSearch(start,finish,step, maxSteps);
        if(step.contains(finish))
            return step;
        return null; //hopefully you'll return some Iterable object like an ArrayList for example with the names of cities leading from start to finish
    }

    //depth search method
    private void dSearch(String start, String finish, ArrayList<String> step, int max)
    {
        if(step.contains(start))//if visited return
            return;
        if(step.size()>max)//if exceed max step return
            return;
        if(start.equals(finish))//if found return
        {
            step.add(finish);
            return;
        }
        step.add(start);//add position to solution
        for (City c : db.get(start).getNeighbours())//walking through neighbor
        {
            dSearch(c.getName(), finish, step, max);//recursive
            if (step.contains(finish))//if found possible solution return result
                return;
        }
        step.remove(start);//if not remove current position from solution
    }

    //breadth first search (not used in the program)
    private void bfSearch(String start, String finish, ArrayList<String> step, int max)
    {

        if(step.contains(start))
            return;
        if(step.size()>max)
            return;
        step.add(start);
        searchNeighbor(step,start,finish);
        if(step.contains(finish))
        {
            if(step.size()<=max+1)
                return;
            else
            {
                step.remove(finish);
            }
        }
        for(City c : db.get(start).getNeighbours())
        {
            bfSearch(c.getName(),finish,step,max);
            if(step.get(step.size()-1).equals(finish))
                return;
        }
        step.remove(start);
    }
    private void searchNeighbor(ArrayList<String> step, String position, String finish)
    {
        for(City c : db.get(position).getNeighbours())
        {
            if(c.getName().equals(finish))
            {
                step.add(finish);
                return;
            }
        }

    }
}
