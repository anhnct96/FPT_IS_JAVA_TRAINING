package model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Extends from AbstractEntity, having all instances from AbstractEntity.
 * Contains 5 common fields of a Person: username, firstName, lastName, password, hiringDate.
 */
public class Person extends AbstractEntity{
    /**
     * userName: not null or blank, unique, distinct.
     * usage: defined a username to log in to the system of a person.
     */
    private String username;
    /**
     * firstName: not null or blank.
     * usage: contains person's first name.
     * requirement: firstName contains at least 5 and at most 20 characters, only  accept a-z, A-Z and the spaces!
     * accepted: nguyen, Nguyen, NGUYEN, Nguyen      ,     Nguyen    , NgUyEn   , Nguyen Cong.
     * standardize before updating/creating to the database.
     * standard firstName rule:
     *      Following the Standard string rule in the AbstractEntity class.
     *      First character of firstName must be uppercase character.
     */
    private String firstName;
    /**
     * lastName: not null or blank.
     * usage: contains person's last name.
     * requirement: lastName contains at least 5 and at most 20 characters, only  accept a-z, A-Z and the spaces!
     * accepted: Thao, thao, THAO, Thao      ,     Thao       , ThAO.
     * standardize before updating/creating to the database.
     * standard lastName rule:
     *      Following the Standard string rule in the AbstractEntity class.
     *      First character of lastName must be uppercase character.
     */
    private String lastName;
    /**
     * password: not null or blank.
     * requirement: password contains at least 6 characters.
     */
    private String password;
    /**
     * hiringDate: not null or blank.
     * usage: contains hiring date of a person.
     */
    private LocalDateTime hiringDate;

    /**
     * Constructor with full instances.
     * @param id
     * @param version
     * @param createdAt
     * @param modifiedAt
     * @param username
     * @param firstName
     * @param lastName
     * @param password
     * @param hiringDate
     */
    public Person(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, String username, String firstName, String lastName, String password, LocalDateTime hiringDate) {
        super(id, version, createdAt, modifiedAt);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.hiringDate = hiringDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDateTime hiringDate) {
        this.hiringDate = hiringDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", hiringDate=" + hiringDate +
                ", id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return username.equals(person.username) && firstName.equals(person.firstName) && lastName.equals(person.lastName) && password.equals(person.password) && hiringDate.equals(person.hiringDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, firstName, lastName, password, hiringDate);
    }
}
