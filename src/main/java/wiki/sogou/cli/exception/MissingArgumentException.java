package wiki.sogou.cli.exception;

import wiki.sogou.cli.Option;

/**
 * @author JimYip
 */
public class MissingArgumentException extends ParseException {
    /**
     * This exception {@code serialVersionUID}.
     */
    private static final long serialVersionUID = -7098538588704965017L;

    /**
     * The option requiring additional arguments
     */
    private Option option;

    /**
     * Construct a new <code>MissingArgumentException</code>
     * with the specified detail message.
     *
     * @param message the detail message
     */
    public MissingArgumentException(final String message) {
        super(message);
    }

    /**
     * Construct a new <code>MissingArgumentException</code>
     * with the specified detail message.
     *
     * @param option the option requiring an argument
     * @since 1.2
     */
    public MissingArgumentException(final Option option) {
        this("Missing argument for option: " + option.getKey());
        this.option = option;
    }

    /**
     * Return the option requiring an argument that wasn't provided
     * on the command line.
     *
     * @return the related option
     * @since 1.2
     */
    public Option getOption() {
        return option;
    }
}
