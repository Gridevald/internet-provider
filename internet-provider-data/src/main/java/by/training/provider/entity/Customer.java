package by.training.provider.entity;

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
}
