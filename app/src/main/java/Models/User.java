package Models;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String nombre_usuario;
    private String apellido_usuario;
    private String email;
    private String telefono;
    private String columna_1;
    private String columna_2;
    private String columna_3;
    private String columna_4;
    private String created_at;
    private String updated_at;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

}
