package Models;

public class AdafruitPuerta {
    private String id;
    private String value;
    private String feed_id;
    private String feed_fk;
    private String created_at;
    private String created_epoch;
    private String expiration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(String feed_id) {
        this.feed_id = feed_id;
    }

    public String getFeed_fk() {
        return feed_fk;
    }

    public void setFeed_fk(String feed_fk) {
        this.feed_fk = feed_fk;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_epoch() {
        return created_epoch;
    }

    public void setCreated_epoch(String created_epoch) {
        this.created_epoch = created_epoch;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "AdafruitPuerta{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", feed_id='" + feed_id + '\'' +
                ", feed_fk='" + feed_fk + '\'' +
                ", created_at='" + created_at + '\'' +
                ", created_epoch='" + created_epoch + '\'' +
                ", expiration='" + expiration + '\'' +
                '}';
    }
}
