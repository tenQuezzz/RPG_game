public class Gun extends Item {

    private int numOfBullets;
    private final int MIN_DAMAGE = 15;

    Gun(String name, double cost, int damage, int numOfBullets) {
        super(name, cost, damage);
        this.numOfBullets = numOfBullets;
    }

    @Override
    public int use() {
        if (numOfBullets <= 0) {
            System.out.println("There is no bullets in gun. But you hit using gun like club damaging " + MIN_DAMAGE);
            return MIN_DAMAGE;
        } else {
            System.out.println("You use one more bullet, damaging " + (MIN_DAMAGE + damage));
            numOfBullets-=1;
            return MIN_DAMAGE + damage;
        }
    }
}
