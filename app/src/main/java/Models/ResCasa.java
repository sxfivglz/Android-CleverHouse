package Models;

import java.io.Serializable;

public class ResCasa implements Serializable {
    private String message;
    private Datacasa datacasa;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Datacasa getDatacasa() {
        return datacasa;
    }

    public void setDatacasa(Datacasa datacasa) {
        this.datacasa = datacasa;
    }

}
