/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.com.thedreamsanctuary.dreamspeed.commands;

import main.java.com.thedreamsanctuary.dreamspeed.DreamSpeed;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Ajcool1050
 */

public class DreamSpeedCommand implements CommandExecutor 
{

    private final DreamSpeed plugin;

    public DreamSpeedCommand(DreamSpeed plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command comand, String string, String[] args){
        if(comand.getName().equalsIgnoreCase("dreamspeed")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Player only command!");
                return true;
            }
            String playerName = sender.getName();
            Player player = (Player) sender;
            if(plugin.getViewing().contains(playerName)){
                plugin.removeViewer(playerName);
                plugin.getDsListener().disableGUI(player);
            }
            else{
                plugin.addViewer(playerName);
                plugin.getDsListener().enableGUI(player);
            }

            return true;
        }
        return false;
    }

}
