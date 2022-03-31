package pl.poleq.pqekonomia.files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.poleq.pqekonomia.PQEkonomia;

import java.io.File;
import java.io.IOException;

public class PlayerMoney
{
    private File file;
    public static PQEkonomia plugin;

    public PlayerMoney(PQEkonomia plugin)
    {
        PlayerMoney.plugin = plugin;

        //Jeżeli przy wywołaniu config nie istnieje - stwórz go
        file = new File(plugin.getDataFolder(),"data.yml");
        if(!file.exists())
        {
            if(createConfig())
            {
                plugin.clog(plugin.prefix + ChatColor.GREEN + "Stworzono plik `data.yml`!");
            }
            else
            {
                plugin.clog(plugin.prefix + ChatColor.RED + "Niepowodzenie podczas tworzenia pliku `data.yml`!");
            }
        }
    }

    public File getFile()
    {
        return file;
    }

    public boolean createConfig()
    {
        Bukkit.getConsoleSender().sendMessage(plugin.prefix + "Nie znaleziono pliku `data.yml`, tworzenie nowego...");
        file = new File(plugin.getDataFolder(), "data.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        try
        {
            cfg.save(file);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public void saveConfig(File dataFile,FileConfiguration dataConfig)
    {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
