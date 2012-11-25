import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    private ArrayList<String> allTrials = new ArrayList<String>();
    private int curTrialNum;
    private TrialPane curTrialPanel;

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
            allTrials.add(scanner.nextLine());
        }

        curTrialNum = 0;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
//        setContentPane(new TrialPane(allTrials.get(curTrialNum)));

        this.pack();
        this.setVisible(true);

        runNextTrial();
    }

    public void runNextTrial(){

        curTrialNum++;

        System.out.println(curTrialNum);

        if(curTrialNum < allTrials.size()){
            String sentence = allTrials.get(curTrialNum);
            System.out.println(sentence);
//            getContentPane().
//            TrialPane t = new TrialPane(sentence);
//            t.validate();
//            setContentPane(t);
//            revalidate();
            JPanel contentPane = (JPanel) this.getContentPane();

            contentPane.removeAll();
            contentPane.add(new TrialPane(sentence));
            contentPane.revalidate();
            contentPane.repaint();
            pack();
//            curTrialPanel = new TrialPane(sentence);
//            System.out.println("curSentPan: "  + curTrialPanel.getSentence());
//            getContentPane().add(curTrialPanel);
//            revalidate();
//            repaint();
//            pack();
        }
    }


}
