package me.br_.minecraft.bukkit.idfinder;

import org.bukkit.ChatColor;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;

public class IFListener extends BlockListener
{
  public void onBlockDamage(BlockDamageEvent event)
  {
    if (IFMain.active.contains(event.getPlayer())) {
      event.getPlayer().sendMessage(
        ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + "IDFinder" + 
        ChatColor.GREEN + "] " + ChatColor.GOLD + 
        event.getBlock().getTypeId() + ChatColor.WHITE + 
        " = " + ChatColor.RED + 
        event.getBlock().getType());
      event.setCancelled(true);
    }
  }
}