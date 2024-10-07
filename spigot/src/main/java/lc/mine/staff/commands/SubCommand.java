package lc.mine.staff.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lc.mine.staff.storage.StaffData;

public interface SubCommand {
    void handle(StaffData data, Player player, String[] args);
    default List<String> tab(CommandSender sender, String[] args)  {
        return List.of();
    }

    default List<String> playersNames() {
        final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        final List<String> list = new ArrayList<>(players.size());
        for (final Player player : players) {
            list.add(player.getName());
        }
        return list;
    }
}
