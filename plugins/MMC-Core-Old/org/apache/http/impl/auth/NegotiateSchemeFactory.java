// 
// Decompiled by Procyon v0.6.0
// 

package org.apache.http.impl.auth;

import org.apache.http.auth.AuthScheme;
import org.apache.http.params.HttpParams;
import org.apache.http.auth.AuthSchemeFactory;

@Deprecated
public class NegotiateSchemeFactory implements AuthSchemeFactory
{
    private final SpnegoTokenGenerator spengoGenerator;
    private final boolean stripPort;
    
    public NegotiateSchemeFactory(final SpnegoTokenGenerator spengoGenerator, final boolean stripPort) {
        this.spengoGenerator = spengoGenerator;
        this.stripPort = stripPort;
    }
    
    public NegotiateSchemeFactory(final SpnegoTokenGenerator spengoGenerator) {
        this(spengoGenerator, false);
    }
    
    public NegotiateSchemeFactory() {
        this(null, false);
    }
    
    @Override
    public AuthScheme newInstance(final HttpParams params) {
        return new NegotiateScheme(this.spengoGenerator, this.stripPort);
    }
    
    public boolean isStripPort() {
        return this.stripPort;
    }
    
    public SpnegoTokenGenerator getSpengoGenerator() {
        return this.spengoGenerator;
    }
}
