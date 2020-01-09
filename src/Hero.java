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
        BareHands hands = new BareHands("Fists", -1, 5);
        currentItem = hands;
        inventory.add(hands);
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

    private void handleAction(String action, Game game, Mutant mutant) {
        switch(action) {
            case "change":
                selectItem(game.sc);
                break;
            case "heal":
                healAction();
                break;
            case "attack":
                if (currentItem.getName().equals("Medicine")) {
                    System.out.println("Not the best idea to use medicine to attack");
                } else {
                    mutant.getDamage(currentItem.use());
                }
        }
    }

    private void healAction() {
        int idx = -1;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals("Medicine")) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println("You don't have medicine!");
        } else {
            setStamina(this.stamina + inventory.get(idx).use());
            System.out.println("You healed! New stamina: " + getStamina());
        }
    }

    public void fight(Mutant mutant, Game game) {
        selectItem(game.sc);
        boolean yourTurn = true;
        while (true) {
            System.out.println("Mutant stamina: " + mutant.getStamina());
            System.out.println("Your stamina: " + this.stamina);
            System.out.println();
            if (yourTurn) {
                System.out.println("Choose action: ");
                System.out.println("-attack");
                System.out.println("-heal");
                System.out.println("-change (to change item)");
                String choice = game.sc.nextLine();
                handleAction(choice, game, mutant);
                if (!mutant.isAlive()) {
                    break;
                }
                yourTurn = false;
            } else {
                this.setStamina(this.stamina - mutant.hit());
                if (!isAlive()) {
                    break;
                }
                yourTurn = true;
            }
            System.out.println();
        }
        if (!isAlive()) {
            System.out.println("Monster killed your character...");
        } else {
            System.out.println("You killed the monster!");
        }
    }

    public void fightWithLeader(Leader leader, Game game) {
        System.out.println("This will be toughest fight ever...");
        System.out.println("You're fighting against mutant's leader");
        System.out.println("Select your item wisely...");
        selectItem(game.sc);
        boolean yourTurn = true;
        while (true) {
            System.out.println("Leader stamina: " + leader.getStamina());
            System.out.println("Your stamina: " + getStamina());
            if (yourTurn) {
                System.out.println("Please choose your action carefully...");
                System.out.println("-attack");
                System.out.println("-heal");
                System.out.println("-change (to change item)");
                String choice = game.sc.nextLine();
                if (choice.equals("attack")) {
                    System.out.println("Attacking with all your power...");
                    if (currentItem.getName().equals("Medicine")) {
                        System.out.println("I told you to choose your item wisely...");
                        System.out.println("But you choose medicine to DAMAGE!");
                    } else {
                        leader.hit(currentItem.use());
                        if (!leader.isAlive()) {
                            break;
                        }
                    }
                } else if (choice.equals("heal")) {
                    healAction();
                } else {
                    selectItem(game.sc);
                }
                yourTurn = false;
            } else {
                System.out.println("Leader is preparing another attack...");
                this.setStamina(stamina - leader.attack());
                if (!isAlive()) {
                    break;
                }
                yourTurn = true;
            }
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
