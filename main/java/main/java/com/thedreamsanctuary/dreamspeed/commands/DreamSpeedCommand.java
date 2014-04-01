/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.com.thedreamsanctuary.dreamspeed.commands;

import main.java.com.thedreamsanctuary.dreamspeed.DreamSpeed;
import static main.java.com.thedreamsanctuary.dreamspeed.DreamSpeed.veiwing;
import main.java.com.thedreamsanctuary.dreamspeed.listener.DSListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Ajcool1050
 */

public class DreamSpeedCommand implements CommandExecutor 
{

    final DreamSpeed ds;
    public DreamSpeedCommand(DreamSpeed ds)
    {
        this.ds = ds;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            
            if (player.hasPermission("dreamspeed.command.use"))
            { 
                if (!veiwing.contains(player.getName()))
                {
                    veiwing.add(player.getName());
                    DSListener.enableGUI(player);
                }
                else
                {
                    veiwing.remove(player.getName());
                    DSListener.disableGUI(player);
                }
            }
            else player.sendMessage(ChatColor.GOLD + "You are not able to execute that command.");
        }
        else sender.sendMessage("Must be and ingame player to execute command.");
        return false;
    }  
}
