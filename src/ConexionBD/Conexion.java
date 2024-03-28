package ConexionBD;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 *
 * @author PC
 */
public class Conexion {
  public Connection get_connection(){
        Connection conection = null;
        
        try{
              conection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/diccionario","root","");
            if(conection != null){
                System.out.println("Conectado ");
            }
            
        }catch(SQLException e){
        System.out.println(e);
        
        
        
    }
  
        return (Connection) conection;
    }   
    
    
    
}