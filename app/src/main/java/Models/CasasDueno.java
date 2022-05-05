package Models;

import java.io.Serializable;

public class CasasDueno implements Serializable {
    private String nombre_casa;

    public CasasDueno(String nombre_casa){
        this.nombre_casa = nombre_casa;

    }


    public String getNombre_casa() {
        return nombre_casa;
    }

    public void setNombre_casa(String nombre_casa) {
        this.nombre_casa = nombre_casa;
    }

    @Override
    public String toString() {
        return '{' +
                "nombre_casa:'" + nombre_casa + '\'' +
                '}';
    }
}
