public class AbandonedHouse extends Location {
    @Override
    public void printDescription() {

    }

    @Override
    public void handleUserAction(Game game) {
        System.out.println("You are in abandoned house!");
    }
}
