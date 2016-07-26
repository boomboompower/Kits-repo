package us.universalpvp.kit.api;

import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by avigh on 7/23/2016.
 */
public class Kit {
    private final String name, permission;
    private final int attack, defence;
    private final double health;
    private final ItemStack guiItem;
    private final List<String> description;
    private ItemStack[] items;

    public Kit(String name, int attack, int defence, double health,
               ItemStack guiItem, ItemStack[] items, List<String> description, String permission) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.guiItem = guiItem;
        this.items = items;
        this.description = description;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public int getExtraAttack() {
        return attack;
    }

    public int getExtraDefence() {
        return defence;
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

    public String getPermission() {
        return permission;
    }

}
