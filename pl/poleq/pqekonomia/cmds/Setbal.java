package pl.poleq.pqekonomia.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.poleq.pqekonomia.PQEkonomia;
import pl.poleq.pqekonomia.PQEkonomiaAPI;

public class Setbal implements CommandExecutor
{
    private static PQEkonomia plugin;

    public Setbal(PQEkonomia plugin)
    {
        Setbal.plugin = plugin;
        plugin.getCommand("setbal").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.isOp())
            {
                plugin.send(player,"error.only_console");
            }
            else
            {
                plugin.send(player,"error.no_permission");
            }
            return true;
        }

        if(strings.length <= 1)
        {
            return false;
        }

        //Argument 1
        Player cel = Bukkit.getPlayer(strings[0]);
        if(cel == null)
        {
            plugin.clog("Nie odnaleziono gracza " + strings[0]);
            return true;
        }

        //Argument 2
        double money = 0;
        try
        {
            money = Double.parseDouble(strings[1]);
        }
        catch (NumberFormatException e)
        {
            plugin.clog(strings[1] + " to nie jest liczba!");
        }

        PQEkonomiaAPI walutaAPI = new PQEkonomiaAPI(plugin);
        if(walutaAPI.setMoney(cel,money))
        {
            String playerMessage = plugin.getConfig().getString("messages.transactions.set").replaceAll("%kasa%",String.valueOf(money));
            cel.sendMessage(ChatColor.translateAlternateColorCodes('&',playerMessage));
            plugin.clog(ChatColor.GREEN + "Ustawiono pieniadze!");
        }
        else
        {
            String message = "Wystapil niespodziewany blad podczas ustawiania " + money + " $ graczowi " + cel.getName();
            plugin.clog(ChatColor.RED + message);
        }

        return true;
    }
}
