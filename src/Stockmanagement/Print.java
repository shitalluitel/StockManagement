package Stockmanagement;

import java.awt.print.PrinterException;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Shital
 */
public class Print extends javax.swing.JFrame {
    Connection con;
    Printdisplay obj1 = new Printdisplay();
    /**
     * Creates new form Print
     */
    public Print() throws ClassNotFoundException {
        initComponents();        
              
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagement","root","deependentme");
            String pname;
            String expdate;
            int quantity,squantity;
            double price,total = 0;
            PreparedStatement pst;
            ResultSet rs;
            pst = con.prepareStatement("select * from `calculate`");
            rs = pst.executeQuery();
            text_field.setText("\t\tSrizu Pharma\n");
            text_field.append("\t          Biratnager-05, Chadanichowk\n\n");
            //text_field.append("Product\tQuantity\tPrice\tExp. Date\tID\n");
            text_field.append("ID\tQuantity\tPrice\tExp. Date\tProduct\n");
            text_field.append("......................................................................................................................\n");
            while(rs.next()){
                pname = rs.getString("Product");
                squantity = rs.getInt("Up_Quantity");
                quantity = rs.getInt("Quantity");
                price = rs.getDouble("Price");
                expdate = rs.getString("Expdate");
                if(quantity - squantity >=0){
                    total = total + (price * quantity);
                    text_field.append(rs.getString("ID")+"\t"+quantity+"\t"+price+"\t"+expdate+"\t"+pname+"\n");
                }
            }
            text_field.append("......................................................................................................................\n");
            text_field.append("                                                                                                           Total cost: "+String.valueOf(total));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        obj1.setVisible(true);
        try {
            text_field.print();PreparedStatement pst;
           pst = con.prepareStatement("truncate table `calculate`");
           pst.executeQuery();
        } catch (PrinterException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.err.println(e);
        } 
       
        
        end();
    }
    public void end(){
        obj1.dispose();
        this.dispose();
        Details obj=new Details();
        obj.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        text_field = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(201, 233, 251));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImages(null);

        text_field.setColumns(20);
        text_field.setRows(5);
        text_field.setToolTipText("");
        text_field.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(text_field);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        new Print();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea text_field;
    // End of variables declaration//GEN-END:variables
}
