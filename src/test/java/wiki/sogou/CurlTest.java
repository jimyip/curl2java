package wiki.sogou;

import org.junit.Test;
import wiki.sogou.cli.CommandLine;
import wiki.sogou.cli.DefaultParser;
import wiki.sogou.cli.exception.ParseException;

import java.util.List;

public class CurlTest {

    /**
     * user agent
     */
    @Test
    public void testA() {
        String command = "curl              -A " +
                "'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36' " +
                "https://google.com";
        String command2 = "curl      -A '' https://google.com";
        String command3 = "curl -H 'User-Agent: php/1.0' https://google.com";
        execute(command);
        execute(command2);
        execute(command3);

    }

    /**
     * cookie
     */
    @Test
    public void testB() {
        String command = "curl -b 'foo=bar' https://google.com";
        String command2 = "curl -b 'foo1=bar' -b 'foo2=baz' https://google.com";
        String command3 = "curl -b cookies.txt https://www.google.com";
        execute(command);
        execute(command2);
        execute(command3);
    }

    /**
     * put cookie into txt file
     */
    @Test
    public void testC() {
        String command = "curl -c cookies.txt https://www.google.com";
        execute(command);
    }

    /**
     * parameters
     */
    @Test
    public void testD() {
        String command = "curl -d 'login=emma＆password=123' -X POST https://google.com/login";
        String command2 = "curl -d 'login=emma' -d 'password=123' -X POST  https://google.com/login";
        String command3 = "curl -d '@data.txt' https://google.com/login";
        execute(command);
        execute(command2);
        execute(command3);
    }

    /**
     * encode
     */
    @Test
    public void testDataUrlEncode() {
        String command = "curl --data-urlencode 'comment=hello world' https://google.com/login";
        execute(command);
    }

    @Test
    public void testE() {
        String command = "curl -e 'https://google.com?q=example' https://www.example.com";
        String command2 = "curl -H 'Referer: https://google.com?q=example' https://www.example.com";
        execute(command);
        execute(command2);
    }


    @Test
    public void testF() {
        String command = "curl -F 'file=@photo.png' https://google.com/profile";
        String command2 = "curl -F 'file=@photo.png;type=image/png' https://google.com/profile";
        String command3 = "curl -F 'file=@photo.png;filename=me.png' https://google.com/profile";
        execute(command);
        execute(command2);
        execute(command3);
    }

    @Test
    public void testG() {
        String command = "curl -G -d 'q=kitties' -d 'count=20' https://google.com/search";
        String command2 = "curl -G --data-urlencode 'comment=hello world' https://www.example.com";
        execute(command);
        execute(command2);
    }

    @Test
    public void testH() {
        String command2 = "curl -H 'Accept-Language: en-US' -H 'Secret-Message: xyzzy' https://google.com";
        String command3 = "curl https://google.com/login -d  '{\"login\": \"emma\", \"pass\": \"123\"}\\'   -H 'Content-Type: application/json' -H 'Content-Type: application/json'";
//        String command3 = "curl -d '{\"login\": \"emma\", \"pass\": \"123\"}' -H 'Content-Type: application/json' -H 'Content-Type: application/json' https://google.com/login";
//        execute(command2);
        //TODO 这里的json有问题
        execute(command3);
    }


    @Test
    public void testLowerI() {
        String command = "curl -i https://www.example.com";
        execute(command);
    }


    @Test
    public void testUpperI() {
        String command = "curl -I https://www.example.com";
        String command2 = "curl --head https://www.example.com";
        execute(command);
        execute(command2);
    }

    @Test
    public void testK() {
        String command = "curl -k https://www.example.com";
        execute(command);
    }


    @Test
    public void testL() {
        String command = "curl -L -d 'tweet=hi' https://api.twitter.com/tweet";
        execute(command);
    }


    @Test
    public void testLimitRate() {
        String command = "curl --limit-rate 200k https://google.com";
        execute(command);
    }

    @Test
    public void testLowerO() {
        String command = "curl -o example.html https://www.example.com";
        execute(command);
    }


    @Test
    public void testUpperO() {
        String command = "curl -O https://www.example.com/foo/bar.html";
        execute(command);
    }

    @Test
    public void testLowerS() {
        String command = "curl -s https://www.example.com";
        String command2 = "curl -s -o /dev/null https://google.com";
        execute(command);
        execute(command2);
    }

    @Test
    public void testUpperS() {
        String command = "curl -S -o /dev/null https://google.com";
        execute(command);
    }

    @Test
    public void testU() {
        String command = "curl -u 'bob:12345' https://google.com/login";
        String command2 = "curl https://bob:12345@google.com/login";
        String command3 = "curl -u 'bob' https://google.com/login";
        execute(command);
        execute(command2);
        execute(command3);
    }

    @Test
    public void testV() {
        String command = "curl -v https://www.example.com";
        String command2 = "curl --trace - https://www.example.com";
        execute(command);
        execute(command2);
    }

    @Test
    public void testLowerX() {
        String command = "curl -x socks5://james:cats@myproxy.com:8080 https://www.example.com";
        String command2 = "curl -x james:cats@myproxy.com:8080 https://www.example.com";
        execute(command);
        execute(command2);
    }

    @Test
    public void testUpperX() {
        String command = "curl -X POST https://www.example.com";
        execute(command);
    }


    public String execute(String line) {
        List<String> args = ArgsUtil.parseColumns(line);
        args.remove(0);
        String[] array = args.toArray(new String[0]);
        DefaultParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(Arguments.OPTIONS, array);
            System.out.println(commandLine);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
