package main.java.com.thedreamsanctuary.dreamspeed.listener;

import main.java.com.thedreamsanctuary.dreamspeed.DreamSpeed;
import org.bukkit.Bukkit;
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

    private final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private final Scoreboard infoGUI = manager.getNewScoreboard();
    private final Objective infoOb = infoGUI.registerNewObjective("serverInfo", "dummy");
    private final Score ticks = infoOb.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "TPS:"));
    private final Score ram = infoOb.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "MEM:"));

    private final DreamSpeed plugin;
    private long mem = 0;

    public DSListener(DreamSpeed plugin)
    {
        this.plugin = plugin;
    }
   
    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
        {
            @Override
            public final void run()
            {
                Runtime runtime = Runtime.getRuntime();
                mem = ((runtime.totalMemory() - runtime.freeMemory()) / 1048576L);

                for (String player : plugin.getViewing())
                {
                    Player p = Bukkit.getServer().getPlayer(player);
                    updateGUI(p);
                 }
            }
        }, 0, 1); //Set to this for testing, will read from config later on.

    }
    
    public void enableGUI(Player player)
    {
        infoOb.setDisplaySlot(DisplaySlot.SIDEBAR);
        infoOb.setDisplayName(ChatColor.GOLD + "Server Info");

        ram.setScore((int) mem);
        
        player.setScoreboard(infoGUI);
    }
    
    public void disableGUI(Player player)
    {
        player.setScoreboard(manager.getNewScoreboard());
    }
    
    public void updateGUI(Player player)
    {   
        ram.setScore((int) mem);

        player.setScoreboard(infoGUI);
    }
    
}
