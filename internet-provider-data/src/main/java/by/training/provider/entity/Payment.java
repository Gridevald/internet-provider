package by.training.provider.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Payment implements Identifiable {

    private Integer id;
    private BigDecimal sum;
    private Date date;
    private Integer userId;

    public Payment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        Payment payment = (Payment) o;
        return Objects.equals(getId(), payment.getId()) &&
                Objects.equals(getSum(), payment.getSum()) &&
                Objects.equals(getDate(), payment.getDate()) &&
                Objects.equals(getUserId(), payment.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSum(), getDate(), getUserId());
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", sum=" + sum +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }
}
