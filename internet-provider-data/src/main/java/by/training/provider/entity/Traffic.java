package by.training.provider.entity;

import java.util.Date;

public class Traffic implements Identifiable {

    private Integer id;
    private Double downloaded;
    private Double uploaded;
    private Date date;
    private Integer userId;

    //////////////////////////////////////////////////////////////////////

    public Traffic() {
    }

    //////////////////////////////////////////////////////////////////////

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(Double downloaded) {
        this.downloaded = downloaded;
    }

    public Double getUploaded() {
        return uploaded;
    }

    public void setUploaded(Double uploaded) {
        this.uploaded = uploaded;
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
        return "Traffic{" +
                "id=" + id +
                ", downloaded=" + downloaded +
                ", uploaded=" + uploaded +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }
}
