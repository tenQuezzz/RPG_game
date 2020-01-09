public class AbandonedHouse extends Location {

    private boolean boxUsed = false;

    @Override
    public void printDescription() {
        if (boxUsed) {
            System.out.println("There is nothing left you here...");
        } else {
            System.out.println("Your entered the abandoned house...");
            System.out.println("It seems that it's been a long time since somebody visited this place");
            System.out.println("It's very dark inside but you managed to see the box in the corner");
            System.out.println("So you can grab the box or leave...");
        }
    }

    private void handleBoxAction(Game game) {
        boxUsed = true;
        System.out.println("In the box you found sword and medicine");
        System.out.println("So you can grab them");
        System.out.println("Do you want to grab them?(Y/N): ");
        String choice = game.sc.nextLine();
        if (choice.equals("Y")) {
            game.getCharacter().addItemToInventory(new Sword("Sword", 10, 25));
            game.getCharacter().addItemToInventory(new Medicine("Medicine", 20, 0, 50));
        } else {
            System.out.println("It's up to you...");
        }
    }

    @Override
    public void handleUserAction(Game game) {
        printDescription();
        System.out.println("*** Select action ***");
        if (!boxUsed) {
            System.out.println("-box");
        }
        System.out.println("-leave");
        System.out.println("**********************");
        String choice = game.sc.nextLine();
        switch(choice) {
            case "box":
                handleBoxAction(game);
                break;
            case "leave":
                game.setNewCurrentLocation(game.getLocations().get("Forest"));
                break;
            default:
                System.out.println("Unknown action");
                game.getCharacter().setStamina(0);
        }
    }
}
