// 
// Decompiled by Procyon v0.6.0
// 

package org.apache.http.cookie;

import org.apache.http.ProtocolException;

public class MalformedCookieException extends ProtocolException
{
    private static final long serialVersionUID = -6695462944287282185L;
    
    public MalformedCookieException() {
    }
    
    public MalformedCookieException(final String message) {
        super(message);
    }
    
    public MalformedCookieException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
