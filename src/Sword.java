public class Sword extends Item {

    Sword(String name, double cost, int damage) {
        super(name, cost, damage);
    }

    @Override
    public int use() {
        return 0;
    }
}
