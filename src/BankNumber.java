import java.util.Random;

/**
 * 9 digit code
 */
public class BankNumber {
    // instance variables with private access
    private String code;

    // constructors
    public BankNumber(String code) {
        setCode(code);
    }

    public BankNumber() {
        this.code = "";
    }

    // accessor function
    public String getCode() {
        return code;
    }

    // mutator function
    public void setCode(String code) {
        checkCode(code);
        this.code = code;
    }

    // check function
    private void checkCode(String code) {
        try {
            if (code.length() != 9)
                throw new NumberFormatException();
            int codeInt = Integer.parseInt(code);
            if (codeInt < 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            String alert = String.format("\"%s\" is not valid bank routing number.", code);
            throw new IllegalArgumentException(alert);
        }
    }

    // generate function
    public static String generateRandomNumber() {
        Random rand = new Random();
        int numberInt = rand.nextInt(1000000000);
        return String.format("%09d", numberInt);
    }
}
