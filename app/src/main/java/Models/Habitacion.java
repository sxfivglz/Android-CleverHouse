package Models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Habitacion implements Serializable {
    private int id;
    private String nombre_habitacion;
    private String columna1;
    private String columna2;
    private String columna3;
    private String columna4;
    private String created_at;
    private String updated_at;

    public Habitacion(int id, String nombre_habitacion, String columna1, String columna2, String columna3, String columna4, String created_at, String updated_at) {
        this.id = id;
        this.nombre_habitacion = nombre_habitacion;
        this.columna1 = columna1;
        this.columna2 = columna2;
        this.columna3 = columna3;
        this.columna4 = columna4;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre_habitacion() {
        return nombre_habitacion;
    }
    public void setNombre_habitacion(String nombre_habitacion) {
        this.nombre_habitacion = nombre_habitacion;
    }
    public String getColumna1() {
        return columna1;
    }
    public void setColumna1(String columna1) {
        this.columna1 = columna1;
    }
    public String getColumna2() {
        return columna2;
    }
    public void setColumna2(String columna2) {
        this.columna2 = columna2;
    }
    public String getColumna3() {
        return columna3;
    }
    public void setColumna3(String columna3) {
        this.columna3 = columna3;
    }
    public String getColumna4() {
        return columna4;
    }
    public void setColumna4(String columna4) {
        this.columna4 = columna4;
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
        return "Habitacion{" +
                "id=" + id +
                ", nombre_habitacion='" + nombre_habitacion + '\'' +
                ", columna1='" + columna1 + '\'' +
                ", columna2='" + columna2 + '\'' +
                ", columna3='" + columna3 + '\'' +
                ", columna4='" + columna4 + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}



