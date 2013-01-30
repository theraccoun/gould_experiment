import au.com.bytecode.opencsv.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: Steven MacCoun
 */
public class Simulator extends JFrame{

    private static Simulator instance = null;

    private final String CSV_FILENAME = "experiment_layout_1.csv";
    private String subjectName;
    private File subjectFile;
    private static final String SUBJECT_DIRECTORY = "Subjects";
    private File outFile;
    private int curLine;
    private ArrayList<String[]> allLines = new ArrayList<String[]>();

    public static Simulator getInstance(){
        if(instance == null){
            instance = new Simulator();
        }

        return instance;
    }

    private Simulator(){
        this.subjectName = "Bob";
        outFile = new File("/tmp/" + subjectName);
    }

    public void initialize(){

        InputStream inputStream = getClass().getResourceAsStream(CSV_FILENAME);

        if(inputStream == null){
            System.out.println("Could not load " + CSV_FILENAME);
        }

        InputStreamReader isr = new InputStreamReader(inputStream);
        CSVReader reader = new CSVReader(isr);

        try {
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                for(String c : nextLine){
                    System.out.print(c + ",");
                }
                System.out.println(" ");
                allLines.add(nextLine);
            }
        } catch (IOException e) {
            System.out.println("Could not read line in file");
            e.printStackTrace();
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);

        this.pack();
        this.setVisible(true);

        runExperiment();
    }

    public void executeNextLine(){
        curLine++;
        executeCurLine();
    }

    public void executeCurLine(){

        if(curLine < allLines.size()){

            String[] curRow = allLines.get(curLine);
            String eType = curRow[0];
            System.out.println("eType: " + eType);

//            MetaCollector metaCollector = new MetaCollector("Name");
//            add(metaCollector);

            if(eType.matches("P[0-9]+T[0-9]+")){
                Trial t = new Trial("Prac", curRow[1]);
                runTrial(t);
            }
            else if(eType.matches("B[0-9]+T[0-9]+")){
                Trial t = new Trial("BOB", curRow[1]);
                runTrial(t);
            }
            else if(eType.matches("I[0-9]")){
                InstructionPanel ip = new InstructionPanel(curRow[1]);
                runInstruction(ip);
            }
            else if(eType.matches("meta")){
                MetaCollector mc = new MetaCollector();
                add(mc);
                validate();
            }
        }
        else{

        }

    }

    private void runExperiment(){
        curLine = 1;
        executeCurLine();

    }

    public void runTrial(Trial t){
        getContentPane().removeAll();
        add(t.getRenderer());
        t.runTrial();
        validate();

    }

    public void setStudentInfo(String name){
        File subjectsDir = new File(SUBJECT_DIRECTORY);
        if(!subjectsDir.isDirectory())
            subjectsDir.mkdir();

        this.subjectName = name;
        this.subjectFile = new File(SUBJECT_DIRECTORY + File.separator + this.subjectName);
        boolean success = (this.subjectFile).mkdirs();
        if (!success) {
            System.out.println("Failed to create directory: ./" + subjectName);
        }
    }

    private void runInstruction(InstructionPanel ip){
        getContentPane().removeAll();
        add(ip);
        ip.requestFocusInWindow();
        validate();
    }

    public void collectTrialAndWriteToFile(Trial t)
    {

    }


}
