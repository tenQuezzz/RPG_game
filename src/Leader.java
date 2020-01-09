public class Leader {
    final int HEAL = 30;
    private int numOfHealing;
    private String name;
    private int baseDamage;
    private int stamina;

    Leader(String name, int baseDamage, int stamina, int numOfHealing) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.stamina = stamina;
        this.numOfHealing = numOfHealing;
    }

    public int attack() {
        System.out.printf("%s attacking!!! Damaging %d...\n", name, baseDamage);
        return baseDamage;
    }

    public void hit(int damage) {
        stamina -= damage;
    }

    public void heal() {
        if (numOfHealing > 0) {
            System.out.println("Leader is healing himself...");
            this.stamina += HEAL;
            numOfHealing -= 1;
        } else {
            System.out.println("Leader in trying to heal himself but it seems he is out of medicine...");
        }
    }

    public int getStamina() {
        return stamina;
    }

    public boolean isAlive() {
        return stamina > 0;
    }
}
