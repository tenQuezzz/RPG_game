public class Mutant {
    private int stamina;
    private int damage;
    private boolean isAlive;

    Mutant(int stamina, int damage) {
        this.stamina = stamina;
        this.damage = damage;
    }


    public void setStamina(int newStamina) {
        if (newStamina <= 0) {
            System.out.println("Mutant is killed!!!");
        }
        this.stamina = newStamina;
    }

    public int getStamina() {
        return stamina;
    }

    public void getDamage(int damage) {
        setStamina(this.stamina - damage);
    }

    public boolean isAlive() {
        return stamina > 0;
    }

    public int hit() {
        System.out.println("Monster damaging " + damage + "...");
        return damage;
    }

}
