package xyz.bendevnull.bdnlib;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import xyz.bendevnull.bdnlib.localization.LocalizationHandler;

public class MainClass extends JavaPlugin {

    private LocalizationHandler localizationHandler;
    private String defaultLocale;

    private boolean localVerbose;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        defaultLocale = getConfig().getString("fallback_language");
        
        localizationHandler = new LocalizationHandler(new HashMap<>(), defaultLocale, this);
        localizationHandler.registerLocalization("en_US");

        this.localVerbose = getConfig().getBoolean("verbose.local");
        if (localVerbose) {
            getLogger().warning(localizationHandler.getString(null, "localVerboseMessage"));
        }

        this.getCommand("bdnlib").setExecutor(new InfoCommand(this));
    }

    @Override
    public void onDisable() {

    }

    public LocalizationHandler getLocalizationHandler() {
        return localizationHandler;
    }

    public String getDefaultLocale() {
        return defaultLocale;
    }
}
