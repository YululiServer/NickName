package xyz.acrylicstyle.nick.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.acrylicstyle.craftbukkit.v1_8_R3.entity.CraftPlayer;

public class UnNick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "/unnick [player]");
            return true;
        }
        if (args.length == 0) {
            Player player = (Player) sender;
            player.setDisplayName(player.getName());
            player.setPlayerListName(player.getName());
            new CraftPlayer(player).getProfile().setField("name", player.getName());
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (player.getUniqueId().equals(p.getUniqueId())) continue;
                p.hidePlayer(player);
                p.showPlayer(player);
            }
            sender.sendMessage(ChatColor.GREEN + "ニックネームをリセットしました。");
            return true;
        }
        if (args.length == 1) {
            Player player = Bukkit.getPlayerExact(args[0]);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "プレイヤーが見つかりません。");
                return true;
            }
            player.setDisplayName(player.getName());
            player.setPlayerListName(player.getName());
            new CraftPlayer(player).getProfile().setField("name", player.getName());
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (player.getUniqueId().equals(p.getUniqueId())) continue;
                p.hidePlayer(player);
                p.showPlayer(player);
            }
            sender.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "のニックネームをリセットしました。");
        }
        return true;
    }
}
