// 
// Decompiled by Procyon v0.6.0
// 

package club.minemen.spigot.util;

import java.util.Iterator;
import java.util.Set;
import java.util.List;

public class OptimizedRemoveUtil
{
    private OptimizedRemoveUtil() {
    }
    
    public static <T extends Marker> int removeAll(final List<T> list, final Set<T> toRemove, int position) {
        for (final Marker marker : toRemove) {
            marker.markRemoval();
        }
        final int size = list.size();
        int insertAt = 0;
        for (int i = 0; i < size; ++i) {
            final T element = list.get(i);
            if (i == position) {
                position = insertAt;
            }
            if (element != null && !element.isNeedRemoval()) {
                list.set(insertAt++, element);
            }
        }
        list.subList(insertAt, size).clear();
        return position;
    }
    
    public interface Marker
    {
        boolean isNeedRemoval();
        
        void markRemoval();
    }
}
