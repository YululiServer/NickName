package xyz.acrylicstyle.nick;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.acrylicstyle.nick.commands.Nick;
import xyz.acrylicstyle.nick.commands.NickSkin;
import xyz.acrylicstyle.nick.commands.UnNick;

@SuppressWarnings("unused")
public class NickName extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("nick").setExecutor(new Nick());
        Bukkit.getPluginCommand("unnick").setExecutor(new UnNick());
        Bukkit.getPluginCommand("nickskin").setExecutor(new NickSkin());
    }
}
