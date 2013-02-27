import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Steven MacCoun
 */
public class InstructionPanel extends JPanel{

    private JLabel instructions;
    private JButton nextButton;
    private JButton prevButton;
    private static final Font buttonFont = new Font("Serif", Font.BOLD, 18);


    public InstructionPanel(String imgPath) {

        this.setBackground(new Color(173, 216, 230));

        imgPath = "resources" + File.separator + imgPath;

        try {
            File f = new File(imgPath);
            if(!f.exists())
                System.out.println(imgPath + " does not exist!");
            BufferedImage bi  = ImageIO.read(f);
            this.instructions = new JLabel(new ImageIcon(bi));
        } catch (IOException ex) {
            System.out.println("Unable to load instruction image " + imgPath);
            ex.printStackTrace();
        }

        this.nextButton = new JButton("Next");
        this.nextButton.setOpaque(true);
        this.nextButton.setVisible(true);
        this.nextButton.setFont(buttonFont);
        this.nextButton.setPreferredSize(new Dimension(150, 100));
        this.nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Simulator.getInstance().executeNextLine();
            }
        });


        this.prevButton = new JButton("Previous");
        this.prevButton.setOpaque(true);
        this.prevButton.setVisible(true);
        this.prevButton.setFont(buttonFont);
        this.prevButton.setPreferredSize(new Dimension(150, 100));
        this.prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Simulator.getInstance().executePrevLine();
            }
        });

        this.add(prevButton);
        this.add(instructions);
        this.add(nextButton);

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

}
