import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//  Base menu options

public class GuiBaseMenuOption extends JFrame implements ActionListener{
    JButton button;                                                     // button
    JTextField adminIdTextField;
    JTextField lastNameTextField;                                       // admin ID and last name text field
    GuiMainMenu menu;                                                   // main menu object
    JLabel headerLabel;
    JLabel adminIdLabel;
    JLabel lastNameLabel;
    JLabel errorLabel;                                                  // header, admin ID, last name, and error labels
    String[] useOptions = {"Business","Personal","Both"};                       // use options
    String[] statusOptions = {"Active", "Inactive"};                            // status options
    String[] typeOptions = {"Sedan", "Hatchback", "Luxury", "Sport", "Other"};  // type options
    String[] methodOptions = {"New", "Certified Pre-Owned", "Used", "Other"};   // method options
    String myFont = "Courier";

    // GuiBaseMenuOption Constructor
    GuiBaseMenuOption(GuiMainMenu mainMenu)
    {
        menu = mainMenu;       

        // if window is closed, return to main menu
        final GuiBaseMenuOption guiBaseMenuOption = this;
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.returnToMainMenu(guiBaseMenuOption);
            }
        });

        // set background and size
        Color c1 = new Color(39, 150, 81);
        this.getContentPane().setBackground(c1);
        this.setLayout(null);
        this.setSize(500, 320);

        // button
        button = new JButton();
        button.setBounds(175, 220, 150, 40);
        button.setFont(new Font(myFont, Font.PLAIN, 18));
        button.addActionListener(this);

        // header label
        headerLabel = new JLabel();
        headerLabel.setFont(new Font(myFont, Font.PLAIN, 30));
        headerLabel.setBounds(160, 20, 300, 50);

        // admin ID label
        adminIdLabel = new JLabel("Admin ID: ");
        adminIdLabel.setFont(new Font(myFont, Font.PLAIN, 17));
        adminIdLabel.setBounds(70, 80, 150, 50);

        // last name label
        lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setFont(new Font(myFont, Font.PLAIN, 17));
        lastNameLabel.setBounds(70, 130, 150, 50);

        // admin ID text field
        adminIdTextField = new JTextField(20);
        adminIdTextField.setFont(new Font(myFont, Font.PLAIN, 17));
        adminIdTextField.setBounds(180,88,260,33);

        // last name text field
        lastNameTextField = new JTextField(20);
        lastNameTextField.setFont(new Font(myFont, Font.PLAIN, 17));
        lastNameTextField.setBounds(180,138,260,33);

        // error label
        errorLabel = new JLabel("The customer profile could not be found.", SwingConstants.CENTER);
        errorLabel.setFont(new Font(myFont, Font.BOLD, 14));
        errorLabel.setBounds(0, 175, 500, 33);
        errorLabel.setForeground(Color.red);

        // add components
        this.add(button);
        this.add(headerLabel);
        this.add(adminIdLabel);
        this.add(lastNameLabel);
        this.add(adminIdTextField);
        this.add(lastNameTextField);
        this.setResizable(false);
    }

    
    public void actionHandler() { // action handler which subclasses override
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == button){       // if button clicked
            try{
                actionHandler();                // call action handler
                this.dispose();                 // close this 
            }
            catch (Exception e){                // catch error
                this.add(errorLabel);           // add error label
                this.repaint();                 // updates this 
            }
        }
    }
}
