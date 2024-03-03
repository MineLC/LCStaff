package lc.mine.staff.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lc.mine.staff.commands.SubCommand;
import lc.mine.staff.messages.Messages;
import lc.mine.staff.storage.StaffData;

public final class FreezeCommand implements SubCommand {

    @Override
    public void handle(StaffData data, Player player, String[] args) {
        if (args.length != 2) {
            Messages.send(player, "freeze-format");
            return;
        }
        final Player target = Bukkit.getPlayer(player.getName());
        if (target == null) {
            send(player, Messages.get("target-no-exist").replace("%player%", args[1]));
            return;
        }
        if (data.getPlayerFreeze() != null) {
            final Player freezePlayer = Bukkit.getPlayer(data.getPlayerFreeze());
            freezePlayer.removePotionEffect(PotionEffectType.SLOW);
            freezePlayer.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            freezePlayer.removePotionEffect(PotionEffectType.BLINDNESS);
        }
        data.setPlayerFreeze(target.getUniqueId());

        target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100000, 1));
        target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100000, 100));
        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 100));

        Messages.send(target, "freeze-message");
        Messages.send(player, "freeze-complete");
    }

    @Override
    public String[] tab(CommandSender sender, String[] args) {
        return (args.length == 0) ? (String[])Bukkit.getOnlinePlayers().toArray() : none();
    }
}