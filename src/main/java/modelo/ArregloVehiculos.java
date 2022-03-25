
package modelo;

import java.io.Serializable;

public class ArregloVehiculos implements Serializable{
    private static final long serialVersionUID=26L;
    private int nv; //Cantidad de vehículos en el arreglo
    private int ov; //Orden en el arreglo del último vehículo agregado
    private Vehiculo[] vehiculos;

    public ArregloVehiculos() {
        this.nv = 0;
        this.ov = nv - 1;
        this.vehiculos = new Vehiculo[Configuracion.maxVehiculosXCliente];
    }
    
    

    //MÉTODOS DEL FUNCIONAMIENTO DE LA CLASE
    public boolean registrarVehiculo(String placa, String marca, String modelo, String categoria, int ejes, String tipoUso, float pesoBruto, int año, Cliente dueño) {
        boolean result = false;
        if(nv != Configuracion.maxVehiculosXCliente){
            if (verificarExistenciaVehiculo(placa) == false) {
            this.nv++;
            this.ov = this.nv - 1;
            this.vehiculos[ov] = new Vehiculo(placa, marca, modelo, categoria, ejes, tipoUso, pesoBruto, año, dueño);
            result = true;
            //Verificar si el registro fue correcto
            if (result == false) {
                this.nv--;
                this.ov = this.nv - 1;
            }
            }
        }
        
        return result;
    }
    public boolean registrarVehiculo(Vehiculo auto){
        boolean result = false;
        if(nv != Configuracion.maxVehiculosXCliente){
            if (verificarExistenciaVehiculo(auto.getPlaca()) == false) {
            this.nv++;
            this.ov = this.nv - 1;
            this.vehiculos[ov] = auto;
            result = true;
            //Verificar si el registro fue correcto
            if (result == false) {
                this.nv--;
                this.ov = this.nv - 1;
            }
            }
        }
        
        return result;
    }
    public boolean registrarVehiculo(String placa, String marca, String modelo, int ejes, float pesoBruto, int año, Cliente dueño) {
        boolean result = false;
        if (verificarExistenciaVehiculo(placa) == false) {
            this.nv++;
            this.ov = this.nv - 1;
            this.vehiculos[ov] = new Vehiculo(placa, marca, modelo, ejes, pesoBruto, año, dueño);
            result = true;
            //Verificar si el registro fue correcto
            if (result == false) {
                this.nv--;
                this.ov = this.nv - 1;
            }
        }
        return result;
    }

    public boolean eliminarVehiculo(Vehiculo vehiculo) {
        boolean result = false;
        int orden = -1;
        //Buscar en qué orden del arreglo se encuentra el objeto
        for (int i = 0; i < nv && orden == -1; i++) {
            if (vehiculo == this.vehiculos[i]) {
                orden = i;
                result = true;
            }
        }
        //Una vez ubicado el objeto, se borra copiándose todos los objetos desde adelante hacia atrás
        for (int i = orden; i < nv; i++) {
            this.vehiculos[i] = this.vehiculos[i + 1];
        }
        //Se borra el último espacio en memoria del arreglo y se cambia el número y el orden de los vehículos
        this.vehiculos[this.ov] = null;
        this.nv--;
        this.ov = this.nv - 1;
        //Verificar si el borrado fue correcto
        if (result == false) {
            this.nv++;
            this.ov = this.nv - 1;
        }
        return result;
    }
    
    //Nuevo método de Eliminar Vehículo
    public void eliminarVehiculo(int orden) {
        //Se borra copiándose todos los objetos desde adelante hacia atrás
        for (int i = orden; i < nv; i++) {
            this.vehiculos[i] = this.vehiculos[i + 1];
        }
        //Se borra el último espacio en memoria del arreglo y se cambia el número y el orden de los vehículos
        this.vehiculos[this.ov] = null;
        this.nv--;
        this.ov = this.nv - 1;
    }
    
    //Nuevo método de Eliminar Vehículo usando la placa
    public boolean eliminarVehiculo(String placa) {
        boolean result = false;
        //Se buscael vehículo en el arreglo y se almacena su orden
        int orden=-1;
        orden = buscarOrdenVehiculo(placa);
        
        if(orden>=0){
            //Se borra copiándose todos los objetos desde adelante hacia atrás
            for (int i = orden; i < nv; i++) {
                this.vehiculos[i] = this.vehiculos[i + 1];
            }
            //Se borra el último espacio en memoria del arreglo y se cambia el número y el orden de los vehículos
            this.vehiculos[this.ov] = null;
            this.nv--;
            this.ov = this.nv - 1;
            result = true;
        }
        return result;
    }
    
    //Método que devuelve el orden de un vehículo en el arreglo
    public int buscarOrdenVehiculo(String placa) {
        int orden = -1;
        for (int i = 0; (i < nv && orden == -1); i++) {
            if (placa.equals(this.vehiculos[i].getPlaca())) {
                orden = i;
            }
        } 
        return orden;
    }
    public Vehiculo buscarVehiculo(String placa){
        Vehiculo extra=null;
        for(int i=0;i<nv;i++){
            if(placa.equalsIgnoreCase(this.vehiculos[i].getPlaca())){
                extra=this.vehiculos[i];
            }
        }
        return extra;
    }
    public boolean verificarExistenciaVehiculo(String placa) {
        boolean result = false;
        for (int i = 0; i < nv; i++) {
            if (placa.equals(this.vehiculos[i].getPlaca())) {
                result = true;
            }
        }
        return result;
    }

    public void ordenarVehiculosXEjes() {
        Vehiculo aux;
        for (int i = 0; i < (this.nv); i++) {
            for (int j = i + 1; j < (this.nv); j++) {
                if (vehiculos[i].getEjes() > vehiculos[j].getEjes()) {
                    aux = vehiculos[i];
                    vehiculos[i] = vehiculos[j];
                    vehiculos[j] = aux;
                }
            }
        }
    }

    public void ordenarVehiculosXPesoBruto() {
        Vehiculo aux;
        for (int i = 0; i < (this.nv); i++) {
            for (int j = i + 1; j < (this.nv); j++) {
                if (vehiculos[i].getPesoBruto() > vehiculos[j].getPesoBruto()) {
                    aux = vehiculos[i];
                    vehiculos[i] = vehiculos[j];
                    vehiculos[j] = aux;
                }
            }
        }
    }

    public void ordenarVehiculosXAño() {
        Vehiculo aux;
        for (int i = 0; i < (this.nv); i++) {
            for (int j = i + 1; j < (this.nv); j++) {
                if (vehiculos[i].getAño() > vehiculos[j].getAño()) {
                    aux = vehiculos[i];
                    vehiculos[i] = vehiculos[j];
                    vehiculos[j] = aux;
                }
            }
        }
    }

    public void ordenarVehiculosXPlaca() {
        Vehiculo aux;
        for (int i = 0; i < (this.nv); i++) {
            for (int j = i + 1; j < (this.nv); j++) {
                if (vehiculos[i].getPlaca().compareTo(vehiculos[j].getPlaca()) > 0) {
                    aux = vehiculos[i];
                    vehiculos[i] = vehiculos[j];
                    vehiculos[j] = aux;
                }
            }
        }
    }

    public void mostrarVehiculos() {
        System.out.format("%-15s%-15s%-15s%-12s%-8s%-10s%-10s%-6s\n", "Placa", "Marca", "Modelo", "Categoría", "Ejes", "Uso", "Peso", "Año");
        System.out.format("%-15s%-15s%-15s%-12s%-8s%-10s%-10s%-6s\n", "=====", "=====", "======", "=========", "====", "===", "====", "===");
        for (int i = 0; i < nv; i++) {
            System.out.format("%-15s%-15s%-15s%-12s%-8s%-10s%-10s%-6s\n", vehiculos[i].getPlaca(), vehiculos[i].getMarca(), vehiculos[i].getModelo(), vehiculos[i].getCategoria(), vehiculos[i].getEjes(), vehiculos[i].getTipoUso(), vehiculos[i].getPesoBruto(), vehiculos[i].getAño());
        }
    }

    public Object[][] datosVehiculosAZ(){
        Object datosVehiculos[][] = new Object[this.nv][8];
        
        for(int i=0;i<this.nv;i++){
            datosVehiculos[i] = vehiculos[i].datosVehiculo();

        }
        
        return datosVehiculos;
    }
    
    public Object[][] datosVehiculosZA(){
        Object datosVehiculos[][] = new Object[this.nv][8];
        
        for(int i=0;i<this.nv;i++){
            datosVehiculos[i] = vehiculos[this.ov-i].datosVehiculo();
        }
        
        return datosVehiculos;
    }
public Vehiculo getVehiculo(int i) { //Se puede usar para llamar a un vehículo del arreglo
        return vehiculos[i];
    }

    public void setVehiculos(int i, Vehiculo vehiculo) { //solo se usa al registrar el primer vehículo
        this.vehiculos[i] = vehiculo;
        this.nv = 1;
        this.ov = this.nv - 1;
    }

    public int getNv() {
        return nv;
    }

    public void setNv(int nv) {
        this.nv = nv;
    }

    public int getOv() {
        return ov;
    }

    public void setOv(int ov) {
        this.ov = ov;
    }

}
