package by.training.provider.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Plan implements Identifiable {

    private Integer id;
    private String name;
    private Integer downloadSpeed;
    private Integer uploadSpeed;
    private BigDecimal price;
    private String description;

    public Plan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDownloadSpeed() {
        return downloadSpeed;
    }

    public void setDownloadSpeed(Integer downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public Integer getUploadSpeed() {
        return uploadSpeed;
    }

    public void setUploadSpeed(Integer uploadSpeed) {
        this.uploadSpeed = uploadSpeed;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plan)) {
            return false;
        }
        Plan plan = (Plan) o;
        return Objects.equals(getId(), plan.getId()) &&
                Objects.equals(getName(), plan.getName()) &&
                Objects.equals(getDownloadSpeed(), plan.getDownloadSpeed()) &&
                Objects.equals(getUploadSpeed(), plan.getUploadSpeed()) &&
                Objects.equals(getPrice(), plan.getPrice()) &&
                Objects.equals(getDescription(), plan.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDownloadSpeed(), getUploadSpeed(),
                getPrice(), getDescription());
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", downloadSpeed=" + downloadSpeed +
                ", uploadSpeed=" + uploadSpeed +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
