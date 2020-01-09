import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Motel extends Location {
    final int NUM_OF_MUTANTS = 2;
    final int MUTANT_STAMINA = 70;
    final int MIN_DAMAGE = 15;
    final int MAX_DAMAGE = 30;

    private List<Mutant> mutants = new ArrayList<>();

    Motel() {
        Random generator = new Random();
        for (int i = 0; i < NUM_OF_MUTANTS; i++) {
            mutants.add(new Mutant(MUTANT_STAMINA,
                    MIN_DAMAGE + generator.nextInt(MAX_DAMAGE - MIN_DAMAGE)));
        }
    }

    @Override
    public void printDescription() {
        System.out.println("***********************************************");
        System.out.println("So you entered motel...");
        if (mutants.size() != 0) {
            System.out.println("You heard some sounds coming from room nearby...");
            System.out.println("In the room you saw two mutants");
            System.out.println("You can fight them or you can run away(You saw Gas Station near Motel)");
        } else {
            System.out.println("There is nothing for you left in the motel, maybe it's time to go to Gas Station");
        }
        System.out.println("***********************************************");
    }

    private void handleFightAction(Game game) {
        if (mutants.size() == 0) {
            System.out.println("You defeated all monsters...");
            return;
        }
        System.out.println("It will be tough fight. You have to beat all of them...");
        for (int i = 0; i < NUM_OF_MUTANTS; i++) {
            System.out.println("Fighting mutant number " + (i + 1));
            Mutant mutant = mutants.remove(0);
            game.getCharacter().fight(mutant, game);
            if (game.getCharacter().isAlive()) {
                System.out.println("You defeated this mutant");
            } else {
                System.out.println("You didn't survive this fight...");
            }
        }
        if (game.getCharacter().isAlive()) {
            System.out.println("After you heroically defeated all mutants");
            System.out.println("You understood that they were hiding some useful stuff.");
            System.out.println("Do you want to grab medicine and gun?(Y/N): ");
            String choice = game.sc.nextLine();
            if (choice.equals("Y")) {
                System.out.println("You grabbing medicine and gun");
                game.getCharacter().addItemToInventory(new Medicine("Medicine", 20, 0, 35));
                game.getCharacter().addItemToInventory(new Gun("Gun", 100, 35, 10));
            } else {
                System.out.println("Ok, move on to Gas Station!");
                handleLeaveAction(game);
            }
        }
    }

    private void handleLeaveAction(Game game) {
        game.setNewCurrentLocation(game.getLocations().get("Gas Station"));
    }

    @Override
    public void handleUserAction(Game game) {
        printDescription();
        System.out.println("Select action");
        System.out.println("-attack");
        System.out.println("-leave (go to Gas Station)");
        String choice = game.sc.nextLine();
        switch (choice) {
            case "attack":
                handleFightAction(game);
                break;
            case "leave":
                handleLeaveAction(game);
                break;
            default:
                System.out.println("Wrong action!!!");
                System.out.println("While you being so sloppy monsters saw you and killed you!");
                System.out.println("Game over");
                game.getCharacter().setStamina(0);
        }
    }
}
