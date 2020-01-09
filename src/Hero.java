import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hero {
    private String name;
    private int stamina;
    private List<Item> inventory;
    private Item currentItem;

    Hero(String name, int stamina) {
        this.name = name;
        this.stamina = stamina;
        inventory = new ArrayList<>();
        inventory.add(new BareHands("Fists", -1, 5));
    }

    public void printStatus() {
        System.out.println("*** Your status ***");
        System.out.println("Stamina: " + stamina);
        System.out.println("Current item: " + (currentItem == null ? "not selected" : currentItem.getName()));
        System.out.println("Inventory: ");
        for (Item item : inventory) {
            System.out.println("-" + item.getName());
        }
        System.out.println("********************");
    }

    public int getStamina() {
        return stamina;
    }


    public List<Item> getInventory() {
        return inventory;
    }

    public void setStamina(int newStamina) {
        this.stamina = newStamina;
    }

    public void selectItem(Scanner sc) {
        System.out.println("Your inventory: ");
        for (Item item: inventory) {
            System.out.println("-" + item.getName());
        }
        System.out.print("Select item: ");
        String itemName = sc.nextLine();
        for (Item item: inventory) {
            if (item.getName().equals(itemName)) {
                currentItem = item;
            }
        }
    }


    public void fight(AnimalMutant mutant, Game game) {
        selectItem(game.sc);
        boolean yourTurn = true;
        while (true) {
            System.out.println("Mutant stamina: " + mutant.getStamina());
            System.out.println("Your stamina: " + this.stamina);
            System.out.println();
            if (yourTurn) {
                System.out.println("-attack");
                System.out.println("-change (to change item)");
                String choice = game.sc.nextLine();
                if (choice.equals("change")) {
                    selectItem(game.sc);
                }
                mutant.getDamage(currentItem.use());
                if (!mutant.isAlive()) {
                    break;
                }
                yourTurn = false;
                System.out.println();
            } else {
                this.setStamina(this.stamina - mutant.hit());
                if (!isAlive()) {
                    break;
                }
                yourTurn = true;
                System.out.println();
            }
        }
        if (!isAlive()) {
            System.out.println("Monster killed your character...");
        } else {
            System.out.println("You killed the monster!");
        }
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return stamina > 0;
    }
}
