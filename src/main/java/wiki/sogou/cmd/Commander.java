package wiki.sogou.cmd;

import java.util.LinkedList;
import java.util.List;

/**
 * @author JimYip
 */
public class Commander {

    private final List<String> args = new LinkedList<>();


    protected Commander(Builder builder) {


    }


    public Builder builder() {
        return new Builder();
    }


    public static class Builder {

    }
}
