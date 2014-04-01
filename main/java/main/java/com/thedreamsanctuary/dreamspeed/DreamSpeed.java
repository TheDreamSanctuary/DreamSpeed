package main.java.com.thedreamsanctuary.dreamspeed;

import main.java.com.thedreamsanctuary.dreamspeed.commands.DreamSpeedCommand;
import java.util.ArrayList;
import java.util.List;
import main.java.com.thedreamsanctuary.dreamspeed.listener.DSListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Butters on 3/30/14.
 * @author Ajcool1050
 */

public class DreamSpeed extends JavaPlugin 
{
    final DSListener listener = new DSListener(this);
    
    public int tps = 0;
    
    public static List<String> veiwing = new ArrayList<String>();
    
    @Override
    public void onEnable()
    {
        Bukkit.getServer().getPluginManager().registerEvents(listener, this);
        DSListener.loadConfig(); 
        
        getCommand("dreamspeed").setExecutor(new DreamSpeedCommand(this));
    }
    
    @Override
    public void onDisable()
    {
        DSListener.unloadConfig();
    }
}
