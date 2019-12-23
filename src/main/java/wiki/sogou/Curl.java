package wiki.sogou;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author JimYip
 */
public class Curl {


    private String command;

    private String[] splitCommand = null;
    private Map<String, String> headers = null;
    private Map<String, String> parameters = null;
    private String requestBody;

    private Curl() {
    }

    private Curl(String command) {
        this.command = command;
        this.splitCommand = command.split("\\s+");
        this.headers = getHeaders();
        this.parameters = getParameters();
        this.requestBody = getRequestBody();
    }

    private Map<String, String> getHeaders() {
        headers = new HashMap<>();
        return headers;
    }

    /**
     * TODO 获取body内容
     */
    private String getRequestBody() {
        return null;
    }

    /**
     * TODO 获取参数
     */
    private Map<String, String> getParameters() {
        parameters = new LinkedHashMap<>();
        return parameters;
    }

    public static Curl parse(String command) {
        Objects.requireNonNull(command, "command cannot be null");
        if (!command.startsWith(Constants.CURL)) {
            throw new IllegalStateException("Command must start with \"curl\"");
        }
        return new Curl(command);
    }

    public String getHttpResponse() {
        return null;
    }

}
