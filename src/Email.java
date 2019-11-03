import java.util.ArrayList;

/**
 * Store a standard email
 * The standard format is in RFC 5322 and RFC 5321
 */
public class Email {
    // instance variables with private access
    private String localPart;
    private String domain;

    // constructors
    public Email(String localPart, String domain) {
        setLocalPart(localPart);
        setDomain(domain);
    }

    public Email(String email) {
        this();
        setEmail(email);
    }

    public Email() {
        this("", "");
    }

    // mutator functions
    public void setEmail(String email) {
        checkEmail(email);
        String[] emailSplit = email.split("@");
        setLocalPart(emailSplit[0]);
        setDomain(emailSplit[1]);
    }

    public void setLocalPart(String localPart) {
        checkLocalPart(localPart);
        this.localPart = localPart;
    }

    public void setDomain(String domain) {
        checkDomain(domain);
        this.domain = domain;
    }

    // private static check functions

    private static void checkEmail(String email) {
        String[] emailSplit = email.split("@");
        if (emailSplit.length != 2)
            throw new IllegalArgumentException("Email must use \"@\" to divide local-part and domain.");
    }

    /**
     * IMPORTANT: different from standard form
     * Does not allow "."
     * Allow uppercase and lowercase Latin letters A to Z and a to z
     * Allow digits 0 to 9
     * Allow printable characters !#$%&'*+-/=?^_`{|}~
     */
    private static void checkLocalPart(String localPart) {
        if (localPart.length() > 64)
            throw new IllegalArgumentException("Email's local-part must not exceed 64 length.");
        ArrayList<Character> validChars = new ArrayList<>();
        Character c;
        for (c='A'; c<='Z'; c++)
            validChars.add(c);
        for (c='a'; c<='z'; c++)
            validChars.add(c);
        for (c='0'; c<='9'; c++)
            validChars.add(c);
        String specialChars = "!#$%&'*+-/=?^_`{|}~";
        for (char ch: "!#$%&'*+-/=?^_`{|}~".toCharArray())
            validChars.add((Character) ch);
        for (char ch: localPart.toCharArray()) {
            if (!validChars.contains((Character) ch)) {
                String alert = String.format("\"%s\" is not valid in email's local-part.", ch);
                throw new IllegalArgumentException(alert);
            }
        }
    }

    private static void checkDomain(String domain) {
        if (domain.length() > 255)
            throw new IllegalArgumentException("Email's domain must not exceed 255 length.");
        String[] labels = domain.split("\\.");
        for (String label: labels)
            checkLabel(label);
    }

    /**
     * IMPORTANT: don't allow "-" in any position
     */
    private static void checkLabel(String label) {
        if (label.length() > 63)
            throw new IllegalArgumentException("Email's domain's label must not exceed 63 length.");
        ArrayList<Character> validChars = new ArrayList<>();
        Character c;
        for (c='A'; c<='Z'; c++)
            validChars.add(c);
        for (c='a'; c<='z'; c++)
            validChars.add(c);
        for (c='0'; c<='9'; c++)
            validChars.add(c);
        for (char ch: label.toCharArray()) {
            if (!validChars.contains((Character) ch)) {
                String alert = String.format("\"%s\" is not valid in email's label.", ch);
                throw new IllegalArgumentException(alert);
            }
        }
    }

    // override functions
    @Override
    public String toString() {
        return localPart + "@" + domain;
    }
}
