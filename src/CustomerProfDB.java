public class CustomerProfDB {
    private int numCustomer, currentCustomerIndex;
    private String fileName;
    private CustomerProf[] customerList;

    public CustomerProfDB(String whatfileName){
        fileName = whatfileName;
        numCustomer = 0; // init to 0
        currentCustomerIndex = 0; // init to 0
    }
}
