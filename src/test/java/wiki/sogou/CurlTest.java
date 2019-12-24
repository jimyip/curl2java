package wiki.sogou;

import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurlTest {

    /**
     * user agent
     */
    @Test
    public void testA() throws SQLException {
        String command = "curl              -A " +
                "'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36' " +
                "https://google.com";
        String command2 = "curl      -A '' https://google.com";
        List<String> list = parseColumns(command, Curl.WHITE_SPACE);
        List<String> list2 = parseColumns(command2, Curl.WHITE_SPACE);
        System.out.println(list);
        System.out.println(list2);
        System.out.println(list.size());
        System.out.println(list2.size());
    }


    /**
     * cookie
     */
    @Test
    public void testB() {
        String command = "curl -b 'foo=bar' https://google.com";
        String command2 = "curl -b 'foo1=bar' -b 'foo2=baz' https://google.com";
        String command3 = "curl -b cookies.txt https://www.google.com";
    }


    /**
     * put cookie into txt file
     */
    @Test
    public void testC() {
        String command = "curl -c cookies.txt https://www.google.com";
    }

    /**
     * parameters
     */
    @Test
    public void testD() {
        String command = "curl -d 'login=emma＆password=123' -X POST https://google.com/login";
        String command2 = "curl -d 'login=emma' -d 'password=123' -X POST  https://google.com/login";
        String command3 = "curl -d '@data.txt' https://google.com/login";
    }

    /**
     * encode
     */
    @Test
    public void testDataUrlEncode() {
        String command = "curl --data-urlencode 'comment=hello world' https://google.com/login";
    }

    @Test
    public void testE() {
        String command = "curl -e 'https://google.com?q=example' https://www.example.com";
        String command2 = "curl -H 'Referer: https://google.com?q=example' https://www.example.com";
    }


    @Test
    public void testF() {
        String command = "curl -F 'file=@photo.png' https://google.com/profile";
        String command2 = "curl -F 'file=@photo.png;type=image/png' https://google.com/profile";
        String command3 = "curl -F 'file=@photo.png;filename=me.png' https://google.com/profile";
    }

    @Test
    public void testG() {
        String command = "curl -G -d 'q=kitties' -d 'count=20' https://google.com/search";
        String command2 = "curl -G --data-urlencode 'comment=hello world' https://www.example.com";

    }

    @Test
    public void testH() {

        String command2 = "curl -H 'Accept-Language: en-US' -H 'Secret-Message: xyzzy' https://google.com";
        String command3 = "curl -d '{\"login\": \"emma\", \"pass\": \"123\"}' -H 'Content-Type: application/json' https://google.com/login";
    }


    @Test
    public void testLowerI() {
        String command = "curl -i https://www.example.com";
    }


    @Test
    public void testUpperI() {
        String command = "curl -I https://www.example.com";
        String command2 = "curl --head https://www.example.com";
    }

    @Test
    public void testK() {
        String command = "curl -k https://www.example.com";
    }


    @Test
    public void testL() {
        String command = "curl -L -d 'tweet=hi' https://api.twitter.com/tweet";
    }


    @Test
    public void testLimitRate() {
        String command = "curl --limit-rate 200k https://google.com";
    }

    @Test
    public void testLowerO() {
        String command = "curl -o example.html https://www.example.com";
    }


    @Test
    public void testUpperO() {
        String command = "curl -O https://www.example.com/foo/bar.html";
    }

    @Test
    public void testLowerS() {
        String command = "curl -s https://www.example.com";
        String command2 = "curl -s -o /dev/null https://google.com";
    }

    @Test
    public void testUpperS() {
        String command = "curl -S -o /dev/null https://google.com";
    }

    @Test
    public void testU() {
        String command = "curl -u 'bob:12345' https://google.com/login";
        String command2 = "curl https://bob:12345@google.com/login";
        String command3 = "curl -u 'bob' https://google.com/login";
    }

    @Test
    public void testV() {
        String command = "curl -v https://www.example.com";
        String command2 = "curl --trace - https://www.example.com";
    }

    @Test
    public void testLowerX() {
        String command = "curl -x socks5://james:cats@myproxy.com:8080 https://www.example.com";
        String command2 = "curl -x james:cats@myproxy.com:8080 https://www.example.com";
    }

    @Test
    public void testUpperX() {
        String command = "curl -X POST https://www.example.com";
    }


    List<String> parseColumns(String line, final char separator) throws SQLException {
        List<String> columns = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        boolean spaceFlag = false;
        MainLoop:
        for (int i = 0, max = line.length(); i < max; i++) {
            char ch = line.charAt(i);
            if (ch == '"' || ch == '\'') {
                spaceFlag = false;
                // quoted start
                if (value.toString().trim().length() != 0) {
                    // now not support quoted in middle field, etc: abc"xyz" will cause Exception
                    throw new SQLException("Unexpected char '" + ch + "', position=" + i);
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
                                throw new SQLException("Unexpected char '" + ch + "', position=" + i);
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
                if (spaceFlag && Character.isWhitespace(ch)) {
                    continue;
                }
                columns.add(value.toString());
                value.setLength(0);
                spaceFlag = true;
            } else {
                spaceFlag = false;
                value.append(ch);
            }
        }
        // last field
        columns.add(value.toString());
        return columns;
    }

}
