import org.jspecify.annotations.NullMarked;

@NullMarked
module dev.mccue.jdk.httpserver.fileupload {
    requires transitive org.jspecify;
    requires transitive jdk.httpserver;
    requires transitive dev.mccue.jdk.httpserver;
    requires transitive org.apache.commons.fileupload2.core;

    exports dev.mccue.jdk.httpserver.fileupload;
}