
package modelo;

import java.io.Serializable;

public class Vehiculo implements Serializable{
    private static final long serialVersionUID=26L;
    private String placa;
    private String marca;
    private String modelo;
    private String categoria;
    private int ejes;
    private String tipoUso;
    private float pesoBruto;
    private int año;
    private Cliente dueño;
    
    
    public Vehiculo(){
        
    }
    //MÉTODOS ACCESORES 
    public String getPlaca() {
        return placa;
     //   dueño.getCuenta().getMovimientos(5).getEstacion().getPeaje()
   //  dueño.getCuenta().getMovimientos(5).getVehiculo().getPlaca().compareTo(categoria)
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
    this.marca = marca;

    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getEjes() {
        return ejes;
    }

    public void setEjes(int ejes) {
        this.ejes = ejes;
    }

    public String getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(String tipoUso) {
        this.tipoUso = tipoUso;
    }

    public float getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(float pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public Cliente getDueño() {
        return dueño;        
    }

    public void setDueño(Cliente dueño) {
        this.dueño = dueño;
    }
    
    //MÉTODOS DE FUNCIONAMIENTO DE LA CLASE 
    //Si tiene dos ejes, es de categoría liviana y puede tener un tipo de uso público o privado
    
    public Vehiculo(String placa, String marca, String modelo, String categoria, int ejes, String tipoUso, float pesoBruto, int año, Cliente dueño) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.categoria = categoria;
        this.ejes = ejes;
        this.tipoUso = tipoUso;
        this.pesoBruto = pesoBruto;
        this.año = año;
        this.dueño = dueño;
    }

    public Vehiculo(String placa, String marca, String modelo, int ejes, String tipoUso, float pesoBruto, int año, Cliente dueño) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ejes = ejes;
        this.tipoUso = tipoUso;
        this.pesoBruto = pesoBruto;
        this.año = año;
        this.categoria = "Liviano";
        this.dueño = dueño;
    }
    
    //Si tiene más de dos ejes, es de categoría pesada y es de tipo privado
    public Vehiculo(String placa, String marca, String modelo, int ejes, float pesoBruto, int año, Cliente dueño) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ejes = ejes;
        this.pesoBruto = pesoBruto;
        this.año = año;
        this.categoria = "Pesado";
        this.tipoUso = "Privado";
        this.dueño = dueño;
    }
    
    //Mostrar los datos del vehículo
    @Override
    public String toString() {
        return "***Datos del vehículo***\n" + 
               "Placa: " + placa + "\n" + 
               "Marca: " + marca + "\n" +
               "Modelo: " + modelo + "\n" +
               "Categoria: " + categoria + "\n" +
               "Ejes: " + ejes + "\n" +
               "Tipo de Uso: " + tipoUso + "\n" +
               "PesoBruto: " + pesoBruto + "\n" +
               "Año: " + año;
    }
    
    public Object[] datosVehiculo(){
        Object datos[] = new Object[8];
        
        datos[0] = this.placa;
        datos[1] = this.marca;
        datos[2] = this.modelo;
        datos[3] = this.categoria;
        datos[4] = this.ejes;
        datos[5] = this.tipoUso;
        datos[6] = this.pesoBruto;
        datos[7] = this.año;
        
        return datos;
    }
}
