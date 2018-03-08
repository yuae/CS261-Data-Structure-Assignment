import java.io.*;
public class Driver
{
    public static void main(String[]args)throws IOException
    {
        AuthorGuesser ag = new AuthorGuesser();
        ag.set();//put the words into hash table
        //testing remove method
        //ag.delete("and");
        //ag.delete("the");
        //ag.delete("a");
        //ag.delete("to");
        //ag.delete("of");
        //ag.delete("with");
        //ag.delete("this");
        //ag.delete("at");
        //ag.delete("an");
        //ag.delete("these");
        System.out.println(ag.test()+"/100");//prints result
    }
}
