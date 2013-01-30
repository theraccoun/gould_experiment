import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Steven MacCoun
 */
public class MetaCollector extends JPanel{

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPanel firstPanel = new JPanel();
    private JPanel secondPanel = new JPanel();
    private JLabel firstNameLabel = new JLabel("First name: ");
    private JLabel lastNameLabel = new JLabel("Last name: ");
    private JButton continueButton = new JButton("Continue");
    private final Font textFieldFont = new Font("Serif", Font.BOLD | Font.ITALIC, 24);

    public MetaCollector(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        firstNameField = new JTextField();
        firstNameField.setPreferredSize(new Dimension(120,25));
        firstNameField.setFont(textFieldFont);
        firstNameField.setVisible(true);

        firstNameLabel.setVisible(true);
        firstNameLabel.setFont(textFieldFont);

        lastNameField = new JTextField();
        lastNameField.setPreferredSize(new Dimension(120, 25));
        lastNameField.setFont(textFieldFont);
        lastNameField.setVisible(true);

        lastNameLabel.setVisible(true);
        lastNameLabel.setFont(textFieldFont);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String subjectName = firstNameField.getText() + "_" + lastNameField.getText();
                Simulator.getInstance().setStudentInfo(subjectName);
                Simulator.getInstance().executeNextLine();
            }
        });

        firstPanel.add(firstNameLabel);
        firstPanel.add(firstNameField);
        this.add(firstPanel);

        secondPanel.add(lastNameLabel);
        secondPanel.add(lastNameField);
        secondPanel.add(continueButton);
        this.add(secondPanel);

    }

}
