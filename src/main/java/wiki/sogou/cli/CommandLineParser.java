package wiki.sogou.cli;

import wiki.sogou.cli.exception.ParseException;

/**
 * @author JimYip
 */
public interface CommandLineParser {
    CommandLine parse(Options options, String[] arguments) throws ParseException;

    CommandLine parse(Options options, String[] arguments, boolean stopAtNonOption) throws ParseException;

}
