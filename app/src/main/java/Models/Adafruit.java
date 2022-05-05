package Models;

public class Adafruit {
    String id;
    int feed_id;
    String value;
    String location;
    String created_at;
    String updated_at;
    String expiration;
    String lat;
    String lon;
    String ele;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(int feed_id) {
        this.feed_id = feed_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getEle() {
        return ele;
    }

    public void setEle(String ele) {
        this.ele = ele;
    }

    @Override
    public String toString() {
        return "Adafruit{" +
                "id='" + id + '\'' +
                ", feed_id='" + feed_id + '\'' +
                ", value='" + value + '\'' +
                ", location='" + location + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", expiration='" + expiration + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", ele='" + ele + '\'' +
                '}';
    }
}
