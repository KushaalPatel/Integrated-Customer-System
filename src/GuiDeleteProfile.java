import java.io.FileNotFoundException;



public class GuiDeleteProfile extends GuiBaseMenuOption {

    // GuiDeleteProfile Constructor
    GuiDeleteProfile(GuiMainMenu mainMenu)
    {
        super(mainMenu);                            // call parent constructor
        this.setTitle("Delete Profile");            // set title
        button.setText("Delete");                   // set button text
        headerLabel.setText("Delete Profile");      // set header label text
        
        this.setVisible(true);                      // show window
    }

    @Override
    public void actionHandler(){
        menu.db.deleteProfile(adminIdTextField.getText(), lastNameTextField.getText());     // delete customer profile by adminId and last name
        new GuiDeleteProfileMsg(menu);                                                      // delete confirmation msg
    }

    // Test Client
    public static void main(String[] args){
        CustomerProfDB db = new CustomerProfDB("database/db1.txt");         // create db object
        try{
            db.initializeDatabase("database/db1.txt");                      // initialize db
            new GuiDeleteProfile(new GuiMainMenu(db));                      // create new delete profile window
        }catch (FileNotFoundException e){                                   // catch file not found exception
            System.out.println("The file could not be found");              // display error message
        }
    }
}
