package Models;

import android.content.Context;
import android.content.SharedPreferences;

public class Usuario {
    private String nombre_usuario;
    private String apellidos_usuario;
    private String email;
    private String password;
    private String telefono;
    public Usuario( String nombre_usuario, String apellidos_usuario, String email, String password, String telefono){
        this.nombre_usuario = Usuario.this.nombre_usuario;
        this.apellidos_usuario = Usuario.this.apellidos_usuario;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }

    public String getNombre(){
        return nombre_usuario;
    }

    public void setNombre(String nombre_usuario){
        this.nombre_usuario = Usuario.this.nombre_usuario;
    }

    public String getApellidos(){
        return apellidos_usuario;
    }

    public void setApellidos(String apellidos_usuario){
        this.apellidos_usuario = apellidos_usuario;
    }

    public String getCorreo(){
        return email;
    }

    public void setCorreo(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "nombre_usuario='" + nombre_usuario + '\'' +
                ", apellidos_usuario='" + apellidos_usuario + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
    public void guardarUsuario(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre",nombre_usuario);
        editor.putString("apellidos",apellidos_usuario);
        editor.putString("email",email);
        editor.putString("password",password);
        editor.putString("telefono",telefono);
        editor.apply();
    }

}
