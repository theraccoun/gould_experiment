import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: Steven MacCoun
 */
public class Simulator extends JFrame{

    private static Simulator instance = null;

    private final String CSV_FILENAME = "experiment_layout_1.csv";
    private ArrayList<Trial> allTrials = new ArrayList<Trial>();
    private int curLine;
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
            String line = scanner.nextLine();
            System.out.println(line);
            allLines.add(scanner.nextLine());
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);

        this.pack();
        this.setVisible(true);

        runExperiment();
    }

    public void executeNextLine(){

        curLine++;
        if(curLine < allLines.size()){

            String[] lineSeg = allLines.get(curLine).split(",");
            System.out.println("FIRST: " + lineSeg[0]);

            String eType = lineSeg[0];

            if(eType.matches("P[0-9]T[0-9]+")){

            }
            else if(eType.matches("B[0-9]T[0-9]+")){
                curTrialNum++;
                Trial t = new Trial("BOB", lineSeg[1]);
                runTrial(t);
            }
            else if(eType.matches("I[0-9]")){
                InstructionPanel ip = new InstructionPanel();
            }
        }
        else{

        }

    }

    private void runExperiment(){

        curLine = 10;
        executeNextLine();

    }

    public void runTrial(Trial t){


        getContentPane().removeAll();
        add(t.getRenderer());
        t.runTrial();
        validate();

    }


}
