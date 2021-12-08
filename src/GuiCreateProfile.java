import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;



public class GuiCreateProfile extends GuiBaseMenuOption{
    JComboBox<String> useCb;
    JComboBox<String> statusCb;
    JComboBox<String> typeCb;
    JComboBox<String> methodCb;                                         // status use type method combo boxes
    JLabel[] labels = new JLabel[13];                                   // labels forcustomer profile 
    JTextField[] textFields = new JTextField[8];                        // text fields for customer profile
    boolean hasErrors;                                                  // boolean -> user input is invalid
    Border defaultBorder;                                               // default border for text fields 
    
    // GuiCreateProfile Constructor
    GuiCreateProfile(GuiMainMenu mainMenu)
    {
        super(mainMenu);        // call parent constructor
        String myFont = "Courier";
        
        // remove unneeded fields
        this.remove(adminIdTextField);
        this.remove(adminIdLabel);
        this.remove(lastNameTextField);
        this.remove(lastNameLabel);

        // set title and size
        this.setTitle("Create Profile");
        this.setSize(500, 625);

        // submit button
        button.setText("Submit");
        button.setBounds(175, 525, 150, 40);

        // header label
        headerLabel.setText("Create Profile");
        headerLabel.setBounds(100, 20, 300, 50);

        // field labels
        labels[0] = headerLabel;

        // set properties of field labels
        String[] info = {"Admin ID","First Name","Last Name","Address","Phone","Income","Status", "Use", "Model","Year","Type","Method"};
        int yInc = 0;
        for(int i = 1; i < labels.length; i++){
            labels[i] = new JLabel(info[i-1]+": ");
            labels[i].setFont(new Font(myFont, Font.PLAIN, 16));
            labels[i].setBounds(65, 70+yInc, 120, 30);
            this.add(labels[i]);
            yInc += 33;
        }

        // set properties of text fields
        yInc = 0;
        for(int i = 0; i < textFields.length; i++){
            textFields[i] = new JTextField(20);
            textFields[i].setFont(new Font(myFont, Font.PLAIN, 18));
            if(i >= 6){
                textFields[i].setBounds(new Rectangle(185,140+yInc,250,30));
            }else{
                textFields[i].setBounds(new Rectangle(185,70+yInc,250,30));
            }
            this.add(textFields[i]);
            yInc += 33;
        }

        defaultBorder = textFields[0].getBorder();      // set default border

        // use combo box
        useCb = new JComboBox<>(useOptions);
        useCb.setBounds(185, 303, 255, 32);
        useCb.setFont(new Font(myFont, Font.PLAIN, 18));
        useCb.setAlignmentX((float) 0.5);

        // status combo box
        statusCb = new JComboBox<>(statusOptions);
        statusCb.setBounds(185, 268, 255, 32);
        statusCb.setFont(new Font(myFont, Font.PLAIN, 18));
        statusCb.setAlignmentX((float) 0.5);

        // type combo box
        typeCb = new JComboBox<>(typeOptions);
        typeCb.setBounds(185, 402, 255, 32);
        typeCb.setFont(new Font(myFont, Font.PLAIN, 18));
        typeCb.setAlignmentX((float) 0.5);

        // method combo box
        methodCb = new JComboBox<>(methodOptions);
        methodCb.setBounds(185, 435, 255, 32);
        methodCb.setFont(new Font(myFont, Font.PLAIN, 18));
        methodCb.setAlignmentX((float) 0.5);

        // error label
        errorLabel.setText("");
        errorLabel.setFont(new Font(myFont, Font.BOLD, 11));
        errorLabel.setBounds(new Rectangle(140, 470, 300, 45));
        errorLabel.setForeground(Color.red);

        // add components
        this.add(useCb);
        this.add(statusCb);
        this.add(typeCb);
        this.add(methodCb);

        this.setVisible(true);         // set visible to true
    }

    
    public void checkIfEmpty(JTextField field){
        if (field.getText().isEmpty()) {        // if field is empty
            hasErrors = true;                   // error in user input
            if(!errorLabel.getText().contains("* Missing one or more fields")) {  // if error label for missing field not present
                errorLabel.setText(errorLabel.getText() + "* Missing one or more fields<br>");  // add missing field error
            }
            field.setBorder(new LineBorder(Color.red,1));
            this.add(errorLabel);     // add error label
            this.repaint();           // update
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == button) {        // if submit button is clicked, create a new profile
            hasErrors = false;                    // no errors
            errorLabel.setText("<html>");         // error = "" -> start html for error label

            // resets the text field borders to their original states
            for(int i=0;i<8;i++){
                textFields[i].setBorder(defaultBorder);
            }
    

            if (!menu.db.getValidAdminIds().contains(textFields[0].getText())) {                // if adminId invalid
                hasErrors = true;                                                               // error in user input
                errorLabel.setText(errorLabel.getText()+"* Unable to authenticate Admin ID<br>"); // state error
                textFields[0].setBorder(new LineBorder(Color.red,1));  //red board
                this.add(errorLabel);     // add error label
                this.repaint();         // updates this component
            }

            checkIfEmpty(textFields[1]);        // check if: first name is empty
            checkIfEmpty(textFields[2]);        // last name is empty
            checkIfEmpty(textFields[3]);        // address is empty
            checkIfEmpty(textFields[6]);        // model is empty

            VehicleInfo newVehicleInfo = new VehicleInfo(textFields[6].getText(), "1000", (String) typeCb.getSelectedItem(), (String) methodCb.getSelectedItem());   // default MedCond
            try {       // try to set year
                newVehicleInfo.updateYear(textFields[7].getText());
            }catch(Exception e){
                hasErrors = true;
                errorLabel.setText(errorLabel.getText()+"* Year must be in correct format (e.g. 2010)<br>"); //display error
                textFields[7].setBorder(new LineBorder(Color.red,1)); //red boarder
                this.add(errorLabel);     // add error label
                this.repaint();         // updates this component
            }

            CustomerProf newCustomerProf = new CustomerProf(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(), textFields[3].getText(), "0000000000", 0, (String) statusCb.getSelectedItem(), (String) useCb.getSelectedItem(), newVehicleInfo);     // default CustomerProf

            try {       // try to set phone
                newCustomerProf.updatePhone(textFields[4].getText());
            }catch(Exception e){
                hasErrors = true;       // error in user input
                if(!errorLabel.getText().contains("* Phone number must be in correct format (e.g. 203999999)")){             // check if error label already there
                    errorLabel.setText(errorLabel.getText()+"* Phone number must be in correct format (e.g. 203999999)<br>"); //if not, add it
                }
                textFields[4].setBorder(new LineBorder(Color.red,1));
                this.add(errorLabel);     // add error label
                this.repaint();         // updates this component
            }

            try {       // try to set customer Income
                newCustomerProf.updateIncome(Float.parseFloat(textFields[5].getText()));
            }catch(Exception e){
                hasErrors = true;        // error in user input
                errorLabel.setText(errorLabel.getText()+"* Income must be a positive float<br>"); //set income error label
                textFields[5].setBorder(new LineBorder(Color.red,1)); 
                this.add(errorLabel);       // add error label
                this.repaint();             // updates this component
            }

            errorLabel.setText(errorLabel.getText()+"</html>");     // end html for error label

            if(!hasErrors){                                     // if there are no errors
                menu.db.insertNewProfile(newCustomerProf);       // insert new customer profile
                menu.returnToMainMenu(this);            // return to main menu
            }
        }
    }

    // Test Client
    public static void main(String[] args){
        CustomerProfDB db = new CustomerProfDB("database/db1.txt");         // create db object
        try{
            db.initializeDatabase("database/db1.txt");                      // initialize db
            new GuiCreateProfile(new GuiMainMenu(db));                      // display create profile
        }catch (FileNotFoundException e){                                   // catch file not found exceptions
            System.out.println("The file could not be found");              // display error message
        }
    }
}
