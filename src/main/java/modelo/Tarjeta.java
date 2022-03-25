
package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Tarjeta implements Serializable{
private static final long serialVersionUID=26L;
    private String medioPago;
    private String numTarjeta;
    private String fechaVencimiento;
    private String cvv;

    public Tarjeta(){
        
    }
    
    //MÉTODOS ACCESORES
    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    //CONSTRUCTORES
    public Tarjeta(String medioPago, String numTarjeta, String fechaVencimiento, String cvv) {
        this.medioPago = medioPago;
        this.numTarjeta = numTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
    }

    public Tarjeta(String medioPago, String numTarjeta, String cvv) {
        this.medioPago = medioPago;
        this.numTarjeta = numTarjeta;
        this.cvv = cvv;
    }
    
    //MÉTODOS DEL FUNCIONAMIENTO DE LA CLASE
    public void editarTarjeta(String numTarjeta) {
        //Revisar este método, es posible que sea innecesario
    }

    public boolean verificarDatosTarjeta(String numTarjeta, String cvv) {
        boolean result = false;
        if(numTarjeta == this.numTarjeta && cvv == this.cvv){
            result = true;
        }
        return result;
    }
    
    /*Visa: el primer número es 4 (16 números)
    Master Card: los dos primeros números están entre 51 y 55 (16 números)
    Diners Club: los primeros números están entre 300-305, 36, 38 y 39 (14 números)
    American Express: Los dos primeros números pueden ser 34 y 37 (15 números)*/
    
    public boolean verificarValidezTajeta(String numTarjeta, String medioPago) {
        boolean result = false;
        String aux;
        if(numTarjeta.length() == 16){
            if(numTarjeta.charAt(0) == '4' && medioPago.equalsIgnoreCase("VISA")){ //VISA
                result = true;
            }
            else if(numTarjeta.charAt(0) == '5' && medioPago.equalsIgnoreCase("MASTER CARD")){ //MASTER CARD
                switch (numTarjeta.charAt(1)){
                    case '1': 
                    case '2': 
                    case '3': 
                    case '4': 
                    case '5': result = true;
                        break;
                    default: result = false;
                        break;
                }
            }
        }
        else if (numTarjeta.length() == 15 && numTarjeta.charAt(0) == '3' && medioPago.equalsIgnoreCase("AMERICAN EXPRESS")) {
            switch(numTarjeta.charAt(1)){ //AMERICAN EXPRESS
                case '4':
                case '7': result = true;
                    break;
                default: result = false;
                    break;
            }
        }
        else if(numTarjeta.length() == 14 && numTarjeta.charAt(0) == '3' && medioPago.equalsIgnoreCase("DINERS CLUB")){
            switch(numTarjeta.charAt(1)){ //DINERS CLUB
                case '0':
                case '6':
                case '8':
                case '9': result = true;
                    break;
                default: result = false;
                    break;
            }
        }
        return result;
    }
    //CVV de Visa y Mastercard posee 3 dígitos
    //CVV de american express y diners card 4 dígitos
    public boolean verificarValidezCVV(String cvv, String medioPago){
        boolean result = false;
        if(cvv.length() == 3){
            if(medioPago.equalsIgnoreCase("VISA")){
                result = true;
            }
            else if(medioPago.equalsIgnoreCase("MASTER CARD")){
                result = true;
            }
        }
        else if(cvv.length() == 4){
            if(medioPago.equalsIgnoreCase("AMERICAN EXPRESS")){
                result = true;
            }
            else if(medioPago.equalsIgnoreCase("DINERS CLUB")){
                result = true;
            }
        }
        return result;
    }
    
    //REVISAR FUNCIONAMIENTO
    public boolean verificarVigenciaTarjeta(String fechaVencimiento) {
        boolean result = false;
        Date fechaActual = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("MM/yyyy");
        if(fechaVencimiento.compareTo(fecha.format(fechaActual))>0){
            return true;
        }
        
        return result;
    }

    @Override
    public String toString() {
        return "***Datos de Tarjeta***" + "\n" +
                "Medio de Pago: " + medioPago + "\n" +
                "Número de Tarjeta: " + numTarjeta + "\n" +
                "Fecha de Vencimiento: " + fechaVencimiento + "\n" +
                "CVV: " + cvv;
    }
    
}

