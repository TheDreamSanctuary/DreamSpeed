package main.java.com.thedreamsanctuary.dreamspeed.listener;

import java.io.File;
import main.java.com.thedreamsanctuary.dreamspeed.DreamSpeed;
import static main.java.com.thedreamsanctuary.dreamspeed.DreamSpeed.veiwing;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getLogger;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

/**
 * @author Ajcool1050
 */

public class DSListener implements Listener
{
    static DreamSpeed ds;
    public DSListener(DreamSpeed ds)
    {
        DSListener.ds = ds;
    }
    
    public static long mem = 0;
    public static long getMEM()
    {
        return mem;
    }
   
    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        if (event.getPlugin() == ds) 
        {
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ds, new Runnable()
            {
              long mem;
              
              @Override
              public final void run()
              {
                  
                  
                  Runtime runtime = Runtime.getRuntime();
                  mem = ((runtime.totalMemory() - runtime.freeMemory()) / 1048576L);
                  
                  for (String player : veiwing)
                  {
                    Player p = Bukkit.getServer().getPlayer(player);
                    updateGUI(p);
                  }
                }
            }, 0, 1); //Set to this for testing, will read from config later on.
        }
    } 
    
    public static void loadConfig()
    {
        File config = new File(ds.getDataFolder(), "config.yml");
        
        if (!config.exists())
        {
            try 
            {
                ds.saveDefaultConfig();
                getLogger().info("Config dose not exist, Creating...");
            }
            catch (Exception e) 
            {
                getLogger().severe("Error creating config.");
            } 
        }
        else
        {
            getLogger().info("Config Loaded!");
        }
    }
    
    public static void unloadConfig()
    {
        File config = new File(ds.getDataFolder(), "config.yml");
        
        if (!config.exists())
        {
            getLogger().severe("Error saving config, No config to save");
        }
        else
        {
            try 
            {
                ds.saveConfig();
                getLogger().info("Config Saved!");   
            }
            catch (Exception e) 
            {
                getLogger().severe("Error saving config.");
            }  
        }
    }
    
    final static ScoreboardManager manager = Bukkit.getScoreboardManager();
    final static Scoreboard infoGUI = manager.getNewScoreboard();
    final static Objective infoOb = infoGUI.registerNewObjective("serverInfo", "dummy");
    final static Score ticks = infoOb.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "TPS:"));
    final static Score ram = infoOb.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "MEM:"));
    
    public static void enableGUI(Player player)
    {
        infoOb.setDisplaySlot(DisplaySlot.SIDEBAR);
        infoOb.setDisplayName(ChatColor.GOLD + "Server Info");

        ram.setScore((int) getMEM());
        
        player.setScoreboard(infoGUI);
    }
    
    public static void disableGUI(Player player)
    {   
        player.setScoreboard(manager.getNewScoreboard());
    }
    
    public static void updateGUI(Player player)
    {   
        ram.setScore((int) getMEM());

        player.setScoreboard(infoGUI);
    }
    
}
