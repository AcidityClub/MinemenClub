// 
// Decompiled by Procyon v0.6.0
// 

package org.apache.http.impl.client;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Comparator;
import org.apache.http.cookie.CookieIdentityComparator;
import org.apache.http.cookie.Cookie;
import java.util.TreeSet;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.annotation.Contract;
import java.io.Serializable;
import org.apache.http.client.CookieStore;

@Contract(threading = ThreadingBehavior.SAFE)
public class BasicCookieStore implements CookieStore, Serializable
{
    private static final long serialVersionUID = -7581093305228232025L;
    private final TreeSet<Cookie> cookies;
    
    public BasicCookieStore() {
        this.cookies = new TreeSet<Cookie>(new CookieIdentityComparator());
    }
    
    @Override
    public synchronized void addCookie(final Cookie cookie) {
        if (cookie != null) {
            this.cookies.remove(cookie);
            if (!cookie.isExpired(new Date())) {
                this.cookies.add(cookie);
            }
        }
    }
    
    public synchronized void addCookies(final Cookie[] cookies) {
        if (cookies != null) {
            for (final Cookie cooky : cookies) {
                this.addCookie(cooky);
            }
        }
    }
    
    @Override
    public synchronized List<Cookie> getCookies() {
        return new ArrayList<Cookie>(this.cookies);
    }
    
    @Override
    public synchronized boolean clearExpired(final Date date) {
        if (date == null) {
            return false;
        }
        boolean removed = false;
        final Iterator<Cookie> it = this.cookies.iterator();
        while (it.hasNext()) {
            if (it.next().isExpired(date)) {
                it.remove();
                removed = true;
            }
        }
        return removed;
    }
    
    @Override
    public synchronized void clear() {
        this.cookies.clear();
    }
    
    @Override
    public synchronized String toString() {
        return this.cookies.toString();
    }
}
