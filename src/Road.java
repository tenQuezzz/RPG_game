public class Road extends Location {

    private boolean isVisited = false;


    @Override
    public void printDescription() {
        System.out.println("You coming closer to the road and you hear noise of approaching car...");
        System.out.println("You can hide or try to stop the car");
    }

    private void handleHidingAction(Game game) {
        System.out.println("So you waited while passing car will drive away...");
        System.out.println("But while the car was passing, you saw that driver dropped something resembled knife");
        System.out.println("Do you want to grab it? (Y/N): ");
        String choice = game.sc.nextLine();
        if (choice.equals("Y")) {
            game.getCharacter().addItemToInventory(new Knife("Knife", 20, 20));
            System.out.println("So you grabbed the knife...");
        } else {
            System.out.println("Ok, move on");
        }
        handleAdditionalAction(game);
    }

    private void handleAdditionalAction(Game game) {
        System.out.println("While walking along the road you spotted old Motel");
        System.out.println("Your possible actions:");
        System.out.println("-motel");
        System.out.println("-away (continue seeking for something else)");
        System.out.println("-status");
        String choice = game.sc.nextLine();

        switch (choice) {
            case "motel":
                System.out.println("Sooo, going to spooky motel...");
                game.setNewCurrentLocation(game.getLocations().get("Motel"));
                break;
            case "away":
                System.out.println("You've been wandering for days until you died from lack of water...");
                game.getCharacter().setStamina(0);
                break;
            case "status":
                game.getCharacter().printStatus();
                break;
            default:
                System.out.println("Wrong choice, you're dead!");
                game.getCharacter().setStamina(0);
                break;
        }
    }

    private void handleStoppingAction(Game game) {
        System.out.println("You're trying to stop the car...");
        System.out.println("But seeing you driver thought that you are one of the mutants");
        System.out.println("So he/she(we don't know) increased speed and killed you...");
        System.out.println("Game over");
        game.getCharacter().setStamina(0);
    }

    @Override
    public void handleUserAction(Game game) {
        if (isVisited) {
            handleAdditionalAction(game);
        } else {
            isVisited = true;
            printDescription();
            System.out.println("Select action");
            System.out.println("-hide");
            System.out.println("-stop");
            String choice = game.sc.nextLine();
            if (choice.equals("hide")) {
                handleHidingAction(game);
            } else {
                handleStoppingAction(game);
            }
        }

    }
}
