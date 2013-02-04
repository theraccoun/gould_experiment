import javax.swing.*;

/**
 * @Author: Steven MacCoun
 */
public class Trial {

    private String trialName;
    private double reactionTime;
    private String keyPressed;

    public Trial(String trialName, double reactionTime, String keyPressed){

        this.trialName = trialName;
        this.reactionTime = reactionTime;
        this.keyPressed = keyPressed;
    }

    public double getReactionTime(){
        return this.reactionTime;
    }

    public String getTrialName(){
        return this.trialName;
    }

    public String getKeyPressed(){
        return  keyPressed;
    }


}
