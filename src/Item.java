public abstract class Item {
    protected String name;
    protected double cost;
    protected int damage;
    // Negative cost indicates that this item can't be sold
    Item(String name, double cost, int damage) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public abstract int use();
}
