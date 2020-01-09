public class Medicine extends Item {

    private int healingValue;

    Medicine(String name, double cost, int damage, int healingValue) {
        super(name, cost, damage);
        this.healingValue = healingValue;
    }

    @Override
    public int use() {
        return healingValue;
    }
}
