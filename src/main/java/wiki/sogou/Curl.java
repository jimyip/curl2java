package wiki.sogou;

import java.net.http.HttpRequest;
import java.util.List;

/**
 * @author JimYip
 */
public class Curl {

    private HttpRequest.Builder builder;
    private String line;
    private List<String> args;

    public Curl() {
        builder = HttpRequest.newBuilder();
    }

    public Curl(String line, Parser parser) {
        this.line = line;
        this.args = parser.parseColumns(line);
    }


    /**
     * -A, --user-agent
     */
    public void userAgent(String value) {
        builder.header("User-Agent", value);
    }


    /**
     * -b, --cookie
     */
    public void cookie(String cookie) {
        String[] values = cookie.split(":");
        builder.header(values[0], values[1]);
    }


    public void cookieJar() {

    }


}
