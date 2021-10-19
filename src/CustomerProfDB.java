public class CustomerProfDB {
    private int numCustomer, currentCustomerIndex;
    private String fileName;
    private CustomerProf[] customerList;

    /*
    CustomerProfDB: This is a constructor method which accepts the name of the file in which the
    customer profiles will be stored. Thus, for the ICS system in its current version, we recommend that the
    database of CustomerProfs is implemented using a text file. Students, however, are also free to use
    json format to store the customer profiles. The filename variable holds the name of this file
    containing customer profiles. The CustomerProfDB method calls the initialize database method, by
    passing it the filename as discussed below.
     */
    public CustomerProfDB(String whatfileName){
        fileName = whatfileName;
        numCustomer = 0; // init to 0
        currentCustomerIndex = 0; // init to 0
    }


    /*
    insertNewProfile: This method accepts a CustomerProf as input and inserts it into the array
    customerList.
     */
    public void insertNewProfile(CustomerProf c) {

    }


    /*
    deleteProfile: This method accepts the adminID and lastName as inputs and deletes the
    corresponding customer profile. It returns a Boolean value to indicate whether the delete operation was
    successful.
     */
    public boolean deleteProfile(String adminID, String lastName) {

        return false;
    }

    /*
    findProfile: This method accepts the adminID and lastName as inputs and returns the
    corresponding customer profile as output. For the purpose of this project, assume that adminID and
    lastName, uniquely identifies a customer.
     */
    public CustomerProf findProfile(String adminID, String lastName) {


    }

    public CustomerProf findFirstProfile() {
        //return the first prof in array customerList

        //set index to first
        currentCustomerIndex = 0;

    }

    public CustomerProf findNextProfile() {


    }


    /*
    writeAllCustomerProf: This method writes all CustomerProf stored in the array
    customerList to the database file, which is provided as input to the system at startup.
     */
    public void writeAllCustomerProf() {

    }


    /*
    initializeDatabase: This method is used upon startup to read in the existing customer profiles
    that are currently in the database file. The name of the database file is provided as input to
    CustomerProfInterface as discussed below. If the customer database indicated by the file does
    not exist, a new database file will be created.
     */
    public void initializeDatabase(String s) {

    }
}
