package wiki.sogou;

import java.util.*;

/**
 * @author JimYip
 */
public class Curl {
    public static final char WHITE_SPACE = 32;
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

    List<String> parseColumns(String line, final char separator) {
        List<String> columns = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        MainLoop:
        for (int i = 0, max = line.length(); i < max; i++) {
            char ch = line.charAt(i);
            if (ch == '"' || ch == '\'') {
                // quoted start
                if (value.toString().trim().length() != 0) {
                    // now not support quoted in middle field, etc: abc"xyz" will cause Exception
                    throw new RuntimeException("Unexpected char '" + ch + "', position=" + i);
                }
                // ignore the before-ing white spaces
                value.setLength(0);
                // find end quote mark in this line and after lines
                for (i++; ; ) {
                    if (i < max) {
                        ch = line.charAt(i);
                        if (ch == '"' || ch == '\'') {
                            if ((i + 1 < max) && (line.charAt(i + 1) == '"' || line.charAt(i + 1) == '\'')) {
                                // double quote
                                value.append(ch);
                                i += 2;
                                continue;
                            }
                            // the only valid exit point that match quote
                            // find separator
                            while (++i < max) {
                                ch = line.charAt(i);
                                if (ch == separator) {
                                    columns.add(value.toString());
                                    value.setLength(0);
                                    continue MainLoop;
                                } else if (Character.isWhitespace(ch)) {
                                    continue;
                                }
                                throw new RuntimeException("Unexpected char '" + ch + "', position=" + i);
                            }
                            // reaches end-of-line
                            break MainLoop;
                        } else {
                            value.append(ch);
                        }
                        i++;
                    } else {
                        // continue find end quote mark in next line
                        // reset cursor
                        i = 0;
                        max = line.length();
                    }
                }
            } else if (ch == separator) {
                columns.add(value.toString());
                value.setLength(0);
            } else {
                value.append(ch);
            }
        }
        // last field
        columns.add(value.toString());
        return columns;
    }


    public String getHttpResponse() {
        return null;
    }

}
