package lc.mine.staff;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lc.mine.staff.command.LCStaffReloadCommand;
import lc.mine.staff.command.ReportCommand;
import lc.mine.staff.command.StaffChatCommand;
import lc.mine.staff.config.ConfigManager;
import lc.mine.staff.data.Staff;
import lc.mine.staff.listener.PlayerJoinAndQuitListener;
import lc.mine.staff.listener.StaffChatListener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class LCStaffBungee extends Plugin {

    @Override
    public void onEnable() {
        final Map<UUID, Staff> onlineStaffs = new HashMap<>();

        loadConfig();

        final PluginManager manager = getProxy().getPluginManager();
        manager.registerCommand(this, new LCStaffReloadCommand(this));
        manager.registerCommand(this, new ReportCommand(onlineStaffs));
        manager.registerCommand(this, new StaffChatCommand(onlineStaffs));

        manager.registerListener(this, new PlayerJoinAndQuitListener(onlineStaffs));
        manager.registerListener(this, new StaffChatListener(onlineStaffs));
    }

    public void loadConfig() {
        new ConfigManager(getLogger()).load(getDataFolder());
    }
}
