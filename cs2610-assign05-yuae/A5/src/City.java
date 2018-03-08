import java.util.Set;
import java.util.TreeSet;

/**
* Class that implements an Adjacency List Graph
* getNeighbours returns the list of connections to this city
*
  This is slightly simplified from the text because there are no weights on the edges 
**/
public class City implements Comparable<City> {

    //cities I can connect to
    //use a set here because no duplicates and fast access 
    private Set<City> neighbours;
    //the name of this city (airport code)
    private String name;
    
    public City (String airportCode) {
        neighbours = new TreeSet<>();
        name = airportCode;
    }

    /**
    * Add a connection to City city
    *
    **/ 
    public void addNeighbour(City city) {
        neighbours.add(city);
    }

    /**
    * Return the neighbouring cities
    **/
    public Set<City> getNeighbours() {
        return neighbours;
    }   

    /* Return the name of this city */
    public String getName() { 
        return name;
    }

    /* Compare this city to another */
    public int compareTo(City c) {
        return this.name.compareTo(c.getName());
    }

    /* override equals */
    public boolean equals(City c) {
        return this.name.equals(c.getName());
    }
}

