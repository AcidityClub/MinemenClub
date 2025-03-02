// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot;

import java.util.Iterator;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import java.util.Map;
import club.minemen.spigot.command.KnockbackCommand;
import org.bukkit.command.Command;
import java.util.HashMap;
import java.util.HashSet;
import org.bukkit.ChatColor;
import club.minemen.spigot.handler.MovementHandler;
import club.minemen.spigot.handler.PacketHandler;
import java.util.Set;

public enum ClubSpigot
{
    INSTANCE("INSTANCE", 0);
    
    public static final String PRIMARY_COLOR;
    public static final String VALUE_COLOR;
    private ClubSpigotConfig config;
    private Set<PacketHandler> packetHandlers;
    private Set<MovementHandler> movementHandlers;
    
    static {
        PRIMARY_COLOR = ChatColor.WHITE.toString();
        VALUE_COLOR = ChatColor.WHITE.toString();
    }
    
    private ClubSpigot(final String s, final int n) {
        this.packetHandlers = new HashSet<PacketHandler>();
        this.movementHandlers = new HashSet<MovementHandler>();
    }
    
    public ClubSpigotConfig getConfig() {
        return this.config;
    }
    
    public Set<PacketHandler> getPacketHandlers() {
        return this.packetHandlers;
    }
    
    public Set<MovementHandler> getMovementHandlers() {
        return this.movementHandlers;
    }
    
    public void setConfig(final ClubSpigotConfig config) {
        this.config = config;
    }
    
    public void addPacketHandler(final PacketHandler handler) {
        this.packetHandlers.add(handler);
    }
    
    public void addMovementHandler(final MovementHandler handler) {
        this.movementHandlers.add(handler);
    }
    
    public void registerCommands() {
        final Map<String, Command> commands = new HashMap<String, Command>();
        commands.put("knockback", new KnockbackCommand());
        for (final Map.Entry<String, Command> entry : commands.entrySet()) {
            MinecraftServer.getServer().server.getCommandMap().register((String)entry.getKey(), "Spigot", (Command)entry.getValue());
        }
    }
}
