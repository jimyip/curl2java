package wiki.sogou.curl;

import java.util.Collections;
import java.util.Map;

/**
 * @author JimYip
 */
public class Request {

    private Map<String, String> headers;
    private Map<String, String> params;
    private String body;


    protected Request() {
    }

    public Builder newBuilder() {
        return new RequestBuilderImpl();
    }


    Request(Map<String, String> headers, Map<String, String> params, String body) {
        this.headers = headers;
        this.params = params;
        this.body = body;
    }

    public Map<String, String> headers() {
        return Collections.unmodifiableMap(headers);
    }

    public Map<String, String> params() {
        return Collections.unmodifiableMap(params);
    }

    public String body() {
        return body;
    }


    public interface Builder {

        Builder header(String key, String value);

        Builder headers(Map<String, String> headers);

        Builder param(String key, String value);

        Builder params(String params);

        Builder body(String body);

        Request build();

    }

}
