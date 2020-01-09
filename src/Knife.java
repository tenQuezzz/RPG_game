public class Knife extends Item {
    Knife(String name, double cost, int damage) {
        super(name, cost, damage);
    }

    @Override
    public int use() {
        System.out.printf("Agile hit with knife... Damage: %d\n", damage);
        return damage;
    }
}
