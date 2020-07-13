package xyz.bendevnull.bdnlib.localization;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

public class LocalizationHandler {
    private Map<String, LocalizationFile> languageFiles;
    private String defaultLanguage;
    private JavaPlugin plugin;

    public LocalizationHandler(Map<String, LocalizationFile> lMap, String defLang, JavaPlugin p) {
        if (lMap != null) languageFiles = lMap;
        else languageFiles = new HashMap<>();

        if (defLang != null) defaultLanguage = defLang;
        else defaultLanguage = "en_US";

        plugin = p;
    }

    public String getString(String lang, String key) {
        if (lang == null) lang = defaultLanguage;
        LocalizationFile file = languageFiles.get(lang);
        if (file != null) {
            String res = file.getConfig().getString(key);
            if (res != null) return res;
        }
        return key;
    }

    public void registerLocalization(String lang) {
        String filePath = String.format("lang/%s.yml", lang);
        try {
            InputStream resource = plugin.getResource(filePath);
            if (resource == null) throw new NullPointerException(filePath + " could not be found.");
            Reader file = new InputStreamReader(resource, "UTF8");
            languageFiles.put(lang, new LocalizationFile(lang, file));
        } catch(Exception e) {
            plugin.getLogger().warning(e.toString());
        }
    }
}