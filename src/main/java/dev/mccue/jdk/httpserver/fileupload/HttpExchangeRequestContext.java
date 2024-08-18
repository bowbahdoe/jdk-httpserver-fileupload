package dev.mccue.jdk.httpserver.fileupload;

import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.fileupload2.core.AbstractRequestContext;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;

public final class HttpExchangeRequestContext extends AbstractRequestContext<HttpExchange> {

    public HttpExchangeRequestContext(final HttpExchange request) {
        super(
                header -> request.getRequestHeaders().getFirst(header),
                () -> -1,
                request
        );
    }

    @Override
    public @Nullable String getCharacterEncoding() {
        return Parsing.findContentTypeCharset(getRequest()).orElse(null);
    }

    @Override
    public @Nullable String getContentType() {
        return Parsing.findContentType(getRequest()).orElse(null);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return getRequest().getRequestBody();
    }
}
