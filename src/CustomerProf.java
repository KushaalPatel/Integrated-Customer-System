import java.util.HashSet;
import java.util.Arrays;

public class CustomerProf {
    // customerprof private variables
    private String adminID, firstName, lastName, address, phone;
    private Float income;
    private String status, use;
    private VehicleInfo VehInfo;
    private HashSet<String> validStatus = new HashSet<>(Arrays.asList("active","inactive"));
    private HashSet<String> validUse = new HashSet<>(Arrays.asList("business","personal","both"));

    // construct the class
    public CustomerProf(String administrativeId, String customerfirstName, String customerlastName,
            String customerAddress, String customerPhone, float customerIncome, String customerStatus,
            String customerUse, VehicleInfo customerVehInfo) {
        adminID = administrativeId;
        updateFirstName(customerfirstName);
        updateLastName(customerlastName);
        updateAddress(customerAddress);
        updatePhone(customerPhone);
        updateIncome(customerIncome);
        updateStatus(customerStatus);
        updateUse(customerUse);
        updateVehicleInfo(customerVehInfo);
    }

    // get adminid
    public String getAdminId() {
        return adminID;
    }

    // get firstname
    public String getFirstName() {
        return firstName;
    }

    // update firstname
    public void updateFirstName(String customerFirstName) {
        firstName = customerFirstName;
    }

    // get lastname
    public String getLastName() {
        return lastName;
    }

    // update lastname
    public void updateLastName(String customerLastName) {
        lastName = customerLastName;
    }

    // get address
    public String getAddress() {
        return address;
    }

    // update address
    public void updateAddress(String customerAddress) {
        address = customerAddress;
    }

    // get phone
    public String getPhone() {
        return phone;
    }

    // update phone
    public void updatePhone(String customerPhone) {
        //String phoneArr[] = customerPhone.split("-");
        // if(phoneArr[0].length() == 3 && phoneArr[1].length() == 3 && phoneArr[2].length() == 4) {  // if each element of the inputted phone number has a valid length,
        //     try{
        //         // check if these each part are integers
        //         Integer.parseInt(phoneArr[0]);
        //         Integer.parseInt(phoneArr[1]);
        //         Integer.parseInt(phoneArr[2]);
        //         phone = customerPhone;               // if they all are integers, set phone to patientPhone (inputted value)
        //     }catch (NumberFormatException e){
        //         // if they all are not all integers, throw an IllegalArgumentException
        //         throw new IllegalArgumentException("Phone number can only contain integers.");
        //     }
        // }else{
        //     // else, throw an IllegalArgumentException (there are not 3 string elements or they are not the correct length)
        //     throw new IllegalArgumentException("Invalid phone number format, Enter valid format e.g. '111-111-1111'.");
        // }
        // System.out.println(customerPhone);
        if (customerPhone.length() == 10) {
            try{
                phone = customerPhone;
            }catch(NumberFormatException e){
                // if they all are not all integers, throw an IllegalArgumentException
                throw new IllegalArgumentException("Phone number can only contain integers.");
            }
        }
        else{
            // else, throw an IllegalArgumentException (not the correct length)
            throw new IllegalArgumentException("Invalid phone number format, Enter valid format e.g. '1112223333'.");
        }
    }

    // get income
    public Float getIncome() {
        return income;
    }

    // update income
    public void updateIncome(Float customerIncome) {
        income = customerIncome;
    }

    // get status
    public String getStatus() {
        return status;
    }

    // update status
    public void updateStatus(String customerStatus) {
        if (validStatus.contains(customerStatus.toLowerCase())){
            status = customerStatus;
        }
        else {
            throw new IllegalArgumentException("Status must be either: active or inactive");
        }
        
    }

    // get use
    public String getUse() {
        return use;
    }

    // update use
    public void updateUse(String customerUse){
        if (validUse.contains(customerUse.toLowerCase())){
            use = customerUse;
        }
        else {
            throw new IllegalArgumentException("Use must be either: business, personal, or both");
        }
    }

    // get vehicleinfo
    public VehicleInfo getVehicleInfo() {
        return VehInfo;
    }

    // update vehicleinfo
    public void updateVehicleInfo(VehicleInfo customerVehInfo) {
        VehInfo = customerVehInfo;
    }

    public static void main(String[] args){
        VehicleInfo vehicleInfo = new VehicleInfo("GLK", "2010", "Luxury", "new");
        CustomerProf customerProf1 = new CustomerProf("C1","Kushaal", "Patel", "2 mapleview road", "203-204-2045", 100000, "active", "Personal", vehicleInfo);
        System.out.println(
            "Admin ID: " + customerProf1.getAdminId() +"\n"+
            "Customer's first name: " + customerProf1.getFirstName() +"\n"+
            "Customer's last name: " + customerProf1.getLastName() +"\n"+
            "Customer's address: " + customerProf1.getAddress() + "\n"+
            "Customer's phone: " + customerProf1.getPhone() + "\n"+
            "Customer's income: " + customerProf1.getIncome() + "\n"+
            "Customer's status: " + customerProf1.getStatus() + "\n"+
            "Customer's use: " + customerProf1.getUse() + "\n"+
            "Customer's vehicle model: " +customerProf1.getVehicleInfo().getModel() + "\n"+
            "Customer's vehicle year: " + customerProf1.getVehicleInfo().getYear() + "\n"+
            "Customer's vehicle type: " + customerProf1.getVehicleInfo().getType() + "\n"+
            "Customer's vehicle method: " + customerProf1.getVehicleInfo().getMethod() + "\n"
        );
    }
}

