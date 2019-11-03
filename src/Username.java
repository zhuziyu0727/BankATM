import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

/**
 * Unique to one member
 */
public class Username {
    // instance variable with private access
    private String username;

    // constructors
    public Username(String username) {
        setUsername(username);
    }

    public Username() {
        this.username = "";
    }

    // accessor function
    public String getUsername() {
        return username;
    }

    // mutator function
    public void setUsername(String username) {
        checkUsername(username);
        this.username = username;
    }

    // private static check function

    /**
     * contain 5 to 10 characters
     * allow uppercase and lowercase letters
     * allow 0 to 9 digits
     * no other characters allowed
     */
    private static void checkUsername(String username) {
        if (username.length()<5 || username.length()>10) {
            String alert = String.format("Username \"%s\" must not stay in length from 5 to 10.", username);
            throw new IllegalArgumentException(alert);
        }
        ArrayList<Character> validChars = new ArrayList<>();
        Character c;
        for (c='A'; c<='Z'; c++)
            validChars.add(c);
        for (c='a'; c<='z'; c++)
            validChars.add(c);
        for (c='0'; c<='9'; c++)
            validChars.add(c);
        for (char ch: username.toCharArray()) {
            if (!validChars.contains((Character) ch)) {
                String alert = String.format("Character \"%s\" is not allowed in username.", ch);
                throw new IllegalArgumentException(alert);
            }
        }
    }

    // override function
    @Override
    public String toString() {
        return username;
    }
}
