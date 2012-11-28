import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Steven MacCoun
 */
public class Renderer extends JPanel{

    private String sentence;
    private boolean isDisplaySentence;

    public Renderer(String sentence){

        this.sentence = sentence;

        setFocusable(true);
        setLayout(null);
        setBackground(new Color(253, 139, 132));
        setLayout(null);
        setVisible(true);
        requestFocusInWindow();

        setKeyBindings();

    }

    private void setKeyBindings(){
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "A");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, true), "released_h");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, false), "pressed_h");
        am.put("released_h", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displaySentence(false);
                System.out.println("YOU just released h!");
            }
        });
        am.put("pressed_h", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displaySentence(true);
                System.out.println("YOU just released h!");
            }
        });
        am.put("A", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("a");
                System.out.println("KEY: " + Thread.currentThread().getName());
                Simulator.getInstance().runNextTrial();
            }
        });
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
