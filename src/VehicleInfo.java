public class VehicleInfo {
    private String model; // name of the vehicle model
    private String year; // year in which the vehicle was manufactured
    private String type; // type of vehicle, must select from: sedan, hatchback, luxury, sport, other
    private String method; // how the vehical was acquired -- must select from: new certified pre-owned,
                           // used, other

    public VehicleInfo(String vehicle_model, String vehicle_year, String vehicle_type, String vehicle_method) {
        model = vehicle_model;
        year = vehicle_year;
        type = vehicle_type;
        method = vehicle_method;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getMethod() {
        return method;
    }

    public void updateModel(String NewModel) {
        model = NewModel;
    }

    public void updateYear(String NewYear) {
        year = NewYear;
    }

    public void updateType(String NewType) {
        type = NewType;
    }

    public void updateMethod(String NewMethod) {
        method = NewMethod;
    }
}
