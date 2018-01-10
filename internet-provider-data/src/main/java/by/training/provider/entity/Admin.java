package by.training.provider.entity;

public class Admin extends Person {

    private Integer personnelNumber;

    //////////////////////////////////////////////////////////////////////

    public Admin() {
    }

    //////////////////////////////////////////////////////////////////////

    public Integer getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(Integer personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    //////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                "personnelNumber=" + personnelNumber +
                '}';
    }
}
