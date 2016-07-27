package us.universalpvp.kit.api;

import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by avigh on 7/23/2016.
 */
public class Kit {
    private final String name;
    private final boolean permission;
    private final double health;
    private final ItemStack guiItem;
    private final List<String> description;
    private final ItemStack[] items;
    private final ItemStack[] armor;

    /**
     * @param name        Name of the kit.
     * @param health      The max health.
     * @param guiItem     The item which would be in a gui representing the kit.
     * @param items       The items in the kit
     * @param armor       The armor in the kit
     * @param description The description of the kit.
     * @param permission  States if there will be a permission to use the kit.
     */
    public Kit(String name, double health,
               ItemStack guiItem, ItemStack[] items, ItemStack[] armor, List<String> description, boolean permission) {
        this.name = name;
        this.armor = armor;
        this.health = health;
        this.guiItem = guiItem;
        this.items = items;
        this.description = description;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public ItemStack[] getItems() {
        return items;
    }

    public ItemStack getGuiItem() {
        return guiItem;
    }

    public List<String> getDescription() {
        return description;
    }

    public boolean isPermissible() {
        return permission;
    }

    public ItemStack[] getArmor() {
        return armor;
    }
}

