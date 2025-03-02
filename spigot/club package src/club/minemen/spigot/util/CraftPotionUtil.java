// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot.util;

import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;
import net.minecraft.server.v1_8_R3.MobEffect;

public class CraftPotionUtil
{
    private CraftPotionUtil() {
    }
    
    public static PotionEffect toBukkit(final MobEffect effect) {
        return new PotionEffect(PotionEffectType.getById(effect.getEffectId()), effect.getDuration(), effect.getAmplifier(), effect.isAmbient());
    }
    
    public static MobEffect toNMS(final PotionEffect effect) {
        return new MobEffect(effect.getType().getId(), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.hasParticles());
    }
    
    public static MobEffect cloneWithDuration(final MobEffect effect, final int duration) {
        return new MobEffect(effect.getEffectId(), duration, effect.getAmplifier(), effect.isAmbient(), effect.isShowParticles());
    }
    
    public static void extendDuration(final MobEffect effect, final int duration) {
        effect.a(cloneWithDuration(effect, duration));
    }
}
