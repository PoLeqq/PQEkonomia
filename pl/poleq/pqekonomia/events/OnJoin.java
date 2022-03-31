package pl.poleq.pqekonomia.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.poleq.pqekonomia.PQEkonomia;
import pl.poleq.pqekonomia.files.PlayerMoney;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class OnJoin implements Listener
{
    public static PQEkonomia plugin;

    public OnJoin(PQEkonomia plugin)
    {
        OnJoin.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        PlayerMoney playerPln = new PlayerMoney(plugin);
        File dataFile = playerPln.getFile();
        FileConfiguration dataConfig = YamlConfiguration.loadConfiguration(dataFile);

        if(dataConfig.contains(uuid + ".money"))
        {
            return;
        }

        dataConfig.set(uuid+".money",0);
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
