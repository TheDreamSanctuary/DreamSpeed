package main.java.com.thedreamsanctuary.dreamspeed;

import main.java.com.thedreamsanctuary.dreamspeed.commands.DreamSpeedCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import main.java.com.thedreamsanctuary.dreamspeed.listener.DSListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getLogger;

/**
 * Created by Butters on 3/30/14.
 * @author Ajcool1050
 */

public class DreamSpeed extends JavaPlugin 
{

    private final List<String> veiwing = new ArrayList<String>();
    public DSListener dsListener;

    @Override
    public void onEnable()
    {
        this.saveDefaultConfig();
        this.dsListener = new DSListener(this);
        Bukkit.getServer().getPluginManager().registerEvents(dsListener, this);
        getCommand("dreamspeed").setExecutor(new DreamSpeedCommand(this));
    }
    
    @Override
    public void onDisable()
    {

    }

    public List<String> getViewing(){
        return veiwing;
    }

    public void addViewer(String viewer){
        veiwing.add(viewer);
    }

    public void removeViewer(String viewer){
        veiwing.remove(viewer);
    }

    public DSListener getDsListener(){
        return dsListener;
    }

}
