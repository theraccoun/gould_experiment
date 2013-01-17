import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * @author Steven MacCoun
 */
public class InstructionPanel extends JPanel {

    private String instructionText;
    private boolean isShowRecBorder = true;
    private Font instructionFont;

    public InstructionPanel(String text) {

        this.instructionText = text;
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
                System.out.println("YOU HIT N!");
                Simulator.getInstance().executeNextLine();
            }
        });
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        requestFocus();
        float sWidth = this.getWidth();
        float sHeight = this.getHeight();
        float rectWidth = sWidth/20.0f;
        Dimension panelD = this.getSize();
        Dimension padD = new Dimension((int) (0.6*panelD.width),
                (int) (0.6*panelD.height));
        Point panelP = new Point((int)(this.getWidth() * 0.2), (int)(this.getHeight()*0.20));

        Point p1 = new Point((int)(panelP.getX() - rectWidth), (int)(panelP.getY()-rectWidth));
        Point p2 = new Point((int)(panelP.getX() + padD.getWidth() + rectWidth), (int)p1.getY());
        Point p3 = new Point((int)p1.getX(), (int)(panelP.getY() + padD.getHeight() + rectWidth));
        Point p4 = new Point((int)p2.getX(), (int) p3.getY());

        if(isShowRecBorder)
        {
            Rectangle2D border = new Rectangle.Double(panelP.getX()- rectWidth/2, panelP.getY()-rectWidth/2,
                    padD.getWidth()+rectWidth, padD.getHeight()+rectWidth);


            g2d.setPaint(Color.BLACK);
            Stroke bStroke = new BasicStroke(rectWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2d.setStroke(bStroke);
            g2d.draw(border);


            Color gradColor1 = new Color(173,216,230);
            Paint bPaint = new GradientPaint((float)panelP.getX(), (float)panelP.getY(), gradColor1,
                    (float)(panelP.getX() + padD.getWidth()), (float)(panelP.getY() + padD.getHeight()),
                    Color.WHITE);
            g2d.setPaint(bPaint);
            g2d.fillRect((int)panelP.getX(), (int)panelP.getY(), (int)padD.getWidth(), (int)padD.getHeight());

            int fSize = (int) (padD.getHeight()/12.5);
            this.instructionFont = new Font("Serif", Font.BOLD | Font.ITALIC, fSize);
            Dimension textSize = getFontDimension(g, instructionFont, instructionText);
            g2d.setFont(instructionFont);
            g2d.setPaint(Color.BLACK);
            int centerPadX = (int)((2.0*panelP.getX() + padD.getWidth())/2.0);

            g2d.drawString(instructionText, centerPadX - (int)(textSize.getWidth()/2),
                    (int) (panelP.getY() + padD.getHeight()/2) - instructionFont.getSize());

        }
        else
        {
            int fSize = (int) (padD.getHeight()/12.5);
            Font sbarF = new Font("Serif", Font.BOLD | Font.ITALIC, fSize);
            String sbar = new String("Press SpaceBar to Begin Practice Round");
            Dimension sbarSize = getFontDimension(g, sbarF, sbar);
            g2d.setFont(sbarF);
            g2d.setPaint(Color.BLACK);
            int centerPadX = (int)((2.0*panelP.getX() + padD.getWidth())/2.0);

            g2d.drawString(sbar, centerPadX - (int)(sbarSize.getWidth()/2),
                    (int) (panelP.getY() + padD.getHeight()/2) - sbarF.getSize());
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

}
