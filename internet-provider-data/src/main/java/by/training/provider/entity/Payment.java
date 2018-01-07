package by.training.provider.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Payment implements Identifiable {

    private Integer id;
    private BigDecimal sum;
    private Date date;
    private Integer userId;

    //////////////////////////////////////////////////////////////////////

    public Payment() {
    }

    //////////////////////////////////////////////////////////////////////

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

    //////////////////////////////////////////////////////////////////////

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
