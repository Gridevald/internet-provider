package by.training.provider.entity;

import java.util.Objects;

public class Customer extends Person {

    private String city;
    private String street;
    private String building;
    private Integer apartments;
    private Integer planId;
    private Plan plan;

    //////////////////////////////////////////////////////////////////////

    public Customer() {
    }

    //////////////////////////////////////////////////////////////////////

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Integer getApartments() {
        return apartments;
    }

    public void setApartments(Integer apartments) {
        this.apartments = apartments;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    //////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(getCity(), customer.getCity()) &&
                Objects.equals(getStreet(), customer.getStreet()) &&
                Objects.equals(getBuilding(), customer.getBuilding()) &&
                Objects.equals(getApartments(), customer.getApartments()) &&
                Objects.equals(getPlanId(), customer.getPlanId()) &&
                Objects.equals(getPlan(), customer.getPlan());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCity(), getStreet(), getBuilding(),
                getApartments(), getPlanId(), getPlan());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", apartments=" + apartments +
                ", planId=" + planId +
                ", plan=" + plan +
                "super=" + super.toString() + "}";
    }
}
