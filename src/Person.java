/**
 * stand for one single person
 * a person has name and dob
 */
public class Person {
    // instance variable with private access
    private Name name;
    private Date dob;

    // constructors
    public Person(String lastName, String firstName, String middleName, String nickName) {
        name = new Name(lastName, firstName, middleName, nickName);
        dob = new Date();
    }

    public Person() {
        name = new Name();
        dob = new Date();
    }

    // accessor function
    public String getName() {
        return name.toString();
    }

    public String getDOB() {
        return dob.toString();
    }

    // mutator functions
    public void setLastName(String lastName) {
        name.setLastName(lastName);
    }

    public void setFirstName(String firstName) {
        name.setFirstName(firstName);
    }

    public void setMiddleName(String middleName) {
        name.setMiddleName(middleName);
    }

    public void setNickName(String nickName) {
        name.setNickName(nickName);
    }

    public void setDay(int day) {
        dob.setDay(day);
    }

    public void setMonth(int month) {
        dob.setMonth(month);
    }

    public void setYear(int year) {
        dob.setYear(year);
    }
}
