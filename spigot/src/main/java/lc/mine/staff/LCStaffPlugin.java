package lc.mine.staff;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import lc.mine.staff.commands.StaffCommand;
import lc.mine.staff.listener.PlayerQuitListener;
import lc.mine.staff.messages.StartMessages;
import lc.mine.staff.storage.StaffData;

public class LCStaffPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new StartMessages().load(this);

        final Map<UUID, StaffData> staffs = new HashMap<>();
        final PluginCommand command = getCommand("s");
        final StaffCommand staffCommand = new StaffCommand(staffs);
        command.setExecutor(staffCommand);
        command.setTabCompleter(staffCommand);

        getServer().getPluginManager().registerEvents(new PlayerQuitListener(staffs), this);
    }

    public FileConfiguration loadConfig(final String name) {
        final String fileFormat = name + ".yml";
        final File file = new File(getDataFolder(), fileFormat);
        if (!file.exists()) {
            saveResource(fileFormat, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }
}