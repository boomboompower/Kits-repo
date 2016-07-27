package us.universalpvp.kit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import us.universalpvp.kit.api.KitAPI;
import us.universalpvp.kit.commands.KitCommands;
import us.universalpvp.kit.event.listeners.GUIInteractionListener;

import java.io.File;

/**
 * Created by avigh on 7/23/2016.
 */
public class KitMain extends JavaPlugin {

    private static FileConfiguration kits;


    @Override
    public void onEnable() {
        createKitFile();

        KitAPI.initialise(this);
        KitAPI.getAPI().loadKits();

        getServer().getPluginManager().registerEvents(new GUIInteractionListener(this), this);
        getCommand("kits").setExecutor(new KitCommands(this));

        saveDefaultConfig();
    }

    public FileConfiguration getKitsConfig() {
        return kits;
    }


    private void createKitFile() {

        File kitsFile = new File(getDataFolder(), "kits.yml");

        if (!kitsFile.exists()) {
            kitsFile.getParentFile().mkdirs();
            saveResource("kits.yml", false);
        }

        kits = new YamlConfiguration();
    }

    public void saveKitsConfig() {
        saveResource("kits.yml", false);
    }

}
