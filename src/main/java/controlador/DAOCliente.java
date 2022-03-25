
package controlador;

import java.sql.*;
import modelo.Cliente;

public class DAOCliente {
    
    private static final String INSERT="INSERT INTO peaje.cliente (tipo_documento,num_doc,nombres,a_paterno,a_materno,sexo,fecha_nacimiento,razon_social,cuenta_dia,credencial_correo,direccion_avenida) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
    private Connection conexion;
    
    public DAOCliente(Connection conexion){
        this.conexion=conexion;
    }
public Cliente seleccionar(String nDoc) throws SQLException{
    String SELECT="SELECT tipo_documento,num_doc,nombres,a_paterno,a_materno,sexo,fecha_nacimiento,razon_social FROM peaje.cliente WHERE num_doc=?";
    Cliente cliente=null;
    Connection conexion=null;
    PreparedStatement prepSta=null;
    ResultSet result=null;
    try{
    if(this.conexion==null){
        conexion=Conexion.conectar();
    }else{
        conexion=this.conexion;
    }
    prepSta=conexion.prepareStatement(SELECT);
    prepSta.setString(1,nDoc);
    
    result=prepSta.executeQuery();
    result.next();
    String tipodoc=result.getString("tipo_documento");
    String num_doc=result.getString("num_doc");
    String nombres=result.getString("nombres");
    String paterno=result.getString("a_paterno");
    String materno=result.getString("a_materno");
    String sexo=result.getString("sexo");
    String fecha_nacimiento=result.getString("fecha_nacimiento");
    String razon=result.getString("razon_social");
    cliente=new Cliente(tipodoc,num_doc,nombres,paterno,materno,sexo,fecha_nacimiento,razon);
    }
    finally{
        try{
            Conexion.cerrarResultSet(result);
            Conexion.cerrarPreparedStatement(prepSta);
            if(this.conexion==null){
               Conexion.cerrarConnection(conexion);
            }
        }catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
    }
    
    return cliente;
}
public void ingresar(Cliente cliente,int cuenta_dia,String credencial_correo,String direccion_avenida) throws SQLException{
    Connection conexion=null;
    PreparedStatement statement=null;
    try {
            if (this.conexion== null) {
                conexion = Conexion.conectar();
            } else {
                conexion = this.conexion;
            }
            statement = conexion.prepareStatement(INSERT);
            statement.setString(1, cliente.getTipoDocumento());
            statement.setString(2, cliente.getNumDocumento());
            statement.setString(3, cliente.getNombres());
            statement.setString(4, cliente.getApPaterno());
            statement.setString(5, cliente.getApMaterno());
            statement.setString(6, cliente.getSexo());
            statement.setString(7, cliente.getFechaNacimiento());
            statement.setString(8, cliente.getRazonSocial());
            statement.setInt(9, cuenta_dia);
            statement.setString(10,credencial_correo);
            statement.setString(11, direccion_avenida);
            statement.executeUpdate();

        } finally{
         try {
                Conexion.cerrarPreparedStatement(statement);
                if (this.conexion == null) {
                    Conexion.cerrarConnection(conexion);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
    }
}

}
