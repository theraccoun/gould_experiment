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
    private JLabel instructionLabel;
    private Font instructionFont;
    private JButton nextButton;
    private JButton prevButton;


    public InstructionPanel(String text ) {

        this.setLayout(null);

        this.instructionText = "<html><p>" + text + "</p><html>";
        this.instructionLabel = new JLabel(instructionText);
        this.instructionLabel.setHorizontalAlignment( SwingConstants.CENTER );
        this.add(instructionLabel);

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
        this.instructionFont = new Font("Serif", Font.PLAIN, fSize);
        g2d.setFont(instructionFont);
        g2d.setPaint(Color.BLACK);

        instructionLabel.setFont(instructionFont);
        int textX = (int)(panelP1X + borderThickness);
        int textY = this.getHeight()/4;
        int textWidth = (int)(padD.getWidth() - borderThickness);
        int textHeight;
        // Heights less than twenty cause matrix inversion issues
        if(panelD.getHeight() > 20)
            textHeight = (int)(panelD.getHeight()/2.0);
        else
            textHeight = 30;

        instructionLabel.setBounds(textX, textY, textWidth, textHeight);

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

}
