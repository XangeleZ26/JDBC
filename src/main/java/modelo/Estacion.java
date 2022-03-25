
package modelo;

import java.io.Serializable;

public class Estacion implements Serializable{
    private static final long serialVersionUID=26L;
    private String nombreEstacion;
    private String codigoEstacion;
    
    //MÉTODOS ACCESORES
    public String getNombreEstacion() {
        return nombreEstacion;
    }

    public void setNombreEstacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
    }

    public String getCodigoEstacion() {
        return codigoEstacion;
    }

    public void setCodigoEstacion(String codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }

    /*public float getTarifaNeta() {
    return tarifaNeta;
    }
    public void setTarifaNeta(float tarifaNeta) {
    this.tarifaNeta = tarifaNeta;
    }*/

    //public float getPorcentajeVariacion() {
    //    return porcentajeVariacion;
    //}

    //public void setPorcentajeVariacion(float porcentajeVariacion) {
    //    this.porcentajeVariacion = porcentajeVariacion;
    //}

 
    
    //CONSTRUCTOR
    public Estacion(String nombreEstacion, String codigoEstacion) {
        this.nombreEstacion = nombreEstacion;
        this.codigoEstacion = codigoEstacion;
    }
    public Estacion(){
        
    }
 
    @Override
    public String toString() {
        return "***DATOS DE LA ESTACIÓN***" + "\n" +
               "Nombre de la estación: " + nombreEstacion + "\n" +
               "Codigo de la estación" + codigoEstacion + "\n" ;
    }
    
}