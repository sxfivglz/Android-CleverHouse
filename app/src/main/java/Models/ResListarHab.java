package Models;

import java.io.Serializable;

public class ResListarHab implements Serializable {
    private String nombre_casa;
    private CasasDueno casa;

    public ResListarHab(String nombre_casa, CasasDueno casa){
        this.nombre_casa = nombre_casa;
        this.casa = casa;
    }

    public String getNombre_casa() {
        return nombre_casa;
    }

    public void setNombre_casa(String nombre_casa) {
        this.nombre_casa = nombre_casa;
    }

    public CasasDueno getCasa() {
        return casa;
    }

    public void setCasa(CasasDueno casa) {
        this.casa = casa;
    }
}
