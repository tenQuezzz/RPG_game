public class GasStation extends Location {
    @Override
    public void printDescription() {
        System.out.println("So, you are at gas station and you noticed working phone");
        System.out.println("But...");
        System.out.println("Near the phone you see very huge mutant");
        System.out.println("This must be mutant's leader...");
        System.out.println("There is only one way out...");
    }

    @Override
    public void handleUserAction(Game game) {
        printDescription();
        Leader leader = new Leader("Taiga", 30, 120, 1);
        System.out.println("Select action: ");
        System.out.println("-Fight");
        System.out.println("-Suicide");
        String choice = game.sc.nextLine();
        if (choice.equals("Fight")) {
            game.getCharacter().fightWithLeader(leader, game);
            if (game.getCharacter().isAlive()) {
                game.setGameCompleted(true);
            } else {
                System.out.println("You lost to leader...");
            }
        } else {
            System.out.println("Seeing their leader you decided that the only way is to die...");
            System.out.println("So you killed yourself with your bare hands...");
            System.out.println("Game Over");
            game.getCharacter().setStamina(0);
        }
    }
}
