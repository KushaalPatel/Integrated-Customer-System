import java.io.FileNotFoundException;


public class GuiFindProfile extends GuiBaseMenuOption {

    // GuiFindProfile Constructor
    GuiFindProfile(GuiMainMenu mainMenu)
    {
        super(mainMenu);                            // call parent constructor
        this.setTitle("Find Profile");              // set title
        button.setText("Find");                     // set button text
        headerLabel.setText("Find Profile");        // set header label text
        this.setVisible(true);                      // show window
    }

    @Override
    public void actionHandler(){
        // find profile by adminId and last name and display it
        new GuiDisplayProfile(menu, menu.db.findProfile(adminIdTextField.getText(), lastNameTextField.getText()));
    }

    // Test Client
    public static void main(String[] args){
        CustomerProfDB db = new CustomerProfDB("database/db1.txt");             // create new db object
        try{
            db.initializeDatabase("database/db1.txt");                          // initialize db
            new GuiFindProfile(new GuiMainMenu(db));                            // create new find profile window
        }catch (FileNotFoundException e){                                       // catch file not found exceptions
            System.out.println("The file could not be found");                  // display error message
        }
    }
}
