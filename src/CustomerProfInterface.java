
/*
The fourth class, CustomerProfInterface, is used to access and update the database of customer
profiles for queries by administrative users.
 */
public class CustomerProfInterface {

    /*
     The constructor
    CustomerProfInterface accepts the name of the file that serves as the database for the user interface
    session. It calls the CustomerProfDB to initialize the database, to read in data from the database file into the
    application before accepting any selections for menu options from the user. The interface also offers the user an
    option to exit the session and all the profiles in the memory are written to the database file before the
    application finishes exiting.
     */
    public CustomerProfInterface() {

    }

    /*
    the method getUserChoice will present the user with a
    menu of options to choose from, record that selection, and call the appropriate method.
     */
    public void getUserChoice() {

    }

    /*
    This option deletes the CustomerProf when the
    lastName is supplied. To identify the correct CustomerProf, adminID is used. The method
    deleteCustomerProf is responsible for this function. Note that an administrative user cannot
    remove the CustomerProf unless they created it.
     */
    public void deleteCustomerProf(String lastName, String adminID) {

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
    public void findCustomerProf(String lastName, String adminID) {


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
    public void updateCustomerProf() {

    }


    public void displayCustomerProf(String adminID) {

    }

    /*
    Display all profiles: This option displays all the CustomerProf in the ICS for a given
    administrative user. The method displayAllCustomerProf should prompt the user for their ID
    and print all corresponding profiles to the screen, one at a time.
     */
    public void displayAllCustomerProf(String adminID) {
        //use for each loop to iterate through list of customers and call displayCustomerProf for each customer

    }

    /*
    Write to database: This option will write all profiles currently in memory, to the text file database
    that is provided to the application at application start up.
     */
    public void writeToDB() {

    }

    /*
    Enter a New CustomerProf: All inputs required to create an instance of a customer profile as
    defined by the CustomerProf class is solicited from the user. The method
    createNewCustomerProf is concerned with this responsibility. This method presents the user with
    a menu to elicit the profile information and in turn invokes another method createVehicleInfo to
    obtain the information of the vehicle for the customer.
     */
    public CustomerProf createNewCustomerProf() {

    }


    public VehicleInfo createNewVehicleInfo() {

    }


}
