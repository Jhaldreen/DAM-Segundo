/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 * @author Eurosystem
 */
public class Conexion {
    static Connection con;    
    static final String USER = "manuel";
    static final String PASS = "1234";
    static final String BD="northwind";
    final String DB_URL = "jdbc:mysql://localhost:3306/"+BD;        
    
    //Constructor que crea la conexion
    public Conexion(){ 
        con=null;
        try {
            con=(Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (Exception ex) {
            System.out.println("Problemas al conectar con la Base de Datos");
        }
    }    
    //Getter de la clase
    public Connection getCon() {
        return con;
    } 
 
}
