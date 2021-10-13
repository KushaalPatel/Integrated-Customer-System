public class VehicleInfo {
    // private vehicleinfo variables
    private String model; // name of the vehicle model
    private String year; // year in which the vehicle was manufactured
    private String type; // type of vehicle, must select from: sedan, hatchback, luxury, sport, other
    private String method; // how the vehical was acquired -- must select from: new certified pre-owned,
                           // used, other
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