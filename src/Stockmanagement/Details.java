/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Stockmanagement;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Shital
 */
public class Details extends javax.swing.JFrame {
    Connection con;
    Connection con1;
    DefaultTableModel dm = null;
    DefaultTableModel dm1 = null;
    double total;
    double total1 = 0;
    int i=1;
    /**
     * Creates new form Details
     */
    public Details() {
        initComponents();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String path =("jdbc:mysql://localhost:3306/stockmanagement");
            con = DriverManager.getConnection(path,"root","deependentme");
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        //trunc();
        clock();
        addcolumn();
        
    }
    
    public void clock(){
        
        Thread clk = new Thread(){
            public void run(){                 
                int day,mth,yer,sec,min,hr;                
                try{
                    while(true){             
                        Calendar cal= new GregorianCalendar();           
                        day = cal.get(Calendar.DAY_OF_MONTH);
                        mth = cal.get(Calendar.MONTH);
                        yer = cal.get(Calendar.YEAR);
                        sec = cal.get(Calendar.SECOND);
                        min= cal.get(Calendar.MINUTE);
                        hr = cal.get(Calendar.HOUR);
                        test_area1.setText("Time: "+hr+":"+min+":"+sec);
                        test_area2.setText("Date:"+yer+"/"+mth+"/"+day);
                    }
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Error "+e);
                }
            }
        };
        clk.start();
    }
    public void addcolumn1(int n){
        try{
            PreparedStatement pst;
            ResultSet rs,rs1;
            switch (n) {
                case 0:
                    pst = con.prepareStatement("select `ID`,`Product Name`,`Stock`,`Sell`,`Price`,`Date`,`Time`,`Expdate` from `total_record`");
                    break;
                case 1:
                    pst = con.prepareStatement("select `ID`,`Product Name`,`Stock`,`Sell`,`Price`,`Date`,`Time`,`Expdate` from `total_record` where `ID`= '"+Integer.parseInt(select_day_over.getText())+"'");
                    break;
                case 2:
                    pst = con.prepareStatement("select `ID`,`Product Name`,`Stock`,`Sell`,`Price`,`Date`,`Time`,`Expdate` from `total_record` where `Product Name`= '"+select_day_over.getText()+"'");
                    break;
                case 3:
                    pst = con.prepareStatement("select `ID`,`Product Name`,`Stock`,`Sell`,`Price`,`Date`,`Time`,`Expdate` from `total_record`");
                    break;
                default:
                    pst = con.prepareStatement("select `ID`,`Product Name`,`Stock`,`Sell`,`Price`,`Date`,`Time`,`Expdate` from `total_record` where `Date`= '"+select_day_over.getText()+"'");
                    break;
            }
            rs = pst.executeQuery();           
            while(rs.next()){
                total1 += rs.getInt("Sell") * rs.getDouble("Price");
            }            
            rs1 = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs1));
            dm1 = (DefaultTableModel) table1.getModel();
            table1.getColumnModel().getColumn(0).setPreferredWidth(65);
            table1.getColumnModel().getColumn(1).setPreferredWidth(265);
            table1.getColumnModel().getColumn(2).setPreferredWidth(110);
            table1.getColumnModel().getColumn(3).setPreferredWidth(110);
            table1.getColumnModel().getColumn(4).setPreferredWidth(160);
            table1.getColumnModel().getColumn(5).setPreferredWidth(155);
            table1.getColumnModel().getColumn(6).setPreferredWidth(155);
            table1.getColumnModel().getColumn(7).setPreferredWidth(180);
            total_record_price.setText(String.valueOf(total1));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error "+e);
        }
    }
    /*
    public void addcolumn2(){
        try{
          PreparedStatement pst;
            ResultSet rs;
            pst = con.prepareStatement("select `ID`,`Product Name`,`Stock`,`Sell`,`Price`,`Date`,`Time`,`Expdate` from `total_record`");
            rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
            dm1 = (DefaultTableModel) table1.getModel();
            total1 = 0;
            total_record_price.setText(String.valueOf(total1));            
        }
        catch(Exception e){
            
        }
    }*/
    public void addcolumn(){
        dm = (DefaultTableModel) table.getModel();
        dm.addColumn("ID");
        dm.addColumn("Product Name");
        dm.addColumn("Quantity");
        dm.addColumn("Price");
        dm.addColumn("Expdate");
        table.getColumnModel().getColumn(0).setPreferredWidth(85);
        table.getColumnModel().getColumn(1).setPreferredWidth(245);
        table.getColumnModel().getColumn(2).setPreferredWidth(155);
        table.getColumnModel().getColumn(3).setPreferredWidth(155);
        table.getColumnModel().getColumn(4).setPreferredWidth(180);
        String i;
        String proname;
        String quantity;
        String price;
        String expdate;
        
        try {
            PreparedStatement pst;
            ResultSet rs;
            pst = con.prepareStatement("select * from `details`");
            rs = pst.executeQuery();
            while(rs.next()){               
                 i = rs.getString("ID");
                  proname = rs.getString("Product Name");
                   quantity = rs.getString("Quantity");
                    price = rs.getString("Price");
                    expdate = rs.getString("Expdate");
                    String[] rowdata = {i,proname,quantity,price,expdate};
                   dm.addRow(rowdata);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        id = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        qnty = new javax.swing.JTextField();
        pname = new javax.swing.JTextField();
        pric = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        buy = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        tcost = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        test_area2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        test_area1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        total_record_price = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        note1 = new javax.swing.JLabel();
        note2 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        search = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        select_day_over = new javax.swing.JTextField();
        combobox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabbedPane1MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBorder(null);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select Section", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabel5.setText("Id");

        pname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pnameKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pnameKeyReleased(evt);
            }
        });

        pric.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pricActionPerformed(evt);
            }
        });

        jLabel4.setText("Price");

        jLabel2.setText("Quantity");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel1.setText("Product Name");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Option", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jButton4.setText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Bill");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        buy.setText("Buy");
        buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buy, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(buy)
                .addGap(7, 7, 7)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jButton4))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qnty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pric, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(6, 6, 6)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(qnty, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Cost", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        tcost.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        tcost.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tcost, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tcost, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Today Date", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel6.setToolTipText("");

        test_area2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        test_area2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(test_area2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(test_area2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Free space", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel9.setToolTipText("");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 164, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Today Time", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        test_area1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        test_area1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(test_area1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(test_area1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Home", jPanel1);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table1MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search and Print", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Transaction", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        total_record_price.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(total_record_price, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(total_record_price, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Note", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(note2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(note1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(note1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(note2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchKeyPressed(evt);
            }
        });

        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        select_day_over.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                select_day_overKeyPressed(evt);
            }
        });

        combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "Product Name", "Date" }));
        combobox.setToolTipText("");
        combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(select_day_over, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(select_day_over, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Day Book", jPanel2);

        jMenu1.setText("File");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Add");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Update");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Delete");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Option");

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Buy");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Bill");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Cancel");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Chage Password");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("About");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("About Us");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        this.dispose();
        Biling obj = new Biling();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Add add = new Add();
        add.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Update update = new Update();
        update.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        Update update = new Update();
        update.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        dispose();
        Changepw obj = new Changepw();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        dispose();
        Helpandsupport obj = new Helpandsupport();
        obj.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    public void show_sort(String str){
        //String str = pname.getText();
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(dm);
        table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(str));
    }    public void buy(){
        try{
            String product = "";
            String expdate="";
            int did = 0;
            int quantity=0;
            int upquantity;
            double price=0;
            PreparedStatement pst;
            ResultSet rs;
            pst = con.prepareStatement("select * from `details` where `Product Name`='"+pname.getText()+"'");
            rs= pst.executeQuery();
            if(rs.next()){
                did= rs.getInt("ID");
                product = rs.getString("Product Name");
                quantity = rs.getInt("Quantity");
                price = rs.getDouble("Price");
                expdate = rs.getString("Expdate");
            }
            upquantity = Integer.parseInt(qnty.getText());
            total += (Double.parseDouble(String.valueOf(upquantity)) * price);
            pric.setText(String.valueOf(price));
            tcost.setText(String.valueOf(total));
            id.setText(String.valueOf(did));

            Statement stmt;
            stmt = con.createStatement();
            stmt.executeUpdate("insert into `calculate`(`ID`,`Product`,`Up_Quantity`,`Price`,`Quantity`,`Expdate`)values('"+did+"','"+product+"','"+upquantity+"','"+price+"','"+quantity+"','"+expdate+"') ");

        }
        catch(SQLException | NumberFormatException e){
            System.err.println("e");
        }
    }
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
           buy();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        try{
            PreparedStatement pst;
            ResultSet rs;
            pst = con.prepareStatement("truncate table `calculate`");
            rs= pst.executeQuery();
            pst.close();
            total = 0;
            pname.setText("");
            qnty.setText("");
            pric.setText("");
            id.setText("");
            tcost.setText("");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error"+e);
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        addcolumn1(0);
        total1=0;
        display_note();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTabbedPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MousePressed
        // TODO add your handling code here:
        addcolumn1(0);
        total1=0;
        display_note();
    }//GEN-LAST:event_jTabbedPane1MousePressed

    private void comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxActionPerformed
        display_note();
    }//GEN-LAST:event_comboboxActionPerformed

    private void select_day_overKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_select_day_overKeyPressed
        list_item(evt);
    }//GEN-LAST:event_select_day_overKeyPressed
    
    public void list_item(java.awt.event.KeyEvent evt){
        try{
            if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                String check = (combobox.getSelectedItem()).toString();
                if(check.equals("Id")){
                    addcolumn1(1);
                }
                if(check.equals("Product Name")){
                    addcolumn1(2);
                }
                if(check.equals("Date")){
                    addcolumn1(4);
                }
            }
            if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                addcolumn1(3);
                search.setText("");
                sort("M");
            }
            total1 =0;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error "+e);
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        sort(search.getText());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        int mrow = table1.getSelectedRow();
        int index = table1.convertRowIndexToModel(mrow);
        String check = (combobox.getSelectedItem()).toString();
        try{
            String id1;
            switch (check) {
                case "Id":
                    id1 = (table1.getModel().getValueAt(index, 0)).toString();
                    break;
                case "Product Name":
                    id1 = (table1.getModel().getValueAt(index, 1)).toString();
                    break;
                default:
                    id1 = (table1.getModel().getValueAt(index, 5)).toString();
                    break;
            }
            select_day_over.setText(id1);
        }
        catch(Exception e){
            System.err.println("Error "+ e );
        }
    }//GEN-LAST:event_table1MouseClicked

    private void table1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MousePressed

    }//GEN-LAST:event_table1MousePressed

    private void buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyActionPerformed
        buy();
        show_sort("/");
    }//GEN-LAST:event_buyActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        Biling obj = new Biling();
        obj.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
            PreparedStatement pst;
            ResultSet rs;
            pst = con.prepareStatement("truncate table `calculate`");
            rs= pst.executeQuery();
            pst.close();
            total = 0;
            pname.setText("");
            qnty.setText("");
            pric.setText("");
            id.setText("");
            tcost.setText("");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error"+e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void pricActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pricActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pricActionPerformed

    private void pnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnameKeyReleased
        show_sort(pname.getText());
    }//GEN-LAST:event_pnameKeyReleased

    private void pnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnameKeyTyped
       // get_selected_key(evt);
    }//GEN-LAST:event_pnameKeyTyped

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        //get_selected_key(evt);
    }//GEN-LAST:event_tableKeyPressed
    public void get_selected_key(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN){
            int mrow = table.getSelectedRow();
            int index = table.convertRowIndexToModel(mrow);
            String id1 = (table.getModel().getValueAt(index, 0)).toString();
            String pname1 = (table.getModel().getValueAt(index, 1)).toString();
            String pric1 = (table.getModel().getValueAt(index, 3)).toString();
            /*try{
                PreparedStatement pst;
                ResultSet rs;
                pst = con.prepareStatement("select * from `details` where `ID` = '"+id1+"'");
                rs = pst.executeQuery();
                if(rs.next()){
                    pname.setText(rs.getString("Product Name"));
                    pric.setText(rs.getString("Price"));
                    id.setText(rs.getString("ID"));

                }
            }
            catch(Exception e){
                System.err.println("Error "+ e );
            }*/
            pname.setText(pname1);
            pric.setText(pric1);
            id.setText(id1);

        }
    }
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int mrow = table.getSelectedRow();
        int index = table.convertRowIndexToModel(mrow);
        String id1 = (table.getModel().getValueAt(index, 0)).toString();
        try{
            PreparedStatement pst;
            ResultSet rs;
            pst = con.prepareStatement("select * from `details` where `ID` = '"+id1+"'");
            rs = pst.executeQuery();
            if(rs.next()){
                pname.setText(rs.getString("Product Name"));
                pric.setText(rs.getString("Price"));
                id.setText(rs.getString("ID"));

            }
        }
        catch(Exception e){
            System.err.println("Error "+ e );
        }
    }//GEN-LAST:event_tableMouseClicked

    private void tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyReleased
        
    }//GEN-LAST:event_tableKeyReleased

    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed
        sort(search.getText());        
    }//GEN-LAST:event_searchKeyPressed
    public void display_note(){
        if(((combobox.getSelectedItem()).toString()).equals("Id")){
            note1.setText("Id must be valid");
        }
        if(((combobox.getSelectedItem()).toString()).equals("Product Name")){
            note1.setText("Name of the item must be ");
            note2.setText("starting with capital letter.");
        }
        if(((combobox.getSelectedItem()).toString()).equals("Date")){
            note1.setText("yyyy/mm/dd must be the format of ");
            note2.setText("the date that you are going to enter.");
        }
    }    public void sort(String str){
        //String str = search.getText();
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(dm1);
        table1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(str));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buy;
    private javax.swing.JComboBox<String> combobox;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel note1;
    private javax.swing.JLabel note2;
    private javax.swing.JTextField pname;
    private javax.swing.JTextField pric;
    private javax.swing.JTextField qnty;
    private javax.swing.JTextField search;
    private javax.swing.JTextField select_day_over;
    private javax.swing.JTable table;
    private javax.swing.JTable table1;
    private javax.swing.JLabel tcost;
    private javax.swing.JLabel test_area1;
    private javax.swing.JLabel test_area2;
    private javax.swing.JLabel total_record_price;
    // End of variables declaration//GEN-END:variables
}
