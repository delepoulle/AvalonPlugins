package fr.theobessel.silkspawner;

import org.bukkit.plugin.java.JavaPlugin;

public class pluginLoader extends JavaPlugin{
    public static void main(String[] args) {}
    @Override
    public void onEnable() {
        System.out.println("[PLUGIN INFO] Plugin started !");
        getServer().getPluginManager().registerEvents(new spawnerMining(), this);
    }
    @Override
    public void onDisable() {
        System.out.println("[PLUGIN INFO] Plugin closed !");
    }
}
