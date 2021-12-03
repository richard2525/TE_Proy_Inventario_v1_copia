package com.emergentes.utiles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validate extends ConexionDB {
    Connection con = this.conectar();
    PreparedStatement pr;
    
    public boolean  ckeckUser(String username, String password)
    {
        boolean resultado = false;
        try {
            
            String sql = "select * from usuario where username=? and  password=sha1(?)";
            
            pr=con.prepareStatement(sql);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            resultado = rs.next();
            
            
        } catch (SQLException ex) {
            //Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ric ERROR al Validarrrrrrr ");
        }
        return resultado;
    }
}
