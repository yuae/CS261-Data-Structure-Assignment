import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javafx.util.Pair;

/* A little helper class to Drive Assignment 1.

   Once you implement CircularlyDoublyLinkedList you'll be able to
   run this to hear a theme song and play it back wards and forwards.
 */

public class DJ extends JFrame implements Runnable {

    //a rough version of mcDonald's I'm lovin it.
    public static final int[] MCD_PITCHES = {64,66,68,73,71,68,66,64,64, 0};
    public static final double [] MCD_DURATIONS = {0.2, 0.2, 0.45, 0.45, 0.45, 0.45, 0.2, 0.2, 0.45, 0.2};

    /*helper to convert piano keys to sound frequencies*/
    public static double pianoKeyToFrequency(int key) {
        return Math.pow(2.0, ((double)key - 49.0)/12.0) * 440.0;
    }

    /* failing tests produce a print out to standard output */
    public void performTests() {
        //perform a few checks ot make sure your circularly linked list is working correctly
        CircularlyDoublyLinkedList<Pair<Integer, Double> > testSong = new CircularlyDoublyLinkedList<>();

        for(int i = 0; i < MCD_PITCHES.length; i++) {
            //add items using add first
            testSong.addFirst(new Pair<Integer, Double> (MCD_PITCHES[i], MCD_DURATIONS[i]));
        }


        if(song.size() != MCD_PITCHES.length) {
            System.out.println("error 1");

        }
        if(testSong.size() != song.size()) {
            System.out.println("error 2");

        }

        for(int i = 0; i < song.size(); i++) {
            if(!testSong.last().equals(song.first()) ) {
                System.out.println("error 3");

            }
            testSong.rotate();
            song.rotateBackwards();
        }


        //remove all but one element in testSong
        while(testSong.size() > 1) {
            testSong.removeFirst();
        }

        CircularlyDoublyLinkedList<Pair<Integer, Double> > testSong2 = new CircularlyDoublyLinkedList<>();


        testSong2.addLast(testSong.first());
        if(testSong.size() != 1 ) {
            System.out.println("Error 4");
        }

        testSong2.rotateBackwards();
        testSong2.rotate();

        if(!testSong2.first().equals(testSong.last())) {
            System.out.println("Error 5");
        }

        if(!testSong2.removeLast().equals(testSong.removeFirst())) {
            System.out.println("Error 6");
        }

    }

    //this is the song
    private CircularlyDoublyLinkedList<Pair<Integer, Double> > song;
    private boolean stopped;
    private boolean forward;

    public DJ() {
        super("Sample DJ App");
        stopped = true;
        forward = true;

        //create a song using the circularly doubly linked list
        song = new CircularlyDoublyLinkedList<>();

        for(int i = 0; i < MCD_PITCHES.length; i++) {
            song.addLast(new Pair<Integer, Double> (MCD_PITCHES[i], MCD_DURATIONS[i]));
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                System.out.println(keyCode);

                if(keyCode == 71) {
                    if (stopped) {
                        stopped = false;
                        new Thread(DJ.this).start();
                    }
                }
                else if (keyCode == 83) {
                    stopped = true;
                }
                else
                    forward = !forward;
            }
        });
    }

    /** Play the song */
    public void run( ) {
        while(!stopped) {
            Pair<Integer, Double> nextNote;
            if(forward) {
                nextNote = song.first();
                song.rotate();
                StdAudio.play(pianoKeyToFrequency(nextNote.getKey()), nextNote.getValue(), 0.5);
            }
            else {
                nextNote = song.first();
                song.rotateBackwards();
                StdAudio.play(pianoKeyToFrequency(nextNote.getKey()), nextNote.getValue(), 0.5);
            }
        }
    }

    public static void main(String[] args) {

        DJ f = new DJ();

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel(new FlowLayout());
        jPanel.setPreferredSize(new Dimension(320, 480));

        JLabel startLabel = new JLabel("Press 'g' to start playing a song");
        JLabel label = new JLabel("Press 's' to stop song playback!");
        JLabel label2 = new JLabel("Press any other key to reverse playback direction!");

        jPanel.add(startLabel);
        jPanel.add(label);
        jPanel.add(label2);

        f.getContentPane().add(jPanel, BorderLayout.CENTER);
        f.pack();

        f.setFocusable(true);
        f.setVisible(true);
    }
}