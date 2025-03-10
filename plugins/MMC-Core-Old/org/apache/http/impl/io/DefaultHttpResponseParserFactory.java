// 
// Decompiled by Procyon v0.6.0
// 

package org.apache.http.impl.io;

import org.apache.http.io.HttpMessageParser;
import org.apache.http.config.MessageConstraints;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.message.BasicLineParser;
import org.apache.http.HttpResponseFactory;
import org.apache.http.message.LineParser;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.annotation.Contract;
import org.apache.http.HttpResponse;
import org.apache.http.io.HttpMessageParserFactory;

@Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
public class DefaultHttpResponseParserFactory implements HttpMessageParserFactory<HttpResponse>
{
    public static final DefaultHttpResponseParserFactory INSTANCE;
    private final LineParser lineParser;
    private final HttpResponseFactory responseFactory;
    
    public DefaultHttpResponseParserFactory(final LineParser lineParser, final HttpResponseFactory responseFactory) {
        this.lineParser = ((lineParser != null) ? lineParser : BasicLineParser.INSTANCE);
        this.responseFactory = ((responseFactory != null) ? responseFactory : DefaultHttpResponseFactory.INSTANCE);
    }
    
    public DefaultHttpResponseParserFactory() {
        this(null, null);
    }
    
    @Override
    public HttpMessageParser<HttpResponse> create(final SessionInputBuffer buffer, final MessageConstraints constraints) {
        return new DefaultHttpResponseParser(buffer, this.lineParser, this.responseFactory, constraints);
    }
    
    static {
        INSTANCE = new DefaultHttpResponseParserFactory();
    }
}
