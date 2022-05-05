package Models;

import java.util.ArrayList;

public class Sensor {
    private Integer id;
    private String nombre_sensor;
    private String columna_1;
    private String columna_2;
    private String columna_3;
    private String columna_4;
    private String created_at;
    private String updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_sensor() {
        return nombre_sensor;
    }

    public void setNombre_sensor(String nombre_sensor) {
        this.nombre_sensor = nombre_sensor;
    }

    public String getColumna_1() {
        return columna_1;
    }

    public void setColumna_1(String columna_1) {
        this.columna_1 = columna_1;
    }

    public String getColumna_2() {
        return columna_2;
    }

    public void setColumna_2(String columna_2) {
        this.columna_2 = columna_2;
    }

    public String getColumna_3() {
        return columna_3;
    }

    public void setColumna_3(String columna_3) {
        this.columna_3 = columna_3;
    }

    public String getColumna_4() {
        return columna_4;
    }

    public void setColumna_4(String columna_4) {
        this.columna_4 = columna_4;
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

    @Override
    public String toString() {
        return "Sensor{" +
                "id='" + id + '\'' +
                ", nombre_sensor='" + nombre_sensor + '\'' +
                ", columna_1='" + columna_1 + '\'' +
                ", columna_2='" + columna_2 + '\'' +
                ", columna_3='" + columna_3 + '\'' +
                ", columna_4='" + columna_4 + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
