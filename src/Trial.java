import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @Author: Steven MacCoun
 */
public class Trial {

    private JPanel renderer;
    private Integer trialNum;
    private String sentence;
    private boolean isDisplaySentence;

    public Trial(Integer trialNum, String sentence){

        this.trialNum = trialNum;
        this.sentence = sentence;
        this.isDisplaySentence = false;

        renderer = new Renderer(sentence);
    }



    public void setSentence(String sentence){
        this.sentence = sentence;
    }

    public String getSentence(){
        return this.sentence;
    }


    public JPanel getRenderer(){
        return renderer;
    }


}
