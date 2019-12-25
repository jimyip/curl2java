package wiki.sogou;

import java.util.List;

/**
 * @author JimYip
 */
public interface Parser {
    List<String> parseColumns(String line, char separator);

    List<String> parseColumns(String line);
}
