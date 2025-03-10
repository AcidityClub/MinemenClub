// 
// Decompiled by Procyon v0.6.0
// 

package org.apache.http.impl.io;

import java.io.IOException;
import org.apache.http.util.Args;
import org.apache.http.params.HttpParams;
import java.net.Socket;

@Deprecated
public class SocketOutputBuffer extends AbstractSessionOutputBuffer
{
    public SocketOutputBuffer(final Socket socket, final int buffersize, final HttpParams params) throws IOException {
        Args.notNull(socket, "Socket");
        int n = buffersize;
        if (n < 0) {
            n = socket.getSendBufferSize();
        }
        if (n < 1024) {
            n = 1024;
        }
        this.init(socket.getOutputStream(), n, params);
    }
}
