
//import packages
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game
{
    //private treenode class
    private class TreeNode
    {
        private TreeNode left = null;
        private TreeNode right = null;
        private String value;

        //constructor
        public TreeNode(String value)
        {
            this.value = value;
        }

        //getters
        public String getValue()
        {
            return value;
        }
        public char getOperator()
        {
            return value.charAt(value.length()-1);
        }

        //setters
        public TreeNode getLeft()
        {
            return left;
        }
        public void setLeft(TreeNode left)
        {
            this.left = left;
        }
        public TreeNode getRight()
        {
            return right;
        }
        public void setRight(TreeNode right)
        {
            this.right = right;
        }
    }

    //driver
    public static void main(String[]args)
    {
        Game g = new Game();
        Scanner s = new Scanner(System.in);
        System.out.println("Wanna play a game?");
        char answer;
        do
        {
            answer = s.nextLine().toUpperCase().charAt(0);

            switch (answer) {
                case 'Y':
                    g.start();
                    break;
                case 'N':
                    System.out.println("OK, bye.");
                    System.exit(0);
                default:
                    System.out.println("Please enter valid operation.");
                    break;
            }
        }
        while(answer!='Y');
        System.out.println("Thanks for playing, bye.");
    }

    //variables
    private Scanner s = new Scanner(System.in);
    private TreeNode root = null;
    private String filename = "";
    private boolean saveStatus =false;

    //constructor
    public Game()
    {}

    //methods
    public void start()
    {
        int status;

        do
        {
            //main menu
            System.out.println("1.Play the game");
            System.out.println("2.Read knowledge");
            System.out.println("3.Save Knowledge");
            System.out.println("4.Quit the game");

            status = s.nextInt();//scan command
            s.nextLine();//clear scanner

            //actions
            switch(status)
            {
                case 1 :
                {
                    if(root == null)
                    {
                        createInitialGame();//create initial games
                    }
                    play(root);
                    break;
                }
                case 2 :
                {
                    System.out.println("Enter file name(eg : sample.txt)");
                    String filename = s.nextLine();
                    try
                    {
                        FileReader fr = new FileReader(filename);
                        root = readFile(fr,fr.read());
                        fr.close();
                        System.out.println("Done");
                    }
                    catch(IOException e)
                    {
                        System.out.println("No such file exist.");
                    }
                    break;
                }
                case 3 :
                {
                    if(root == null)//if no knowledge exist
                        System.out.println("Nothing to be written");
                    else
                    {
                        System.out.println("Enter file name");
                        filename = s.nextLine();
                        try
                        {
                            FileWriter fw = new FileWriter(filename,false);
                            saveFile(root,fw);
                            fw.close();
                        }
                        catch(IOException e)
                        {
                            System.out.println("Unable to write file.");
                        }
                        System.out.println("Done.");
                        saveStatus = true;
                    }
                    break;
                }
                case 4 :
                    if(!saveStatus)//if knowledge not saved
                    {
                        System.out.println("You are about to quit without saving are you sure?");
                        if(s.nextLine().toUpperCase().charAt(0) == 'N')
                        {
                            status = 3;//move to save status
                        }
                    }
                    break;
                default :
                {
                    System.out.println("Please enter valid integer command");
                    break;
                }
            }
        }
        while(status!=4);
    }

    private TreeNode play(TreeNode root)
    {
        char a;
        if(root == null)//check valid tree
        {
            return null;
        }

        //print messages
        process(root);

        do
        {
            a = s.nextLine().toUpperCase().charAt(0);
            if( a != 'Y' && a != 'N')
                System.out.println("Please enter Yes or No.");
        }
        while(a != 'Y' && a!= 'N');//check valid answer

        if(a == 'Y')
        {
            //if yes for question
            root.setLeft(play(root.getLeft()));
            //if yes for answer
            if(root.getOperator()=='.')
            {
                System.out.println("Yeah, beat you.");
            }
        }
        else
        {
            //if no for question
            root.setRight(play(root.getRight()));
            //if no for answer
            if(root.getOperator()=='.')
            {
                System.out.println("I’m stumped, What is it?");
                String answer = s.nextLine().trim();
                System.out.println("Please type a key word(s) that only "
                        +answer+" have but not for "+root.getValue());
                String question = s.nextLine().trim();
                TreeNode an = new TreeNode(answer+".");
                TreeNode qn = new TreeNode(question+"?");
                qn.setLeft(an);
                qn.setRight(root);
                root = qn;//set current answer node to question node
            }
        }
        return root;//return node
    }

    private void process(TreeNode tr)
    {
        if(tr.getOperator() == '?')//if it's a question
        {
            System.out.println("Is it (having) "+tr.getValue());
        }
        else//if it's an answer
        {
            System.out.println("Ah, I got it. It's "+tr.getValue());
        }
    }

    private void saveFile(TreeNode root,FileWriter fw)
    {
        if(root==null)
            return;
        try
        {
            fw.write(root.getValue()+" ");
        }
        catch(IOException e)
        {
            System.out.println("Unable to write file.");
        }
        saveFile(root.getLeft(),fw);
        saveFile(root.getRight(),fw);
    }

    private TreeNode readFile(FileReader fr, int f)
    {
        if(f == -1)//if reach to end
            return null;
        String word = "";//construct word
        TreeNode n = null;//initial node
        int ch = f;//take current position value
        try
        {
           while((char)ch != ' '&& ch!=-1)//if it's not delimiter or the end of the file
           {
               word += (char) ch;//add current char to the word
               ch = fr.read();//read next char value
           }
           n = new TreeNode(word.trim());//create node
           if(n.getOperator() != '.')//if it's question
           {
               n.setLeft(readFile(fr,fr.read()));//set next node to question
               n.setRight(readFile(fr,fr.read()));
           }
           else
           {
               return n;//return answer node
           }
        }
        catch(IOException e)
        {
            System.out.println("Can't read file");
        }

        return n;
    }

    //initialize game
    private void createInitialGame()
    {
        TreeNode qn = new TreeNode("Immortal?");
        TreeNode an1 = new TreeNode("Vampire.");
        TreeNode an2 = new TreeNode("WareWolf.");
        qn.setLeft(an1);
        qn.setRight(an2);
        root = qn;
    }
}
