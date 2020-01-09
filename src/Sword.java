public class Sword extends Item {

    Sword(String name, double cost, int damage) {
        super(name, cost, damage);
    }

    @Override
    public int use() {
        System.out.printf("Damaging %d with cool sword...\n", damage);
        return damage;
    }
}
