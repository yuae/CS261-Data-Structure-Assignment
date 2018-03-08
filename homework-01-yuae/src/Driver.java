/**
 * /** Homework Number 1 CS2610
 Purpose: Get acquainted with the IntelliJ IDEA IDE and git and remind ourselves of some
 casting and inheritance rules.
 References: If you consulted other code/reference material to do this homework list the source
 Notes: 1. Generally it is not recommended to 'hide' fields in subclasses in java - this is done here
 for interest's sake. Hiding here means we have an instance variable named x, in A, B and C.
 2. This homework is a freebie - everyone will get credit. Just be cautioned that anyone not submitting
 will be asked to come visit me to prove you have figured out how to submit future homework and assignments
 through the git interface.

 Textbook creativity exercise C-2.21

 Sample exam 1 point question: Java fields (instance variables) are polymorphic. T or F?
 **/

public class Driver {

    public static void main(String [] args) {

        //create a C object
        C c = new C();

        //7 is my lucky number
        c.changeBaseAX(7);

        //test the results
        System.out.println( (c.getCX() == 3 && c.getAX() == 7 ? "Nice Work!": "Something's not yet working"));

        //SOMETHING TO THINK ABOUT - Why create getAX and getCX
        //as opposed to casting to type A and then calling getX() ?

        //remember that overridden methods in java are dynamically dispatched
        //if you aren't familiar with the term dynamic dispatch and/or polymorphism -> see text page 68
    }
}
