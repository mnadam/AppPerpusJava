/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public class P_Profile extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql,test,sql_id;
    Timer timer;
    
    String nis = UserSession.getU_nis();
    String nama_siswa = UserSession.getU_nama_siswa();
    String nama_guru = UserSession.getU_nama_guru();
    String username = UserSession.getU_username();
    String telp = UserSession.getU_no_telp();
    String ttl = UserSession.getU_ttl();
    
    /**
     * Creates new form P_Profile
     */
    public P_Profile() {
        initComponents();
        Koneksi DB = new Koneksi();
   
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
       
        Tampil_Data();
        setExtendedState(JFrame.MAXIMIZED_BOTH);  
        
        
        
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Time in 24Hrs Format
                Date date = new Date();
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String time = timeFormat.format(date);
                txt_waktu_hari_ini.setText(time);
                
              
              
                
                 Date date2 = new Date();
                DateFormat timeFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                String time2 = timeFormat2.format(date2);
                txt_hari_ini.setText(time2);
            }
        };
        timer = new Timer(1000, actionListener);
        timer.setInitialDelay(0);
        timer.start();
    }
    
     public void Tampil_Data(){
         try{
        String sql_siswa = "select nis from siswa where nis ='"+nis+"'";
        
        rs = stat.executeQuery(sql_siswa);
     
                                          if ( rs.next()){
                                txt_id.setText(nis);
                                txt_nama.setText(nama_siswa);
                              
                                txt_telp.setText(telp);
                                txt_nama_pengunjung.setText(nama_siswa);
                                txt_ttl.setText(ttl);
                               }

                                          
        String sql_guru = "select username from guru where username ='"+username+"'";
        
        rs = stat.executeQuery(sql_guru);
                                          if (rs.next()){
                                txt_id.setText(username);
                                txt_nama.setText(nama_guru);
                              
                                txt_telp.setText(telp);
                                txt_nama_pengunjung.setText(nama_guru);
                                txt_ttl.setText(ttl);
                               }
        
         }
         
          catch (Exception e) {
    e.printStackTrace();
    
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

        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        txt_nama_siswa8 = new javax.swing.JLabel();
        txt_nis8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_hari_ini = new javax.swing.JLabel();
        txt_waktu_hari_ini = new javax.swing.JLabel();
        txt_nama_pengunjung = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_id = new javax.swing.JLabel();
        txt_nama = new javax.swing.JLabel();
        txt_telp = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_ttl = new java.awt.Label();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PERPUSTAKAAN");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 25, -1, -1));

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jPanel8.setPreferredSize(new java.awt.Dimension(174, 51));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel8MousePressed(evt);
            }
        });
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-dashboard.png"))); // NOI18N
        jPanel8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Dashboard");
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 11, -1, 21));

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 252, 43));

        jPanel10.setBackground(new java.awt.Color(102, 102, 102));
        jPanel10.setPreferredSize(new java.awt.Dimension(174, 51));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-book.png"))); // NOI18N
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Buku");
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 11, -1, 21));

        jPanel4.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 252, 43));

        jPanel16.setBackground(new java.awt.Color(102, 102, 102));
        jPanel16.setPreferredSize(new java.awt.Dimension(174, 51));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-borrow-book.png"))); // NOI18N
        jPanel16.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Transaksi");
        jPanel16.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 11, -1, 21));

        jPanel4.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 141, 252, 43));

        jPanel17.setBackground(new java.awt.Color(102, 102, 102));
        jPanel17.setPreferredSize(new java.awt.Dimension(174, 51));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
        });
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-user.png"))); // NOI18N
        jPanel17.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Profile");
        jPanel17.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 11, -1, 21));

        jPanel4.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 252, 43));

        jPanel19.setBackground(new java.awt.Color(102, 102, 102));
        jPanel19.setPreferredSize(new java.awt.Dimension(174, 51));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel19MouseClicked(evt);
            }
        });
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-logout-rounded-left-25.png"))); // NOI18N
        jPanel19.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Logout");
        jPanel19.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 11, -1, 21));

        jPanel4.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 252, 43));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 889));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setPreferredSize(new java.awt.Dimension(1034, 128));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nama_siswa8.setText("Nama Siswa");
        jPanel25.add(txt_nama_siswa8, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 70, -1, -1));

        txt_nis8.setText("nis");
        jPanel25.add(txt_nis8, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 85, 39, 32));

        jLabel15.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-user.png"))); // NOI18N
        jLabel15.setText("Profile");
        jPanel25.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 20, 171, -1));

        getContentPane().add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 58, 1070, 67));

        txt_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_hari_ini.setText("jLabel8");
        getContentPane().add(txt_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1188, 23, 81, -1));

        txt_waktu_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_waktu_hari_ini.setText("jLabel8");
        getContentPane().add(txt_waktu_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1275, 23, 74, -1));

        txt_nama_pengunjung.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nama_pengunjung.setText("Nama Pengunjung");
        getContentPane().add(txt_nama_pengunjung, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 23, 176, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("User Info");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 22, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/user-image.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 69, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Username / NIS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Nama");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("No Telepon");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        txt_id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_id.setText("jLabel7");
        jPanel1.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, -1, -1));

        txt_nama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_nama.setText("jLabel9");
        jPanel1.add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, -1, -1));

        txt_telp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_telp.setText("jLabel10");
        jPanel1.add(txt_telp, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Tanggal Lahir");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        txt_ttl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txt_ttl.setText("label1");
        jPanel1.add(txt_ttl, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 138, 1070, 493));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Hallo");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 23, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8MousePressed

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        P_Buku bk = new P_Buku();
        this.dispose();
        bk.setVisible(true);
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        P_Profile prf = new P_Profile();
        this.dispose();
        prf.setVisible(true);
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        P_Transaksi trs = new P_Transaksi();
        this.dispose();
        trs.setVisible(true);
    }//GEN-LAST:event_jPanel16MouseClicked

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
         int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin Logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
       if (confirm == 0)  {
        try {
             Login lg = new Login();
             if(username != null){
                 username = null;
             }
                 if(nis != null){
                 nis = null;
             }
             
               
        this.dispose();
        lg.setVisible(true);
        }
        
          catch (Exception e) {
            
            JOptionPane.showMessageDialog(this, e.getMessage() );
                    }
       }
    }//GEN-LAST:event_jPanel19MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        P_Dashboard dsh = new P_Dashboard();
        this.dispose();
        dsh.setVisible(true);
    }//GEN-LAST:event_jPanel8MouseClicked

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
            java.util.logging.Logger.getLogger(P_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P_Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P_Profile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel txt_hari_ini;
    private javax.swing.JLabel txt_id;
    private javax.swing.JLabel txt_nama;
    private javax.swing.JLabel txt_nama_pengunjung;
    private javax.swing.JLabel txt_nama_siswa8;
    private javax.swing.JLabel txt_nis8;
    private javax.swing.JLabel txt_telp;
    private java.awt.Label txt_ttl;
    private javax.swing.JLabel txt_waktu_hari_ini;
    // End of variables declaration//GEN-END:variables
}