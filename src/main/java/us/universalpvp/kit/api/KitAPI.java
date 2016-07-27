package us.universalpvp.kit.api;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import us.universalpvp.kit.KitMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by avigh on 7/24/2016.
 */
public final class KitAPI {

    private static KitAPI api;
    private static KitGUI kitGUI;

    public static KitAPI getAPI() {
        return api;
    }

    public static KitGUI getGUI() {
        return kitGUI;
    }


    public static void initialise(JavaPlugin plugin) {
        if (api != null || kitGUI != null) {
            throw new IllegalStateException();
        }
        api = new KitAPI(plugin);
        kitGUI = new KitGUI();
    }

    private final JavaPlugin plugin;

    private List<Kit> registeredKits = new ArrayList<>();

    private KitAPI(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadKits() {
        KitMain pl = (KitMain) plugin;

        ConfigurationSection cs = pl.getKitsConfig().getConfigurationSection("Kits");

        for (String cKey : cs.getKeys(false)) {
            String name = cs.getString(cKey + "kit-name", cKey),
                    permission = cs.getString(cKey + "permission", "");

            double health = cs.getDouble(cKey + ".max-health", 20.0);
            List<String> description = cs.getStringList(cKey + ".description");

            ItemStack guiItem = new ItemStack(Material.getMaterial(cs.getString(cKey + ".gui-item").toUpperCase()));
            List<ItemStack> items = new ArrayList<>(),
                    armor = new ArrayList<>();

            ConfigurationSection ics = cs.getConfigurationSection("Items");
            for (String iKey : ics.getKeys(false)) {
                int quantity = ics.getInt(iKey + ".quantity");
                Material material = Material.getMaterial(ics.getString(iKey + ".material"));

                ItemStack item = new ItemStack(material, quantity);
                ics.getStringList(iKey + ".enchantments")
                        .forEach(s -> item.addEnchantment(Enchantment.getByName(s.split(":")[0]),
                                Integer.parseInt(s.split(":")[1])));
                item.getItemMeta().setDisplayName(iKey + ".name");

                items.add(item);
            }

            ConfigurationSection acs = cs.getConfigurationSection("armor");
            ConfigurationSection[] hcs = {acs.getConfigurationSection("Helmet"), acs.getConfigurationSection("Chest-plate"),
                    acs.getConfigurationSection("Leggings"), acs.getConfigurationSection("Boots")};

            for (ConfigurationSection armorCs : hcs) {
                Material material = Material.getMaterial(armorCs.getString("material"));

                ItemStack armorItem = new ItemStack(material);
                ics.getStringList(armorCs + ".enchantments")
                        .forEach(s -> armorItem.addEnchantment(Enchantment.getByName(s.split(":")[0]),
                                Integer.parseInt(s.split(":")[1])));
                armorItem.getItemMeta().setDisplayName("name");

                armor.add(armorItem);
            }


            Kit kit = new Kit(name, health, guiItem, items.toArray(new ItemStack[items.size()]),
                    armor.toArray(new ItemStack[armor.size()]), description, permission);
            registerKit(kit);
        }
    }

    public List<Kit> getRegisteredKits() {
        return registeredKits;
    }

    public void registerKit(Kit... kits) {
        Arrays.asList(kits).forEach(registeredKits::add);
    }
}
