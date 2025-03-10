// 
// Decompiled by Procyon v0.6.0
// 

package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.OutputStream;

class LoggingOutputStream extends OutputStream
{
    private final OutputStream out;
    private final Wire wire;
    
    public LoggingOutputStream(final OutputStream out, final Wire wire) {
        this.out = out;
        this.wire = wire;
    }
    
    @Override
    public void write(final int b) throws IOException {
        try {
            this.wire.output(b);
        }
        catch (final IOException ex) {
            this.wire.output("[write] I/O error: " + ex.getMessage());
            throw ex;
        }
    }
    
    @Override
    public void write(final byte[] b) throws IOException {
        try {
            this.wire.output(b);
            this.out.write(b);
        }
        catch (final IOException ex) {
            this.wire.output("[write] I/O error: " + ex.getMessage());
            throw ex;
        }
    }
    
    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        try {
            this.wire.output(b, off, len);
            this.out.write(b, off, len);
        }
        catch (final IOException ex) {
            this.wire.output("[write] I/O error: " + ex.getMessage());
            throw ex;
        }
    }
    
    @Override
    public void flush() throws IOException {
        try {
            this.out.flush();
        }
        catch (final IOException ex) {
            this.wire.output("[flush] I/O error: " + ex.getMessage());
            throw ex;
        }
    }
    
    @Override
    public void close() throws IOException {
        try {
            this.out.close();
        }
        catch (final IOException ex) {
            this.wire.output("[close] I/O error: " + ex.getMessage());
            throw ex;
        }
    }
}
