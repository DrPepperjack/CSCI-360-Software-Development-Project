import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all flight planning by calculating distance,
 * estimated time, fuel needs, and determines if refueling stops are necessary.
 */
public class FlightPlanning {

    /**
     * Plans a flight from one airport to another using the given airplane.
     * 
     * @param start - Starting airport
     * @param destination - Destination airport
     * @param airplane - Airplane to be used for the flight
     * @param allAirports - List of all airports available for refueling
     */
    public static Flight planFlight(Airport start, Airport destination, Airplane airplane, List<Airport> allAirports) {
        validateInput(start, destination, airplane, allAirports);

        // Methods to calculate distance, estimated time, fuel needs, and heading.
        double distance = calculateHaversineDistance(start, destination);
        double airspeedMph = airplane.getAirspeed() * 0.621371;
        double estimatedTime = distance / (airspeedMph * 0.85);
        double fuelNeeded = estimatedTime * airplane.getFuelBurnRate();
        double heading = calculateHeading(start, destination);

        // Create a list to store refuel stops.
        List<String> refuelStops = new ArrayList<>();

        // Check if the airplane can reach the destination without refueling.
        if (calculateHaversineDistance(start, destination) > airplane.getMaxRange()) {
            // Find optimal refueling airports along the route.
            List<Airport> refuelingAirports = findRefuelingAirports(start, destination, airplane, allAirports);
            // Add refueling airports to the refuel stops list.
            for (Airport refuelAirport : refuelingAirports) {
                refuelStops.add(refuelAirport.getAirportName());
            }
        }

        // Create a complete Flight object with all the details.
        Flight flight = new Flight(
            start.getAirportName(),
            destination.getAirportName(),
            estimatedTime,
            distance,
            fuelNeeded,
            heading,
            destination.getRadioFrequencies(),
            refuelStops
        );

        return flight;
        

    }

    // Validate inputs to ensure no null values
    private static void validateInput(Airport start, Airport destination, Airplane airplane, List<Airport> allAirports) {
        if (start == null || destination == null || airplane == null || allAirports == null) {
            throw new IllegalArgumentException("Invalid input data: Ensure all values are provided.");
        }
    }

    /*
     * Determines the best refueling airports along the route from start to destination.
     * 
     * @param start - Starting airport
     * @param destination - Destination airport
     * @param airplane - Airplane to be used for the flight
     * @param allAirports - List of all airports available for refueling
     * @return List of refueling airports
     */
    private static List<Airport> findRefuelingAirports(Airport start, Airport destination, Airplane airplane, List<Airport> allAirports) {
        // Initalize the list to store the sequence of refueling airports.
        List<Airport> refuelRoute = new ArrayList<>();
        Airport current = start;
        // Get the maximum range of the airplane.
        double maxRange = airplane.getMaxRange();
    
        // Continue planning route segments until the destination is reachable.
        while (calculateHaversineDistance(current, destination) > maxRange) {
            Airport bestStop = null;
            double shortestRemaining = Double.MAX_VALUE;
    
            // Evaluate each potential airport as a refuling stop.
            for (Airport candidate : allAirports) {
                // Skip invalid candidates.
                if (candidate.equals(current) || candidate.equals(destination) || refuelRoute.contains(candidate)) continue;
    
                double distToCandidate = calculateHaversineDistance(current, candidate);
                double distFromCandidateToDest = calculateHaversineDistance(candidate, destination);
    
                // Select the best candidate based on distance and fuel compatibility.
                if (distToCandidate <= maxRange && isFuelCompatible(airplane, candidate) && distFromCandidateToDest < shortestRemaining) {
                    bestStop = candidate;
                    shortestRemaining = distFromCandidateToDest;
                }
            }
    
            // Handle case where no suitable refueling stop is found.
            if (bestStop == null) {
                System.out.println("ERROR: No valid refuel stop from " + current.getAirportName());
                break;
            }
    
            // Add the best stop to the refuel route.
            refuelRoute.add(bestStop);
            current = bestStop;
        }
    
        return refuelRoute;
    }
    

    // Calculate distance using the Haversine formula
    private static double calculateHaversineDistance(Airport a, Airport b) {
        // Radius of the Earth used for calculating distance. 
        final int R = 3959; 

        // Convert the latitude and longitude from degrees to radians.
        double lat1 = Math.toRadians(a.getLatitude());
        double lon1 = Math.toRadians(a.getLongitude());

        // Convert the latitude and longitude from degrees to radians.
        double lat2 = Math.toRadians(b.getLatitude());
        double lon2 = Math.toRadians(b.getLongitude());

        // Calculate the differences between the two points
        double latDistance = lat2 - lat1;
        double lonDistance = lon2 - lon1;

        // Using the Haversine formula, we calculate the distance.
        // Step 1: Formula to calculate the cord lengths between two points
        double aFormula = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                         + Math.cos(lat1) * Math.cos(lat2)
                         * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        
        // Step 2: Formula to calculate the angle between the two points. 
        double c = 2 * Math.atan2(Math.sqrt(aFormula), Math.sqrt(1 - aFormula));
        return R * c;
    }

    // Calculate the heading from one airport to another
    private static double calculateHeading(Airport a, Airport b) {
        // Convert the latitudes and longitudes from degrees to radians.
        double lat1 = Math.toRadians(a.getLatitude());
        double lat2 = Math.toRadians(b.getLatitude());

        // Calculate the difference in longitude between the two airports. 
        double deltaLon = Math.toRadians(b.getLongitude() - a.getLongitude());

        // Calculate the Y bearing.
        // Represents the projection of the second point onto the east-west axis.
        double y = Math.sin(deltaLon) * Math.cos(lat2);

        // Calculate the X bearing.
        // Represents the projection of the second point onto the north-south axis.
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(deltaLon);
        
        // Convert the result from radians to degrees. 
        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
    }

    // Check if the airplane's fuel type is compatible with the airport's available fuel types.
    private static boolean isFuelCompatible(Airplane airplane, Airport airport) {
        return airport.getAvailableFuelTypes().stream().anyMatch(fuel ->
            (airplane.getFuelType() == 1.0 && fuel.equalsIgnoreCase("Avgas")) ||
            (airplane.getFuelType() == 2.0 && fuel.equalsIgnoreCase("Jet A"))
        );
    }
    
}