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
    private boolean isDisplaySentence;
    private double releasedHTime;
    private JButton nextButton = new JButton("Next");
    private JButton prevButton = new JButton("Prev");

    public TrialRenderer(String trialName, String sentence){

        this.sentence = sentence;
        this.trialName = trialName;

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

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "8");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "Q");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, true), "released_p");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "pressed_p");
        am.put("released_p", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displaySentence(false);
                releasedHTime = System.currentTimeMillis();
                System.out.println("YOU just released h!");
            }
        });
        am.put("pressed_p", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displaySentence(true);
                System.out.println("YOU just released h!");
            }
        });
        am.put("8", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("a");
                double reactTime = (double)System.currentTimeMillis() - releasedHTime;
                System.out.println("REACTION TIME: " + reactTime);
                writeTrialInfoToFile(reactTime, "1");
            }
        });
        am.put("Q", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("a");
                double reactTime = (double)System.currentTimeMillis() - releasedHTime;
                System.out.println("REACTION TIME: " + reactTime);
                writeTrialInfoToFile(reactTime, "0");
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

            Graphics2D g2d = (Graphics2D) g;

            int panelWidth = this.getWidth();
            int panelHeight = this.getHeight();

            int fSize = (int) (this.getHeight()/8.0);
            Font exp = new Font("Serif", Font.BOLD, fSize);
            Dimension expSize = getFontDimension(g, exp, sentence);
            g2d.setFont(exp);
            g2d.setPaint(Color.BLACK);

            g2d.drawString(sentence, (panelWidth/2) - (int)(expSize.getWidth()/2),
                    (panelHeight/2) - (int)(expSize.getHeight()/2));

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
        this.isDisplaySentence = shouldDisplay;
        repaint();
    }
}
