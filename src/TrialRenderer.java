import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author Steven MacCoun
 */
public class TrialRenderer extends JPanel{

    private String sentence;
    private String trialName;
    private JLabel sentenceLabel;
    private boolean isDisplaySentence;
    private double releasedHTime;
    private boolean hasPressedP;

    public TrialRenderer(String trialName, String sentence){

        this.sentence = sentence;
        this.trialName = trialName;
        this.hasPressedP = false;

        this.sentenceLabel = new JLabel("<html><p>" + sentence + "</p></html>");
        this.sentenceLabel.setHorizontalAlignment( SwingConstants.CENTER );
        this.sentenceLabel.setVerticalAlignment(SwingConstants.TOP);
        this.add(sentenceLabel);

        setFocusable(true);
        setLayout(null);
        setLayout(null);
        setVisible(true);
        requestFocusInWindow();

        setKeyBindings();

    }

    private void setKeyBindings(){
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0), "8");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "Q");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, true), "released_p");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "pressed_p");

        am.put("released_p", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!hasPressedP){
                    hasPressedP = true;
                    displaySentence(false);
                    releasedHTime = System.currentTimeMillis();
                }
            }
        });
        am.put("pressed_p", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!hasPressedP)
                    displaySentence(true);
            }
        });
        am.put("8", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hasPressedP){
                    double reactTime = (double)System.currentTimeMillis() - releasedHTime;
                    writeTrialInfoToFile(reactTime, "0");
                }

            }
        });
        am.put("Q", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hasPressedP){
                    double reactTime = (double)System.currentTimeMillis() - releasedHTime;
                    writeTrialInfoToFile(reactTime, "1");
                }
            }
        });
    }

    private void writeTrialInfoToFile(double reactionTime, String keyPressed){
        Trial t = new Trial(this.trialName, reactionTime, keyPressed);
        Simulator.getInstance().collectTrialAndWriteToFile(t);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(this.isDisplaySentence == true){

            int fSize = (int) (this.getHeight()/14.0);
            Font exp = new Font("Serif", Font.BOLD, fSize);
            Dimension expSize = getFontDimension(g, exp, sentence);

            this.sentenceLabel.setFont(exp);
            this.sentenceLabel.setBounds(0, this.getHeight()/2 - (int)expSize.getHeight(), this.getWidth(), this.getHeight()/2);

        }

    }

    private Dimension getFontDimension(Graphics g, Font f, String s)
    {
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics metrics = g2d.getFontMetrics(f);
        int hgt = metrics.getHeight();
        int adv = metrics.stringWidth(s);

        Dimension d =  new Dimension(adv+2, hgt+2);
        return d;
    }

    public void displaySentence(boolean shouldDisplay){

        if(shouldDisplay){
            this.add(this.sentenceLabel);
        } else{
            this.remove(this.sentenceLabel);
        }

        this.isDisplaySentence = shouldDisplay;
        repaint();
    }
}
