package aplicacion;

import modelo.*;
import controlador.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class app {
  //OJO: Tabla Estación de base de datos "peaje" está en deshuso  
    
 //UNA MANERA PARA HACER QUE LA LLAVE PRIMARIA NO SEAN COSAS "GENERALES" COMO LO QUE
 //HICE CON dia_creacion EN Cuenta SERÍA AGREGAR UN CAMPO EN EL QUE SE COLOQUE UN DIGITO NO SIMILAR CON OTRO
//(PARA Q FUNCIONE DEBEMOS DE INVOCAR EN PRIMERAS DICHO CAMPO Y USAR UNA FUNCIÓN EN JAVA
//QUE VERIFIQUE SI DICHO NUMERO EXISTE O NO, Y SI YA EXISTE, QUE CREE OTRO NUMERO AL AZAR 
 //USANDO ALGUNA FUNCIÓN RAMDOM().
    public static void main(String[] args) {
        String SELECT_MAYORIA = "SELECT c.tipo_documento,c.num_doc,c.nombres,c.a_paterno,c.a_materno,c.sexo,\n"
                + "c.fecha_nacimiento,c.razon_social,\n"
                + "\n"
                + "cr.correo,cr.contrasena,\n"
                + "\n"
                + "d.departamento,d.provincia,d.distrito,d.avenida,d.numero,d.referencia,d.telefono,\n"
                + "\n"
                + "cu.saldo_total,cu.monto,cu.dia_creacion,cu.mes_creacion,cu.anio_creacion,\n"
                + "\n"
                + "t.medio_pago,t.num_tarjeta,t.fecha_vencimiento,t.cvv\n"
                + "\n"
                + " FROM cliente c INNER JOIN credencial cr ON c.credencial_correo=cr.correo\n"
                + "INNER JOIN direccion d ON c.direccion_avenida=d.avenida INNER JOIN cuenta cu ON \n"
                + "cu.dia_creacion=c.cuenta_dia INNER JOIN tarjeta t ON cu.tarjeta_numtar=t.num_tarjeta\n"
                + "WHERE cr.correo=? AND cr.contrasena=?";

        String SELECT_VEHICULOS = "SELECT v.placa,v.marca,v.modelo,v.categoria,v.ejes,v.tipo_uso,v.peso_bruto,v.anio\n"
                + "FROM cliente c INNER JOIN credencial cr ON c.credencial_correo=cr.correo \n"
                + "INNER JOIN cuenta cu ON c.cuenta_dia=cu.dia_creacion\n"
                + "INNER JOIN vehiculos v ON v.cuenta_dia=cu.dia_creacion\n"
                + "WHERE cr.correo=? AND cr.contrasena=?";

        String SELECT_MOVIMIENTOS = "SELECT m.fecha,m.monto,m.tipo,m.estacion_nombre,m.vehiculo_placa\n"
                + "FROM cliente c INNER JOIN credencial cr ON c.credencial_correo=cr.correo \n"
                + "INNER JOIN cuenta cu ON c.cuenta_dia=cu.dia_creacion\n"
                + "INNER JOIN movimientos m ON m.cuenta_dia=cu.dia_creacion\n"
                + "WHERE cr.correo=? AND cr.contrasena=?";
        
        String INSERT_CREDENCIAL="INSERT INTO peaje.credencial(correo,contrasena) VALUES (?,?)"; //2
        String INSERT_DIRECCION="INSERT INTO peaje.direccion(departamento,provincia,distrito,avenida,numero,referencia,telefono) VALUES(?,?,?,?,?,?,?)"; //7
        String INSERT_TARJETA="INSERT INTO peaje.tarjeta(medio_pago,num_tarjeta,fecha_vencimiento,cvv) VALUES(?,?,?,?)"; //4
        String INSERT_CUENTA="INSERT INTO peaje.cuenta(saldo_total,monto,dia_creacion,mes_creacion,anio_creacion,tarjeta_numtar) VALUES(?,?,?,?,?,?)"; //6
        String INSERT_CLIENTE="INSERT INTO peaje.cliente(tipo_documento,num_doc,nombres,a_paterno,a_materno,sexo,fecha_nacimiento,razon_social,cuenta_dia,credencial_correo,direccion_avenida) VALUES(?,?,?,?,?,?,?,?,?,?,?)"; //11
        String INSERT_VEHICULOS="INSERT INTO peaje.vehiculos(placa,marca,modelo,categoria,ejes,tipo_uso,peso_bruto,anio,cliente_numdoc,cuenta_dia) VALUES(?,?,?,?,?,?,?,?,?,?)"; //10
        String INSERT_MOVIMIENTOS="INSERT INTO peaje.movimientos(fecha,monto,tipo,estacion_nombre,vehiculo_placa,cuenta_dia) VALUES(?,?,?,?,?,?)"; //6
        Connection conexion = null;
        
        Cliente persona = new Cliente(); //esta persona es para el SELECT, para INSERT estoy usando otro
        String correo = "jose@gmail.com";
        String contra = "123";
        //ESTO ES PARA PODER INSERTAR======================================================


//        PreparedStatement insertStatement=null;
//        
//        Credencial credencial=new Credencial("jose@gmail.com","123");
//        Direccion direccion=new Direccion("Jaén","Ancash","Los Olivos","San felipe",32,"no c","5678");
//        Tarjeta tarjeta=new Tarjeta("BCP","7654","30/02/2024","123");
//        Cuenta cuenta=new Cuenta(123, (float) 0.0,tarjeta,23,12,2006);
//        Cliente cliente=new Cliente("dni","7865","jose","cardozo","mendoza","hombre","20/03/2001",null,direccion,cuenta,credencial);
//        Vehiculo vehiculo1=new Vehiculo("AX12","XD","Todo terreno",4,"Privado", (float) 42.3,2004,cliente);
//        Vehiculo vehiculo2=new Vehiculo("ZZ03","Mancha","Urbano",6,"Privado", (float) 34.2,2001,cliente);
//        Movimiento mov1=new Movimiento("23/12/1995",12,"Recarga","Palmeras",vehiculo1);
//        Movimiento mov2=new Movimiento("29/08/1999",132,"Consumo","Llama",vehiculo2);
//        Movimiento mov3=new Movimiento("14/01/2100", (float) 43.3,"Recarga","ZZZ",vehiculo1);
//          
//        //agregamos los campos sueltos dentro de cliente
//        cliente.getCuenta().setTarjeta(tarjeta);
//        cliente.getCuenta().getVehiculos().registrarVehiculo(vehiculo1);
//        cliente.getCuenta().getVehiculos().registrarVehiculo(vehiculo2);
//        cliente.getCuenta().getMovimientos().registrarMovimiento(mov1);
//        cliente.getCuenta().getMovimientos().registrarMovimiento(mov2);
//        cliente.getCuenta().getMovimientos().registrarMovimiento(mov3);
//        //ahora toca meterlo en base de datos
//            try {
//                conexion=Conexion.conectar();
//                if (conexion.getAutoCommit() == true) {
//                conexion.setAutoCommit(false);
//            }
//                insertStatement=conexion.prepareStatement(INSERT_CREDENCIAL);
//                insertStatement.setString(1,cliente.getCredencial().getCorreo());
//                insertStatement.setString(2,cliente.getCredencial().getContraseña());
//                insertStatement.executeUpdate();
//                
//                insertStatement=conexion.prepareStatement(INSERT_DIRECCION);
//                insertStatement.setString(1,cliente.getDireccion().getDepartamento());
//                insertStatement.setString(2,cliente.getDireccion().getProvincia());
//                insertStatement.setString(3,cliente.getDireccion().getDistrito());
//                insertStatement.setString(4,cliente.getDireccion().getAvenida());
//                insertStatement.setInt(5,cliente.getDireccion().getNumero());
//                insertStatement.setString(6,cliente.getDireccion().getReferencia());
//                insertStatement.setString(7,cliente.getDireccion().getTelefono());
//                insertStatement.executeUpdate();
//                
//                insertStatement=conexion.prepareStatement(INSERT_TARJETA);
//                insertStatement.setString(1,cliente.getCuenta().getTarjeta().getMedioPago());
//                insertStatement.setString(2,cliente.getCuenta().getTarjeta().getNumTarjeta());
//                insertStatement.setString(3,cliente.getCuenta().getTarjeta().getFechaVencimiento());
//                insertStatement.setString(4,cliente.getCuenta().getTarjeta().getCvv());
//                insertStatement.executeUpdate();
//                
//                insertStatement=conexion.prepareStatement(INSERT_CUENTA);
//                insertStatement.setFloat(1,cliente.getCuenta().getSaldoTotal());
//                insertStatement.setFloat(2,cliente.getCuenta().getMonto());
//                insertStatement.setInt(3,cliente.getCuenta().getDiaCreacion());
//                insertStatement.setInt(4,cliente.getCuenta().getMesCreacion());
//                insertStatement.setInt(5,cliente.getCuenta().getAnioCreacion());
//                insertStatement.setString(6,cliente.getCuenta().getTarjeta().getNumTarjeta());
//                insertStatement.executeUpdate();
//                
//                insertStatement=conexion.prepareStatement(INSERT_CLIENTE);
//                insertStatement.setString(1,cliente.getTipoDocumento());
//                insertStatement.setString(2,cliente.getNumDocumento());
//                insertStatement.setString(3,cliente.getNombres());
//                insertStatement.setString(4,cliente.getApPaterno());
//                insertStatement.setString(5,cliente.getApMaterno());
//                insertStatement.setString(6,cliente.getSexo());
//                insertStatement.setString(7,cliente.getFechaNacimiento());
//                insertStatement.setString(8,cliente.getRazonSocial());
//                insertStatement.setInt(9,cliente.getCuenta().getDiaCreacion());
//                insertStatement.setString(10,cliente.getCredencial().getCorreo());
//                insertStatement.setString(11,cliente.getDireccion().getAvenida());
//                insertStatement.executeUpdate();
//                
//                insertStatement=conexion.prepareStatement(INSERT_VEHICULOS);
//                for(int i=0;i<cliente.getCuenta().getVehiculos().getNv();i++){
//                    insertStatement.setString(1,cliente.getCuenta().getVehiculos().getVehiculo(i).getPlaca());
//                    insertStatement.setString(2,cliente.getCuenta().getVehiculos().getVehiculo(i).getMarca());
//                    insertStatement.setString(3,cliente.getCuenta().getVehiculos().getVehiculo(i).getModelo());
//                    insertStatement.setString(4,cliente.getCuenta().getVehiculos().getVehiculo(i).getCategoria());
//                    insertStatement.setInt(5,cliente.getCuenta().getVehiculos().getVehiculo(i).getEjes());
//                    insertStatement.setString(6,cliente.getCuenta().getVehiculos().getVehiculo(i).getTipoUso());
//                    insertStatement.setFloat(7,cliente.getCuenta().getVehiculos().getVehiculo(i).getPesoBruto());
//                    insertStatement.setInt(8,cliente.getCuenta().getVehiculos().getVehiculo(i).getAño());
//                    insertStatement.setString(9,cliente.getNumDocumento());
//                    insertStatement.setInt(10,cliente.getCuenta().getDiaCreacion());
//                    insertStatement.executeUpdate();
//                }
//                insertStatement=conexion.prepareStatement(INSERT_MOVIMIENTOS);
//                for(int j=0;j<cliente.getCuenta().getMovimientos().getNm();j++){
//                    insertStatement.setString(1,cliente.getCuenta().getMovimientos().getMovimiento(j).getFecha());
//                    insertStatement.setFloat(2,cliente.getCuenta().getMovimientos().getMovimiento(j).getMonto());
//                    insertStatement.setString(3,cliente.getCuenta().getMovimientos().getMovimiento(j).getTipo());
//                    insertStatement.setString(4,cliente.getCuenta().getMovimientos().getMovimiento(j).getNombreEstacion());
//                    insertStatement.setString(5,cliente.getCuenta().getMovimientos().getMovimiento(j).getVehiculo().getPlaca());
//                    insertStatement.setInt(6,cliente.getCuenta().getDiaCreacion());
//                    insertStatement.executeUpdate();
//                }
//                insertStatement.close();
//                conexion.commit();
//                conexion.close();
//                System.out.println("GUARDADO CON ÉXITO");
//            } catch (SQLException ex) {
//                   ex.printStackTrace(System.out);
//            try {
//                insertStatement.close();
//                System.out.println("rollback");
//                conexion.rollback();
//                conexion.close();
//            } catch (SQLException ex1) {
//                ex.printStackTrace(System.out);
//            }
//            }

        
        //TODO LO DE ABAJO ES PARA PODER SELECCIONAR=====================================
        PreparedStatement statement = null;
        ResultSet result = null;

        PreparedStatement staVehiculos = null;
        ResultSet resultVehiculos = null;

        PreparedStatement staMov = null;
        ResultSet resultMov = null;
        
        try { //connection
            conexion = Conexion.conectar();
            if (conexion.getAutoCommit() == true) {
                conexion.setAutoCommit(false);
            }
            statement = conexion.prepareStatement(SELECT_MAYORIA); //PreparedStatement
            statement.setString(1, correo);
            statement.setString(2, contra);

            result = statement.executeQuery(); //ResultSet
            //SI DESEO HACER UN ARREGLO DE USUARIOS, SOLO TENDRÍA QUE PONERLE UN WHILE A result.next()
            //DESDE AQUI HASTA LA PARTE FINAL DONDE SE SELECCIONAN MOVIMIENTOS, TODO ESO ENCERRADO Y ALFINAL
            //DICHO OBJETO Cliente LLENO, PASARLO A UN ARREGLO DE CLIENTES. OJO: debemos de quitar el
            //WHERE de SELECT_MAYORIA, ya que tratamos ahora un aspecto más global,
            //LUEGO,CONSIDERANDO el Credencial del cliente actual que se estaría tratando en el while,
            //SE BUSCA EN LA BASE DE DATOS INDICANDO EN EL WHERE dicho correo y contraseña
            result.next();
            //cliente
            persona.setTipoDocumento(result.getString("tipo_documento"));
            persona.setNumDocumento(result.getString("num_doc"));
            persona.setNombres(result.getString("nombres"));
            persona.setApPaterno(result.getString("a_paterno"));
            persona.setApMaterno(result.getString("a_materno"));
            persona.setSexo(result.getString("sexo"));
            persona.setFechaNacimiento(result.getString("fecha_nacimiento"));
            persona.setRazonSocial(result.getString("razon_social"));
            //direccion
            persona.getDireccion().setDepartamento(result.getString("departamento"));
            persona.getDireccion().setProvincia(result.getString("provincia"));
            persona.getDireccion().setDistrito(result.getString("distrito"));
            persona.getDireccion().setAvenida(result.getString("avenida"));
            persona.getDireccion().setNumero(result.getInt("numero"));
            persona.getDireccion().setReferencia(result.getString("referencia"));
            persona.getDireccion().setTelefono(result.getString("telefono"));
            //credencial
            persona.getCredencial().setCorreo(result.getString("correo"));
            persona.getCredencial().setContraseña(result.getString("contrasena"));
            //cuenta 
            persona.getCuenta().setSaldoTotal(result.getFloat("saldo_total"));
            persona.getCuenta().setMonto(result.getFloat("monto"));
            persona.getCuenta().setDiaCreacion(result.getInt("dia_creacion"));
            persona.getCuenta().setMesCreacion(result.getInt("mes_creacion"));
            persona.getCuenta().setAnioCreacion(result.getInt("anio_creacion"));
            //tarjeta
            persona.getCuenta().getTarjeta().setMedioPago(result.getString("medio_pago"));
            persona.getCuenta().getTarjeta().setNumTarjeta(result.getString("num_tarjeta"));
            persona.getCuenta().getTarjeta().setFechaVencimiento(result.getString("fecha_vencimiento"));
            persona.getCuenta().getTarjeta().setCvv(result.getString("cvv"));

            result.close();
            statement.close();

            //AHORA PARA LOS VEHICULOS***********************************
            staVehiculos = conexion.prepareStatement(SELECT_VEHICULOS);
            staVehiculos.setString(1, correo);
            staVehiculos.setString(2, contra);
            resultVehiculos = staVehiculos.executeQuery();
            while (resultVehiculos.next()) {
                String placa = resultVehiculos.getString("placa");
                String marca = resultVehiculos.getString("marca");
                String modelo = resultVehiculos.getString("modelo");
                String categoria = resultVehiculos.getString("categoria");
                int ejes = resultVehiculos.getInt("ejes");
                String tipo_uso = resultVehiculos.getString("tipo_uso");
                float peso_bruto = resultVehiculos.getFloat("peso_bruto");
                int anio = resultVehiculos.getInt("anio");

                Vehiculo vehiculo = new Vehiculo(placa, marca, modelo, categoria, ejes, tipo_uso, peso_bruto, anio, persona);
                persona.getCuenta().getVehiculos().registrarVehiculo(vehiculo);
            }
            resultVehiculos.close();
            staVehiculos.close();

            //AHORA PARA LOS MOVIMIENTOS
            staMov = conexion.prepareStatement(SELECT_MOVIMIENTOS);
            staMov.setString(1, correo);
            staMov.setString(2, contra);
            resultMov = staMov.executeQuery();

            while (resultMov.next()) {
                String fecha = resultMov.getString("fecha");
                float monto = resultMov.getFloat("monto");
                String tipo = resultMov.getString("tipo");
                String nombreEstacion = resultMov.getString("estacion_nombre");
                String placa = resultMov.getString("vehiculo_placa");

                Vehiculo extra = persona.getCuenta().getVehiculos().buscarVehiculo(placa);
                Movimiento mov = new Movimiento(fecha, monto, tipo, nombreEstacion, extra);
                persona.getCuenta().getMovimientos().registrarMovimiento(mov);
            }
            resultMov.close();
            staMov.close();
            //*¨************************************************
            conexion.commit();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                resultMov.close();
                staMov.close();

                resultVehiculos.close();
                staVehiculos.close();
                
                result.close();
                statement.close();
                System.out.println("rollback");
                conexion.rollback();
                conexion.close();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }

        System.out.println("finalizado");
        System.out.println("****PERSONA (y credencial)******");
        System.out.println(persona);
        System.out.println("****DIRECCION******");
        System.out.println(persona.getDireccion());
        System.out.println("****ALGO DE CUENTA******");
        System.out.println(persona.getCuenta().getSaldoTotal());
        System.out.println(persona.getCuenta().getAnioCreacion());
        System.out.println("****TARJETA******");
        System.out.println(persona.getCuenta().getTarjeta());
        System.out.println("****VEHICULOS******"); //las placas corresponden a jose (me da weba crear un metodo de mostrarVehiculos)
        System.out.println(persona.getCuenta().getVehiculos().buscarVehiculo("AX12"));
        System.out.println(persona.getCuenta().getVehiculos().buscarVehiculo("ZZ03"));
        System.out.println("****MOVIMIENTOS******");
        persona.getCuenta().getMovimientos().mostrarMovimientos();
    }
}
