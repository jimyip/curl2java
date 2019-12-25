package wiki.sogou;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JimYip
 */
public class DefaultParser implements Parser {
    public static final char WHITE_SPACE = 32;

    @Override
    public List<String> parseColumns(String line, char separator) {
        List<String> columns = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        boolean whiteSpaceFlag = false;
        MainLoop:
        for (int i = 0, max = line.length(); i < max; i++) {
            char ch = line.charAt(i);
            if (ch == '"' || ch == '\'') {
                whiteSpaceFlag = false;
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
                if (whiteSpaceFlag && Character.isWhitespace(ch)) {
                    continue;
                }
                columns.add(value.toString());
                value.setLength(0);
                whiteSpaceFlag = true;
            } else {
                whiteSpaceFlag = false;
                value.append(ch);
            }
        }
        // last field
        columns.add(value.toString());
        return columns;
    }

    @Override
    public List<String> parseColumns(String line) {
        return parseColumns(line, WHITE_SPACE);
    }
}
