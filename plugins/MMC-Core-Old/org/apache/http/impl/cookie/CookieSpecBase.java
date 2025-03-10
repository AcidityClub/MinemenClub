// 
// Decompiled by Procyon v0.6.0
// 

package org.apache.http.impl.cookie;

import java.util.Iterator;
import org.apache.http.util.Args;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.NameValuePair;
import org.apache.http.cookie.SetCookie;
import java.util.Locale;
import java.util.ArrayList;
import org.apache.http.cookie.Cookie;
import java.util.List;
import org.apache.http.HeaderElement;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.CookieAttributeHandler;
import java.util.HashMap;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.annotation.Contract;

@Contract(threading = ThreadingBehavior.SAFE)
public abstract class CookieSpecBase extends AbstractCookieSpec
{
    public CookieSpecBase() {
    }
    
    protected CookieSpecBase(final HashMap<String, CookieAttributeHandler> map) {
        super(map);
    }
    
    protected CookieSpecBase(final CommonCookieAttributeHandler... handlers) {
        super(handlers);
    }
    
    protected static String getDefaultPath(final CookieOrigin origin) {
        String defaultPath = origin.getPath();
        int lastSlashIndex = defaultPath.lastIndexOf(47);
        if (lastSlashIndex >= 0) {
            if (lastSlashIndex == 0) {
                lastSlashIndex = 1;
            }
            defaultPath = defaultPath.substring(0, lastSlashIndex);
        }
        return defaultPath;
    }
    
    protected static String getDefaultDomain(final CookieOrigin origin) {
        return origin.getHost();
    }
    
    protected List<Cookie> parse(final HeaderElement[] elems, final CookieOrigin origin) throws MalformedCookieException {
        final List<Cookie> cookies = new ArrayList<Cookie>(elems.length);
        for (final HeaderElement headerelement : elems) {
            final String name = headerelement.getName();
            final String value = headerelement.getValue();
            if (name != null) {
                if (!name.isEmpty()) {
                    final BasicClientCookie cookie = new BasicClientCookie(name, value);
                    cookie.setPath(getDefaultPath(origin));
                    cookie.setDomain(getDefaultDomain(origin));
                    final NameValuePair[] attribs = headerelement.getParameters();
                    for (int j = attribs.length - 1; j >= 0; --j) {
                        final NameValuePair attrib = attribs[j];
                        final String s = attrib.getName().toLowerCase(Locale.ROOT);
                        cookie.setAttribute(s, attrib.getValue());
                        final CookieAttributeHandler handler = this.findAttribHandler(s);
                        if (handler != null) {
                            handler.parse(cookie, attrib.getValue());
                        }
                    }
                    cookies.add(cookie);
                }
            }
        }
        return cookies;
    }
    
    @Override
    public void validate(final Cookie cookie, final CookieOrigin origin) throws MalformedCookieException {
        Args.notNull(cookie, "Cookie");
        Args.notNull(origin, "Cookie origin");
        for (final CookieAttributeHandler handler : this.getAttribHandlers()) {
            handler.validate(cookie, origin);
        }
    }
    
    @Override
    public boolean match(final Cookie cookie, final CookieOrigin origin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(origin, "Cookie origin");
        for (final CookieAttributeHandler handler : this.getAttribHandlers()) {
            if (!handler.match(cookie, origin)) {
                return false;
            }
        }
        return true;
    }
}
