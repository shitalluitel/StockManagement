/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stockmanagement;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 
 * @author Shital
 */
public class Dayover {
    public Dayover(){
        try{
            Connection con;
            Class.forName("con.mysql.jdbc.Driver");
            con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagement","root","deependentme");
            PreparedStatement pst;
            pst = con.prepareStatement("truncate table `today_record`");
            pst.executeQuery();                
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Error "+e);
        }
    }
    
}
