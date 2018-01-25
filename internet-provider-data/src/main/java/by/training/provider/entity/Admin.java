package by.training.provider.entity;

import java.util.Objects;

public class Admin extends Person {

    private Integer personnelNumber;

    public Admin() {
    }

    public Integer getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(Integer personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Admin)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Admin admin = (Admin) o;
        return Objects.equals(getPersonnelNumber(), admin.getPersonnelNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPersonnelNumber());
    }

    @Override
    public String toString() {
        return "Admin{" +
                "personnelNumber=" + personnelNumber +
                "super=" + super.toString() + "}";
    }
}
