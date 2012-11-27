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
    private Trial curTrialPanel;
    private int curTrialNum;
    private Trial curTrial;
    private JFrame frame;

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
            String sentence = scanner.nextLine();
//            allTrials.add(new Trial(sentence));
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

        if(curTrialNum < allTrials.size()){
            Trial curTrial = allTrials.get(curTrialNum);
            String sentence = allTrials.get(curTrialNum).getSentence();
            System.out.println(sentence);
//            getContentPane().
            this.setContentPane(curTrial.getRenderer());
            this.validate();
//            getContentPane().validate();
//            getContentPane().repaint();
//            validate();
//            repaint();
//            t.validate();
//            setContentPane(t);
//            revalidate();
//            JPanel contentPane = (JPanel) this.getContentPane();

            //           contentPane.removeAll();
//            contentPane.add(new Trial(sentence));
//            contentPane.revalidate();
//            contentPane.repaint();
//            pack();
//            curTrialPanel = new Trial(sentence);
//            System.out.println("curSentPan: "  + curTrialPanel.getSentence());
//            getContentPane().add(curTrialPanel);
//            revalidate();
//            repaint();
//            pack();
        }
    }


}
