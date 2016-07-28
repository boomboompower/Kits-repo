package us.universalpvp.kit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.universalpvp.kit.KitMain;
import us.universalpvp.kit.api.KitAPI;

import java.util.List;

/**
 * Created by avigh on 7/26/2016.
 */
public class KitCommands implements CommandExecutor {

    private final KitMain plugin;

    public KitCommands(KitMain plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("kits")) {
                if (p.hasPermission("kit.kits"))
                    KitAPI.getGUI().openGui(p, plugin);
                else
                    p.sendMessage(plugin.getConfig().getString("No-Permission"));

                if (args.length <= 2) {
                    if (args[0].equalsIgnoreCase("help")) {
                        List<String> help = plugin.getConfig().getStringList("Help");

                        p.sendMessage(help.toArray(new String[help.size()]));
                    } else if (args[0].equalsIgnoreCase("unregister")) {
                        if (KitAPI.getAPI().getRegisteredKits().contains(KitAPI.getAPI().getKitByName(args[1])))
                            KitAPI.getAPI().unregisterKit(KitAPI.getAPI().getKitByName(args[1]));
                        else {
                            p.sendMessage("The kit you specified does not exist!");
                        }
                    }
                }
            }
        } else
            sender.sendMessage("Only players can use kits!");
        return false;
    }
}

