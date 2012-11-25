import javax.swing.*;
import java.awt.*;

/**
 * @Author: Steven MacCoun
 */
public class Simulator extends JFrame {

    public Simulator(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);

        pack();
        setVisible(true);
    }

    public void runTrial(Trial trial){

        setContentPane(trial);
    }
}
