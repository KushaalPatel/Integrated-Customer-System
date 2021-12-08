import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;



public class GuiInitDB extends JFrame implements ActionListener {
    JButton initButton;
    JButton cancelButton;
    JButton submitButton;                               // init, cancel, and submit button
    JLabel messageLabel;
    JLabel pathLabel;
    JLabel errorLabel;                                  // message, path, and error label
    JTextField pathTextField;                           // text field for path
    String defFile;                                     // default file path string
    CustomerProfDB db;                                  // database object
    String myFont = "Courier";
    
    // GuiInitDB Constructor
    GuiInitDB(String file)
    {
        // set default file path to inputted file path and create new DB object
        defFile = file;
        db = new CustomerProfDB(defFile);
        

        // set background, title, and size
        this.setTitle("Initialize Database");
        Color c1 = new Color(39, 150, 81);
        this.getContentPane().setBackground(c1);
        this.setLayout(null);
        this.setSize(500, 320);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);            // if this window closed, stop app

        // initialize button
        initButton = new JButton("Initialize");
        initButton.setBounds(100, 170, 120, 40);
        initButton.setFont(new Font(myFont, Font.PLAIN, 12));
        initButton.addActionListener(this);

        // submit button
        submitButton = new JButton("Submit");
        submitButton.setBounds(120, 170, 100, 40);
        submitButton.setFont(new Font(myFont, Font.PLAIN, 14));
        submitButton.addActionListener(this);

        // cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(275, 170, 100, 40);
        cancelButton.setFont(new Font(myFont, Font.PLAIN, 14));
        cancelButton.addActionListener(this);

        // message label
        messageLabel = new JLabel("Would you like to initialize the database?");
        messageLabel.setFont(new Font(myFont, Font.BOLD, 14));
        messageLabel.setBounds(76, 90, 400, 50);

        // path label
        pathLabel = new JLabel("Path to database: ");
        pathLabel.setFont(new Font(myFont, Font.PLAIN, 14));
        pathLabel.setBounds(30, 90, 150, 50);

        // path text field
        pathTextField = new JTextField(20);
        pathTextField.setFont(new Font(myFont, Font.PLAIN, 14));
        pathTextField.setBounds(175,98,300,35);

        // error label
        errorLabel = new JLabel("", SwingConstants.CENTER);

        // add components
        this.add(initButton);
        this.add(cancelButton);
        this.add(messageLabel);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void initDB(String file){
        try {
            // try to initialize database 
            db.initializeDatabase(file);
            System.out.println("DB was initialized to "+ file);
            new GuiMainMenu(db);                // create a new menu
            this.dispose();                     // close window
        }catch (FileNotFoundException e){       // catch errors
            errorLabel.setText("Error initializing database. File not found.");
        }
        catch(NoSuchElementException e){
            errorLabel.setText("Error initializing database.");
        }
        catch(IllegalArgumentException e){
            errorLabel.setText("Error initializing database. Bad DB format.");
        }
        // sets the properties of the error label (only reached if the database fails to initialize)
        errorLabel.setFont(new Font(myFont, Font.BOLD, 15));
        errorLabel.setBounds(new Rectangle(90, 135, 350, 33));
        errorLabel.setForeground(Color.red);
        this.add(errorLabel);               // add error label
        this.repaint();                     // updates this component
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == cancelButton) {    // if cancel button is selected
            initDB(defFile);                        // initialize db to default file
        }
        if(event.getSource() == initButton){        // if initialize button is selected
            // remove message label and init button; add submit button, path text field and label
            this.remove(messageLabel);
            this.remove(initButton);
            this.add(submitButton);
            this.add(pathTextField);
            this.add(pathLabel);
            this.repaint();           // update component
        }
        if(event.getSource() == submitButton){      // if submit button is selected
            initDB(pathTextField.getText());        // initdb to path in text field
        }
    }

    // Test Client
    public static void main(String[] args){
        new GuiInitDB("database/db1.txt");
    }
}
