import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Steven MacCoun
 */
public class InstructionPanel extends JPanel{

    private JLabel instructions;
    private JButton nextButton;
    private JButton prevButton;


    public InstructionPanel(String imgPath) {

        this.setLayout(null);

        try {
            InputStream is = ClassLoader.getSystemResourceAsStream(imgPath);
            BufferedImage bi  = ImageIO.read(is);
            this.instructions = new JLabel(new ImageIcon(bi));
            this.instructions.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
            this.add(instructions);
        } catch (IOException ex) {
            System.out.println("Unable to load instruction image " + imgPath);
            ex.printStackTrace();
        }



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

//    public void paintComponent(Graphics g)
//    {
//        super.paintComponent(g);
//        setButtonPositions();
//    }


//    private void setButtonPositions(){
//
//        int buttonWidth = (int)(padD.getWidth()*0.2);
//        int buttonHeight = (int)(padD.getHeight()*0.1);
//        int buttonY = (int)(panelP2Y + borderThickness/2) - 10;
//
//        int nButton1X = (int)(panelP2X) - 10;
//        nextButton.setBounds(nButton1X, buttonY, buttonWidth, buttonHeight);
//
//        int pButton1X = (int)(panelP1X + borderThickness/2) + 10;
//        prevButton.setBounds(pButton1X, buttonY, buttonWidth, buttonHeight);
//    }

}
