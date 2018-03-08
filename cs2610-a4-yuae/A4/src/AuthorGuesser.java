import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AuthorGuesser
{
    //create 3 hash tables for authors
    FrequencyHashTable eapHashTable;
    FrequencyHashTable mwsHashTable;
    FrequencyHashTable hplHashTable;
    ArrayList<String> common = new ArrayList<>();

    //constructor
    public AuthorGuesser(){}

    //method for mapping the words into author hash table
    public void set()throws IOException
    {
        //hash table for authors
        eapHashTable = new FrequencyHashTable();
        mwsHashTable = new FrequencyHashTable();
        hplHashTable = new FrequencyHashTable();

        //setup scanner
        File f = new File("train.csv");
        Scanner scan = new Scanner (f);

        //iterate through the file
        while(scan.hasNext() ) {
            String rawLine = scan.nextLine();
            Record r = new Record(rawLine);
            if (r.getAuthor().equals("EAP"))
            {
                //if it's written by EAP, increment each word
                for (String word : r.getText().split(" ") )
                {
                    eapHashTable.increment(word);
                }
            }
            if (r.getAuthor().equals("MWS"))
            {
                //it's written by MWS, increment each word
                for (String word : r.getText().split(" ") )
                {
                    mwsHashTable.increment(word);
                }
            }
            if (r.getAuthor().equals("HPL")) {
                //it's written by HPL, increment each word
                for (String word : r.getText().split(" ") )
                {
                    hplHashTable.increment(word);
                }
            }
        }
    }

    //method to delete common words
    public void delete(String key)
    {
        eapHashTable.remove(key);
        mwsHashTable.remove(key);
        hplHashTable.remove(key);
        //add this common word to ArrayList
        common.add(key);
    }

    //method for getting the result
    public int test() throws IOException
    {
        int count = 0;//variable storing the correct times

        //setup scanner
        File f = new File("test.csv");
        Scanner scan = new Scanner (f);
        //iterate through the file
        while(scan.hasNext() )
        {
            //initial the possibility for each author
            double eap = 1.0;
            double mws = 1.0;
            double hpl = 1.0;
            //variable holds the most possible author
            double largest;
            String author;

            String rawLine = scan.nextLine();
            Record r = new Record(rawLine);
            for (String word : r.getText().split(" ") )
            {
                if(common.contains(word))//if it's common word then doesn't change
                {
                    eap *= 1.0;
                    mws *= 1.0;
                    hpl *= 1.0;
                }
                else//calculating the possibility
                {
                    eap *= eapHashTable.get(word);
                    mws *= mwsHashTable.get(word);
                    hpl *= hplHashTable.get(word);
                }
            }

            //getting the most possible answer
            if(Math.max(eap,mws)==eap)
            {
                author = "EAP";
                largest = eap;
            }
            else
            {
                author = "MWS";
                largest = mws;
            }
            if(Math.max(largest,hpl)==hpl)
            {
                author = "HPL";
            }

            //checking if the result is right
            if (author.equals(r.getAuthor()))
            {
                count++;
            }
        }
        return count;
    }
}
