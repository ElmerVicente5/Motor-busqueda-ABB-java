package ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import arbolBinarioBusqueda.arbolBinarioDeBusqueda;



public class DAO {
	
	public static void insertarPalabra(String palabra,String definicion)
	{
	
		  Conexion db_connect = new Conexion();
	       PreparedStatement ps = null;
	       try(Connection Conexion = db_connect.get_connection()){
	           try{
	               String query = "INSERT INTO `palabras` (palabra,definicion) VALUES (?,?)";
	               ps = Conexion.prepareStatement(query);
	               ps.setString(1,palabra);
	               ps.setString(2,definicion);
	              
	               ps.executeUpdate();
	               System.out.println("palabra agregado exitosamente");
	               
	           }catch(SQLException x){
	               System.out.println(x);
	           }
	       }catch(SQLException e){
	           System.out.println(e);
	       }
		
	}
	
	public static void llenarArbol(int opc, arbolBinarioDeBusqueda arbol)
	{
		 Conexion db_conexion = new Conexion();
	        
	        PreparedStatement ps = null;
	        
	        ResultSet rs = null;
	        
	        try(Connection Conexion = db_conexion.get_connection()){
	        
	            
	                String query = "SELECT * FROM `palabras`";
	                ps = Conexion.prepareStatement(query);
	                rs = ps.executeQuery();
	                
	                if(opc==1)
	                {
	                	 while(rs.next()){
	 	                	arbol.llenarArboldesdeBD(rs.getString("palabra"),rs.getString("definicion"));
	 	                	/*
	 	                	
	 	                   
	 	                    System.out.println("dpi: "+rs.getString("dpiCliente"));
	 	                    System.out.println("temperatura: "+rs.getString("temperatura"));
	 	                    System.out.println("numero de sala: "+rs.getString("sala"));
	 	                    System.out.println("numero de asiento: "+rs.getString("numAsiento"));
	 	                    */
	 	                }	
	                }else if(opc==2)
	                {
	                	while(rs.next()){
	                	 System.out.print(rs.getString("palabra")+": ");
	 	                 System.out.print(rs.getString("definicion"));
	 	                 System.out.println();
	 	                System.out.println();
	                	}
	                }
	               
	      
	            
	        }catch(SQLException e){
	            System.out.println(e);
	        } 
	}
	
	 public static void borrarPalabraDB(String palabra){
         PreparedStatement ps = null;
           Conexion db_connect = new Conexion();
        
         try(Connection Conexion = db_connect.get_connection()){
           try{
               String query =  "DELETE FROM palabras WHERE `palabra` = ?";
               ps = Conexion.prepareStatement(query);
               ps.setString(1, palabra);
              
               ps.executeUpdate();
               System.out.println("Palabra eliminada correctamente");
               
           }catch(SQLException x){
               System.out.println(x);
               System.out.println("No se pudo Borrar la palabra");
           }
       }catch(SQLException e){
           System.out.println(e);
       }
        
        
    }
	 
	 public static void actualizarMensajeDB(String newDefinition){
	        
	      
	        PreparedStatement ps = null;
	           Conexion db_connect = new Conexion();
	        
	        try(Connection Conexion = db_connect.get_connection()){
	           try{
	               String query =  "UPDATE `palabras` SET `definicion` = ? WHERE `palabras`.`id_mensaje` = ?";
	               ps = Conexion.prepareStatement(query);
	              
	               ps.setString(1, newDefinition);
	               
	               
	               
	               ps.executeUpdate();
	               System.out.println("Mensaje Actualizado");
	               
	           }catch(SQLException x){
	               System.out.println(x);
	               System.out.println("No se pudo Editar el mensaje el mensaje");
	           }
	       }catch(SQLException e){
	           System.out.println(e);
	       }
	    }
	
	
	

}
