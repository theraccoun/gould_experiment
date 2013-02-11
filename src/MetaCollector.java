import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Steven MacCoun
 */
public class MetaCollector extends InfoPanel{

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton continueButton = new JButton("Continue");

    public MetaCollector(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.firstNameField = new JTextField("First Name");
        this.lastNameField = new JTextField("Last Name");

        this.continueButton = new JButton("Continue");
        this.continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String subjectName = firstNameField.getName() + "_" + lastNameField.getName();
                Simulator.getInstance().setStudentInfo(subjectName);
                Simulator.getInstance().executeNextLine();
            }
        });

        this.add(firstNameField);
        this.add(lastNameField);
        this.add(continueButton);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int nameFieldWidth = this.getWidth()/4;
        int nameFieldHeight = this.getHeight()/10;
        int nameFieldX = this.getWidth()/2 - nameFieldWidth/2;

        int firstNameY = this.getHeight()/2 - nameFieldHeight*2;
        int lastNameY = this.getHeight()/2 - nameFieldHeight;
        int continueButtonY = this.getHeight()/2;

        firstNameField.setBounds(nameFieldX, firstNameY, nameFieldWidth, nameFieldHeight);int firstNameWidth = this.getWidth()/4;
        lastNameField.setBounds(nameFieldX, lastNameY, firstNameWidth, nameFieldHeight);
        continueButton.setBounds(nameFieldX, continueButtonY, nameFieldWidth, nameFieldHeight);

    }

}
