
import java.util.List;
import java.util.Scanner;

/**
 * this class handles the user interface and manages
 * flight planning operations.
 */
public class Main {
    public static void main(String[] args) {
        // Display warning message at startup
        System.out.println("\n*************************************************");
        System.out.println("*                   WARNING                     *");
        System.out.println("*                                               *");
        System.out.println("* THIS SOFTWARE IS NOT TO BE USED FOR FLIGHT    *");
        System.out.println("* PLANNING OR NAVIGATIONAL PURPOSE              *");
        System.out.println("*                                               *");
        System.out.println("*************************************************\n");

        Scanner scanner = new Scanner(System.in);
        AirplaneManager airplaneManager = new AirplaneManager();
        AirportManager airportManager = new AirportManager();

        while (true) {
            System.out.println("\n[[--Flight Planning System--]]\n");
            System.out.println("1. Airport Functions");
            System.out.println("2. Airplane Functions");
            System.out.println("3. Plan a Flight");
            System.out.println("4. Exit");
            System.out.print("\nChoose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1:
                airportFunctionsMenu(scanner, airportManager);
                break;
            case 2:
                airplaneFunctionsMenu(scanner, airplaneManager);
                break;
            case 3:
                planFlight(scanner, airportManager, airplaneManager);
                break;
            case 4:
                System.out.println("Exiting program...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * This will display the Airport Functions menu.
     */
    private static void airportFunctionsMenu(Scanner scanner, AirportManager airportManager) {
        while (true) {
            System.out.println("\n[[--Airport Functions--]]\n");
            System.out.println("1. Add Airport");
            System.out.println("2. Modify Airport");
            System.out.println("3. Display Airport Information");
            System.out.println("4. Search Airport");
            System.out.println("5. Remove Airport");
            System.out.println("6. Back to Main Menu");
            System.out.print("\nChoose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1:
                airportManager.addAirport(scanner);
                break;
            case 2:
                airportManager.modifyAirport(scanner);
                break;
            case 3:
            airportManager.displayAirport(scanner);
                break;
            case 4:
                airportManager.searchAirport(scanner);
                break;
            case 5:
                airportManager.removeAirport(scanner);
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void airplaneFunctionsMenu (Scanner scanner, AirplaneManager airplaneManager) {
        while (true) {
            System.out.println("\n[[--Airplane Functions--]]\n");
            System.out.println("1. Add Airplane");
            System.out.println("2. Modify Airplane");
            System.out.println("3. Display Airplane Information");
            System.out.println("4. Search Airplane");
            System.out.println("5. Remove Airplane");
            System.out.println("6. Back to Main Menu");
            System.out.print("\nChoose an option: ");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1:
                airplaneManager.addAirplane(scanner);
                break;
            case 2:
                airplaneManager.modifyAirplane(scanner);
                break;
            case 3:
                airplaneManager.displayAirplane(scanner);
                break;
            case 4:
                airplaneManager.searchAirplane(scanner);
                break;
            case 5:
                airplaneManager.removeAirplane(scanner);
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void planFlight(Scanner scanner, AirportManager airportManager, AirplaneManager airplaneManager) {
        System.out.println("\nAvailable Airports:");
        List<Airport> airports = airportManager.getAirports();
        for (int i = 0; i < airports.size(); i++) {
            System.out.printf("%d. %s (%s)\n", i + 1, airports.get(i).getAirportName(), airports.get(i).getICAO());
        }
        System.out.print("Select start airport (number): ");
        int startIdx = scanner.nextInt() - 1;

        System.out.println("\nAvailable Destination Airports:");
        for (int i = 0; i < airports.size(); i++) {
            if (i != startIdx) {
                System.out.printf("%d. %s (%s)\n", i + 1, airports.get(i).getAirportName(), airports.get(i).getICAO());
            }
        }
        System.out.print("Select destination airport (number): ");
        int destIdx = scanner.nextInt() - 1;

        System.out.println("\nAvailable Airplanes:");
        List<Airplane> airplanes = airplaneManager.getAirplanes();
        for (int i = 0; i < airplanes.size(); i++) {
            System.out.printf("%d. %s (%s)\n", i + 1, airplanes.get(i).getMakeModel(), airplanes.get(i).getFuelType());
        }
        System.out.print("Select an airplane (number): ");
        int planeIdx = scanner.nextInt() - 1;
        
        Flight flight = FlightPlanning.planFlight(airports.get(startIdx), airports.get(destIdx), airplanes.get(planeIdx), airports);
        flight.displayFlightPlan();
    }
}

