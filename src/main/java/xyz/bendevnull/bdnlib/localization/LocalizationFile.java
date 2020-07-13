package xyz.bendevnull.bdnlib.localization;

import java.io.Reader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocalizationFile {

    private FileConfiguration config;
    private String locale;
    
    public LocalizationFile(String locale, Reader file) {
        config = YamlConfiguration.loadConfiguration(file);
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    public FileConfiguration getConfig() {
        return config;
    }
}