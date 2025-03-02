// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot.chunk;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_8_R3.PacketPlayOutMultiBlockChange;

public class CraftFakeMultiBlockChange implements FakeMultiBlockChange
{
    private final PacketPlayOutMultiBlockChange packet;
    
    public CraftFakeMultiBlockChange(final PacketPlayOutMultiBlockChange packet) {
        this.packet = packet;
    }
    
    @Override
    public void sendTo(final Player player) {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)this.packet);
    }
}
