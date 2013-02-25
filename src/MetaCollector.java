import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Steven MacCoun
 */
public class MetaCollector extends InfoPanel{

    private JTextField subjectNumber;
    private JButton continueButton = new JButton("Continue");

    public MetaCollector(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.subjectNumber = new JTextField("Subject Number");

        this.continueButton = new JButton("Continue");
        this.continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String subjectName = subjectNumber.getText();
                Simulator.getInstance().setStudentInfo(subjectName);
                Simulator.getInstance().executeNextLine();
            }
        });

        this.add(subjectNumber);
        this.add(continueButton);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int nameFieldWidth = this.getWidth()/4;
        int nameFieldHeight = this.getHeight()/10;
        int nameFieldX = this.getWidth()/2 - nameFieldWidth/2;

        int firstNameY = this.getHeight()/2 - nameFieldHeight*2;
        int continueButtonY = this.getHeight()/2;

        subjectNumber.setBounds(nameFieldX, firstNameY, nameFieldWidth, nameFieldHeight);int firstNameWidth = this.getWidth()/4;
        continueButton.setBounds(nameFieldX, continueButtonY, nameFieldWidth, nameFieldHeight);

    }

}
