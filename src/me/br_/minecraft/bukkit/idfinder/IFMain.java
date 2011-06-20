package me.br_.minecraft.bukkit.idfinder;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public class IFMain extends JavaPlugin {
	Logger log = Logger.getLogger("minecraft");
	public static Set<Player> active;

	public void onDisable() {
		log.info("[IDFinder] Disabled.");
	}

	public void onEnable() {
		getServer().getPluginManager().registerEvent(Event.Type.BLOCK_DAMAGE,
				new IFListener(), Event.Priority.Low, this);
		active = new HashSet<Player>();
		log.info("[IDFinder] Enabled.");
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if ((sender instanceof ConsoleCommandSender)) {
			log.info("[IDFinder] You can't use this command!");
			return false;
		}
		if ("id".equalsIgnoreCase(label)) {
			if (args.length != 0) {
				return false;
			}
			if (active.contains((Player) sender)) {
				sender.sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN
						+ "IDFinder" + ChatColor.GREEN + "] " + ChatColor.RED
						+ "Disabled.");
				active.remove((Player) sender);
			} else {
				sender.sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN
						+ "IDFinder" + ChatColor.GREEN + "] " + ChatColor.GOLD
						+ "Enabled.");
				active.add((Player) sender);
			}
		} else {
			sender.sendMessage(ChatColor.GREEN + "[" + ChatColor.DARK_GREEN
					+ "IDFinder" + ChatColor.GREEN + "] " + ChatColor.GOLD
					+ ((Player) sender).getItemInHand().getTypeId()
					+ ChatColor.WHITE + " = " + ChatColor.RED
					+ ((Player) sender).getItemInHand().getType());
		}
		return true;
	}
}