import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author Steven MacCoun
 */
public class InstructionPanel extends InfoPanel {

    private String instructionText;
    private Font instructionFont;
    private JButton nextButton;
    private JButton prevButton;


    public InstructionPanel(String text ) {

        this.setLayout(null);

        this.instructionText = text;

        this.nextButton = new JButton("Next");
        this.nextButton.setVisible(true);
        this.nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Simulator.getInstance().executeNextLine();
            }
        });
        this.add(nextButton);


        this.prevButton = new JButton("Prev");
        this.prevButton.setVisible(true);
        this.prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Simulator.getInstance().executePrevLine();
            }
        });
        this.add(prevButton);

        requestFocusInWindow();
        setKeyBindings();
    }

    private void setKeyBindings(){
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), "N");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0), "B");
        am.put("N", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Simulator.getInstance().executeNextLine();
            }
        });
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int fSize = (int) (padD.getHeight()/12.5);
        this.instructionFont = new Font("Serif", Font.BOLD | Font.ITALIC, fSize);
        Dimension textSize = getFontDimension(g, instructionFont, instructionText);
        g2d.setFont(instructionFont);
        g2d.setPaint(Color.BLACK);
        int centerPadX = (int)((2.0*panelP.getX() + padD.getWidth())/2.0);

        g2d.drawString(instructionText, centerPadX - (int)(textSize.getWidth()/2),
                (int) (panelP.getY() + padD.getHeight()/2) - instructionFont.getSize());

        setButtonPositions();
    }



    private void setButtonPositions(){

        int buttonWidth = (int)(padD.getWidth()*0.2);
        int buttonHeight = (int)(padD.getHeight()*0.1);
        int buttonY = (int)(panelP2Y + borderThickness/2) - 10;

        int nButton1X = (int)(panelP2X) - 10;
        nextButton.setBounds(nButton1X, buttonY, buttonWidth, buttonHeight);

        int pButton1X = (int)(panelP1X + borderThickness/2) + 10;
        prevButton.setBounds(pButton1X, buttonY, buttonWidth, buttonHeight);
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

}
