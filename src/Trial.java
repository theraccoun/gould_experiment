import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @Author: Steven MacCoun
 */
public class Trial {

    private String subjectName;
    private JPanel renderer;
    private String sentence;
    private Character keyPressed;
    private double reactionTime;

    public Trial(String subjectName, String sentence){

        this.sentence = sentence;
        renderer = new Renderer(this);
    }


    public void runTrial(){
        this.renderer.requestFocusInWindow();
    }

    public String getSentence(){
        return this.sentence;
    }


    public JPanel getRenderer(){
        return renderer;
    }


}
