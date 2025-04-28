import java.util.Collection;
import java.util.List;

/**
 * represents airports with attributes
 */
public class Airport {
    private String airportName;
    private Double latitude;
    private Double longitude;
    private Double radioFrequencies;
    private String regionState;
    private String regionAbbr;
    private String city;
    private String ICAO;
    private Double fuelTypes;

    /**
     * Constructor for airport class
     * @param airportName Name of the airport
     * @param latitude Latitude of the airport
     * @param longitude Longitude of the airport'
     * @param radioFrequencies Radio frequencies used at the airport
     * @param regionState State of the airport
     * @param regionAbbr Abbreviation of the state
     * @param city City where the airport is located
     * @param ICAO ICAO code of the airport
     * @param fuelTypes Types of fuel available at the airport
     */
    public Airport(String airportName, Double latitude, Double longitude, Double radioFrequencies, String regionState, String regionAbbr, String city, String ICAO, Double fuelTypes) {
        this.airportName = airportName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radioFrequencies = radioFrequencies;
        this.regionState = regionState;
        this.regionAbbr = regionAbbr;
        this.city = city;
        this.ICAO = ICAO;
        this.fuelTypes = fuelTypes;
    }

    //Getter methods
    public String getAirportName() {return airportName;}
    public Double getLatitude() {return latitude;}
    public Double getLongitude() {return longitude;}
    public Double getRadioFrequencies() {return radioFrequencies;}
    public String getRegionState() {return regionState;}
    public String getRegionAbbr() {return regionAbbr;}
    public String getCity() {return city;}
    public String getICAO() {return ICAO;}
    public Double getFuelTypes() {return fuelTypes;}

    /**
     * Retrieves a collection of available fuel types at the airport.
     * @return A collection of available fuel types.
     */
    public Collection<String> getAvailableFuelTypes() {
        // Example implementation, assuming fuelTypes is a bitmask or similar representation
        // Replace with actual logic as per your requirements
        return List.of("Jet A", "100LL", "Avgas");
    }
}

