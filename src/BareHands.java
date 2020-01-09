public class BareHands extends Item {
    BareHands(String name, double cost, int damage) {
        super(name, cost, damage);
    }

    @Override
    public int use() {
        System.out.printf("Damaging %d with your bare hands...\n", damage);
        return damage;
    }
}
