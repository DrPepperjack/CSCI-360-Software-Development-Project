import java.util.List;

/**
 * this class represents flights between 2 airports
 * shows estimated time, fuel needs, and refueling stops if needed.
 */

public class Flight {
    private String startingAirport;
    private String destinationAirport;
    private double estimatedTime;
    private double distance;
    private double fuelNeeded;
    private double heading;
    private double destinationCOM;
    private List<String> refuelStops;

    /**
     * Constructor for this class
     * @param startingAirport: the starting airport.
     * @param destinationAirport: the destination airport.
     * @param estimatedTime: Estimated time it will take to travel
     * @param distance: distance between chosen airports.
     * @param fuelNeeded: the amount of fuel needed to make the trip.
     * @param heading: the direction in which the plane will fly
     * @param destinationCOM: the communication frequency used at destination airport
     * @param refuelStops: will show airport name if a refuel stop is needed at another airport.
     */

	/**
     * Constructor to initialize a flight without refueling stops.
     */
	public Flight(String startingAirport, String destinationAirport, double estimatedTime,
                double distance, double fuelNeeded, double heading, String destinationCOM) {
			this(startingAirport, destinationAirport, estimatedTime, distance, fuelNeeded, heading, destinationCOM, List.of());
		}

	/**
     * Constructor to initialize a flight that has refueling stops.
     */

    public Flight(String startingAirport, String destinationAirport, double estimatedTime, double distance, double fuelNeeded, double heading, Double destinationCOM, List<String> refuelStops) {
        this.startingAirport = startingAirport;
        this.destinationAirport = destinationAirport;
        this.estimatedTime = estimatedTime;
        this.distance = distance;
        this.fuelNeeded = fuelNeeded;
        this.heading = heading;
        this.destinationCOM = destinationCOM;
        this.refuelStops = refuelStops;
    }

    public Flight(String startingAirport2, String destinationAirport2, double estimatedTime2, double distance2,
            double fuelNeeded2, double heading2, String destinationCOM2, List<Object> of) {
            }
        //  {Auto-generated constructor stub}

    /**
     * Display all flight details and include refueling stops if necessary.
     */

    public void displayFlightPlan() {
    	System.out.println("\nFlight Plan:");
    	System.out.println("Starting Airport: " + startingAirport);
    	System.out.println("Destination Airport: " + destinationAirport);
    	System.out.printf("Estimated Time of Arrival: %.2f hours\n", estimatedTime);
    	System.out.printf("Approximate Distance: %.2f miles\n", distance);
    	System.out.printf("Fuel Needed: %.2f gallons\n", fuelNeeded);
    	System.out.printf("Plane Heading: %.2f degrees\n", heading);
    	System.out.println("Destination Communication Frequency: " + destinationCOM);
        if (refuelStops != null && !refuelStops.isEmpty()) {
            System.out.println("Refuel Stops:");
            for (int i = 0; i < refuelStops.size(); i++) {
                System.out.println(" Stop " + (i + 1) + ": " + refuelStops.get(i));
            }
        } else {
            System.out.println("No refuel stops necessary.");
        }
              
    }
    
    // Getter and Setter methods
    public String getStartingAirport() { return startingAirport; }
    public void setStartingAirport(String startingAirport) { this.startingAirport = startingAirport; }

    public String getDestinationAirport() { return destinationAirport; }
    public void setDestinationAirport(String destinationAirport) { this.destinationAirport = destinationAirport; }

    public double getEstimatedTime() { return estimatedTime; }
    public void setEstimatedTime(double estimatedTime) {
        if (estimatedTime <= 0) throw new IllegalArgumentException("Estimated time must be positive.");
        this.estimatedTime = estimatedTime;
    }

    public double getDistance() { return distance; }
    public void setDistance(double distance) {
        if (distance <= 0) throw new IllegalArgumentException("Distance must be positive.");
        this.distance = distance;
    }

    public double getFuelNeeded() { return fuelNeeded; }
    public void setFuelNeeded(double fuelNeeded) {
        if (fuelNeeded <= 0) throw new IllegalArgumentException("Fuel needed must be positive.");
        this.fuelNeeded = fuelNeeded;
    }

    public double getHeading() { return heading; }
    public void setHeading(double heading) {
        if (heading < 0 || heading > 360) throw new IllegalArgumentException("Heading must be between 0 and 360 degrees.");
        this.heading = heading;
    }

    public Double getDestinationCOM() { return destinationCOM; }
    public void setDestinationCOM(double destinationCOM) { this.destinationCOM = destinationCOM; }

    public List<String> getRefuelStops() { return refuelStops; }
    public void setRefuelStops(List<String> refuelStops) { this.refuelStops = refuelStops; }

    public String getFlightPlanDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Flight Plan:\n");
        details.append("Starting Airport: ").append(startingAirport).append("\n");
        details.append("Destination Airport: ").append(destinationAirport).append("\n");
        details.append(String.format("Estimated Time of Arrival: %.2f hours\n", estimatedTime));
        details.append(String.format("Approximate Distance: %.2f miles\n", distance));
        details.append(String.format("Fuel Needed: %.2f gallons\n", fuelNeeded));
        details.append(String.format("Plane Heading: %.2f degrees\n", heading));
        details.append("Destination Communication Frequency: ").append(destinationCOM).append("\n");
        if (refuelStops != null && !refuelStops.isEmpty()) {
            details.append("Refuel Stops:\n");
            refuelStops.forEach(stop -> details.append(" - ").append(stop).append("\n"));
        } else {
            details.append("No refuel stops necessary.\n");
        }
        return details.toString();
    }
}