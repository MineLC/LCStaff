package lc.mine.staff.listener;

import java.util.Map;
import java.util.UUID;

import lc.mine.staff.data.Staff;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class PlayerJoinAndQuitListener implements Listener {

    private final Map<UUID, Staff> onlineStaffs;

    public PlayerJoinAndQuitListener(Map<UUID, Staff> onlineStaffs) {
        this.onlineStaffs = onlineStaffs;
    }

    @EventHandler
    public void onPlayerDisconnect(final PlayerDisconnectEvent event) {
        onlineStaffs.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(final PostLoginEvent event) {
        if (event.getPlayer().hasPermission("lcstaff")) {
            onlineStaffs.put(event.getPlayer().getUniqueId(), new Staff(event.getPlayer()));
        }
    }
}
