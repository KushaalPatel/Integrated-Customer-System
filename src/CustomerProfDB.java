import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;


public class CustomerProfDB {
    private int numCustomer, currentCustomerIndex;
    private String fileName;
    private ArrayList<CustomerProf> customerList = new ArrayList<CustomerProf>();

    public CustomerProfDB(String fName){
        fileName = fName;
        numCustomer = 0; // init to 0
        currentCustomerIndex = 0; // init to 0
    }

    public void insertNewProfile(CustomerProf prof){
        try{
            CustomerProf existingProf = findProfile(prof.getAdminId(), prof.getLastName());   // checks if the prof exists in the customerList with the same adminId and lastName
            // if no exceptions are caught, the prof exists so update its info to the inputted customerprof (prof)
            existingProf.updateFirstName(prof.getFirstName());
            existingProf.updateLastName(prof.getLastName());
            existingProf.updateAddress(prof.getAddress());
            existingProf.updatePhone(prof.getPhone());
            existingProf.updateIncome(prof.getIncome());
            existingProf.updateStatus(prof.getStatus());
            existingProf.updateVehicleInfo(prof.getVehicleInfo());
        }
        catch(IllegalArgumentException e){
        customerList.add(prof);
        numCustomer++;
        }
    }

    public boolean deleteProfile(String adminID, String lastname){
        numCustomer--;
        return customerList.remove(findProfile(adminID,lastname));
    }
    
    public CustomerProf findProfile(String adminID, String lastname){
        for(CustomerProf customer: customerList){
            if(customer.getAdminId().equals(adminID) && customer.getLastName().equals(lastname)){
                currentCustomerIndex = customerList.indexOf(customer);
                return customer;
            }
        }
        throw new IllegalArgumentException("Couldn't find customer");
    }
    
    public CustomerProf findFirstProfile() {
        if(numCustomer <= 0) {
            return null;
        }
        else{
            CustomerProf customer = customerList.get(0); //1st customer in the list is at index 0
            return customer;
        }
    }

    public CustomerProf findNextProfile() {
        currentCustomerIndex++;
        return customerList.get(currentCustomerIndex);
    }

    public void writeAllPatientProf(String newFileName) throws IOException{
        FileWriter myWriter = new FileWriter(newFileName, false);           // create FileWriter for the file with the file path newFileName, set append to false (we will be clearing the file and rewriting it each time method is called)
        myWriter.write("Customer Profiles\n");                                   // database file header
        // loop through all the patients in the patientList
        for(CustomerProf customer: customerList){
                // write patient attributes to file with separator between each different patient
                myWriter.write( "------------------------------------------------------\n"+
                        "Administrative ID: " + customer.getAdminId()+"\n"+
                        "Customer's first name: "+ customer.getFirstName()+"\n"+
                        "Customer's last name: "+ customer.getLastName()+"\n"+
                        "Customer's address: "+ customer.getAddress()+"\n"+
                        "Customer's phone number: "+ customer.getPhone()+"\n"+
                        "Customer's income: " + customer.getIncome() + "\n"+
                        "Customer's status: " + customer.getStatus() + "\n"+
                        "Customer's use: " + customer.getUse() + "\n"+
                        "Customer's vehicle model: " +customer.getVehicleInfo().getModel() + "\n"+
                        "Customer's vehicle year: " + customer.getVehicleInfo().getYear() + "\n"+
                        "Customer's vehicle type: " + customer.getVehicleInfo().getType() + "\n"+
                        "Customer's vehicle method: " + customer.getVehicleInfo().getMethod() + "\n"
                );
        }
        myWriter.close();      // close FileWriter
    }

    // helper to split strings for initDB
    /*private String splitString(String line){
        String[] arr = line.split(": "); //split by colon + space 
        return arr[1]; //return what comes after colon + space (the values we want)
    }*/

    public void initDatabase(String newFile) throws FileNotFoundException, NoSuchElementException {
        fileName = newFile;
        File myFile = new File(fileName);
        Scanner s = new Scanner(myFile);
    
        while(s.hasNextLine()){
            s.nextLine();
            CustomerProf newProf = new CustomerProf(
            (s.nextLine()),                      // adminId
            (s.nextLine()),                      // firstName
            (s.nextLine()),                      // lastName
            (s.nextLine()),                      // address
            (s.nextLine()),                      // phone
            Float.parseFloat((s.nextLine())),  // income
            (s.nextLine()),                      // status
            (s.nextLine()),                      // use
            new VehicleInfo(
                (s.nextLine()), // model
                (s.nextLine()), // year
                (s.nextLine()), // type
                (s.nextLine())) // method
            );
            insertNewProfile(newProf);
            s.close();
        }
    }
    
    public static void main(String[] args){
        VehicleInfo vehicleInfo1 = new VehicleInfo("GLK", "2010", "luxury", "new");
        CustomerProf customerProf1 = new CustomerProf("C1","Kushaal", "Patel", "231 mapleview road", "203-204-2045", 100000, "active", "Personal", vehicleInfo1);
        CustomerProfDB db = new CustomerProfDB("database/dbTest.txt");
        // MAKE SURE FILE IS EMPTY WHEN TESTING
        try{
            db.initDatabase("database/dbTest.txt");
        }
        catch (FileNotFoundException e){
            System.out.println("Error initializing database. File not found.");
        }
        catch(NoSuchElementException e){
            System.out.println("Error initializing database.");
        }
        catch(IllegalArgumentException e){
            System.out.println("Profile updated");
        }

        db.insertNewProfile(customerProf1);
        db.findFirstProfile();

        try {
            db.writeAllPatientProf("database/dbTest.txt");
        } catch(IOException e){
            System.out.println("Error writing to database.");
        }
        catch(NoSuchElementException e){
            System.out.println("Error writing to database.");
        }
    }   
}
