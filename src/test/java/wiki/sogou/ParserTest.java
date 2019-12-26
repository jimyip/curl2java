package wiki.sogou;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ParserTest {

    @Test
    public void testA() {
        String command = "curl              -A " +
                "'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36' " +
                "https://google.com";
        String command2 = "curl      -A '' https://google.com";
        List<String> list = ArgsUtil.parseColumns(command);
        List<String> list2 = ArgsUtil.parseColumns(command2);
        Assert.assertEquals("curl", list.get(0));
        Assert.assertEquals("-A", list.get(1));
        Assert.assertEquals("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36", list.get(2));
        Assert.assertEquals("https://google.com", list.get(3));

        Assert.assertEquals("curl", list2.get(0));
        Assert.assertEquals("-A", list2.get(1));
        Assert.assertEquals("", list2.get(2));
        Assert.assertEquals("https://google.com", list2.get(3));
    }
}
