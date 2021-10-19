import java.util.*;

public class VehicleInfo {
    // private vehicleinfo variables
    private String model, year, type, method;
    private HashSet<String> validType = new HashSet<>(Arrays.asList("sedan", "hatchback", "luxury", "sport", "other"));
    private HashSet<String> validMethod = new HashSet<>(Arrays.asList("new", "certified pre-owned", "used", "other"));

    // construct class
    public VehicleInfo(String vehicle_model, String vehicle_year, String vehicle_type, String vehicle_method) {
        updateModel(vehicle_model);
        updateYear(vehicle_year);
        updateType(vehicle_type);
        updateMethod(vehicle_method);
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
        model = NewModel; //update model
    }

    // update year
    public void updateYear(String NewYear) {
        try{ 
            if (Integer.parseInt(NewYear) > 999 && Integer.parseInt(NewYear) < 10000){ //check if year is 4 length and all numbers
                year = NewYear; //if so, update year
            }
            else {
                throw new IllegalArgumentException("Invalid Year, Format: 4 Integers Eg. 2010."); // else throw exception if not 4 digits
            }
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid Year, Format: 4 Integers Eg. 2010."); // throw exception if not all numberss
        }
    }

    // update type
    public void updateType(String NewType) {
        if (validType.contains(NewType.toLowerCase())){ //check if valid type
            type = NewType.toLowerCase(); // if so, update type
        }
        else {
            throw new IllegalArgumentException("Type must be one of the following: sedan, hatchback, luxury, sport, other."); // else throw exception
        }
    }

    // update method
    public void updateMethod(String NewMethod) {
        if (validMethod.contains(NewMethod.toLowerCase())){ //check if valid method
            method = NewMethod.toLowerCase(); //if so, update method
        }
        else {
            throw new IllegalArgumentException("Model must be one of the following: new, certified pre-owned, used, other."); // else throw exception
        }
    }
    // testing for this class
     public static void main(String[] args){
        /*VehicleInfo testVehInfo = new VehicleInfo("GLK", "2010", "Luxury", "new");
        System.out.println(
                        "model: "+ testVehInfo.getModel()+"\n"+
                        "year: "+ testVehInfo.getYear()+"\n"+
                        "type: "+ testVehInfo.getType()+"\n"+
                        "method: "+ testVehInfo.getMethod()+"\n"
        );*/
        // year invalid
        /*VehicleInfo testVehInfo1 = new VehicleInfo("GLK", "20101", "Luxury", "new");
        System.out.println(
                        "model: "+ testVehInfo1.getModel()+"\n"+
                        "year: "+ testVehInfo1.getYear()+"\n"+
                        "type: "+ testVehInfo1.getType()+"\n"+
                        "method: "+ testVehInfo1.getMethod()+"\n"
        );*/
        // type invalid
        /*VehicleInfo testVehInfo2 = new VehicleInfo("GLK", "2010", "Luxuryasd", "new");
        System.out.println(
                        "model: "+ testVehInfo2.getModel()+"\n"+
                        "year: "+ testVehInfo2.getYear()+"\n"+
                        "type: "+ testVehInfo2.getType()+"\n"+
                        "method: "+ testVehInfo2.getMethod()+"\n"
        );*/
        // model invalid
        /*VehicleInfo testVehInfo3 = new VehicleInfo("GLK", "2010", "Luxury", "newasd");
        System.out.println(
                        "model: "+ testVehInfo3.getModel()+"\n"+
                        "year: "+ testVehInfo3.getYear()+"\n"+
                        "type: "+ testVehInfo3.getType()+"\n"+
                        "method: "+ testVehInfo3.getMethod()+"\n"
        );*/
    }
}