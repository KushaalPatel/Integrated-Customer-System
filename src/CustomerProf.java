public class CustomerProf {
    // customerprof private variables
    private String adminID, firstName, lastName, address, phone;
    private Float income;
    private String status, use;
    private VehicleInfo VehInfo;

    // construct the class
    public CustomerProf(String administrativeId, String customerfirstName, String customerlastName,
            String customerAddress, String customerPhone, Float customerIncome, String customerStatus,
            String customerUse, VehicleInfo customerVehInfo) {
        adminID = administrativeId;
        firstName = customerfirstName;
        lastName = customerlastName;
        address = customerAddress;
        phone = customerPhone;
        income = customerIncome;
        status = customerStatus;
        use = customerUse;
        VehInfo = customerVehInfo;
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
        phone = customerPhone;
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
        status = customerStatus;
    }

    // get use
    public String getUse() {
        return use;
    }

    // get vehicleinfo
    public VehicleInfo getVehicleInfo() {
        return VehInfo;
    }

    // update vehicleinfo
    public void updateVehicleInfo(VehicleInfo customerVehInfo) {
        VehInfo = customerVehInfo;
    }

}
