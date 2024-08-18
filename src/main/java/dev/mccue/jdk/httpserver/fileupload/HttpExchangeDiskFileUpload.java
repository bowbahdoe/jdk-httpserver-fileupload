package dev.mccue.jdk.httpserver.fileupload;

import  org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;

public final class HttpExchangeDiskFileUpload
        extends HttpExchangeFileUpload<DiskFileItem, DiskFileItemFactory> {
    public HttpExchangeDiskFileUpload() {
        super(DiskFileItemFactory.builder().get());
    }

    public HttpExchangeDiskFileUpload(DiskFileItemFactory fileItemFactory) {
        super(fileItemFactory);
    }
}
