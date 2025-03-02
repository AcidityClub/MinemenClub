// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot.command;

import club.minemen.spigot.ClubSpigot;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import java.util.List;
import java.util.Arrays;
import org.bukkit.command.Command;

public class KnockbackCommand extends Command
{
    public KnockbackCommand() {
        super("knockback");
        this.setAliases((List)Arrays.asList("kb"));
        this.setUsage(ChatColor.RED + "Usage: /knockback <horizontal multiplier> <vertical multiplier>");
    }
    
    public boolean execute(final CommandSender sender, final String alias, final String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("Unknown command. Type \"help\" for help.");
            return true;
        }
        if (args.length > 1 && NumberUtils.isNumber(args[0]) && NumberUtils.isNumber(args[1])) {
            final double knockbackHorizontal = Double.parseDouble(args[0]);
            final double knockbackVertical = Double.parseDouble(args[1]);
            ClubSpigot.INSTANCE.getConfig().setKnockbackHorizontal(knockbackHorizontal);
            ClubSpigot.INSTANCE.getConfig().setKnockbackVertical(knockbackVertical);
            sender.sendMessage(ChatColor.GREEN + "Set knockback values to " + ChatColor.GREEN + knockbackHorizontal + ChatColor.GREEN + " and " + ChatColor.GREEN + knockbackVertical + ChatColor.GREEN + ".");
        }
        else {
            sender.sendMessage(this.usageMessage);
            sender.sendMessage(ChatColor.GREEN + "Current knockback values: " + ClubSpigot.INSTANCE.getConfig().getKnockbackHorizontal() + " and " + ClubSpigot.INSTANCE.getConfig().getKnockbackVertical());
        }
        return true;
    }
}
