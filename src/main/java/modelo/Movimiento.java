
package modelo;
import java.io.Serializable;
import java.util.Date;

public class Movimiento implements Serializable{
private static final long serialVersionUID=26L;

    private String fecha;
    private float monto;
    private String tipo;
    private String nombreEstacion;
    private Vehiculo vehiculo;

      public Movimiento(String fecha,float monto, String tipo, String nombreEstacion,Vehiculo vehiculo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.monto = monto;
        this.vehiculo = vehiculo;
        this.nombreEstacion = nombreEstacion;
    }
    public Movimiento(String fecha, String tipo, float monto) {
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = fecha;
       
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public float getMonto() {
        return monto; 
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getNombreEstacion() {
        return nombreEstacion;
    }

    public void setNombreEstacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    public void mostrarBoleta(String fecha, float monto) {
        
    }
    /*
    public void agregarMovimiento(Date fecha, float monto) {
           
    }
    */
    /*
    @Override
    public String toString() {
        return  "***Datos del movimiento***" + "\n" +
                "Fecha: " + fecha + "\n" +
                "Monto: " + monto + "\n"
                "Vehiculo: " + vehiculo.getPlaca() + " " + vehiculo.getMarca() + " " + vehiculo.getModelo() + "\n" +
                "Estacion: " + estacion.getCodigoEstacion() + " " + estacion.getNombreEstacion();
    }
    */

    @Override
    public String toString() {
        return "Movimiento{" + "fecha=" + fecha + ", monto=" + monto + ", tipo=" + tipo + ", nombreEstacion=" + nombreEstacion + ", vehiculo=" + vehiculo.getPlaca() + '}';
    }
    
    public String imprimirMovimientoSimple(){
        if(this.vehiculo!=null && this.nombreEstacion!=null){
            return "***Datos del movimiento***" + "\n" +
                   "Fecha: " + fecha + "\n" +
                   "Monto: " + monto + "\n" +
                   "Vehiculo: " + vehiculo.getPlaca() + " " + vehiculo.getMarca() + " " + vehiculo.getModelo() + "\n" +
                   "Estacion: " + nombreEstacion;
        }
        else if(this.vehiculo==null && this.nombreEstacion==null){
            return "***Datos del movimiento***" + "\n" +
                   "Fecha: " + fecha + "\n" +
                   "Monto: " + monto;
        }
        else{
            return "El movimiento no existe";
        }
    }
}

