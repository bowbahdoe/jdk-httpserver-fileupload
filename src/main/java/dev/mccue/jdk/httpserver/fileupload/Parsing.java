package dev.mccue.jdk.httpserver.fileupload;

import com.sun.net.httpserver.Request;

import java.util.Optional;
import java.util.regex.Pattern;

final class Parsing {
    private Parsing() {}

    static final Pattern TOKEN
            = Pattern.compile("[!#$%&'*\\-+.0-9A-Z\\^_`a-z\\|~]+");

    static final Pattern QUOTED
            = Pattern.compile("\"((?:\\\"|[^\"])*)\"");

    static final Pattern VALUE
            = Pattern.compile("(" + TOKEN + ")|" + QUOTED);

    static final Pattern CHARSET
            = Pattern.compile(";(?:.*\\s)?(?i:charset)=(?:" + VALUE + ")\\s*(?:;|$)");

    static Optional<String> findContentTypeCharset(String s) {
        var m = CHARSET.matcher(s);
        if (m.find()) {
            var groupOne = m.group(1);
            if (groupOne != null) {
                return Optional.of(groupOne);
            }

            var groupTwo = m.group(2);
            if (groupTwo != null) {
                return Optional.of(groupTwo);
            }
        }

        return Optional.empty();
    }

    static Optional<String> findContentTypeCharset(Request request) {
        var contentType = request.getRequestHeaders().getFirst("Content-Type");
        if (contentType != null) {
            return findContentTypeCharset(contentType);
        }
        return Optional.empty();
    }

    static Optional<String> findContentType(Request request) {
        var contentType = request.getRequestHeaders().getFirst("Content-Type");
        return Optional.ofNullable(contentType);
    }
}
