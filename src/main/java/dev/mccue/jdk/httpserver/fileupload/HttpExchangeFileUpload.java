package dev.mccue.jdk.httpserver.fileupload;


import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.fileupload2.core.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public sealed class HttpExchangeFileUpload<I extends FileItem<I>, F extends FileItemFactory<I>>
        extends AbstractFileUpload<HttpExchange, I, F>
        permits HttpExchangeDiskFileUpload {

    /**
     * Constant for HTTP POST method.
     */
    private static final String POST_METHOD = "POST";

    public static boolean isMultipartContent(final HttpExchange request) {
        return POST_METHOD.equalsIgnoreCase(request.getRequestMethod()) && AbstractFileUpload.isMultipartContent(new HttpExchangeRequestContext(request));
    }

    public HttpExchangeFileUpload() {
    }

    public HttpExchangeFileUpload(final F fileItemFactory) {
        setFileItemFactory(fileItemFactory);
    }

    @Override
    public FileItemInputIterator getItemIterator(final HttpExchange request) throws FileUploadException, IOException {
        return super.getItemIterator(new HttpExchangeRequestContext(request));
    }

    @Override
    public Map<String, List<I>> parseParameterMap(final HttpExchange request) throws FileUploadException {
        return parseParameterMap(new HttpExchangeRequestContext(request));
    }

    @Override
    public List<I> parseRequest(final HttpExchange request) throws FileUploadException {
        return parseRequest(new HttpExchangeRequestContext(request));
    }
}
