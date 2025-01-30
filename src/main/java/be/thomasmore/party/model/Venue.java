package be.thomasmore.party.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Venue {

    @Id
    private Integer id;

    private String venueName;
    private String linkMoreInfo;

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getLinkMoreInfo() {
        return linkMoreInfo;
    }

    public void setLinkMoreInfo(String linkMoreInfo) {
        this.linkMoreInfo = linkMoreInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
