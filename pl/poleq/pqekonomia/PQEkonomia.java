package pl.poleq.pqekonomia;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.poleq.pqekonomia.cmds.Addbal;
import pl.poleq.pqekonomia.cmds.Money;
import pl.poleq.pqekonomia.cmds.Rembal;
import pl.poleq.pqekonomia.cmds.Setbal;
import pl.poleq.pqekonomia.events.OnJoin;
import pl.poleq.pqekonomia.files.PlayerMoney;

public class PQEkonomia extends JavaPlugin
{
    public static PQEkonomiaAPI pqwaluaAPI;
    public final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "PQEkonomia" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;

    @Override
    public void onEnable()
    {
        //Configi
        getConfig().options().copyDefaults(true);
        saveConfig();
        new PlayerMoney(this);

        //Ukochane API <3
        pqwaluaAPI = new PQEkonomiaAPI(this);

        //Komendy
        new Money(this);
        new Addbal(this);
        new Rembal(this);
        new Setbal(this);

        //Eventy
        getServer().getPluginManager().registerEvents(new OnJoin(this),this);
    }

    public void send(Player player, String path)
    {
        String msg = ChatColor.translateAlternateColorCodes('&',getConfig().getString(path));
        player.sendMessage(prefix + " " + msg);
    }
    public void clog(String msg)
    {
        Bukkit.getConsoleSender().sendMessage(prefix + msg);
    }
}
