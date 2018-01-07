package by.training.provider.entity;

import java.math.BigDecimal;

public class Plan implements Identifiable {

    private Integer id;
    private String name;
    private Integer downloadSpeed;
    private Integer uploadSpeed;
    private BigDecimal price;
    private String description;

    //////////////////////////////////////////////////////////////////////

    public Plan() {
    }

    //////////////////////////////////////////////////////////////////////

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

    //////////////////////////////////////////////////////////////////////
}
