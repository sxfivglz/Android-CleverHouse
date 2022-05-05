package Models;

import java.io.Serializable;
import java.util.List;

public class ResInsertarArray implements Serializable {
 private String nombre_habitacion;

 public ResInsertarArray(String nombre_habitacion) {
  this.nombre_habitacion = nombre_habitacion;
 }

 public String getNombre_habitacion() {
  return nombre_habitacion;
 }

 public void setNombre_habitacion(String nombre_habitacion) {
  this.nombre_habitacion = nombre_habitacion;
 }

 @Override
 public String toString() {
  return "ResInsertarArray{" +
          "nombre_habitacion='" + nombre_habitacion + '\'' +
          '}';
 }
}
