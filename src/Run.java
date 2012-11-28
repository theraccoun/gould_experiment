import javax.swing.*;
import java.awt.*;

/**
 *@author Steven MacCoun
 */
public class Run {

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex){
                    ex.printStackTrace();
                }

                Simulator.getInstance().initialize();
            }
        });
    }
}
