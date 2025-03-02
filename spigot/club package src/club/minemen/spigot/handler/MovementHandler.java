// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot.handler;

import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface MovementHandler
{
    void handleUpdateLocation(final Player p0, final Location p1, final Location p2, final PacketPlayInFlying p3);
    
    void handleUpdateRotation(final Player p0, final Location p1, final Location p2, final PacketPlayInFlying p3);
}
