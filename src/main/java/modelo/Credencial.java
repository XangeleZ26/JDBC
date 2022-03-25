
package modelo;

import java.io.Serializable;

public class Credencial implements Serializable{
    private static final long serialVersionUID=26L;
    protected String correo;
    protected String contraseña;

    //Método constructor
    public Credencial(String correo,String contraseña) {
        this.contraseña = contraseña;
        this.correo = correo;
    }
    public Credencial(){
        
    }
    public Credencial(String correo) {
        this.correo = correo;
    }
    
    //Métodos accesores
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    //Métodos del funcionamiento de la clase
    public boolean cambiarCorreo(String nuevoCorreo){
        boolean result = false;
        if(nuevoCorreo.compareTo(this.correo)!=0){
            this.correo = nuevoCorreo;
            result = true;
        }
        return result;
    }
    
    public boolean cambiarContraseña(String nuevaContraseña){
        boolean result = false;
        if(nuevaContraseña.compareTo(this.contraseña)!=0){
            this.contraseña = nuevaContraseña;
            result = true;
        }
        return result;
    }
}