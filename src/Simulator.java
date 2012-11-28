import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: Steven MacCoun
 */
public class Simulator extends JFrame{

    private static Simulator instance = null;

    private final String CSV_FILENAME = "words.csv";
    private ArrayList<Trial> allTrials = new ArrayList<Trial>();
    private int curTrialNum;
    private ArrayList<String> allLines = new ArrayList<String>();

    public static Simulator getInstance(){
        if(instance == null){
            instance = new Simulator();
        }

        return instance;
    }

    private Simulator(){}

    public void initialize(){
        InputStream inputStream = getClass().getResourceAsStream(CSV_FILENAME);

        if(inputStream == null){
            System.out.println("Could not load " + CSV_FILENAME);
        }

        InputStreamReader isr = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(isr);

        while(scanner.hasNextLine()){
            allLines.add(scanner.nextLine());
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);

        this.pack();
        this.setVisible(true);

        runNextTrial();
    }

    public void runNextTrial(){

        curTrialNum++;

        System.out.println(curTrialNum);

        if(curTrialNum < allLines.size()){
            String sentence = allLines.get(curTrialNum);
            Trial curTrial = new Trial(1, sentence);
            getContentPane().removeAll();
            add(curTrial.getRenderer());
            curTrial.getRenderer().requestFocusInWindow();
            validate();

        }
    }


}
