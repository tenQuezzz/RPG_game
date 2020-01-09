import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Forest extends Location {

    final int NUM_OF_MUTANTS = 2;
    final int MIN_DAMAGE = 5;
    final int MAX_DAMAGE = 20;
    private List<Mutant> mutants = new ArrayList<>();

    Forest() {
        Random damageGenerator = new Random();
        for (int i = 0; i < NUM_OF_MUTANTS; i++) {
            mutants.add(new Mutant(30,
                    MIN_DAMAGE + damageGenerator.nextInt(MAX_DAMAGE - MIN_DAMAGE)));
        }
    }

    @Override
    public void printDescription() {
        System.out.println("***********************************************");
        System.out.println("You are in some unknown forrest...");
        if (mutants.isEmpty()) {
            System.out.println("You killed all mutants in this zone, there is nothing left for you here.");
            System.out.println("You can move on");
        } else {
            System.out.printf("You see %d mutants wandering in the bushes.", mutants.size());
            System.out.println("You can try to run through them to the road");
            System.out.println("Or you can try to fight with them");
            System.out.println("But you spotted some house that seems abandoned, you can try go there");
        }
        System.out.println("Possible actions:");
        printOptions();
        System.out.println("***********************************************");
        System.out.println("Select your next action: ");
    }

    public void printOptions() {
        System.out.println("-status (to print info about your character)");
        System.out.println("-road (to go to the road BUT you won't be able to come back...)");
        System.out.println("-house (to go to the abandoned house)");
        if (mutants.size() != 0) {
            System.out.println("-fight (to fight with mutants)");
        }
    }

    @Override
    public void handleUserAction(Game game) {
        printDescription();
        String choice = game.sc.nextLine();
        switch (choice) {
            case "status":
                game.getCharacter().printStatus();
                break;
            case "road":
                game.setNewCurrentLocation(game.getLocations().get("Road"));
                break;
            case "house":
                game.setNewCurrentLocation(game.getLocations().get("House"));
                break;
            case "fight":
                System.out.println("*** Fighting ***");
                if (mutants.size() != 0) {
                    Hero character = game.getCharacter();
                    character.fight(mutants.remove(0), game);
                } else {
                    System.out.println("You've already killed all mutants!");
                }
                System.out.println("********************");
                break;
            default:
                System.out.println("Unknown action");
                game.getCharacter().setStamina(0);
        }
    }
}
