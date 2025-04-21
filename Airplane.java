/**
 * represents airplanes with attributes
 */
public class Airplane {
    private String makeModel;
    private String planeType;
    private double fuelType;
    private double fuelBurnRate;
    private double fuelCapacity;
    private double airspeed;
    
    /**
     * constructor for airplane class
     * @param makeModel: make and model of airplane
     * @param planeType: type of airplane
     * @param fuelType: type of fuel used by airplane
     * @param fuelBurnRate: the fuel burn rate of airplane
     * @param fuelCapacity: the fuel capacity of airplane
     * @param airspeed: the speed at which the airplane travels
     */
    public Airplane(String makeModel, String planeType, double fuelType, double fuelBurnRate, double fuelCapacity, double airspeed) {
        this.makeModel = makeModel;
        this.planeType = planeType;
        this.fuelType = fuelType;
        this.fuelBurnRate = fuelBurnRate;
        this.fuelCapacity = fuelCapacity;
        this.airspeed = airspeed;
    }

    //Getter methods
    public String getMakeModel() { return makeModel; }
    public String getPlaneType() { return planeType; }
    public double getFuelType() { return fuelType; }
    public double getFuelBurnRate() { return fuelBurnRate; }
    public double getFuelCapacity() { return fuelCapacity; }
    public double getAirspeed() { return airspeed; }

    public double getMaxRange(){
        if(fuelBurnRate <= 0) return 0;
        return (fuelCapacity / fuelBurnRate) * airspeed;
    }
}