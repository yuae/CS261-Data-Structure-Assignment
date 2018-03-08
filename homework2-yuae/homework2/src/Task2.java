/**
 * Created by agodbout on 2017-10-04.
 */
public class Task2 {


    /**
     * A recursive method that draws a shrinking box on the screen
     *
     * @param length the length of the next side that is drawn
     * @param decay the amount the length shrinks by after each side is drawn
     * @param remainingSides how many sides are left to draw
     * @param turtle the turtle that does the drawing, initially this is in the bottom right corner of the screen
     */
    public static void shrinkingBox(double length, double decay, int remainingSides, Turtle turtle ) {

        /*
            your code here
            TODO 2: Complete this method in a recursive fashion to draw a shrinking box
        */
        if(remainingSides==0)
            return;
        else
        {
            //create turtle object
            Turtle t = turtle;


            t.goForward(length);
            t.turnLeft(-270);
            shrinkingBox(length*decay,decay,remainingSides-1,turtle);
        }

    }

    /**
     * An example method that draws something
     * @param length
     */
    public static void drawATriangle(double length) {
        Turtle t = new Turtle(0.05, 0.75, 30); //create a new turtle so we don't mess up your other turtle.
        t.goForward(length);
        t.turnLeft(-120);
        t.goForward(length);
        t.turnLeft(-120);
        t.goForward(length);
    }


    /**
     * Another example using recursion to draw a tree structure
     * @param branchLength bigger values draw longer branches of the tree and more of them
     * @param turtle the turtle that does the drawing
     * @param scale converts branch lengths into turtle drawing lengths
     */
    public static void tree(double branchLength, Turtle turtle, double scale) {
        if(branchLength < 1)
            return;
        double lineLen = branchLength / scale;
        System.out.println(lineLen);
        //draw the limb
        turtle.goForward(lineLen);

        //draw the left branch
        turtle.turnLeft(20.0);
        tree(branchLength - 2, turtle,scale);

        //draw the right branch
        turtle.turnLeft(-40.0);
        tree(branchLength - 2, turtle,scale);
        turtle.turnLeft(20.0);

        //go back to the base of the limb
        turtle.turnLeft(180.0);
        turtle.goForward(lineLen);
        turtle.turnLeft(180.0);
  }


    public static void main(String [] args) {

        //remove these 3 lines to remove the existing drawing
        //draw a tree and a triangular sun overhead
        //drawATriangle(0.2);
       // Turtle turtleTree = new Turtle(0.5, 0.05, 90);
        //tree(15, turtleTree, 100);



        //create a turtle in the bottom right corner of the screen facing upwards
        Turtle turtle = new Turtle(0.95, 0.05, 90.0);

        double length = 0.95;//95% of the screen
        double decay = 0.95; //each side is 95% of previous length
        int sides = 100; //draw a box with 100 sides

        shrinkingBox(length, decay, sides, turtle);
    }




}
