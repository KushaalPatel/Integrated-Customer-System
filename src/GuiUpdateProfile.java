import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

// Allows user to update a profile - they choose which property to update

public class GuiUpdateProfile extends GuiBaseMenuOption {
    JComboBox<String> choiceCb;                // combo box for the user's choice
    int choice;                                      // integer corresponding to the users choice
    String[] info = {"Address","Phone","Income","Status","Use","Model","Year","Type","Method"};  // updatable field options
    JLabel updateFieldLabel;                        // label for update field
    
    
    // GuiUpdateProfile Constructor
    GuiUpdateProfile(GuiMainMenu mainMenu)
    {
        super(mainMenu);                         // call parent constructor
        String myFont = "Courier";
        
        this.setTitle("Update Profile");         // set title
        this.setSize(500, 620);                  // set window size

        button.setText("Find");                     // set button text
        button.setBounds(175, 520, 150, 40);        // set button bounds

        headerLabel.setText("Update Profile");      // set header label text
        adminIdLabel.setBounds(70, 100, 150, 50);   // set admin ID label bounds
        lastNameLabel.setBounds(70, 150, 150, 50);  // set last name label bounds

        adminIdTextField.setBounds(new Rectangle(220,110,210,33)); 
        lastNameTextField.setBounds(new Rectangle(220,160,210,33)); 

        // update label
        updateFieldLabel = new JLabel("Update Field: ");
        updateFieldLabel.setFont(new Font(myFont, Font.PLAIN, 17));
        updateFieldLabel.setBounds(70, 200, 150, 50);

        // choice combo box
        choiceCb = new JComboBox<>(info);
        choiceCb.setBounds(220,210,220,33);
        choiceCb.setFont(new Font(myFont, Font.PLAIN, 17));
        choiceCb.setAlignmentX((float) 0.5);

        errorLabel.setBounds(0, 300, 500, 33);        // set error label bounds

        // add components
        this.add(updateFieldLabel);
        this.add(choiceCb);
        this.setVisible(true);     
    }

    @Override
    public void actionHandler() {
        choice = choiceCb.getSelectedIndex();           // get the user choice
        new GuiUpdateProfileConfirm(this);              // display update profile confirmation
    }

    // Test Client
    public static void main(String[] args){
        CustomerProfDB db = new CustomerProfDB("database/db1.txt");              // create new db object
        try{
            db.initializeDatabase("database/db1.txt");                           // initialize db
            new GuiUpdateProfile(new GuiMainMenu(db));                      // create new update profile screen
        }catch (FileNotFoundException e){                                   // catch file not found exceptions
            System.out.println("The file could not be found");              // display error message
        }
    }
}
