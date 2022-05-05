package Models;

import java.io.Serializable;

public class Data implements Serializable {
    private String sensor_fk;
    private String detalle_habitacion_fk;
    private String created_at;
    private String updated_at;
    private String id;

    public String getSensor_fk() {
        return sensor_fk;
    }

    public void setSensor_fk(String sensor_fk) {
        this.sensor_fk = sensor_fk;
    }

    public String getDetalle_habitacion_fk() {
        return detalle_habitacion_fk;
    }

    public void setDetalle_habitacion_fk(String detalle_habitacion_fk) {
        this.detalle_habitacion_fk = detalle_habitacion_fk;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
