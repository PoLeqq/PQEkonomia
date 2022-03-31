package pl.poleq.pqekonomia.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.poleq.pqekonomia.PQEkonomia;
import pl.poleq.pqekonomia.files.PlayerMoney;

import java.io.File;
import java.util.UUID;

public class Money implements CommandExecutor
{
    public static PQEkonomia plugin;
    public static PlayerMoney playerPln;
    public static File dataFile;
    public static FileConfiguration dataConfig;

    public Money(PQEkonomia plugin)
    {
        Money.plugin = plugin;
        plugin.getCommand("money").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        playerPln = new PlayerMoney(plugin);
        dataFile = playerPln.getFile();
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);

        if(!(commandSender instanceof Player))
        {
            if(strings.length != 1)
            {
                return false;
            }

            Player cel = Bukkit.getPlayer(strings[0]);
            if(cel == null)
            {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("error.no_player").replaceAll("%arg%",strings[0])));
                return true;
            }
            UUID uuid = cel.getUniqueId();

            double money = dataConfig.getDouble(uuid + ".money");
            String message = plugin.getConfig().getString("messages.money_info_others");
            message = message.replaceAll("%kasa%",String.valueOf(money));
            message = message.replaceAll("%gracz%",cel.getName());

            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',message));

            return true;
        }

        Player player = (Player) commandSender;
        UUID uuid = player.getUniqueId();
        double money = dataConfig.getDouble(uuid + ".money");

        player.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("messages.money_info").replaceAll("%kasa%",String.valueOf(money))));

        return true;
    }
}
