import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @Author: Steven MacCoun
 */
public class Trial extends JPanel implements KeyListener{

    private String sentence;
    private boolean isDisplaySentence;

    public Trial(String sentence){

        this.sentence = sentence;
        this.isDisplaySentence = false;

        setFocusable(true);
        setLayout(null);
        setBackground(new Color(253, 253, 253));
        this.addKeyListener(this);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(this.isDisplaySentence == true){

            Graphics2D g2d = (Graphics2D) g;

            int fSize = (int) (this.getHeight()/8.0);
            Font exp = new Font("Serif", Font.BOLD, fSize);
            Dimension expSize = getFontDimension(g, exp, sentence);
            g2d.setFont(exp);
            g2d.setPaint(Color.BLACK);

            g2d.drawString(sentence, (int)(this.getWidth()/2) - (int)(expSize.getWidth()/2),
                    (int)(this.getHeight()/2) - (int)(expSize.getHeight()/2));
        }

    }

    private void displaySentence(){
        this.isDisplaySentence = true;
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



    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if(keyEvent.getKeyChar() == 'h'){
            System.out.println("YOU hit h");
            this.isDisplaySentence = true;
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyChar() == 'h'){
            this.isDisplaySentence = false;
            repaint();
        }
    }
}
