import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;



public class GuiExit extends GuiBaseMenuOption {
    JButton exitButton;   // exit button

    // GuiExit constructor
    GuiExit(GuiMainMenu mainMenu)
    {
        super(mainMenu);                              // call parent constructor
        String myFont = "Courier";
        
        // remove unneeded fields
        this.remove(adminIdTextField);
        this.remove(adminIdLabel);
        this.remove(lastNameTextField);
        this.remove(lastNameLabel);

        this.setTitle("Exit");  

        // exit button
        exitButton = new JButton("Exit");
        exitButton.setBounds(120, 170, 100, 40);
        exitButton.setFont(new Font(myFont, Font.PLAIN, 15));
        exitButton.addActionListener(this);

        // cancel button
        button.setText("Cancel");
        button.setBounds(275, 170, 100, 40);
        button.setFont(new Font(myFont, Font.PLAIN, 15));


        // confirm exit label
        JLabel labelExit = new JLabel("Are you sure you want to exit?");
        labelExit.setFont(new Font(myFont, Font.BOLD, 20));
        labelExit.setBounds(65, 90, 400, 50);

        // add components
        this.add(exitButton);
        this.add(labelExit);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == button) {              // if cancel button
            menu.returnToMainMenu(this);                // return to main menu
        }
        if(event.getSource() == exitButton){                        // if exit button 
            try {
                menu.db.writeAllCustomerProf(menu.db.getFile());    // try writing all customer profiles
                System.exit(0);                                     // exit application
            } catch (IOException e) {
                // display warning label stating there is an error writing profiles to database
                JLabel labelWarning = new JLabel("Error writing customer profiles to database.",  SwingConstants.CENTER);
                labelWarning.setFont(new Font(myFont, Font.BOLD, 10));
                labelWarning.setBounds(new Rectangle(50, 105, 400, 50));
                labelWarning.setForeground(Color.red);
                this.add(labelWarning);                                     // add warning label
                this.repaint();                                             // updates this component
            }
        }
    }

    // Test Client
    public static void main(String[] args){
        CustomerProfDB db = new CustomerProfDB("database/db1.txt");         // create new db object
        try{
            db.initializeDatabase("database/db1.txt");                      // initialize db
            new GuiExit(new GuiMainMenu(db));                               // create new exit
        }catch (FileNotFoundException e){                                   // catch file not found exceptions
            System.out.println("The file could not be found");
        }
    }
}
