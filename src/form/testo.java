/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import connection.dbconnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author NEVE
 */
public class testo extends javax.swing.JFrame {

    public String SQL, kodprov, nameprov, kodkot, namekot, kodcam, namecam, kodkel, namekel;
    public Connection con = new dbconnect().connect();
    public ResultSet rs;
    public Statement stm;

    public testo() {
        initComponents();
        fillcbprov();
    }

    public void fillcbprov() {
        try {
            SQL = "select name from provinces";
            stm = con.createStatement();
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                nameprov = rs.getString("name");
                cbprov.addItem(nameprov);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void fillcbkota() {
        try {
            SQL = "select name from regencies where province_id = (select id from provinces where name = '" + cbprov.getSelectedItem().toString() + "')";
            stm = con.createStatement();
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                namekot = rs.getString("name");
                cbkota.addItem(namekot);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void fillcbkecamatan() {
        try {
            SQL = "select name from districts where regency_id = (select id from regencies where name = '" + cbkota.getSelectedItem().toString() + "')";
            stm = con.createStatement();
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                namecam = rs.getString("name");
                cbcamat.addItem(namecam);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void fillcbkelurahan() {
        try {
            SQL = "select name from villages where district_id = (select id from districts where name = '" + cbcamat.getSelectedItem().toString() + "')";
            stm = con.createStatement();
            rs = stm.executeQuery(SQL);
            while (rs.next()) {
                namekel = rs.getString("name");
                cblurah.addItem(namekel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cblurah = new javax.swing.JComboBox<>();
        cbcamat = new javax.swing.JComboBox<>();
        cbkota = new javax.swing.JComboBox<>();
        cbprov = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cblurah.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH KELURAHAN" }));
        cblurah.setEnabled(false);
        cblurah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cblurahMouseEntered(evt);
            }
        });
        jPanel1.add(cblurah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 360, 30));

        cbcamat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH KECAMATAN" }));
        cbcamat.setEnabled(false);
        cbcamat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbcamatItemStateChanged(evt);
            }
        });
        cbcamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbcamatMouseEntered(evt);
            }
        });
        jPanel1.add(cbcamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 360, 30));

        cbkota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH KABUPATEN/KOTA" }));
        cbkota.setEnabled(false);
        cbkota.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbkotaItemStateChanged(evt);
            }
        });
        cbkota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbkotaMouseEntered(evt);
            }
        });
        jPanel1.add(cbkota, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 360, 30));

        cbprov.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH PROVINSI" }));
        cbprov.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbprovItemStateChanged(evt);
            }
        });
        jPanel1.add(cbprov, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 360, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbprovItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbprovItemStateChanged
        if (cbprov.getSelectedIndex() == 0) {
            cbkota.setEnabled(false);
        } else {
            cbkota.setEnabled(true);
            cbkota.setSelectedIndex(0);
            cbcamat.setSelectedIndex(0);
            cblurah.setSelectedIndex(0);            
            System.out.println("data provinsi terpilih");
        }
    }//GEN-LAST:event_cbprovItemStateChanged

    private void cbkotaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbkotaMouseEntered
        if ("".equals(kodprov)) {

        } else {
            if (cbkota.getSelectedIndex() == 0) {
                cbkota.removeAllItems();
                System.out.println("data kota terinput");
                cbkota.addItem("PILIH KABUPATEN/KOTA");
                fillcbkota();
            }
        }
    }//GEN-LAST:event_cbkotaMouseEntered

    private void cbkotaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbkotaItemStateChanged
        if (cbkota.getSelectedIndex() == 0) {
            cbcamat.setEnabled(false);
        } else {
            cbcamat.setEnabled(true);
            cbcamat.setSelectedIndex(0);
            System.out.println("data kota terpilih");
        }
    }//GEN-LAST:event_cbkotaItemStateChanged

    private void cbcamatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbcamatMouseEntered
        if ("".equals(kodkot)) {

        } else {
            if (cbcamat.getSelectedIndex() == 0) {
                cbcamat.removeAllItems();
                System.out.println("data kecamatan terinput");
                cbcamat.addItem("PILIH KECAMATAN");
                fillcbkecamatan();
            }
        }
    }//GEN-LAST:event_cbcamatMouseEntered

    private void cbcamatItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbcamatItemStateChanged
        if (cbcamat.getSelectedIndex() == 0) {
            cblurah.setEnabled(false);
        } else {
            cblurah.setEnabled(true);
            cblurah.setSelectedIndex(0);
            System.out.println("data kecamatan terpilih");
        }
    }//GEN-LAST:event_cbcamatItemStateChanged

    private void cblurahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cblurahMouseEntered
        if ("".equals(kodcam)) {

        } else {
            if (cblurah.getSelectedIndex() == 0) {
                cblurah.removeAllItems();
                System.out.println("data lurah terinput");
                cblurah.addItem("PILIH KELURAHAN");
                fillcbkelurahan();
            }
        }
    }//GEN-LAST:event_cblurahMouseEntered

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
            java.util.logging.Logger.getLogger(testo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbcamat;
    private javax.swing.JComboBox<String> cbkota;
    private javax.swing.JComboBox<String> cblurah;
    private javax.swing.JComboBox<String> cbprov;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
