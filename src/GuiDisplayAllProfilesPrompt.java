import java.io.FileNotFoundException;



public class GuiDisplayAllProfilesPrompt extends GuiBaseMenuOption {

    // GuiDeleteProfile Constructor
    GuiDisplayAllProfilesPrompt(GuiMainMenu mainMenu)
    {
        super(mainMenu);                                  // call parent constructor
        this.setTitle("Display All Profiles");            // set title

        // remove unneeded labels
        this.remove(lastNameLabel);
        this.remove(lastNameTextField);

        errorLabel.setText("No customer profiles found.");
        button.setText("Display");                        // set button text
        headerLabel.setText("Display All Profiles");      // set header label text
        headerLabel.setBounds(75, 20, 500, 50);
        this.setVisible(true);                            // show window
    }

    @Override
    public void actionHandler(){
        new GuiDisplayAllProfiles(menu, menu.db.findFirstProfile(adminIdTextField.getText()), adminIdTextField.getText());
    }

    // Test Client
    public static void main(String[] args){
        CustomerProfDB db = new CustomerProfDB("database/db1.txt");         // create db object
        try{
            db.initializeDatabase("database/db1.txt");               // initialize db
            new GuiDisplayAllProfilesPrompt(new GuiMainMenu(db));           // create new delete profile window
        }catch (FileNotFoundException e){                                   // catch file not found exception
            System.out.println("The file could not be found");              // display error message
        }
    }
}
