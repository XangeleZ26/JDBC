
package modelo;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Cliente implements Serializable {
    
private static final long serialVersionUID=26L;
    private String tipoDocumento;
    private String numDocumento;
    private String nombres;
    private String apPaterno;
    private String apMaterno;
    private String sexo;
    private String fechaNacimiento;
    private String razonSocial;
    //private String correo;
    //private String contraseña;
    private Direccion direccion;
    //private Tarjeta tarjeta;
    private Cuenta cuenta;
    private Credencial credencial;

    //CONSTRUCTORES
    public Cliente() {
        direccion=new Direccion();
        cuenta=new Cuenta();
        credencial=new Credencial();
    }
    
    public Cliente(String tipoDocumento, String numDocumento, String nombres, String apPaterno, String apMaterno, String sexo, String fechaNacimiento, String razonSocial) {
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.nombres = nombres;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.razonSocial = razonSocial;
        this.direccion=new Direccion();
        this.cuenta=new Cuenta();
        this.credencial=new Credencial();
    }

    public Cliente(String tipoDocumento, String numDocumento, String nombres, String apPaterno, String apMaterno, String sexo, String fechaNacimiento, String razonSocial, Direccion direccion, Cuenta cuenta, Credencial credencial) {
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.nombres = nombres;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.cuenta = cuenta;
        this.credencial = credencial;
    }
  
    public Cliente(Cliente cliente) {
        this.tipoDocumento = cliente.getTipoDocumento();
        this.numDocumento = cliente.getNumDocumento();
        this.nombres = cliente.getNombres();
        this.apPaterno = cliente.getApPaterno();
        this.apMaterno = cliente.getApMaterno();
        this.sexo = cliente.getSexo();
        this.fechaNacimiento = cliente.getFechaNacimiento();
//        this.cuenta = new Cuenta();
//        this.credencial = new Credencial(cliente.getCredencial().getCorreo(),cliente.getCredencial().getContraseña());
        Cuenta cuentaPolimorfismo = new Cuenta();
        Credencial credencialPolimorfismo = new Credencial(cliente.getCredencial().getCorreo(), cliente.getCredencial().getContraseña());
        guardarAtributos(cuentaPolimorfismo);
        guardarAtributos(credencialPolimorfismo);
    }

    public Cliente(String tipoDocumento, String numDocumento, String nombres, String apPaterno, String apMaterno, String sexo, String fechaNacimiento, String correo, String contraseña) {
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.nombres = nombres;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
//        this.cuenta = new Cuenta();
//        this.credencial = new Credencial (correo,contraseña);      
        Cuenta cuentaPolimorfismo = new Cuenta();
        Credencial credencialPolimorfismo = new Credencial(correo, contraseña);
        guardarAtributos(cuentaPolimorfismo);
        guardarAtributos(credencialPolimorfismo);

    }

    public Cliente(String tipoDocumento, String numDocumento, String correo, String razonSocial) {
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.razonSocial = razonSocial;
//        this.cuenta = new Cuenta();
//        this.credencial = new Credencial(correo,numDocumento);
        Cuenta cuentaPolimorfismo = new Cuenta();
        Credencial credencialPolimorfismo = new Credencial(correo,numDocumento);
        guardarAtributos(cuentaPolimorfismo);
        guardarAtributos(credencialPolimorfismo);
    }

    public Cliente(String tipoDocumento, String numDocumento, String correo, String razonSocial, String contraseña) {
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.razonSocial = razonSocial;
//        this.cuenta = new Cuenta();
//        this.credencial = new Credencial(correo,contraseña);
        Cuenta cuentaPolimorfismo = new Cuenta();
        Credencial credencialPolimorfismo = new Credencial(correo,contraseña);
        guardarAtributos(cuentaPolimorfismo);
        guardarAtributos(credencialPolimorfismo);
    }

    public Cliente(String numDocumento, String correo) {
        this.numDocumento = numDocumento;
//        this.cuenta = new Cuenta();
//        this.credencial = new Credencial(correo);
        Cuenta cuentaPolimorfismo = new Cuenta();
        Credencial credencialPolimorfismo = new Credencial(correo);
        guardarAtributos(cuentaPolimorfismo);
        guardarAtributos(credencialPolimorfismo);
    }

    public Cliente(String numDocumento, String correo, String contraseña) {
        this.numDocumento = numDocumento;
//        this.cuenta = new Cuenta();
//        this.credencial = new Credencial(correo,contraseña);
        Cuenta cuentaPolimorfismo = new Cuenta();
        Credencial credencialPolimorfismo = new Credencial(correo,contraseña);
        guardarAtributos(cuentaPolimorfismo);
        guardarAtributos(credencialPolimorfismo);
    }

    //MÉTODO PARA PROBAR EL POLIMORFISMO EN EL TRABAJO
    public void guardarAtributos(Credencial atributos) {
        if (atributos instanceof Cuenta) {
            this.cuenta = (Cuenta) atributos;
        } else if (atributos instanceof Credencial) {
            this.credencial = atributos;
        }
    }

    //MÉTODO Contraseña
    public int cambioContrasena(String contraseña, String novoContra, String verifContra) {
        int result = 0;
        if (!(this.credencial.getContraseña().equals(contraseña))) {
            result = 1;
        }
        if (!(novoContra.equals(verifContra))) {
            result = 2;
        }
        if ((!(this.credencial.getContraseña().equals(contraseña))) && (!(novoContra.equals(verifContra)))) {
            result = 3;
        }
        return result;
    }

    //MÉTODOS ACCESORES
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    /*
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
     */
    public Direccion getDireccion() { //Se puede usar para registrar dirección
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /*public Tarjeta getTarjeta() { //Se puede usar para registrar tarjeta
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }*/
    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    //MÉTODOS DEL FUNCIONAMIENTO DE LA CLASE
    public void registrarDireccion(String departamento, String provincia, String distrito, String avenida, int numero, String referencia, String telefono) {
        this.direccion = new Direccion(departamento, provincia, distrito, avenida, numero, referencia, telefono);
    }

    public boolean crearCuenta(Tarjeta tarjeta) {
        boolean result = false;
        if (tarjeta.verificarValidezTajeta(tarjeta.getNumTarjeta(), tarjeta.getMedioPago()) == true
                && tarjeta.verificarVigenciaTarjeta(tarjeta.getFechaVencimiento()) == true
                && tarjeta.verificarValidezCVV(tarjeta.getCvv(), tarjeta.getMedioPago()) == true) {
            this.cuenta.setTarjeta(tarjeta);
            result = true;
        }
        return result;
    }

    /*public void registrarTarjeta(String medioPago, String numTarjeta, String claveTarjeta, String fechaVencimiento, String cvv){
        this.tarjeta = new Tarjeta(medioPago,numTarjeta,claveTarjeta,fechaVencimiento,cvv);
    }*/
 /*
    public void cambiarCorreo(String nuevoCorreo){
        this.correo = nuevoCorreo;
    }
    
    public void cambiarContraseña(String nuevaContraseña){
        this.contraseña = nuevaContraseña;
    }*/
    public boolean verificarValidezRUC(String RUC) {
        boolean result = false;
        int suma = (Character.getNumericValue(RUC.charAt(0)) * 5)
                + (Character.getNumericValue(RUC.charAt(1)) * 4)
                + (Character.getNumericValue(RUC.charAt(2)) * 3)
                + (Character.getNumericValue(RUC.charAt(3)) * 2)
                + (Character.getNumericValue(RUC.charAt(4)) * 7)
                + (Character.getNumericValue(RUC.charAt(5)) * 6)
                + (Character.getNumericValue(RUC.charAt(6)) * 5)
                + (Character.getNumericValue(RUC.charAt(7)) * 4)
                + (Character.getNumericValue(RUC.charAt(8)) * 3)
                + (Character.getNumericValue(RUC.charAt(9)) * 2);
        double division = suma / 11;
        long entero = (long) division;
        int validacion = (int) (11 - (suma - (entero * 11)));
        if (validacion == 10) {
            validacion = 0;
        } else if (validacion == 11) {
            validacion = 1;
        }

        if (RUC.length() == 11) {
            if (RUC.charAt(0) == '2' && RUC.charAt(1) == '0') {
                if (Character.getNumericValue(RUC.charAt(10)) == validacion) {
                    result = true;
                } else {
                    result = false;
                }
            } else if (RUC.charAt(0) == '1') {
                switch (RUC.charAt(1)) {
                    case '0':
                    case '5':
                    case '7':
                        if (Character.getNumericValue(RUC.charAt(10)) == validacion) {
                            result = true;
                        } else {
                            result = false;
                        }
                        break;
                    default:
                        result = false;
                        break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }
    
    public boolean verificarValidezDNI(String DNI){
        boolean result = false;
        if(DNI.length()==8){
            result = true;
        }
        return result;
    }
    
    public boolean verificarValidezExtranjeria(String CarnetExtranjeria){
        boolean result = false;
        if(CarnetExtranjeria.length()==9){
            result = true;
        }
        return result;
    }
    
    public boolean verificarValidezTelefono(String telefono){
        boolean result = false;
        if((telefono.length()>=7)&&(telefono.length()<=9)){
            result = true;
        }
        return result;
    }
    
    public boolean verificarValidezNumero(String numero){
        boolean result = false;
        if((numero.length()>0)&&(numero.length()<=3)){
            result = true;
        }
        return result;
    }
    
    public boolean verificarEdad(String fechaNacimiento){
        boolean result = false;
        Date fechaActual = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
        int mil = Character.getNumericValue(fechaNacimiento.charAt(0)) * 1000;
        int cien = Character.getNumericValue(fechaNacimiento.charAt(1)) * 100;
        int diez = Character.getNumericValue(fechaNacimiento.charAt(2)) * 10;
        int unidad = Character.getNumericValue(fechaNacimiento.charAt(3));
        int añoNacimiento = mil + cien + diez+ unidad;
        if(fechaActual.getYear()+ 1900 - añoNacimiento >=18){
            return true;
        }
        
        return result;
    }


    @Override
    public String toString() {
        return "***Datos de Usuario***" + "\n"
                + "Tipo de Documento: " + tipoDocumento + "\n"
                + "Numero de Documento: " + numDocumento + "\n"
                + "Nombres: " + nombres + "\n"
                + "Ap. Paterno: " + apPaterno + "\n"
                + "Ap. Materno: " + apMaterno + "\n"
                + "Sexo: " + sexo + "\n"
                + "Fecha de nacimiento: " + fechaNacimiento + "\n"
                + "Correo: " + credencial.getCorreo() + "\n"
                + "Contraseña: " + credencial.getContraseña();
    }
}

