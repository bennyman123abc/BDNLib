package xyz.bendevnull.bdnlib;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.bendevnull.bdnlib.localization.LocalizationHandler;

public class InfoCommand implements CommandExecutor {
    
    private LocalizationHandler lh;
    private MainClass plugin;

    public InfoCommand(MainClass p) {
        lh = p.getLocalizationHandler();
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String locale;
        if (sender instanceof Player) {
            Player p = (Player) sender;
            locale = p.getLocale();
        } else {
            locale = plugin.getDefaultLocale();
        }
        sender.sendMessage(lh.getString(locale, "noPermissionsError"));
        return true;
    }
}