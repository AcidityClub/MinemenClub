// 
// Decompiled by Procyon v0.6.0
// 

package org.apache.http.impl.pool;

import org.apache.http.pool.PoolEntry;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.params.HttpParams;
import org.apache.http.pool.ConnFactory;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.annotation.Contract;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.pool.AbstractConnPool;

@Contract(threading = ThreadingBehavior.SAFE_CONDITIONAL)
public class BasicConnPool extends AbstractConnPool<HttpHost, HttpClientConnection, BasicPoolEntry>
{
    private static final AtomicLong COUNTER;
    
    public BasicConnPool(final ConnFactory<HttpHost, HttpClientConnection> connFactory) {
        super(connFactory, 2, 20);
    }
    
    @Deprecated
    public BasicConnPool(final HttpParams params) {
        super(new BasicConnFactory(params), 2, 20);
    }
    
    public BasicConnPool(final SocketConfig sconfig, final ConnectionConfig cconfig) {
        super(new BasicConnFactory(sconfig, cconfig), 2, 20);
    }
    
    public BasicConnPool() {
        super(new BasicConnFactory(SocketConfig.DEFAULT, ConnectionConfig.DEFAULT), 2, 20);
    }
    
    @Override
    protected BasicPoolEntry createEntry(final HttpHost host, final HttpClientConnection conn) {
        return new BasicPoolEntry(Long.toString(BasicConnPool.COUNTER.getAndIncrement()), host, conn);
    }
    
    @Override
    protected boolean validate(final BasicPoolEntry entry) {
        return !((PoolEntry<T, HttpClientConnection>)entry).getConnection().isStale();
    }
    
    static {
        COUNTER = new AtomicLong();
    }
}
