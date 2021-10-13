public class VehicleInfo {
    // private vehicleinfo variables
    private String model, year, type, method;

    // construct class
    public VehicleInfo(String vehicle_model, String vehicle_year, String vehicle_type, String vehicle_method) {
        model = vehicle_model;
        year = vehicle_year;
        type = vehicle_type;
        method = vehicle_method;
    }

    // get model
    public String getModel() {
        return model;
    }

    // get year
    public String getYear() {
        return year;
    }

    // get type
    public String getType() {
        return type;
    }

    // get method
    public String getMethod() {
        return method;
    }

    // update model
    public void updateModel(String NewModel) {
        model = NewModel;
    }

    // update year
    public void updateYear(String NewYear) {
        year = NewYear;
    }

    // update type
    public void updateType(String NewType) {
        type = NewType;
    }

    // update method
    public void updateMethod(String NewMethod) {
        method = NewMethod;
    }
}