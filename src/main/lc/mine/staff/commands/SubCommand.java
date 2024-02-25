package lc.mine.staff.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lc.lcspigot.commands.Command;
import lc.mine.staff.storage.StaffData;

public interface SubCommand extends Command {

    @Override
    default void handle(CommandSender sender, String[] args) {
        return;
    }

    void handle(StaffData data, Player player, String[] args);
}
