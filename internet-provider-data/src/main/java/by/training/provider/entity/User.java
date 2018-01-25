package by.training.provider.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class User extends Person {

    private Integer contract;
    private BigDecimal balance;
    private Byte isBlocked;
    private String city;
    private String street;
    private String building;
    private Integer apartments;
    private Integer planId;
    private Plan plan;
    private List<Payment> paymentList;
    private List<Traffic> trafficList;

    public User() {
    }

    public Integer getContract() {
        return contract;
    }

    public void setContract(Integer contract) {
        this.contract = contract;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Byte getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Byte isBlocked) {
        this.isBlocked = isBlocked;
    }

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

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<Traffic> getTrafficList() {
        return trafficList;
    }

    public void setTrafficList(List<Traffic> trafficList) {
        this.trafficList = trafficList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getContract(), user.getContract()) &&
                Objects.equals(getBalance(), user.getBalance()) &&
                Objects.equals(getIsBlocked(), user.getIsBlocked()) &&
                Objects.equals(getCity(), user.getCity()) &&
                Objects.equals(getStreet(), user.getStreet()) &&
                Objects.equals(getBuilding(), user.getBuilding()) &&
                Objects.equals(getApartments(), user.getApartments()) &&
                Objects.equals(getPlanId(), user.getPlanId()) &&
                Objects.equals(getPlan(), user.getPlan()) &&
                Objects.equals(getPaymentList(), user.getPaymentList()) &&
                Objects.equals(getTrafficList(), user.getTrafficList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getContract(), getBalance(), getIsBlocked(),
                getCity(), getStreet(), getBuilding(), getApartments(), getPlanId(),
                getPlan(), getPaymentList(), getTrafficList());
    }

    @Override
    public String toString() {
        return "User{" +
                "contract=" + contract +
                ", balance=" + balance +
                ", isBlocked=" + isBlocked +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", apartments=" + apartments +
                ", planId=" + planId +
                ", plan=" + plan +
                ", paymentList=" + paymentList +
                ", trafficList=" + trafficList +
                "super=" + super.toString() + "}";
    }
}
