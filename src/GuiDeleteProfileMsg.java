import java.awt.*;
import java.io.FileNotFoundException;


public class GuiDeleteProfileMsg extends GuiBaseMsg {

    // GuiDeleteProfileMsg Constructor
    GuiDeleteProfileMsg(GuiMainMenu mainMenu)
    {
        super(mainMenu);                              // call parent constructor
        this.setTitle("Delete Profile");              // set title
        button.setText("OK");                         // set button text
        String myFont = "Courier";

        // header label
        headerLabel.setText("Delete Profile");
        headerLabel.setFont(new Font(myFont, Font.PLAIN, 30));
        headerLabel.setBounds(130, 20, 300, 50);

        // delete confirmation label
        msgLabel.setText("Profile Deleted!");
        msgLabel.setFont(new Font(myFont, Font.PLAIN, 16));
        msgLabel.setBounds(170, 100, 200, 50);

        this.setVisible(true);      // show window
    }

    // Test Client
    public static void main(String[] args){
        CustomerProfDB db = new CustomerProfDB("database/db1.txt");         // create db object
        try{
            db.initializeDatabase("database/db1.txt");               // initialize db
            new GuiDeleteProfileMsg(new GuiMainMenu(db));                   // create new delete profile confirm
        }catch (FileNotFoundException e){                                   // catch file not found exceptions
            System.out.println("The file could not be found");              // display error message
        }
    }
}
