// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot.handler;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public interface PacketHandler
{
    void handleReceivedPacket(final PlayerConnection p0, final Packet p1);
    
    void handleSentPacket(final PlayerConnection p0, final Packet p1);
}
