package xyz.acrylicstyle.nick.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.acrylicstyle.api.v1_8_R1.MojangAPI;

import java.util.UUID;

public class NickSkin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command cannot be invoked from console.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "/nickskin <nick> [player]");
            return true;
        }
        if (args.length == 1) {
            Player player = (Player) sender;
            UUID uuid;
            try {
                uuid = MojangAPI.getUniqueId(args[0]);
            } catch (RuntimeException ignored) {
                sender.sendMessage(ChatColor.RED + "プレイヤーが見つかりません。");
                return true;
            }
            MojangAPI.changeSkin(player, uuid);
            sender.sendMessage(ChatColor.GREEN + "スキンを" + args[0] + "に設定しました。");
            return true;
        }
        if (args.length == 2) {
            Player player = Bukkit.getPlayerExact(args[1]);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "プレイヤーが見つかりません");
                return true;
            }
            UUID uuid;
            try {
                uuid = MojangAPI.getUniqueId(args[0]);
            } catch (RuntimeException ignored) {
                sender.sendMessage(ChatColor.RED + "プレイヤーが見つかりません。");
                return true;
            }
            MojangAPI.changeSkin(player, uuid);
            player.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[0]));
            sender.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "のスキンを" + args[0] + "に設定しました。");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "/nickskin <nick> [player]");
        return true;
    }
}
