/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perpustakaan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.HashMap;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.util.GregorianCalendar;
import javax.swing.Timer;
/**
 *
 * @author User
 */
public class O_Guru extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
   
    String sql,test,sql_id;
    String nama_operator = UserSession.getU_nama_operator();
    String id = UserSession.getU_id_operator();
    String nis = UserSession.getU_nis();
    String nama_siswa = UserSession.getU_nama_siswa();
    Timer timer;
    /**
     * Creates new form O_Guru
     */
    public O_Guru() {
        initComponents();
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
       
        Locale locale = new Locale ("id", "ID");
        Locale.setDefault(locale);
        
        Tampil_Data();
    
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        txt_nama_operator.setText(nama_operator);
        
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
protected void kosong () {
        txt_username.setText("");
        txt_nama.setText("");
        txt_password.setText("");
//        ttl.setText("");
        txt_telp.setText("");
    }
 private void Tampil_Data(){
         
        String cari = txt_cari1.getText(); 
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Username");
        model.addColumn("password");
        model.addColumn("nama");
        model.addColumn("TTL");
        model.addColumn("Telp");
      
        
       
        
        
        //menampilkan data database kedalam tabel
        try {
            
            sql = "SELECT * FROM `guru`  where username like '%"+cari+"%' OR nama like  '%"+cari+"%' order by username asc ";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nama"),
                    rs.getString("ttl"),
                    rs.getString("no_telp")
                        
                });
            }
         edit.setEnabled(false);
         hapus.setEnabled(false);
        
         jTable3.setModel(model);
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
           
        }
    }
 private void Clear(){
         txt_username.setText(null);
        
         txt_nama.setText(null);
         txt_ttl.setDate(null);
         txt_telp.setText(null);
         txt_password.setText(null);
         
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jLabel9 = new javax.swing.JLabel();
        txt_nama_operator = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txt_nama_siswa = new javax.swing.JLabel();
        txt_nis = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_hari_ini = new javax.swing.JLabel();
        txt_waktu_hari_ini = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tambah = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txt_username = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        txt_telp = new javax.swing.JTextField();
        txt_ttl = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        txt_cari1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
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
        jLabel35 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();

        jMenu3.setText("jMenu3");

        jMenu7.setText("jMenu7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Hallo,");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 40, 60));

        txt_nama_operator.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nama_operator.setText("Nama OP");
        getContentPane().add(txt_nama_operator, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 91, 60));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nama_siswa.setText("Nama Siswa");
        jPanel5.add(txt_nama_siswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 70, -1, -1));

        txt_nis.setText("nis");
        jPanel5.add(txt_nis, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 85, 39, 32));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-print.png"))); // NOI18N
        jButton2.setText("Cetak Data Guru");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 150, 42));

        jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-man-teacher.png"))); // NOI18N
        jLabel6.setText(" Data Guru");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 14, 171, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 61, 1060, 64));

        txt_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_hari_ini.setText("jLabel8");
        getContentPane().add(txt_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, -3, 80, 60));

        txt_waktu_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_waktu_hari_ini.setText("jLabel8");
        getContentPane().add(txt_waktu_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1264, -3, 60, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Username");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 104, 36));

        jLabel5.setText("Password");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 104, 38));

        jLabel2.setText("Nama");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 82, 35));

        jLabel3.setText("Tanggal lahir");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 96, 50));

        jLabel4.setText("No Telp");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 233, 96, 53));

        tambah.setBackground(java.awt.Color.green);
        tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-check-mark.png"))); // NOI18N
        tambah.setText("Tambah");
        tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahMouseClicked(evt);
            }
        });
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel1.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 110, 40));

        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-reset.png"))); // NOI18N
        reset.setText("Reset");
        reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetMouseClicked(evt);
            }
        });
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        jPanel1.add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 95, 40));

        edit.setBackground(java.awt.Color.yellow);
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons-edit.png"))); // NOI18N
        edit.setText("Edit");
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMouseClicked(evt);
            }
        });
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel1.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 97, 40));

        hapus.setBackground(java.awt.Color.red);
        hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-trash.png"))); // NOI18N
        hapus.setText("Hapus");
        hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusMouseClicked(evt);
            }
        });
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel1.add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 90, 40));

        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });

        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_username)
                            .addComponent(txt_ttl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(txt_telp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_password, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(txt_nama)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_ttl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_telp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 330, 260));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 28, 469, 420));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-search.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel10.setText("Masukkan Username / Nama ");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_cari1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(60, 60, 60))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_cari1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(531, 28, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 1060, 480));

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PERPUSTAKAAN");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 25, -1, -1));

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

        jPanel9.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 252, 43));

        jPanel10.setBackground(new java.awt.Color(102, 102, 102));
        jPanel10.setPreferredSize(new java.awt.Dimension(174, 51));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-category.png"))); // NOI18N
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Kategori");
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 11, -1, 21));

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 252, 43));

        jPanel16.setBackground(new java.awt.Color(102, 102, 102));
        jPanel16.setPreferredSize(new java.awt.Dimension(174, 51));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-book.png"))); // NOI18N
        jPanel16.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Buku");
        jPanel16.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 11, -1, 21));

        jPanel9.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 252, 43));

        jPanel17.setBackground(new java.awt.Color(102, 102, 102));
        jPanel17.setPreferredSize(new java.awt.Dimension(174, 51));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
        });
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-man-teacher.png"))); // NOI18N
        jLabel35.setText("  Guru");
        jPanel17.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 21));

        jPanel9.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 252, 43));

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

        jPanel9.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 250, 43));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Operator");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-operator.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 250, 40));

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-man-student.png"))); // NOI18N
        jLabel15.setText("   Siswa");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel15)
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 250, 40));

        jPanel18.setBackground(new java.awt.Color(102, 102, 102));
        jPanel18.setPreferredSize(new java.awt.Dimension(174, 51));
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel18MouseClicked(evt);
            }
        });
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-borrow-book.png"))); // NOI18N
        jPanel18.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Transaksi Siswa");
        jPanel18.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 11, -1, 21));

        jPanel9.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 252, 43));

        jPanel20.setBackground(new java.awt.Color(102, 102, 102));
        jPanel20.setPreferredSize(new java.awt.Dimension(174, 51));
        jPanel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel20MouseClicked(evt);
            }
        });
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-borrow-book.png"))); // NOI18N
        jPanel20.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Transaksi Guru");
        jPanel20.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 11, -1, 21));

        jPanel9.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 252, 43));

        jPanel13.setBackground(new java.awt.Color(102, 102, 102));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Profil");
        jPanel13.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 0, -1, 36));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-user.png"))); // NOI18N
        jPanel13.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jPanel9.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 250, 40));

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            String path = "src/perpustakaan/Report/ReportGuru.jrxml";

            //  JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            File file = new File(path);
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            JasperReport report = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint print = JasperFillManager.fillReport(report,null,con);
            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal Mencetak Laporan ");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahMouseClicked

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
       
  
        
        try{
            
         
  if(((JTextField)txt_ttl.getDateEditor().getUiComponent()).getText().isEmpty() || txt_password.getText().isEmpty() || txt_username.getText().isEmpty()){
                    
                JOptionPane.showMessageDialog(null,"Username, password , tanggal lahir tidak boleh kosong!!!");
                }
  else {
            
        String tampilan = ("yyyy-MM-dd");
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tgl = fm.format(txt_ttl.getDate());

          LocalDateTime myDateObj = LocalDateTime.now();
             
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                String formattedDate = myDateObj.format(myFormatObj);
        
            
            String cek = "select username from guru where username = '"+txt_username.getText()+"'";
            rs = stat.executeQuery(cek);
            if (rs.next()){
           
                JOptionPane.showMessageDialog(null,"Username sudah tedaftar");
            }
            else{
               
            String sql = "insert into guru values('"
            +txt_username.getText()+"','"
            +txt_nama.getText()+"','"

            +tgl+"','"
            +txt_telp.getText()+"','"     
            +txt_password.getText()+"','"
            +formattedDate+"','"
            +('1')+"')";

            stat.execute(sql);
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            kosong();
            txt_username.requestFocus();
                }
        }
  } 
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Data gagal disimpan"+e);
        }

        Tampil_Data();
    }//GEN-LAST:event_tambahActionPerformed

    private void resetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_resetMouseClicked

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        Clear();
        Tampil_Data();

        tambah.setEnabled(true);
        edit.setEnabled(false);
        hapus.setEnabled(false);
        txt_username.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_resetActionPerformed

    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_editMouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed

        
        
        try {
            
        if(((JTextField)txt_ttl.getDateEditor().getUiComponent()).getText().isEmpty() || txt_password.getText().isEmpty() || txt_username.getText().isEmpty()){
                    
                JOptionPane.showMessageDialog(null,"Username, password , tanggal lahir tidak boleh kosong!!!");
                }     
        else {
            String tampilan = ("yyyy-MM-dd");
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tgl = fm.format(txt_ttl.getDate());

            sql = "update guru SET "

            + "nama='"+txt_nama.getText()+"',"
            + "ttl='"+tgl+"',"
            + "no_telp='"+txt_telp.getText()+"',"
            + "password='"+txt_password.getText()+"'"
            +"where username='"+txt_username.getText()+"'";

            stat.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Update Berhasil");

            Tampil_Data();
            Clear();

            tambah.setEnabled(true);
        }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_editActionPerformed

    private void hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusMouseClicked

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == 0)  {
            try {

                sql = "DELETE FROM guru WHERE username='"+txt_username.getText()+"'";
                int row = jTable3.getSelectedRow();
                if(row  == -1){
                    JOptionPane.showMessageDialog(null, "Data Kosong", "PESAN",  JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    stat.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Sukses",  JOptionPane.INFORMATION_MESSAGE);

                }
            }
            catch (Exception e) {

                JOptionPane.showMessageDialog(this, e.getMessage() );
            }
            Clear();
            Tampil_Data();

            tambah.setEnabled(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        try {
            txt_username.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString());
            txt_password.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 1).toString());
            txt_nama.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 2).toString());
            //txt_ttl.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 4).toString());

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)jTable3.getValueAt(jTable3.getSelectedRow(), 3));
            txt_ttl.setDate(date);
            txt_telp.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 4).toString());
        }
        catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage() );
        }
        txt_username.setEnabled(false);  
        tambah.setEnabled(false);
        edit.setEnabled(true);
        hapus.setEnabled(true);
    }//GEN-LAST:event_jTable3MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        Tampil_Data();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        O_Dashboard dash = new O_Dashboard();
        this.dispose();
        dash.setVisible(true);
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8MousePressed

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        O_Kategori kt = new O_Kategori();
        this.dispose();
        kt.setVisible(true);
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        O_Buku bk = new O_Buku();
        this.dispose();
        bk.setVisible(true);
    }//GEN-LAST:event_jPanel16MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        O_Guru guru = new O_Guru();
        this.dispose();
        guru.setVisible(true);
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin Logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == 0)  {
            try {
                Login lg = new Login();
                this.dispose();
                lg.setVisible(true);
            }

            catch (Exception e) {

                JOptionPane.showMessageDialog(this, e.getMessage() );
            }
        }
    }//GEN-LAST:event_jPanel19MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        O_Operator OOP = new O_Operator();
        this.dispose();
        OOP.setVisible(true);
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        O_Siswa sswa = new O_Siswa();
        this.dispose();
        sswa.setVisible(true);
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel18MouseClicked
        O_Transaksi_Siswa otrsswa = new O_Transaksi_Siswa();
        this.dispose();
        otrsswa.setVisible(true);
    }//GEN-LAST:event_jPanel18MouseClicked

    private void jPanel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel20MouseClicked
        O_Transaksi_Guru otrg = new O_Transaksi_Guru();
        this.dispose();
        otrg.setVisible(true);
    }//GEN-LAST:event_jPanel20MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        O_Profile prof = new O_Profile();
        this.dispose();
        prof.setVisible(true);
    }//GEN-LAST:event_jPanel13MouseClicked

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
            java.util.logging.Logger.getLogger(O_Guru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(O_Guru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(O_Guru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(O_Guru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new O_Guru().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton reset;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField txt_cari1;
    private javax.swing.JLabel txt_hari_ini;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JLabel txt_nama_operator;
    private javax.swing.JLabel txt_nama_siswa;
    private javax.swing.JLabel txt_nis;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_telp;
    private com.toedter.calendar.JDateChooser txt_ttl;
    private javax.swing.JTextField txt_username;
    private javax.swing.JLabel txt_waktu_hari_ini;
    // End of variables declaration//GEN-END:variables
}
