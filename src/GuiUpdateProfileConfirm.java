import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.NoSuchElementException;
import javax.swing.JTextField;

// Allows user to update a property of a profile
// This is tested by using the test client of GuiUpdateProfile

public class GuiUpdateProfileConfirm extends JFrame implements ActionListener{
    JButton updateButton;                   // update button
    JLabel updateFieldLabel;                // update field
    JLabel errorLabel;                      // update error
    JTextField updateTextField;             // update text field
    JComboBox<String> updateDropDown;       // update drop down
    GuiUpdateProfile update;                // update updateprofile
    CustomerProf customerProfile;           // customer profile to update
    String myFont = "Courier";


    private void createTextField() {
        // update text field
        updateTextField = new JTextField(20);
        updateTextField.setBounds(200,158,250,33);
        updateTextField.setFont(new Font(myFont, Font.PLAIN, 17));
        this.add(updateTextField);
    }

    private void createDropDown(String[] arr) {
        // update drop down
        updateDropDown = new JComboBox<>(arr);
        updateDropDown.setBounds(190,160,250,33);
        updateDropDown.setFont(new Font(myFont, Font.PLAIN, 17));
        this.add(updateDropDown);
    }

    private void checkIfEmpty(JTextField field) {
        if (field.getText().isEmpty()) {            // if field is empty, throw no such element exception
            throw new NoSuchElementException();
        }
    }

    GuiUpdateProfileConfirm(final GuiUpdateProfile updateProfile)
    {
        update = updateProfile;                        

        // if window closed, return to update profile
        final GuiUpdateProfileConfirm guiUpdateProfileConfirm= this;
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guiUpdateProfileConfirm.dispose();
                new GuiUpdateProfile(updateProfile.menu);
            }
        });

        customerProfile = update.menu.db.findProfile(update.adminIdTextField.getText(), update.lastNameTextField.getText()); // customer profile to update

        // set background, title, size
        this.setTitle("Update");
        Color c1 = new Color(39, 150, 81);
        this.getContentPane().setBackground(c1);
        this.setLayout(null);
        this.setSize(500, 320);

        // update button
        updateButton = new JButton("Submit");
        updateButton.setBounds(175, 220, 150, 40);
        updateButton.setFont(new Font(myFont, Font.PLAIN, 17));
        updateButton.addActionListener(this);

        // header label
        JLabel headerLabel = new JLabel("Update");
        headerLabel.setFont(new Font(myFont, Font.PLAIN, 30));
        headerLabel.setBounds(195, 20, 300, 50);

        // admin Id label
        JLabel adminIdLabel = new JLabel("Admin ID:            "+update.adminIdTextField.getText());
        adminIdLabel.setFont(new Font(myFont, Font.PLAIN, 17));
        adminIdLabel.setBounds(60, 70, 300, 50);

        // last name label
        JLabel lastNameLabel = new JLabel("Last Name:          "+update.lastNameTextField.getText());
        lastNameLabel.setFont(new Font(myFont, Font.PLAIN, 17));
        lastNameLabel.setBounds(60, 110, 300, 50);

        // update label
        updateFieldLabel = new JLabel(update.choiceCb.getSelectedItem()+": ");
        updateFieldLabel.setFont(new Font(myFont, Font.PLAIN, 17));
        updateFieldLabel.setBounds(60, 150, 150, 50);
        // error label
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setFont(new Font(myFont, Font.BOLD, 14));
        errorLabel.setBounds(0, 190, 500, 33);
        errorLabel.setForeground(Color.red);

        // add components
        this.add(updateButton);
        this.add(headerLabel);
        this.add(adminIdLabel);
        this.add(lastNameLabel);
        this.add(updateFieldLabel);
        this.add(errorLabel);

        switch(update.choice)       // choice USER
        {
            case 3:
                createDropDown(update.statusOptions);      // create drop down for status options
                break;
            case 4:
                createDropDown(update.useOptions);         // use options
                break;
            case 7:
                createDropDown(update.typeOptions);        // type options
                break;
            case 8:
                createDropDown(update.methodOptions);      // method options
                break;
            default:
                createTextField();                  // create text field for rest
        }
        this.setResizable(false);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String empty = "This field cannot be empty.";
        if (event.getSource() == updateButton)          // update button
        {
            switch(update.choice)                       // handle choice
            {
                case 0:             
                    try {               
                        checkIfEmpty(updateTextField);
                        customerProfile.updateAddress(updateTextField.getText()); //update address if not empty
                        update.menu.returnToMainMenu(this);     // return main menu
                    } catch(NoSuchElementException e) {       // catch errors
                        errorLabel.setText(empty);
                        this.repaint();
                    }
                    break;
                case 1:                
                    try {               
                        checkIfEmpty(updateTextField);
                        customerProfile.updatePhone(updateTextField.getText()); //update phone if not empty
                        update.menu.returnToMainMenu(this);  //return main menu
                    } catch(NoSuchElementException e) {      // catch error
                        errorLabel.setText(empty);
                        this.repaint();
                    } catch(Exception e) {
                        errorLabel.setText("Please enter a valid phone number (e.g. 203999999).");
                        this.repaint();
                    }
                    break;
                case 3:                
                    customerProfile.updateStatus((String) updateDropDown.getSelectedItem());   // update status
                    update.menu.returnToMainMenu(this);                               // return to main menu
                    break;
                case 2:          
                    try {         
                        checkIfEmpty(updateTextField);
                        customerProfile.updateIncome(Float.parseFloat(updateTextField.getText())); // update income if not empty
                        update.menu.returnToMainMenu(this); //return main menu
                    } catch(NoSuchElementException e) {     // catch error
                        errorLabel.setText(empty);
                        this.repaint();
                    } catch (Exception e){
                        errorLabel.setText("Please enter a float as the customer's co-pay.");
                        this.repaint();
                    }
                    break;
                case 4:                
                    customerProfile.updateUse((String) updateDropDown.getSelectedItem());    // update use
                    update.menu.returnToMainMenu(this);                                   // return to main menu
                    break;
                case 5:               
                    try {             
                        checkIfEmpty(updateTextField);
                        customerProfile.getVehicleInfo().updateModel(updateTextField.getText()); //update model
                        update.menu.returnToMainMenu(this);         // return main menu
                    } catch(NoSuchElementException e) {    // catch error
                        errorLabel.setText(empty);
                        this.repaint();
                    }
                    break;
                case 6:      
                    try {            
                        checkIfEmpty(updateTextField);
                        customerProfile.getVehicleInfo().updateYear(updateTextField.getText()); //update year
                        update.menu.returnToMainMenu(this); //return to main menu
                    } catch(NoSuchElementException e) {     //catch errors
                        errorLabel.setText(empty); 
                        this.repaint();
                    } catch(Exception e){
                        errorLabel.setText("Please enter a valid year (e.g. 2001).");
                        this.repaint();
                    }
                    break;
                case 7:              
                    customerProfile.getVehicleInfo().updateType((String) updateDropDown.getSelectedItem());   // update type 
                    update.menu.returnToMainMenu(this);                                               // return to main menu
                    break;
                case 8:              
                    customerProfile.getVehicleInfo().updateMethod((String) updateDropDown.getSelectedItem());   // update method 
                    update.menu.returnToMainMenu(this);                                               // return to main menu
                    break;
            }
        }
    }
}

