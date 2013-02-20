import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

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
    private int curLine;
    private CSVWriter resultsWriter;
    private ArrayList<String[]> allLines = new ArrayList<String[]>();

    public static Simulator getInstance(){
        if(instance == null){
            instance = new Simulator();
        }

        return instance;
    }

    private Simulator(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("JAVA VERSION:::  " + System.getProperty("java.version"));
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

            reader.close();

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

    public void executePrevLine(){
        curLine--;
        executeCurLine();
    }

    public void executeCurLine(){

        if(curLine < allLines.size()){

            String[] curRow = allLines.get(curLine);
            String eType = curRow[0];

            if(eType.matches("[PB][0-9]+T[0-9]+")){
                TrialRenderer tr = new TrialRenderer(curRow[0], curRow[1]);
                runTrial(tr);
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

    public void runTrial(TrialRenderer trenderer){
        getContentPane().removeAll();
        setBackground(Color.BLACK);
        validate();
        add(trenderer);
        trenderer.requestFocusInWindow();
        setBackground(Color.white);
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
            System.exit(0);
        }
        else{
            try {
                String fileName = this.subjectFile + File.separator + this.subjectName + "_results.csv";
                resultsWriter = new CSVWriter(new FileWriter(fileName), ',');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void runInstruction(InstructionPanel ip){
        getContentPane().removeAll();
        add(ip);
        ip.requestFocusInWindow();
        validate();
    }

    public void collectTrialAndWriteToFile(Trial t){

        try {
            String trialInfo = (t.getTrialName() + ',' + t.getReactionTime() + ',' + t.getKeyPressed());
            String[] commaDelimTrialInfo = trialInfo.split(",");
            resultsWriter.writeNext(commaDelimTrialInfo);
            resultsWriter.flush();
            executeNextLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
