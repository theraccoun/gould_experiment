import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Steven MacCoun
 */
public class SimplePanel extends JPanel implements KeyListener {

    public SimplePanel(Color c){

        setFocusable(true);
        setLayout(null);
        setBackground(c);
        setVisible(true);
        this.addKeyListener(this);
    }
    public void keyTyped(KeyEvent keyEvent) {
        if(keyEvent.getKeyChar() == 'a'){
            System.out.println("a");
            System.out.println("KEY: "  + Thread.currentThread().getName());
            SimpleSim.getInstance().runNextTrial();
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
    }
    public void keyReleased(KeyEvent keyEvent) {
    }
}
