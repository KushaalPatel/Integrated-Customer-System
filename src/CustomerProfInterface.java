import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
The fourth class, CustomerProfInterface, is used to access and update the database of customer
profiles for queries by administrative users.
 */
public class CustomerProfInterface {
    private String fileName;
    private CustomerProfDB db;
    /*
     The constructor
    CustomerProfInterface accepts the name of the file that serves as the database for the user interface
    session. It calls the CustomerProfDB to initialize the database, to read in data from the database file into the
    application before accepting any selections for menu options from the user. The interface also offers the user an
    option to exit the session and all the profiles in the memory are written to the database file before the
    application finishes exiting.
     */
    public CustomerProfInterface(String fName) {
        fileName = fName;
    }

    /*
    the method getUserChoice will present the user with a
    menu of options to choose from, record that selection, and call the appropriate method.
     */
    public void getUserChoice() {
        Scanner s = new Scanner(System.in);
        int choice;
        System.out
                .println("Would you like to initialize the database? The database will be set to " + fileName);
        System.out.println("1. Yes\n0. No");
        // while (true) {
        //     System.out.print("Your choice: ");
        //     try {
        //         choice = Integer.parseInt(s.nextLine());
        //         if (choice == 0) {
        //             try {
        //                 db.initializeDatabase(fileName);
        //                 break;
        //             } catch (FileNotFoundException e) {
        //                 System.out.println("ERROR: Unable to find " + fileName);
        //             }
        //         } else if (choice == 1) {
        //             initDB();
        //             break;
        //         } else {
        //             System.out.println("ERROR: Enter a valid choice.");
        //         }
        //     } catch (NumberFormatException e) {
        //         System.out.println("ERROR: Enter a valid number.");
        //     }
        // }
        while (true) {
            while (true) {
                try {
                    System.out.println(
                            "--------------------------------------MAIN MENU-----------------------------------------");
                    System.out.println("1. Enter a New Customer Profile");
                    System.out.println("2. Delete a Customer Profile by Last Name and AdminID");
                    System.out.println("3. Find and Display a Customer Profile by Last Name and AdminID");
                    System.out.println("4. Make Modifications to Customer Profile");
                    System.out.println("5. Display All Profiles");
                    System.out.println("6. Write to Database"); /* Write to the Current DB in use */
                    System.out.println("0. Exit Application");
                    System.out.print("Selection: ");
                    choice = Integer.parseInt(s.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("ERROR: Please enter a valid menu option.");
                }
            }
            if (choice == 0) {
                break;
            }
            switch (choice) {
            case (1):
                createNewCustomerProf(getUserAdminId(s));
                break;
            case (2):
                deleteCustomerProf(getUserAdminId(s));
                break;
            case (3):
                findCustomerProf(getUserAdminId(s));
                break;
            case (4):
                updateCustomerProf(getUserAdminId(s));
                break;
            case (5):
                displayAllCustomerProf(getUserAdminId(s));
                break;
            case (6):
                writeToDB();
                break;
            default:
                System.out.println("ERROR: Please enter a valid menu option.");
            }
        }
        s.close();
    }

    /*
    This option deletes the CustomerProf when the
    lastName is supplied. To identify the correct CustomerProf, adminID is used. The method
    deleteCustomerProf is responsible for this function. Note that an administrative user cannot
    remove the CustomerProf unless they created it.
     */
    public void deleteCustomerProf(String adminId) {
        Scanner sc = new Scanner(System.in);    // Scanner for getting user input
        // display header and ask user to to input last name of customer to delete
        System.out.println("---------------------------DELETE A CUSTOMER PROFILE---------------------------");
        if(db.getCustomerList().isEmpty()){
            System.out.println("There are no customers to update.");         // if customerList is empty, notify user that nothing to udpate
            sc.close();
            return;
        }
        if(!isThereCustomerProf(adminId)){
            System.out.println("There were no customers found for "+ adminId+".");
            // if there are no customers for that adminId, notify user and go back to main menu
            sc.close();
            return;
        }
        System.out.print("Enter the last name of the customer to delete: ");
        try{
            db.deleteProfile(adminId, sc.nextLine());                             // delete customer profile by adminId and last name
            System.out.println("The customer was deleted successfully.");          // notify user that customer was deleted successfully
        }catch(IllegalArgumentException e){
            System.out.println("The customer to delete could not be found.");      // if exception, notify user the customer they want to delete could not be found
        }
        sc.close();
    }

    /*
    Find and display a CustomerProf by name and adminID: This option searches for a specific
    CustomerProf when supplied with a lastName by the user. To identify the correct
    CustomerProf, adminID is used. The entire CustomerProf is displayed as the output for this
    option. The method findCustomerProf is responsible for providing this functionality. It invokes
    another method displayCustomerProf which accepts CustomerProf as input and prints the
    entire profile of the customer. For the sake of this project, you can assume that the combination of last
    name, and admin ID uniquely identifies a customer.
     */
    public void findCustomerProf(String adminId) {
        Scanner sc = new Scanner(System.in);    // Scanner for getting user input
        // display header and ask user to to input last name of customer to view
        System.out.println("---------------------------FIND AND DISPLAY A CUSTOMER PROFILE---------------------------");
        if(db.getCustomerList().isEmpty()){
            System.out.println("There are no customers to find.");         // if customerList is empty, notify user that no customers found and go back to main menu
            sc.close();
            return;
        }
        if(!isThereCustomerProf(adminId)){
            System.out.println("There were no customers found for "+ adminId+".");    // if there are no customers for that adminId, notify user and go back to main menu
            sc.close();
            return;
        }
        System.out.print("Enter the last name of the customer to view: ");
        try{
            displayCustomerProf(db.findProfile(adminId, sc.nextLine()));         // display customer profile after finding it by adminId and last name
        }catch (IllegalArgumentException e){
            System.out.println("The customer could not be found.");              // exception occurred, tell user the customer they want to display could not be found
        }
        sc.close();
    }

    /*
    CustomerProf Modifications: This in an option that is utilized to allow the user to modify a
    select subset of an existing CustomerProf. Specifically, only the Address, Phone, Use,
    Status are eligible for modification. For example, when a customer repurposes their vehicle from
    personal to business they may need to modify the Use indicator. Additionally, the user should also be
    able to modify the Model, Year, Type, and Method attributes from the VehicleInfo class.
    This option should present the user with a menu of CustomerProf attributes. The user will select
    which attribute should be modified, and will then be prompted to enter a new attribute value.
     */
    public void updateCustomerProf(String adminId) {
        Scanner sc = new Scanner(System.in);    // Scanner for getting user input
        boolean exit = false;                   // boolean that if true the user exits the screen
        CustomerProf customerProf;                // Customer profile object
        System.out.println("---------------------------MODIFY AN EXISTING Customer PROFILE---------------------------");
        if(db.getCustomerList().isEmpty()){
            System.out.println("There are no customers to update.");         // if customerList is empty, notify user that nothing to udpate
            sc.close();
            return;
        }
        if(!isThereCustomerProf(adminId)){
            System.out.println("There were no customers found for "+ adminId+".");
            // if there are no customers for that adminId, notify user and go back to main menu
            sc.close();
            return;
        }
        // while there is an exception, prompt user to input a valid value
        while(true) {
            System.out.print("Enter the last name of the customer to modify: ");
            try {
                customerProf = db.findProfile(adminId, sc.nextLine());       // find profile by adminId and last name
                break;
            } catch (Exception e) {
                System.out.println("The customer could not be found.");      // if exception, tell user the customer cant be found
            }
        }
        // display menu for modifying CustomerProf and showing the current customerProf info
        while(!exit){
            System.out.println("1. Modify Customer Address---------------------------------Current Address: "+ customerProf.getAddress());
            System.out.println("2. Modify Customer Phone Number----------------------------Current Phone: "+ customerProf.getPhone());
            System.out.println("3. Modify Customer Income----------------------------------Current Income: "+customerProf.getIncome());
            System.out.println("4. Modify Customer Use-------------------------------------Current Use: "+customerProf.getUse());
            System.out.println("5. Modify Customer Status----------------------------------Current Status: "+customerProf.getStatus());
            System.out.println("6. Modify Customer Vehicle Model---------------------------Current Vehicle Model: "+customerProf.getVehicleInfo().getModel());
            System.out.println("7. Modify Customer Vehicle Year----------------------------Current Vehicle Year: "+customerProf.getVehicleInfo().getYear());
            System.out.println("8. Modify Customer Vehicle Type----------------------------Current Vehicle Type: "+customerProf.getVehicleInfo().getType());
            System.out.println("9. Modify Customer Vehicle Method--------------------------Current Vehicle Method: "+customerProf.getVehicleInfo().getMethod());
            System.out.println("0. Exit");
            System.out.print("Selection: ");                // prompt user to select a menu option
            int choice = Integer.parseInt(sc.nextLine());   // get integer choice
            switch (choice) {
                case(0):
                    exit = true;        // exit application
                    break;
                case (1):
                    getAddress(customerProf, sc);        // get new address from user
                    break;
                case (2):
                    getPhone(customerProf, sc);           // get new phone from user
                    break;
                case (3):
                    getIncome(customerProf, sc);        // get new income from user
                    break;
                case (4):
                    getUse(customerProf, sc);           // get new use from user
                    break;
                case (5):
                    getStatus(customerProf, sc);     // get new status from user
                    break;
                case (6):
                    getModel(customerProf.getVehicleInfo(), sc);     // get new model from user
                    break;
                case (7):
                    getYear(customerProf.getVehicleInfo(), sc);        // get new year from user
                    break;
                case (8):
                    getType(customerProf.getVehicleInfo(), sc);        // get new type from user
                    break;
                case (9):
                    getMethod(customerProf.getVehicleInfo(), sc);        // get new method from user
                    break;
                default:
                    System.out.println("Please enter a valid menu option.");     // prompt user to enter a valid menu option
            }
        }
        sc.close();
    }


    public void displayCustomerProf(CustomerProf customerProf) {
        System.out.println("Administrative ID: " + customerProf.getAdminId());
        System.out.println("Customer's first name: "+ customerProf.getFirstName());
        System.out.println("Customer's last name: "+ customerProf.getLastName());
        System.out.println("Customer's address: "+ customerProf.getAddress());
        System.out.println("Customer's phone number: "+ customerProf.getPhone());
        System.out.println("Customer's income: " + customerProf.getIncome());
        System.out.println("Customer's status: " + customerProf.getStatus());
        System.out.println("Customer's use: " + customerProf.getUse());
        System.out.println("Customer's vehicle model: " +customerProf.getVehicleInfo().getModel());
        System.out.println("Customer's vehicle year: " + customerProf.getVehicleInfo().getYear());
        System.out.println("Customer's vehicle type: " + customerProf.getVehicleInfo().getType());
        System.out.println("Customer's vehicle method: " + customerProf.getVehicleInfo().getMethod());
    }

    /*
    Display all profiles: This option displays all the CustomerProf in the ICS for a given
    administrative user. The method displayAllCustomerProf should prompt the user for their ID
    and print all corresponding profiles to the screen, one at a time.
     */
    public void displayAllCustomerProf(String adminID) {
        System.out.println("---------------------------------Customer List for "+ adminID+"----------------------------------");
        if (db.getCustomerList().isEmpty()){
            System.out.println("There are no customers to display.");  // if customerlist is empty, notify user that no customers found and go back to main menu
            return;
        }
        if (!isThereCustomerProf(adminID)) {
            System.out.println("There were no customers found for "+ adminID+".");    // if there are no customers for that adminId, notify user and go back to main menu
            return;
        }
        //use for each loop to iterate through list of customers and call displayCustomerProf for each customer
        for (CustomerProf customerProf : db.getCustomerList()){
            if (customerProf.getAdminId().equals(adminID)){
                displayCustomerProf(customerProf);
                System.out.println("\n");
            }
        }
    }

    /*
    Write to database: This option will write all profiles currently in memory, to the text file database
    that is provided to the application at application start up.
     */
    public void writeToDB() {
        try{
            db.writeAllCustomerProf(fileName);   // write all customer profiles to fileName
            System.out.println("Writing to DB was successful.");    // print success
        }
        // catch exceptions that may occur when calling write all
        catch(IOException e){
            System.out.println("Error writing to database.");
        }
    }

    public void initDB() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a file path to be used: ");
            try {
                db.initializeDatabase(sc.nextLine());
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Error initializing database. File not found.");
            } catch (NoSuchElementException e) {
                System.out.println("Error initializing database.");
            }
        }
        System.out.println("DB initialization was successful.");
        sc.close();
    }

    /*
    Enter a New CustomerProf: All inputs required to create an instance of a customer profile as
    defined by the CustomerProf class is solicited from the user. The method
    createNewCustomerProf is concerned with this responsibility. This method presents the user with
    a menu to elicit the profile information and in turn invokes another method createVehicleInfo to
    obtain the information of the vehicle for the customer.
     */
    public CustomerProf createNewCustomerProf(String adminId) {
        Scanner sc = new Scanner(System.in);     // Scanner for getting user input
        VehicleInfo newVehInfo = new VehicleInfo("", "000-000-0000", "other", "other"); //default
        CustomerProf newCustomerProf = new CustomerProf(adminId, "", "", "", "000-000-0000", 0, "sport","new", newVehInfo); //default
        System.out.println("---------------------------CREATE A CUSTOMER PROFILE---------------------------");   // print header
        while(true) {
            try{
                // get the first and last name of the new customer
                System.out.print("Enter the new customer's first name followed by last name separated by a single space: ");
                String curr = sc.nextLine();    // first and last name
                newCustomerProf.updateFirstName(curr.split(" ")[0]);     // set first name
                newCustomerProf.updateLastName(curr.split(" ")[1]);      // set last name
                break;
            }catch (Exception e){
                System.out.println("Please enter a first and last name separated by a single space.");    // exception occurs, prompt user to fix
            }
        }
        getAddress(newCustomerProf, sc);         // get new address from user
        getPhone(newCustomerProf, sc);           // get new phone from user
        getIncome(newCustomerProf, sc);          // get new income from user
        getStatus(newCustomerProf, sc);          // get new status from user
        getUse(newCustomerProf, sc);             // get new use from user
        newCustomerProf.updateVehicleInfo(createNewVehicleInfo(newVehInfo));      // get new VehInfo from user
        db.insertNewProfile(newCustomerProf);    // insert new profile
        return newCustomerProf;                  // return new profile
    }


    public VehicleInfo createNewVehicleInfo(VehicleInfo newVehInfo) {
        Scanner sc = new Scanner(System.in);         // Scanner for getting user input
        System.out.println("-------------------------------------VEHICLE INFO---------------------------------------");
        getModel(newVehInfo, sc);     // get new model from user
        getYear(newVehInfo, sc);      // get new year from user
        getType(newVehInfo, sc);      // get new type from user
        getMethod(newVehInfo, sc);    // get new method from user
        return newVehInfo;            // return new VehInfo
    }

    /* Helper function for getUserChoice */
    public String getUserAdminId(Scanner sc){
        String adminId;             // string to hold input adminId
        // while there is an exception, prompt user to enter a valid input
        while(true) {
            // prompts user for their adminId
            System.out.print("Please enter your admin ID: ");
            adminId = sc.nextLine();
            if (db.getValidAdminIds().contains(adminId)) {  // if their adminId is valid,
                return adminId; // return the admin Id
            }
            else{
                System.out.println("Unable to authenticate admin ID."); // else, print statement saying unable to authenticate adminId
            }
        }
    }

    /* Helper functions for updateCustomerProf and createNewCustomerProf */
    public void getAddress(CustomerProf customerProf, Scanner sc){
        System.out.print("Enter customer's address: ");      // prompts user for  address
        customerProf.updateAddress(sc.nextLine());            // sets address from input
    }

    public void getPhone(CustomerProf customerProf, Scanner sc){
        // while there is an exception, prompt user to input a valid value
        while(true) {
            try{
                System.out.print("Enter phone number (e.g 203-123-4567): ");      // prompts user for  phone number
                customerProf.updatePhone(sc.nextLine());                                         // sets phone
                break;
            }catch (Exception e){
                System.out.println("Please enter a valid phone number.");       // exception occurs, enter valid number
            }
        }
    }

    public void getIncome(CustomerProf customerProf, Scanner sc){
        // while there is an exception, prompt user to input a valid value
        while(true) {
            try{
                System.out.print("Enter the Income: ");                 // prompts user for Income amount
                customerProf.updateIncome(Float.parseFloat(sc.nextLine()));       // set Income
                break;
            }catch (Exception e){
                System.out.println("Please enter a float as the Income.");   // exception occurs, enter valid number
            }
        }
    }

    public void getStatus(CustomerProf customerProf, Scanner sc){
        // while there is an exception, prompt user to input a valid value
        while(true) {
            try{
                System.out.print("Enter the Status (Active or Inactive): ");     // prompts user for status
                customerProf.updateStatus(sc.nextLine());                                         // sets status
                break;
            }catch (Exception e){
                System.out.println("Please enter valid status.");    // exception occurs, enter valid number
            }
        }
    }
    
    public void getUse(CustomerProf customerProf, Scanner sc){
        // while there is an exception, prompt user to input a valid value
        while(true) {
            try{
                System.out.print("Enter the use (Business, Personal, or Both): ");    // prompts user for use
                customerProf.updateUse(sc.nextLine());                                            // sets use
                break;
            }catch (Exception e){
                System.out.println("Please enter a valid use.");   // exception occurs, enter valid number
            }
        }
    }
    
    public void getModel(VehicleInfo Vehinfo, Scanner sc){
        System.out.print("Enter the vehicle's model: ");     // prompts user for model
        Vehinfo.updateModel(sc.nextLine());                             // sets model
    }
    
    public void getYear(VehicleInfo Vehinfo, Scanner sc){
        // while there is an exception, prompt user to input a valid value
        while(true) {
            try{
                System.out.print("Enter the vehicle's year: ");  // prompts user for year
                Vehinfo.updateYear(sc.nextLine());               // sets year
                break;
            }catch (Exception e){
                System.out.println("Please enter a valid year.");   // exception occurs, enter valid number
            }
        }
    }
    
    public void getType(VehicleInfo Vehinfo, Scanner sc){
        // while there is an exception, prompt user to input a valid value
        while(true) {
            try{
                System.out.print("Enter the type (Sedan, hatchback, luxury, sport, or Other): ");   // prompts user for type
                Vehinfo.updateType(sc.nextLine());  // sets type
                break;
            }catch (Exception e){
                System.out.println("Please enter a valid type.");   // exception occurs, enter valid number
            }
        }
    }
    
    public void getMethod(VehicleInfo Vehinfo, Scanner sc){
        // while there is an exception, prompt user to input a valid value
        while(true) {
            try{
                System.out.print("Enter the method (New, Certified pre-owned, used, or Other): ");     // prompts user for method
                Vehinfo.updateMethod(sc.nextLine());  // sets method
                break;
            }catch (Exception e){
                System.out.println("Please enter a valid method.");   // eexception occurs, enter valid number
            }
        }
    }

    /* Helper function that checks if there is a customerProf that exists for the inputted adminId */
    public boolean isThereCustomerProf(String adminID){
        // loops through all customer profiles in the customerList
        for(CustomerProf customerProf: db.getCustomerList()){
            if(customerProf.getAdminId().equals(adminID)){   // if the adminId of customerProf matches the inputted adminId, return true
                return true;
            }
        }
        return false;       // no customers were found, return false
    }
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        System.out.println("Which file are you to edit?");
        Scanner input = new Scanner(System.in);
        String file = input.nextLine();
        CustomerProfInterface application = new CustomerProfInterface(file);
        application.getUserChoice();
        input.close();
        // java .\src\CustomerProfInterface.java .\database\dbTest.txt
    }

}
