import java.util.ArrayList;

/**
 * Store a password for a member
 */
public class Password {
    // instance variable with private access
    private String password;

    // constructors
    public Password(String password) {
        setPassword(password);
    }

    public Password() {
        this.password = "";
    }

    // accessor function
    public String getPassword() {
        return password;
    }

    // mutator function
    public void setPassword(String password) {
        checkPassword(password);
        this.password = password;
    }

    // private static check function

    /**
     * Copied from Bank of America password requirement
     * contain 8 to 20 characters
     * have at least 1 uppercase letter, 1 lowercase letter, and 1 number
     * not repeat the same number or letter more than 3 times in a row
     * not include spaces, and contain only following special characters @#*()+={}/?~;,.-_
     */
    private static void checkPassword(String password) {
        if (password.length()<8 || password.length()>20) {
            String alert = String.format("Password \"%s\" must length from 8 to 20.", password);
            throw new IllegalArgumentException(alert);
        }
        boolean oneUppercaseLetter = false;
        boolean oneLowercaseLetter = false;
        boolean oneNumber = false;
        int sameCharCount = 0;
        ArrayList<Character> validChars = new ArrayList<>();
        Character c;
        for (c='A'; c<='Z'; c++)
            validChars.add(c);
        for (c='a'; c<='z'; c++)
            validChars.add(c);
        for (c='0'; c<='9'; c++)
            validChars.add(c);
        for (char ch: "@#*()+={}/?~;,.-_".toCharArray())
            validChars.add((Character) ch);
        char prev = '`';
        for (char ch: password.toCharArray()) {
            if (!validChars.contains((Character) ch)) {
                String alert = String.format("Character \"%s\" is not allowed in password", ch);
                throw new IllegalArgumentException(alert);
            } else {
                if (ch == prev) {
                    sameCharCount++;
                    if (sameCharCount > 3)
                        throw new IllegalArgumentException("Must not repeat same char more than 3 times in a row.");
                } else {
                    sameCharCount = 1;
                    prev = ch;
                }
                if (ch>='A' && ch<='Z')
                    oneUppercaseLetter = true;
                else if (ch>='a' && ch<='z')
                    oneLowercaseLetter = true;
                else if (ch>='0' && ch<='9')
                    oneNumber = true;
            }
        }
        if (!oneUppercaseLetter)
            throw new IllegalArgumentException("Password must have at least one uppercase letter.");
        if (!oneLowercaseLetter)
            throw new IllegalArgumentException("Password must have at least one lowercase letter.");
        if (!oneNumber)
            throw new IllegalArgumentException("Password must have at least one number.");
    }

    // override function
    @Override
    public String toString() {
        return password;
    }
}
