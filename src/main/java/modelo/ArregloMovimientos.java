package modelo;

import java.io.Serializable;

public class ArregloMovimientos implements Serializable {
private static final long serialVersionUID=26L;
    private Movimiento[] movimientos;
    private int nm;//Cantidad de movimientos en el arreglo
    private int om;//Orden en el arreglo del último movimiento agregado

    public ArregloMovimientos() {
        this.movimientos = new Movimiento[Configuracion.maxMovimientos];
        this.nm = 0;
        this.om = this.nm - 1;
    }

    public Object[] cabecera() {
        Object datos[] = new Object[6];
        datos[0] = "FECHA";
        datos[1] = "TIPO";
        datos[2] = "MONTO";
        datos[3] = "VEHICULO";
        datos[4] = "ESTACION";
        return datos;
    }

    private boolean estaLleno() {
        boolean result = false;
        if (this.nm >= this.movimientos.length) {
            result = true;
        }
        return result;
    }

    public void crecerArreglo() {
        Movimiento movimientosPlus[] = new Movimiento[this.movimientos.length + 2];
        for (int i = 0; i < this.movimientos.length; i++) {
            movimientosPlus[i] = this.movimientos[i];
        }
        this.movimientos = movimientosPlus;
    }

    public void registrarMovimiento(String fecha, String tipo, float monto, Vehiculo vehiculo, String estacion) {
        if (estaLleno()) {
            this.crecerArreglo();
        }
        this.nm++;
        this.om = this.nm - 1;
        this.movimientos[om] = new Movimiento(fecha,monto,tipo,estacion,vehiculo);
    }
 
    public void registrarMovimiento(Movimiento mov){
        if (estaLleno()) {
            this.crecerArreglo();
        }
        this.nm++;
        this.om = this.nm - 1;
        this.movimientos[om] =mov;
    }
    public void registrarMovimiento(String fecha, String tipo, float monto) {
        if (estaLleno()) {
            this.crecerArreglo();
        }
        this.nm++;
        this.om = this.nm - 1;
        this.movimientos[om] = new Movimiento(fecha, tipo, monto);
    }

    public void mostrarMovimientos() {
   
        for (int i = 0; i < nm; i++) {
                System.out.format("fecha: "+movimientos[i].getFecha()+" tipo: "+movimientos[i].getTipo()+" monto: "+movimientos[i].getMonto()+" placa: "+movimientos[i].getVehiculo().getPlaca()+" estacion: "+this.movimientos[i].getNombreEstacion()+"\n");

        }
    }

    //ORDENAR MOVIMIENTOS
    public void ordenarMovimientosXFecha() {
        Movimiento aux;
        for (int i = 0; i < nm; i++) {
            for (int j = 0; j < (this.nm - 1); j++) {
                if (movimientos[j].getFecha().compareTo(movimientos[j + 1].getFecha()) > 0) {
                    aux = movimientos[j];
                    movimientos[j] = movimientos[j + 1];
                    movimientos[j + 1] = aux;
                }
            }
        }
    }

    public void ordenarMovimientosXMonto() {
        Movimiento aux;
        for (int i = 0; i < (this.nm); i++) {
            for (int j = 0; j < (this.nm - 1); j++) {
                if (movimientos[j].getMonto() > movimientos[j + 1].getMonto()) {
                    aux = movimientos[j];
                    movimientos[j] = movimientos[j + 1];
                    movimientos[j + 1] = aux;
                }
            }
        }
    }

    public void ordenarMovimientosXPlacaVehiculo() {
        Movimiento aux;
        for (int z = 0; z < this.nm; z++) {
            if (this.movimientos[z].getVehiculo() == null) {
                this.movimientos[z].setVehiculo(new Vehiculo());
                this.movimientos[z].getVehiculo().setPlaca("¯");
            }
        }
        for (int i = 0; i < (this.nm); i++) {
            for (int j = 0; j < (this.nm - 1); j++) {
                if (this.movimientos[j].getVehiculo().getPlaca().compareTo(this.movimientos[j + 1].getVehiculo().getPlaca()) > 0) {
                    aux = movimientos[j];
                    movimientos[j] = movimientos[j + 1];
                    movimientos[j + 1] = aux;
                }
            }

        }
    }

    public int getNm() {
        return nm;
    }

    public void setNm(int nm) {
        this.nm = nm;
    }

    public int getOm() {
        return om;
    }

    public void setOm(int om) {
        this.om = om;
    }

    public Movimiento getMovimiento(int i) { //Se puede usar para llamar a un movimeento del arreglo
        return movimientos[i];
    }
}

