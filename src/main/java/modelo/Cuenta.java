
package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cuenta extends Credencial implements Serializable {

    private static final long serialVersionUID = 26L;
    private float saldoTotal;
    private float monto;
    private Tarjeta tarjeta;
    private int diaCreacion;
    private int mesCreacion;
    private int anioCreacion;
    private ArregloMovimientos movimientos;
    private ArregloVehiculos vehiculos;

//    private Estacion estacion;
    public Cuenta() {
        super();
        this.saldoTotal = 12;
        this.tarjeta = new Tarjeta();
        this.vehiculos = new ArregloVehiculos();
        this.movimientos = new ArregloMovimientos();
        this.mesCreacion=0;
        this.diaCreacion=0;
        this.anioCreacion=0;
    }

    //CONSTRUCTORES
    public Cuenta(Tarjeta tarjeta) {
        this.saldoTotal = 12;
        this.tarjeta = tarjeta;
        this.vehiculos = new ArregloVehiculos();
        this.movimientos = new ArregloMovimientos();
        this.mesCreacion=0;
        this.diaCreacion=0;
        this.anioCreacion=0;
    }

    public Cuenta(float saldoTotal, float monto, Tarjeta tarjeta, int diaCreacion, int mesCreacion, int anioCreacion) {
        this.saldoTotal = saldoTotal;
        this.monto = monto;
        this.tarjeta = tarjeta;
        this.diaCreacion = diaCreacion;
        this.mesCreacion = mesCreacion;
        this.anioCreacion = anioCreacion;
        this.vehiculos = new ArregloVehiculos();
        this.movimientos = new ArregloMovimientos();
    }

    //MÉTODOS ACCESORES
    //AGREGADO
//    public Estacion getEstacion() {
//        return estacion;
//    }
//
//    public void setEstacion(Estacion estacion) {
//        this.estacion = estacion;
//    }
    //AGREGADO
    //MÉTODOS ACCESORES
    public float getSaldoTotal() {
        return saldoTotal;
    }

    public int getDiaCreacion() {
        return diaCreacion;
    }

    public void setDiaCreacion(int diaCreacion) {
        this.diaCreacion = diaCreacion;
    }

    public int getMesCreacion() {
        return mesCreacion;
    }

    public void setMesCreacion(int mesCreacion) {
        this.mesCreacion = mesCreacion;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }
    
    public ArregloVehiculos getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArregloVehiculos vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void setSaldoTotal(float saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public ArregloMovimientos getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArregloMovimientos movimientos) {
        this.movimientos = movimientos;
    }

    public boolean recargar(float monto, Tarjeta tarjeta, String fecha) {
        boolean result = false;
        if (monto > 0 && tarjeta.getCvv().equals(this.tarjeta.getCvv())) {
            this.saldoTotal = this.saldoTotal + monto;
            result = true;
        }
        //Registrar movimiento al arreglo
        if (result) {
            this.movimientos.registrarMovimiento(fecha, "Recarga", monto);
        }
        return result;
    }
    public void guardarFechaCreacion(String dia,String mes,String anio){
        this.anioCreacion=Integer.parseInt(anio);
        this.diaCreacion=Integer.parseInt(dia);
        this.mesCreacion=Integer.parseInt(mes);
    }
    public boolean compararFechas(int dia,int mes,int anio){
        boolean result=true;
        if (anio >= this.anioCreacion) {
            if ((mes >= this.mesCreacion) || (anio > this.anioCreacion)) {
                if (dia >= this.diaCreacion || (mes > this.mesCreacion || anio > this.anioCreacion)) { //perfect
                } else {
                    result = false;
                }
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        
        return result;
    }
    /*Hacer validación del CVV*/
    public boolean recargar(float monto, String cvv, String fecha) {
        boolean result = false;
        if (monto > 0) {
            this.saldoTotal = this.saldoTotal + monto;
            this.movimientos.registrarMovimiento(fecha, "Recarga", monto);
            result = true;
        }
        //Registrar movimiento al arreglo

        return result;
    }



}
