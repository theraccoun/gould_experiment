import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @author Steven MacCoun
 */
public class SimpleSim extends JFrame{

    private static SimpleSim instance = null;

    public static SimpleSim getInstance(){
        if(instance == null){
            instance = new SimpleSim();
        }

        return instance;
    }

    private SimpleSim(){}

    public void initialize(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.pack();
        this.setVisible(true);

        runNextTrial();
    }

    public void runNextTrial(){
        System.out.println("NEXT: " + Thread.currentThread().getName());
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        SimplePanel simplePanel = new SimplePanel(new Color(r, g, b));
        JPanel contentPane = (JPanel) this.getContentPane();

        contentPane.removeAll();
        contentPane.add(simplePanel);
        contentPane.revalidate();
        contentPane.repaint();

        validate();
        repaint();
    }


}
