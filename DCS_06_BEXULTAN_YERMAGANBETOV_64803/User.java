import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String birthDate;
    private double salary;
    private Gender gender;
    private String division;
    private String workPosition;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    // Конструктор
    public User(String firstName, String lastName, String birthDate, double salary, Gender gender, String division,
            String workPosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
        this.gender = gender;
        this.division = division;
        this.workPosition = workPosition;
    }

    // Геттеры
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public Gender getGender() {
        return gender;
    }

    public String getDivision() {
        return division;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    // Сеттеры (добавьте, если они нужны)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate +
                ", salary=" + salary + ", gender=" + gender + ", division=" + division + ", workPosition="
                + workPosition + "]";
    }
}
