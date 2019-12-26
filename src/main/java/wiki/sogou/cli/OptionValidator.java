package wiki.sogou.cli;

/**
 * Validates an Option string.
 *
 * @since 1.1
 */
final class OptionValidator {
    static void validateOption(final String opt) throws IllegalArgumentException {
        // if opt is NULL do not check further
        if (opt == null) {
            return;
        }

        // handle the single character opt
        if (opt.length() == 1) {
            final char ch = opt.charAt(0);

            if (!isValidOpt(ch)) {
                throw new IllegalArgumentException("Illegal option name '" + ch + "'");
            }
        }

        // handle the multi character opt
        else {
            for (final char ch : opt.toCharArray()) {
                if (!isValidChar(ch)) {
                    throw new IllegalArgumentException("The option '" + opt + "' contains an illegal "
                            + "character : '" + ch + "'");
                }
            }
        }
    }

    /**
     * Returns whether the specified character is a valid Option.
     *
     * @param c the option to validate
     * @return true if <code>c</code> is a letter, '?' or '@', otherwise false.
     */
    private static boolean isValidOpt(final char c) {
        return isValidChar(c) || c == '?' || c == '@';
    }

    /**
     * Returns whether the specified character is a valid character.
     *
     * @param c the character to validate
     * @return true if <code>c</code> is a letter.
     */
    private static boolean isValidChar(final char c) {
        return Character.isJavaIdentifierPart(c);
    }
}
