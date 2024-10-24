/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idat.pe;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author IDAT
 */
public class ConexionBD {
    
    public static void main(String [] args) throws SQLException{
        Connection con = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        try{
            String objetoBD = "{call usp_persona_crud (?, ?, ?, ?)}";
            con = conexion();
            cst = con.prepareCall(objetoBD);
            cst.setString(1, "Insertar");
            cst.setInt(2, 8);
            cst.setString(3, "Rosa");
            cst.setString(4, "Fuentes");
            //int procesar = cst.executeUpdate();
            //System.out.println("Procesar : " + procesar);
            rs = cst.executeQuery();
            while(rs.next()){
                System.out.println(
                        "Codigo: " + rs.getInt("") + "-" +
                        "Nombre: " + rs.getString("") + "-" +
                        "Apellido: " + rs.getString("") + "-");
            }
            
        }catch(Exception ex){
            System.out.println("Error : " + ex.getMessage());
        }finally{
            rs.close();
            cst.close();
            con.close();
        }
        
    }
    
    public static Connection conexion() throws ClassNotFoundException{
        Connection con = null;
        Class.forName ("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/dsw";
        try{
            con = DriverManager.getConnection(url, "root", "123456");
        }catch(Exception ex){
            System.out.println("conexion-Error: " + ex.getMessage());
        }finally{
                
        }
        
        return con;
        
    }
    
}