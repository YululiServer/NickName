package xyz.acrylicstyle.nick.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.acrylicstyle.tomeito_api.TomeitoAPI;

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
            TomeitoAPI.changeSkin(player, args[0]);
            sender.sendMessage(ChatColor.GREEN + "スキンを" + args[0] + "に設定しました。");
            return true;
        }
        if (args.length == 2) {
            Player player = Bukkit.getPlayerExact(args[1]);
            if (player == null) {
                sender.sendMessage(ChatColor.RED + "プレイヤーが見つかりません");
                return true;
            }
            TomeitoAPI.changeSkin(player, args[0]);
            player.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[0]));
            sender.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "のスキンを" + args[0] + "に設定しました。");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "/nickskin <nick> [player]");
        return true;
    }
}
