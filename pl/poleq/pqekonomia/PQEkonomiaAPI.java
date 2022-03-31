package pl.poleq.pqekonomia;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.poleq.pqekonomia.files.PlayerMoney;

import java.io.File;
import java.util.UUID;

public class PQEkonomiaAPI
{
    public static PQEkonomia plugin;

    public PlayerMoney playerPln;
    public File dataFile;
    public FileConfiguration dataConfig;

    public PQEkonomiaAPI(PQEkonomia plugin)
    {
        PQEkonomiaAPI.plugin = plugin;

        reloadFiles();
    }

    public void reloadFiles()
    {
        //Dostep do pliku data.yml
        playerPln = new PlayerMoney(plugin);
        this.dataFile = playerPln.getFile();
        this.dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public double getMoney(Player player)
    {
        UUID uuid = player.getUniqueId();
        return dataConfig.getDouble(uuid + ".money");
    }
    public double getMoney(UUID uuid)
    {
        return dataConfig.getDouble(uuid + ".money");
    }
    public double getMoney(String playerName)
    {
        Player player = Bukkit.getPlayer(playerName);
        if(player == null)
        {
            return 0;
        }
        UUID uuid = player.getUniqueId();
        return dataConfig.getDouble(uuid + ".money");
    }

    public boolean setMoney(Player player,double money)
    {
        UUID uuid = player.getUniqueId();
        dataConfig.set(uuid + ".money",money);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }
    public boolean setMoney(UUID uuid,double money)
    {
        dataConfig.set(uuid + ".money",money);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }
    public boolean setMoney(String playerName,double money)
    {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();

        dataConfig.set(uuid + ".money",money);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }


    public boolean addMoney(Player player, double money)
    {
        reloadFiles();
        UUID uuid = player.getUniqueId();

        double actualMoney = dataConfig.getDouble(uuid + ".money");
        actualMoney = Math.round((actualMoney + money) * 100.0) / 100.0;

        dataConfig.set(uuid + ".money",actualMoney);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }
    public boolean addMoney(UUID uuid,double money)
    {
        reloadFiles();
        double actualMoney = dataConfig.getDouble(uuid + ".money");
        actualMoney = Math.round((actualMoney + money) * 100.0) / 100.0;

        dataConfig.set(uuid + ".money",actualMoney);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }
    public boolean addMoney(String playerName,double money)
    {
        reloadFiles();
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();

        double actualMoney = dataConfig.getDouble(uuid + ".money");
        actualMoney = Math.round((actualMoney + money) * 100.0) / 100.0;

        dataConfig.set(uuid + ".money",actualMoney);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }


    public boolean removeMoney(Player player,double money)
    {
        reloadFiles();
        UUID uuid = player.getUniqueId();
        double actualMoney = dataConfig.getDouble(uuid + ".money");

        if(money > actualMoney)
        {
            return false;
        }

        actualMoney = Math.round((actualMoney - money) * 100.0) / 100.0;
        dataConfig.set(uuid + ".money",actualMoney);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }
    public boolean removeMoney(UUID uuid,double money)
    {
        reloadFiles();
        double actualMoney = dataConfig.getDouble(uuid + ".money");

        if(money > actualMoney)
        {
            return false;
        }

        actualMoney = Math.round((actualMoney - money) * 100.0) / 100.0;
        dataConfig.set(uuid + ".money",actualMoney);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }
    public boolean removeMoney(String playerName,double money)
    {
        reloadFiles();
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();
        double actualMoney = dataConfig.getDouble(uuid + ".money");

        if(money > actualMoney)
        {
            return false;
        }

        actualMoney = Math.round((actualMoney - money) * 100.0) / 100.0;
        dataConfig.set(uuid + ".money",actualMoney);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }


    public boolean removeMoney(Player player,double money,boolean debt)
    {
        reloadFiles();
        UUID uuid = player.getUniqueId();
        double actualMoney = plugin.getConfig().getDouble(uuid + ".money");

        if(!debt)
        {
            if (money > actualMoney)
            {
                return false;
            }
        }

        actualMoney = Math.round((actualMoney - money) * 100.0) / 100.0;
        dataConfig.set(uuid + ".money",actualMoney);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }
    public boolean removeMoney(UUID uuid,double money,boolean debt)
    {
        reloadFiles();
        double actualMoney = plugin.getConfig().getDouble(uuid + ".money");

        if(!debt)
        {
            if (money > actualMoney)
            {
                return false;
            }
        }

        actualMoney = Math.round((actualMoney - money) * 100.0) / 100.0;
        dataConfig.set(uuid + ".money",actualMoney);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }
    public boolean removeMoney(String playerName,double money,boolean debt)
    {
        reloadFiles();
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();
        double actualMoney = plugin.getConfig().getDouble(uuid + ".money");

        if(!debt)
        {
            if (money > actualMoney)
            {
                return false;
            }
        }

        actualMoney = Math.round((actualMoney - money) * 100.0) / 100.0;
        dataConfig.set(uuid + ".money",actualMoney);
        playerPln.saveConfig(dataFile,dataConfig);
        return true;
    }
}
