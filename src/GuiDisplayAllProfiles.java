import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;



public class GuiDisplayAllProfiles extends GuiDisplayProfile {
    JButton nextButton;     // next button
    String id;
    

    // GuiDisplayAllProfiles Constructor
    GuiDisplayAllProfiles(GuiMainMenu mainMenu, CustomerProf firstProf, String adminId)
    {
        super(mainMenu, firstProf);                         // call parent constructor
        this.setTitle("Display All Profiles");              // set title
        id = adminId;
        String myFont = "Courier";

        if (mainMenu.db.moreProfLeft(adminId)) {       // if there is more than one customer profile, add next button and adjust back button
            // next button
            nextButton = new JButton("Next");
            nextButton.setBounds(275, 520, 150, 40);
            nextButton.setFont(new Font(myFont, Font.PLAIN, 20));
            nextButton.addActionListener(this);
            this.add(nextButton);
            // back button
            button.setBounds(75, 520, 150, 40);
        }else{                                                          // else center back button
            button.setBounds(175, 520, 150, 40);
        }
        button.addActionListener(this);

        // header label
        JLabel headerLabel = new JLabel("Customer Profile");
        headerLabel.setFont(new Font(myFont, Font.PLAIN, 35));
        headerLabel.setBounds(100, 20, 300, 50);

        // add components
        this.setVisible(true);
    }

    @Override
    public void actionHandler(ActionEvent e) {
        super.actionHandler(e);                                         // call parents actionHandler
        if (e.getSource() == nextButton)                                // if next button is clicked
        {
            try{
                CustomerProf prof = menu.db.findNextProfile(id);        // set current profile to next profile
                String[] input = {prof.getAdminId(), prof.getFirstName(), prof.getLastName(), prof.getAddress(), prof.getPhone(),
                    Float.toString(prof.getIncome()), prof.getUse(), prof.getStatus(), prof.getVehicleInfo().getModel(),
                    prof.getVehicleInfo().getYear(), prof.getVehicleInfo().getType(), prof.getVehicleInfo().getMethod()};         // place values of current profile into array
                // set labels for fields to the current profiles values
                for (int i = 0; i < 12 ; i++)
                {
                    label2[i].setText(input[i]);
                    label2[i].setFont(new Font(myFont, Font.PLAIN, 18));
                    label2[i].setBounds(270, 70+32*i, 300, 50);
                }
                this.repaint();     // update component
            }catch (IllegalArgumentException ex){
                this.remove(nextButton);
                button.setBounds(175, 520, 150, 40);
                this.repaint();
            }
        }
    }

    // Test Client
    public static void main(String[] args){
        CustomerProfDB db = new CustomerProfDB("database/db1.txt");                 // create new db object
        try{
            db.initializeDatabase("database/db1.txt");                       // initialize db
            new GuiDisplayAllProfiles(new GuiMainMenu(db), db.findFirstProfile("PA1"), "PA1");  // create new display profile
        }catch (FileNotFoundException e){                                           // catch file not found exceptions
            System.out.println("The file could not be found");                      // display error message
        }
    }
}
