import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created with IntelliJ IDEA.
 * User: theraccoun
 * Date: 2/11/13
 * Time: 12:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class InfoPanel extends JPanel {

    protected static final double BORDER_INSET = 0.2;

    protected float borderThickness;

    protected Dimension padD;
    protected Dimension panelD;

    protected Point panelP;
    protected double panelP1X;
    protected double panelP1Y;
    protected double panelP2X;
    protected double panelP2Y;

    public InfoPanel(){

        this.setLayout(null);
        requestFocusInWindow();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawBorder(g2d);
    }

    protected void drawBorder(Graphics2D g2d){
        borderThickness = this.getWidth()/20.0f;
        panelD = this.getSize();
        padD = new Dimension((int) (0.6*panelD.width),
                (int) (0.6*panelD.height));

        // Set the points of the border
        panelP = new Point((int)(this.getWidth() * BORDER_INSET), (int)(this.getHeight()* BORDER_INSET));
        panelP1X = panelP.getX()- borderThickness/2;
        panelP1Y = panelP.getY()-borderThickness/2;
        panelP2X = padD.getWidth()+borderThickness;
        panelP2Y = padD.getHeight()+borderThickness;

        Rectangle2D border = new Rectangle.Double(panelP1X, panelP1Y, panelP2X, panelP2Y);

        g2d.setPaint(Color.BLACK);
        Stroke bStroke = new BasicStroke(borderThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(bStroke);
        g2d.draw(border);

        Color gradColor1 = new Color(173,216,230);
        Paint bPaint = new GradientPaint((float)panelP.getX(), (float)panelP.getY(), gradColor1,
                (float)(panelP.getX() + padD.getWidth()), (float)(panelP.getY() + padD.getHeight()),
                Color.WHITE);
        g2d.setPaint(bPaint);
        g2d.fillRect((int)panelP.getX(), (int)panelP.getY(), (int)padD.getWidth(), (int)padD.getHeight());
    }
}
