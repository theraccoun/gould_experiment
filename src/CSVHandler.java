import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: theraccoun
 * Date: 10/28/11
 * Time: 12:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class CSVHandler {

    private static CSVHandler instance = null;
    private File csvFile;
    private File modCSV;
    private ArrayList<String> header;
    private ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
    private final int TRIAL_INDEX = 0;
    private final int TRIAL_TYPE_INDEX = 1;
    private final int BLOCK_INDEX = 2;
    private final int ITEM_INDEX = 3;
    private final int TERMNUM_INDEX = 4;
    private final int TERMTYPE_INDEX = 5;
    private final int STIM_INDEX = 6;
    private final int WHEEL_INDEX = 7;
    private final int MATCH_INDEX = 8;
    private final int CHIP_INDEX = 9;
    private final int R_INDEX = 10;
    private final int G_INDEX = 11;
    private final int B_INDEX = 12;
    private final int SPIN_INDEX = 13;
    private int curRow = 0;
    private String subjNumber;
    private String compLetter;

    public void setCSVFile(String csvFileName)
    {
        csvFile = new File(csvFileName);
        String curDir = System.getProperty("user.dir");
        String sysSep = System.getProperty("file.separator");
        modCSV = new File(System.getProperty("user.dir") + sysSep + "results.csv");
        if(!csvFile.exists() && !csvFile.canRead())
        {
            System.out.println("Cannot find CSV File!");
            System.exit(0);
        }

        indexCVSFile();
    }

    public static CSVHandler getInstance() throws FileNotFoundException {
        if(instance == null)
        {
            instance = new CSVHandler();
        }

        return instance;
    }

    protected CSVHandler() throws FileNotFoundException {
    }

    //    public void executeCSV() throws FileNotFoundException {
//
//        inFile = new CsvBeanReader(new FileReader(csvFile), CsvPreference.EXCEL_PREFERENCE);
//
//
//        try {
//
//            final String[] header = inFile.getCSVHeader(true);
//            for(String toke : header)
//            {
//                System.out.print(toke + " ");
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//    }
    public void enterSubjNum(String subNum)
    {
        subjNumber = subNum;
    }

    public void enterCompLet(String compLet)
    {
        compLetter = compLet;
    }
    public void executeCSV()
    {
//        int rowCounter = 0;
//        for(ArrayList<String> row : rows)
//        {
//            rowCounter += 1;
//            System.out.println("reading row " + rowCounter);
//
//            for(String col : row)
//            {
//                String trialNum = rows.get(rowCounter).get(TRIAL_INDEX);
//                System.out.println("trialNum is " + trialNum);
//
//                String wheelImage = "Wheel_" + rows.get(rowCounter).get(WHEEL_INDEX) + ".jpg";
//                String word = rows.get(rowCounter).get(STIM_INDEX);
//                int r = Integer.parseInt(rows.get(rowCounter).get(R_INDEX));
//                int g = Integer.parseInt(rows.get(rowCounter).get(G_INDEX));
//                int b = Integer.parseInt(rows.get(rowCounter).get(B_INDEX));
//                System.out.println(wheelImage);
//                Trial t = new Trial(Integer.parseInt(rows.get(12).get(0)), wheelImage, word, r, g, b);
//
//            }
//
//        }
    }

    public void proceedToNextRow() throws IOException {
        curRow += 1;
        if(curRow > 16)
        {
            writeTrial();
        }
        executeCurrentRow();
    }

    public void setCurRow(int whatRow)
    {
        curRow = whatRow;
    }

    public void executeCurrentRow()
    {
        String trialNum = rows.get(curRow).get(TRIAL_INDEX);
        //System.out.println("trialNum is " + trialNum);
        if(rows.get(curRow).size() > 2)
        {
            String wheelImage = "Wheel_" + rows.get(curRow).get(WHEEL_INDEX) + ".jpg";
            System.out.println(wheelImage);
            String word = rows.get(curRow).get(STIM_INDEX);
            int r = Integer.parseInt(rows.get(curRow).get(R_INDEX));
            int g = Integer.parseInt(rows.get(curRow).get(G_INDEX));
            int b = Integer.parseInt(rows.get(curRow).get(B_INDEX));
            // System.out.println(wheelImage);
//            Trial t = new Trial(Integer.parseInt(rows.get(curRow).get(0)), curRow, wheelImage, word, r, g, b);
//            MasterFrame.getInstance().runTrial(t);
        }

    }


    private void indexCVSFile()
    {
        if(subjNumber != null && compLetter != null)
        {
            ArrayList<String> meta = new ArrayList<String>();
            meta.add("subjNumber");
            meta.add(subjNumber);
            meta.add("compLetter");
            meta.add(compLetter);

            rows.add(meta);
        }

        if(!csvFile.exists() && csvFile.canRead())
        {
            System.out.println("Cannot find CSV File!");
            System.exit(0);
        }

        try {
            Scanner scanner = new Scanner(csvFile);

            int lineNum = 0;
            while(scanner.hasNextLine())
            {
                lineNum += 1;
                String curLine = scanner.nextLine();

                Scanner columnscan = new Scanner(curLine);
                columnscan.useDelimiter(",");
                ArrayList<String> column = new ArrayList<String>();
                while(columnscan.hasNext())
                {
                    String curToken = columnscan.next();
                    column.add(curToken);
                    //System.out.print(curToken + " , ");
                }


                for(String toke : column)
                {
                    System.out.println(toke);
                }

                rows.add(column);
                System.out.println();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void writeTrial() throws IOException {
        FileWriter fw = new FileWriter(modCSV);
        BufferedWriter out = new BufferedWriter(fw);

        for(ArrayList<String> row : rows)
        {
            for(String col : row)
            {
                out.write(col);
                out.write(",");
            }

            out.newLine();
        }

        out.close();

        System.exit(0);
    }

    public void appendTrialInfo(Trial t){


//        System.out.println("Trial Number : " + t.getTrialNum());
//        rows.get(t.getCsvRow()).add(Integer.toString(t.getClickedSquare()));
//        System.out.println("--clicked square --> " + t.getClickedSquare());
//        rows.get(t.getCsvRow()).add(Double.toString(t.getTotalTime()));
//        System.out.println("--totalTime --->" + t.getTotalTime());
    }
}
