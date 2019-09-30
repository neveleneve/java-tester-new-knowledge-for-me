/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import connection.dbconnect;
import java.security.Key;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class encryptionTest extends javax.swing.JFrame {

    private static final String algo = "AES";
    private byte[] keyValue;
    public static DefaultTableModel tb_Nasabah ;
    
    public encryptionTest(String key) {
        initComponents();
        keyValue = key.getBytes();  
        fillTableNasabah(jTable1);
    }

    public static void fillTableNasabah(final JTable tbl) {
        tb_Nasabah = new DefaultTableModel();
        tb_Nasabah.addColumn("No. Anggota");
        tb_Nasabah.addColumn("Nama");
        tb_Nasabah.addColumn("Kontak");
        tb_Nasabah.addColumn("Status");
        tb_Nasabah.addColumn("Total Simpanan");
        tb_Nasabah.addColumn("Total Pinjaman");
        tbl.setModel(tb_Nasabah);
        Connection conn = new dbconnect().connect();
        try {
            java.sql.Statement stmt = conn.createStatement();
            String SQL = "select norekening, nama, kontak, status, simpanan, pinjaman from nasabah order by right(norekening, 5) asc";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                tb_Nasabah.addRow(new Object[]{
                    res.getString("norekening"),
                    res.getString("nama"),
                    res.getString("kontak"),
                    res.getString("status"),
                    "Rp. " + NumberFormat.getInstance().format(Float.parseFloat(res.getString("simpanan"))).replace(",", ".") + ",00",
                    "Rp. " + NumberFormat.getInstance().format(Float.parseFloat(res.getString("pinjaman"))).replace(",", ".") + ",00"
                });
            }

            DefaultTableCellRenderer midAligment = new DefaultTableCellRenderer();
            DefaultTableCellRenderer rightAligment = new DefaultTableCellRenderer();
            midAligment.setHorizontalAlignment(JLabel.CENTER);
            rightAligment.setHorizontalAlignment(JLabel.RIGHT);
            tbl.getColumnModel().getColumn(0).setHeaderRenderer(midAligment);
            tbl.getColumnModel().getColumn(1).setHeaderRenderer(midAligment);
            tbl.getColumnModel().getColumn(2).setHeaderRenderer(midAligment);
            tbl.getColumnModel().getColumn(3).setHeaderRenderer(midAligment);
            tbl.getColumnModel().getColumn(4).setHeaderRenderer(midAligment);
            tbl.getColumnModel().getColumn(5).setHeaderRenderer(midAligment);
            tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
            tbl.getColumnModel().getColumn(1).setPreferredWidth(120);
            tbl.getColumnModel().getColumn(2).setPreferredWidth(40);
            tbl.getColumnModel().getColumn(3).setPreferredWidth(20);
            tbl.getColumnModel().getColumn(0).setCellRenderer(midAligment);
            tbl.getColumnModel().getColumn(1).setCellRenderer(midAligment);
            tbl.getColumnModel().getColumn(2).setCellRenderer(midAligment);
            tbl.getColumnModel().getColumn(3).setCellRenderer(midAligment);
            tbl.getColumnModel().getColumn(4).setCellRenderer(rightAligment);
            tbl.getColumnModel().getColumn(5).setCellRenderer(rightAligment);
            tbl.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tbl.getTableHeader().setReorderingAllowed(false);
            tbl.setDefaultEditor(Object.class, null);
        } catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        }
    }
    
    public void ambilGambarKTP(String norekening){
        Connection con = new dbconnect().connect();
        try {
            java.sql.Statement stmt = con.createStatement();
            String SQL = "select DataKTP from nasabah where norekening = '" + norekening + "'";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            if (res.next()) {
                
            }
        } catch (SQLException e) {
        }
    }
    
    public String encrypt(String Data) throws Exception{
        Key key = generateKey();
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] enVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(enVal);
        return encryptedValue;
    }
    
    public String decrypt(String encryptedData) throws Exception{
        Key key = generateKey();
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    
    public Key generateKey() throws Exception{
        Key key = new SecretKeySpec(keyValue, algo);
        return key;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jtWord = new javax.swing.JTextField();
        jlHasil = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(790, 580));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Encrypt!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));
        getContentPane().add(jtWord, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 370, -1));

        jlHasil.setText("None");
        getContentPane().add(jlHasil, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 780, -1));

        jButton2.setText("Decrypt!");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 370, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 670, 200));

        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, 380, 160));

        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 100, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /*try {
            encryptionTest aes = new encryptionTest("lv39eptlvuhaqqs3");
            String firstEncData = aes.encrypt(jtWord.getText());
            String secondEncData = aes.encrypt(firstEncData);
            jlHasil.setText(secondEncData);
        } catch (Exception e) {
            Logger.getLogger(encryptionTest.class.getName()).log(Level.SEVERE, null, e);
        }**/
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            encryptionTest aes = new encryptionTest("lv39eptlvuhaqqs3");
            String firstDecData = aes.decrypt(jlHasil.getText());
            String secondDecData = aes.decrypt(firstDecData);
            jTextField1.setText(secondDecData);
        } catch (Exception e) {
            Logger.getLogger(encryptionTest.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int baris = jTable1.getSelectedRow();
        String norek = jTable1.getValueAt(baris, 0).toString();
        jLabel2.setText(norek);
    }//GEN-LAST:event_jTable1MouseClicked

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(encryptionTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(encryptionTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(encryptionTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(encryptionTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new encryptionTest("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jlHasil;
    private javax.swing.JTextField jtWord;
    // End of variables declaration//GEN-END:variables
}
