import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;


public class GuiDisplayProfile extends GuiBaseMenuOption {
    JLabel[] label1 = new JLabel[13];       // labels for the names of the current customer profile
    JLabel[] label2 = new JLabel[13];       // labels for the values of the current customer profile
    String[] info = {"Admin ID","First Name","Last Name","Address","Phone","Income","Status","Use","Model","Year","Type","Method"};     // string of the property values
    

    // GuiDisplayProfile Constructor
    public GuiDisplayProfile(GuiMainMenu mainMenu, CustomerProf prof)
    {
        super(mainMenu);          
        String myFont = "Courier";

        // remove unneeded fields
        this.remove(adminIdTextField);
        this.remove(adminIdLabel);
        this.remove(lastNameTextField);
        this.remove(lastNameLabel);

        // set title and size
        this.setTitle("Display Profile");
        this.setSize(500, 625);

        // header label
        headerLabel.setText("Customer Profile");
        headerLabel.setBounds(100, 20, 300, 50);
        
        String[] input = {prof.getAdminId(), prof.getFirstName(), prof.getLastName(), prof.getAddress(), prof.getPhone(),
                Float.toString(prof.getIncome()), prof.getStatus(), prof.getUse(), prof.getVehicleInfo().getModel(),
                prof.getVehicleInfo().getYear(), prof.getVehicleInfo().getType(), prof.getVehicleInfo().getMethod()};         // place values of current profile into an array

        // back button
        button.setText("Back");
        button.setBounds(175, 520, 150, 40);

        // set labels for names and values of the customer profile
        for (int i = 0; i < 12 ; i++)
        {
            label1[i] = new JLabel(info[i]+":");
            label1[i].setFont(new Font(myFont, Font.PLAIN, 18));
            label1[i].setBounds(75, 70+32*i, 150, 50);
            this.add(label1[i]);
            label2[i] = new JLabel(input[i]);
            label2[i].setFont(new Font(myFont, Font.PLAIN, 18));
            label2[i].setBounds(275, 70+32*i, 300, 50);
            this.add(label2[i]);
        }

        this.setVisible(true);        // show window
    }

    public void actionHandler(ActionEvent e){
        if (e.getSource() == button){                    // if back button clicked
            menu.returnToMainMenu(this);      // return to main menu
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionHandler(e);
    }

    public static void main(String[] args){
        CustomerProfDB db = new CustomerProfDB("database/db1.txt");            // create db object
        try{
            db.initializeDatabase("database/db1.txt");                   // initialize db
            new GuiDisplayProfile(new GuiMainMenu(db), db.findFirstProfile("PA1"));         // create new display profile
        }catch (FileNotFoundException e){                                       // catch file not found exceptions
            System.out.println("The file could not be found");
        }
    }
}
