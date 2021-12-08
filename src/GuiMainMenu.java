import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

// menu

public class GuiMainMenu extends JFrame implements ActionListener {
    JButton selectButton;                               // select button
    JRadioButton[] buttons = new JRadioButton[6];       // menu option buttons
    CustomerProfDB db;                                   // db object

    // GuiMainMenu Constructor
    GuiMainMenu(CustomerProfDB database)
    {
        db = database;          // set inputted database to db variable
        String myFont = "Courier";
        // if window closed -> gui exit screen -> ask user if exit
        final GuiMainMenu guiMainMenu = this;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guiMainMenu.setVisible(false);
                new GuiExit(guiMainMenu);
            }
        });

        // set background, title, and size
        Color c1 = new Color(39, 150, 81);
        this.getContentPane().setBackground(c1);
        this.setTitle("Main Menu");
        this.setLayout(null);
        this.setSize(500, 530);

        // select button
        selectButton = new JButton("Select");
        selectButton.setBounds(170, 425, 150, 40);
        selectButton.setFont(new Font(myFont, Font.PLAIN, 20));
        selectButton.addActionListener(this);

        // header
        JLabel headerLabel = new JLabel("Integrated Customer System");
        headerLabel.setFont(new Font(myFont, Font.PLAIN, 27));
        headerLabel.setBounds(32, 20, 500, 50);

        // JRadio button menu options
        ButtonGroup buttonGroup = new ButtonGroup();
        buttons[0] = new JRadioButton("Create Profile");
        buttons[1] = new JRadioButton("Delete Profile");
        buttons[2] = new JRadioButton("Update Profile");
        buttons[3] = new JRadioButton("Find/Display Profile");
        buttons[4] = new JRadioButton("Display All Profiles");
        buttons[5] = new JRadioButton("Exit");

        // sets values for the menu option buttons
        int yInc = 0;
        for(JRadioButton button : buttons){
            button.setBounds(170, 100+yInc, 150, 40);
            buttonGroup.add(button);
            this.add(button);
            yInc += 50;
        }

        // add components
        this.add(selectButton);
        this.add(headerLabel);
        this.setResizable(false);
        this.setVisible(true);
    }

    // function to allow user to return to main menu from any screen
    public void returnToMainMenu(JFrame currScreen){
        this.setVisible(true);
        currScreen.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        int choice = -1;                                // get chosen menu option
        for(int i = 0; i < buttons.length; i++)         // iterate through buttons
        {
            if (buttons[i].isSelected())                // selected buttion is the users choice
                choice = i;                             // user choice = index
        }
        switch (choice) {
            case 0:                                     // hide main menu, open create profile screen
                this.setVisible(false);
                new GuiCreateProfile(this);
                break;
            case 1:
                this.setVisible(false);                 
                if(db.getCustomerList().isEmpty()){     // if customer list empty, display no profiles gui
                    new GuiNoProfilesMsg(this);
                }else{                                  // else, open delete profile gui
                    new GuiDeleteProfile(this);
                }
                break;
            case 2:
                this.setVisible(false);                   
                if(db.getCustomerList().isEmpty()){        // if customer list empty, display no profiles gui
                    new GuiNoProfilesMsg(this);
                }else {                                    // else, open update profile gui
                    new GuiUpdateProfile(this);
                }
                break;
            case 3:
                this.setVisible(false);                    
                if(db.getCustomerList().isEmpty()){        // if customer list empty, display no profiles gui
                    new GuiNoProfilesMsg(this);
                }else {                                    // else, open find profile gui
                    new GuiFindProfile(this);
                }
                break;
            case 4:
                this.setVisible(false);                    
                if(db.getCustomerList().isEmpty()){        // if customer list empty, display no profiles gui
                    new GuiNoProfilesMsg(this);
                }else {                                    // else, open display all profiles gui
                    new GuiDisplayAllProfilesPrompt(this);
                }
                break;
            case 5:
                this.setVisible(false);                    
                new GuiExit(this);                         // open exit confirmation gui
                break;
            default:                                       // default case do nothing
                break;
        }
    }

    public static void main(String[] args){
        CustomerProfDB db = new CustomerProfDB("database/testdatacopy.txt");    // create customerprofdb object
        try{
            db.initializeDatabase("database/testdatacopy.txt");                 // initialize the db
            new GuiMainMenu(db);                                                // new main menu
        }catch (FileNotFoundException e){                                       // catch exception if file not found
            System.out.println("The file could not be found");                  
        }
    }
}
