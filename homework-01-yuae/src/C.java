/*
    Class C extends B

    A <--- B <---- C

 TODO: 1. Add your Name and Student ID here!
    YE YUAN 145919
 */

public class C extends B {

    //protected is accessible in subclasses
    protected int x; 

    public C () {
        //assign the value 3 to x
        x = 3;
    }

    /*
     TODO: 2. Implement this method so it changes instance variable x from the A class
     *but not B's x nor C's x
     *do not modify the code for classes A or B
     *Textbook creativity exercise C-2.21
     *hint: java methods are polymorphic but fields are not
    */
    public void changeBaseAX(int newX ) {   ((A)this).x = newX;

    }

    //getter for C's x variable
    public int getCX() {
        return x;
    }
}
