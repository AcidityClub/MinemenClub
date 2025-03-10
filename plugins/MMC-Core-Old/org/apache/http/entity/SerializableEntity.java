// 
// Decompiled by Procyon v0.6.0
// 

package org.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.util.Args;
import java.io.Serializable;

public class SerializableEntity extends AbstractHttpEntity
{
    private byte[] objSer;
    private Serializable objRef;
    
    public SerializableEntity(final Serializable ser, final boolean bufferize) throws IOException {
        Args.notNull(ser, "Source object");
        if (bufferize) {
            this.createBytes(ser);
        }
        else {
            this.objRef = ser;
        }
    }
    
    public SerializableEntity(final Serializable ser) {
        Args.notNull(ser, "Source object");
        this.objRef = ser;
    }
    
    private void createBytes(final Serializable ser) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(ser);
        out.flush();
        this.objSer = baos.toByteArray();
    }
    
    @Override
    public InputStream getContent() throws IOException, IllegalStateException {
        if (this.objSer == null) {
            this.createBytes(this.objRef);
        }
        return new ByteArrayInputStream(this.objSer);
    }
    
    @Override
    public long getContentLength() {
        if (this.objSer == null) {
            return -1L;
        }
        return this.objSer.length;
    }
    
    @Override
    public boolean isRepeatable() {
        return true;
    }
    
    @Override
    public boolean isStreaming() {
        return this.objSer == null;
    }
    
    @Override
    public void writeTo(final OutputStream outstream) throws IOException {
        Args.notNull(outstream, "Output stream");
        if (this.objSer == null) {
            final ObjectOutputStream out = new ObjectOutputStream(outstream);
            out.writeObject(this.objRef);
            out.flush();
        }
        else {
            outstream.write(this.objSer);
            outstream.flush();
        }
    }
}
