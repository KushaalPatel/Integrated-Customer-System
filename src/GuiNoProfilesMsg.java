import java.io.FileNotFoundException;



public class GuiNoProfilesMsg extends GuiBaseMsg {

    // GuiNoProfilesMsg Constructor
    GuiNoProfilesMsg(GuiMainMenu mainMenu)
    {
        super(mainMenu);                                 // call parent constructor
        this.setTitle("No Profiles Found");              // set title
        button.setText("Back");                          // set button text
        headerLabel.setText("Unable to proceed");        // set header label text
        msgLabel.setText("         No profiles found.");          // set message label text
        this.setVisible(true);                           // show window
    }

    // Test Client
    public static void main(String[] args){
        CustomerProfDB db = new CustomerProfDB("database/db1.txt");      // create db object
        try{
            db.initializeDatabase("database/db1.txt");                        // initialize empty db
            new GuiNoProfilesMsg(new GuiMainMenu(db));                           // create new no profiles screen
        }catch (FileNotFoundException e){                                        // catch file not found exceptions
            System.out.println("The file could not be found");                   // display error message
        }
    }
}
