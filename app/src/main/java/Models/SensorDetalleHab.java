package Models;

public class SensorDetalleHab {
    String id;
    String nombre_sensor;

    public SensorDetalleHab(String id, String nombre_sensor) {
        this.id = id;
        this.nombre_sensor = nombre_sensor;
    }

    public String getNombre_sensor(){
        return this.nombre_sensor;
    }

    public void setNombre_sensor(String nombre_sensor){
        this.nombre_sensor = nombre_sensor;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "SensorDetalleHab{" +
                "id='" + id + '\'' +
                ", nombre_sensor='" + nombre_sensor + '\'' +
                '}';
    }
}
