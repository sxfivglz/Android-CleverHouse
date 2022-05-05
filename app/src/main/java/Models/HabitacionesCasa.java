package Models;

import java.io.Serializable;

public class HabitacionesCasa implements Serializable {
    private String nombre_habitacion;
    private int id;

    public HabitacionesCasa(String nombre_habitacion, int id) {
        this.nombre_habitacion = nombre_habitacion;
        this.id = id;
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

    @Override
    public String toString() {
        return "HabitacionesCasa{" +
                "nombre_habitacion='" + nombre_habitacion + '\'' +
                ", id=" + id +
                '}';
    }
}
