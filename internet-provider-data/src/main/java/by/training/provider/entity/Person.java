package by.training.provider.entity;

import java.util.Objects;

/**
 * Abstract Person with common fields for all people.
 */
public abstract class Person implements Identifiable {

    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;

    //////////////////////////////////////////////////////////////////////

    public Person() {
    }

    //////////////////////////////////////////////////////////////////////

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) &&
                Objects.equals(getEmail(), person.getEmail()) &&
                Objects.equals(getPassword(), person.getPassword()) &&
                Objects.equals(getFirstName(), person.getFirstName()) &&
                Objects.equals(getMiddleName(), person.getMiddleName()) &&
                Objects.equals(getLastName(), person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(),
                getFirstName(), getMiddleName(), getLastName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
