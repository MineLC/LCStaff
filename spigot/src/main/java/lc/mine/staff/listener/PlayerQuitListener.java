package lc.mine.staff.listener;

import java.util.Map;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import lc.mine.staff.storage.StaffData;

public final class PlayerQuitListener implements Listener {

    private final Map<UUID, StaffData> staffs;

    public PlayerQuitListener(Map<UUID, StaffData> staffs) {
        this.staffs = staffs;
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        staffs.remove(event.getPlayer().getUniqueId());
    }
}
