package wiki.sogou.curl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author JimYip
 */
public class RequestBuilderImpl implements Request.Builder {

    private Map<String, String> headers = new LinkedHashMap<>();
    private Map<String, String> params = new LinkedHashMap<>();
    private String body;

    @Override
    public Request.Builder header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    @Override
    public Request.Builder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public Request.Builder param(String key, String value) {
        Objects.requireNonNull(key, "key must be non null");
        Objects.requireNonNull(value, "value must be non null");
        this.params.put(key, value);
        return null;
    }

    @Override
    public Request.Builder params(String params) {
        Objects.requireNonNull(params, "params must be non null");
        String[] keyValues = params.split("&");
        for (String keyValue : keyValues) {
            String key = keyValue.split("=")[0];
            String value = "";
            if (keyValue.split("=").length > 1) {
                value = keyValue.split("=")[1];
            }
            this.params.put(key, value);
        }
        return this;
    }

    @Override
    public Request.Builder body(String body) {
        this.body = body;
        return this;
    }

    @Override
    public Request build() {
        return new Request(headers, params, body);
    }
}
