package lc.mine.staff;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import lc.lcspigot.commands.CommandStorage;
import lc.lcspigot.listeners.ListenerRegister;
import lc.mine.staff.commands.StaffCommand;
import lc.mine.staff.messages.StartMessages;
import lc.mine.staff.storage.StaffData;

public class LCStaffPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new StartMessages().load(this);

        final Map<Player, StaffData> staffs = new HashMap<>();
        CommandStorage.register(new StaffCommand(staffs), "s");

        registerListeners(staffs);
    }

    private void registerListeners(final Map<Player, StaffData> staffs) {
        final ListenerRegister listeners = new ListenerRegister(this);

        listeners.fastListener(PlayerQuitEvent.class, (event) -> staffs.remove(((PlayerQuitEvent)event).getPlayer()));
        listeners.fastListener(PlayerJoinEvent.class, (event) -> {
            final PlayerJoinEvent joinEvent = (PlayerJoinEvent)event;
            if (joinEvent.getPlayer().hasPermission("lcstaff")) {
                staffs.put(joinEvent.getPlayer(), new StaffData());
            }
        });
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