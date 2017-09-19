/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stockmanagement;

 import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
/**
 *
 * @author Shital
 */

public class today_record {
    Connection con ;
    
    String pname,expdate;
    String date;
    String time;
    String am_pm;
    int squan,quan,id;
    double price;
    public today_record(){        
    }
    public void transfer(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagement","root","deependentme");
            Calendar cal = new GregorianCalendar();
            int day=cal.get(Calendar.DAY_OF_MONTH);
            int month=cal.get(Calendar.MONTH);    
            int year=cal.get(Calendar.YEAR);    
            int sec=cal.get(Calendar.SECOND);    
            int min=cal.get(Calendar.MINUTE);    
            int hr=cal.get(Calendar.HOUR_OF_DAY);  
            int set=cal.get(Calendar.AM_PM);
            if (set == 0){
                am_pm = "AM";
            }
            if (set == 1){
                am_pm = "PM";
            }
            date = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
            time = String.valueOf(hr)+"-"+String.valueOf(min)+"-"+String.valueOf(sec)+" "+am_pm;                    
            PreparedStatement pst;
            ResultSet rs;
            pst=con.prepareStatement("select * from `calculate`");            
            rs = pst.executeQuery();
                   
            while(rs.next()){
                pname=rs.getString("Product");
                squan = rs.getInt("Up_Quantity");
                quan = rs.getInt("Quantity");
                id = rs.getInt("ID");
                price = rs.getInt("Price");
                expdate = rs.getString("Expdate");
                if((quan - squan)>=0){
                    Statement pst1;
                    pst1=con.createStatement();
                    pst1.executeUpdate("insert into `today_record`(`ID`, `Product Name`, `Stock`, `Sell`, `Price`, `Date`, `Time`,`Expdate`) values ('"+id+"','"+pname+"','"+quan+"','"+squan+"','"+price+"','"+date+"','"+time+"','"+expdate+"')");
                    Statement pst2;
                    pst2=con.createStatement();
                    pst2.executeUpdate("insert into `total_record`(`ID`, `Product Name`, `Stock`, `Sell`, `Price`, `Date`, `Time`,`Expdate`) values ('"+id+"','"+pname+"','"+quan+"','"+squan+"','"+price+"','"+date+"','"+time+"','"+expdate+"')");
                
                }
            }
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"Today_Function Error "+e);
        }
    }
}
