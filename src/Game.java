import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    final int INIT_STAMINA = 100;
    private Map<String, Location> locations = new HashMap<>();
    private Hero character;
    private Location currLocation;
    public Scanner sc = new Scanner(System.in);
    private boolean gameCompleted = false;

    Game() {
        System.out.print("Enter your character name: ");
        character = new Hero(sc.nextLine(), INIT_STAMINA);
        locations.put("Forest", new Forest());
        locations.put("Road", new Road());
        locations.put("House", new AbandonedHouse());
        locations.put("Motel", new Motel());
        locations.put("Gas Station", new GasStation());
        currLocation = locations.get("Forest");
    }

    public Hero getCharacter() {
        return character;
    }

    public void play() {
        while (getCharacter().isAlive() && !gameCompleted) {
            currLocation.handleUserAction(this);
        }
        if (gameCompleted) {
            System.out.println("You did it!!!");
            System.out.println("You managed to get home");
            System.out.println("Game is successfully finished");
            System.out.println("This was only demo version");
            System.out.println("To open full version........");
        } else {
            System.out.println("You're in unknown place...");
            System.out.println("Probably you are already dead...");
            System.out.println("You need to start a new game");
        }
    }

    public void setGameCompleted(boolean gameCompleted) {
        this.gameCompleted = gameCompleted;
    }

    public void setNewCurrentLocation(Location location) {
        currLocation = location;
    }

    public Map<String, Location> getLocations() {
        return locations;
    }
}
