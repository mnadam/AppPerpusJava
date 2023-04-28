/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perpustakaan;

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
public class O_Buku extends javax.swing.JFrame {
//    FullScreen frame = new FullScreen(); 
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
     * Creates new form O_Buku
     */
    public O_Buku() {
        initComponents();
        Koneksi DB = new Koneksi();
      
        Locale locale = new Locale ("id", "ID");
        Locale.setDefault(locale);
      
        DB.config();
        con = DB.con;
        stat = DB.stm;
        DataBaseToComboBox();
        txt_id_buku.setEnabled(false);
        id_buku();
        Tampil_Data();
        
        
        //FULL SCREEN CODE
        setExtendedState(JFrame.MAXIMIZED_BOTH);
   

          txt_nama_operator.setText(nama_operator);


// DATE TIME REAL TIME
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


        
    
        
  
    
    
    private void Tampil_Data(){
         
        String cari = txt_search.getText(); 
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel(){
           @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }    
        };
//        JTableHeader Theader = new JTableHeader
        
       
        
        
        model.addColumn("Id Buku");
        model.addColumn("Nama Buku");
        model.addColumn("Kategori");
        model.addColumn("Penulis");
        model.addColumn("Penerbit");
        model.addColumn("Stok");
        
       
        
        
        //menampilkan data database kedalam tabel
        try {
            
            sql = "SELECT * FROM `buku` as a INNER Join kategori as b ON a.id_kategori = b.id_kategori where id_buku like '%"+cari+"%' OR nama_buku like  '%"+cari+"%' OR penulis like  '%"+cari+"%' OR penerbit like  '%"+cari+"%' order by id_buku asc ";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_buku"),
                    rs.getString("nama_buku"),
                    rs.getString("deskripsi"),
                    rs.getString("penulis"),
                    rs.getString("penerbit"),
                    
                    rs.getString("stok")
                        
                });
            }
         btn_edit.setEnabled(false);
         btn_hapus.setEnabled(false);
          id_buku(); 
       
         jTable1.setModel(model);
        
//            System.out.println("xixixi");
        } catch (Exception e) {
            System.out.println(e.getMessage());
           
        }
    }
   
     private void Clear(){
         txt_id_buku.setText(null);
        
         txt_nama_buku.setText(null);
         txt_penerbit.setText(null);
         txt_stok.setText(null);
         txt_penulis.setText(null);
         jComboBox1.setSelectedItem(this);
  }
    
     public void DataBaseToComboBox(){
       
        try {
            sql = "SELECT * FROM kategori";
         
            rs = stat.executeQuery(sql);
            
            while (rs.next()) {                
                jComboBox1.addItem(rs.getString("deskripsi"));
            }
            
//            rs.last();
//            int jumlahdata = rs.getRow();
//            rs.first();
            
        } catch (Exception e) {
        }
    }
     
    public void id_buku(){
        
        String sql = "SELECT id_buku FROM buku";
        try {
            
            rs = stat.executeQuery(sql);
            if(rs.last()){
                txt_id_buku.setText(String.valueOf(rs.getInt(1)+1));
            }
            else
                txt_id_buku.setText("1");
        } catch (Exception e) {
        }
    }  
    
    
  public void filter() {

      DefaultTableModel Fl = new DefaultTableModel();
      
    
}
  
    
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_id_buku = new javax.swing.JTextField();
        txt_nama_buku = new javax.swing.JTextField();
        txt_penerbit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_stok = new javax.swing.JTextField();
        txt_penulis = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_reset = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_search = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_hari_ini = new javax.swing.JLabel();
        txt_waktu_hari_ini = new javax.swing.JLabel();
        txt_nama_operator = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
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
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txt_id_buku, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 28, 198, 30));
        jPanel1.add(txt_nama_buku, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 78, 198, 30));
        jPanel1.add(txt_penerbit, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 179, 198, 30));

        jLabel2.setText("Nama Buku");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 86, -1, -1));

        jLabel3.setText("Kategori");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 135, -1, -1));

        jLabel4.setText("Penulis");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 243, -1, -1));

        jLabel5.setText("Penerbit");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 179, -1, -1));

        jLabel7.setText("Stok");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 33, -1));

        txt_stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_stokActionPerformed(evt);
            }
        });
        txt_stok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stokKeyTyped(evt);
            }
        });
        jPanel1.add(txt_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 198, 30));
        jPanel1.add(txt_penulis, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 235, 198, 30));

        btn_tambah.setBackground(new java.awt.Color(0, 255, 0));
        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-check-mark.png"))); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        jPanel1.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 27, -1, -1));

        btn_hapus.setBackground(new java.awt.Color(255, 0, 0));
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-trash.png"))); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 180, 99, -1));

        btn_edit.setBackground(new java.awt.Color(255, 255, 51));
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-edit.png"))); // NOI18N
        btn_edit.setText(" Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        jPanel1.add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 79, 99, -1));

        jLabel1.setText("Id Buku");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 36, -1, -1));

        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-reset.png"))); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        jPanel1.add(btn_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 128, 99, -1));

        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 127, 198, 30));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 450, 425));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setEditingColumn(0);
        jTable1.setEditingRow(0);
        jTable1.setFocusable(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setShowVerticalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 490, 330));

        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        jPanel2.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 440, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-search.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel10.setText("Masukkan ID / Judul buku / Nama Pengarang / Penerbit");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 307, -1));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 545, 425));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 156, 1070, 500));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-print.png"))); // NOI18N
        jButton2.setText("Cetak Data Buku ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-book.png"))); // NOI18N
        jLabel6.setText(" Data Buku");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 698, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(40, 40, 40))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 61, 1070, -1));

        txt_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_hari_ini.setText("jLabel8");
        getContentPane().add(txt_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1177, 23, 81, -1));

        txt_waktu_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_waktu_hari_ini.setText("jLabel8");
        getContentPane().add(txt_waktu_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1264, 23, 74, -1));

        txt_nama_operator.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nama_operator.setText("Nama OP");
        getContentPane().add(txt_nama_operator, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 91, 23));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Hallo,");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 23, 40, -1));

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

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-man-student.png"))); // NOI18N
        jLabel15.setText("   Siswa");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel15)
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 250, 40));

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

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Profil");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 0, -1, 36));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-user.png"))); // NOI18N
        jPanel7.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jPanel9.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 250, 40));

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 830));

        setBounds(0, 0, 1368, 870);
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed

        
        
        try {
    
      sql_id = "SELECT * FROM kategori where deskripsi='"+jComboBox1.getSelectedItem()+"'";
                    
            rs = stat.executeQuery(sql_id);
                 
            rs.beforeFirst();
            rs.next();
            String id_kat =  rs.getString("id_kategori");
            sql = "update buku SET "
                    + "id_kategori='"+id_kat+"',"
                    + "nama_buku='"+txt_nama_buku.getText()+"',"
                    + "penulis='"+txt_penulis.getText()+"',"
                    + "penerbit='"+txt_penerbit.getText()+"',"
                    + "stok='"+txt_stok.getText()+"' "
                    + "where id_buku='"+txt_id_buku.getText()+"'";
             stat.executeUpdate(sql);
             
    JOptionPane.showMessageDialog(null, "Update Berhasil");
     Clear();
    Tampil_Data();
   
    
    btn_tambah.setEnabled(true);
  } catch (Exception e) {
    e.printStackTrace();
  }
    }//GEN-LAST:event_btn_editActionPerformed

              
    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
      
        if (txt_stok.getText().equals("")){
          JOptionPane.showMessageDialog(null, "Data Stok Tidak Boleh Kosong" , "Pesan", JOptionPane.ERROR_MESSAGE);
      }
        
//                String sql_buku = "SELECT id_buku FROM buku";
//        
//            
//            rs = stat.executeQuery(sql_buku);
//           
//            rs.beforeFirst();
//            rs.next();
//            String id_bu = rs.getString("id_buku");
//            
//          if(txt_id_buku.getText() == id_bu){
//                 JOptionPane.showMessageDialog(null, "Data sudah ada", "Sukses",  JOptionPane.INFORMATION_MESSAGE);
//    
//            }
//        else if() 
//        {
//        
//        }
        else{
            try {
            
          
//        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");  
//        String tgl = fm.format(txt_date.getDate());
//           
       sql_id = "SELECT * FROM kategori where deskripsi='"+jComboBox1.getSelectedItem()+"'";
                    
            rs = stat.executeQuery(sql_id);
                 
            rs.next();
            String id_kat =  rs.getString("id_kategori");
            String sql = "insert into buku values('"
                    +txt_id_buku.getText()+"','"
                    +id_kat+"','"
                    +txt_nama_buku.getText()+"','"
                    +txt_penulis.getText()+"','"
                    +txt_penerbit.getText()+"','"
                    +txt_stok.getText()+"')";
                   
            stat.execute(sql);
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        }  catch (SQLException e) {
             
            JOptionPane.showMessageDialog(this, "ERROR, Id Buku sudah ada", "GAGAL",  JOptionPane.INFORMATION_MESSAGE );
        }
        
        
        Clear();
        Tampil_Data();
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

    
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
  txt_id_buku.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
  txt_nama_buku.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
  jComboBox1.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
 // txt_kategori.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
  txt_penerbit.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
  txt_penulis.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString());
  txt_stok.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
  

  btn_tambah.setEnabled(false);
  btn_edit.setEnabled(true);
  btn_hapus.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
       Clear();
       Tampil_Data();
       
        btn_tambah.setEnabled(true);
        btn_edit.setEnabled(false);
        btn_hapus.setEnabled(false);
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
       
       int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
       if (confirm == 0)  {
        try {
            
        sql = "DELETE FROM buku WHERE id_buku='"+txt_id_buku.getText()+"'";
        int row = jTable1.getSelectedRow();
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
       
        btn_tambah.setEnabled(true);
    }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void txt_stokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stokKeyTyped
       char c = evt.getKeyChar();
       if(!Character.isDigit(c)){
           evt.consume();
       }
    }//GEN-LAST:event_txt_stokKeyTyped

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
//     DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//     String kata = txt_search.getText().trim();
//     TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
//     jTable1.setRowSorter(tr);
//     tr.setRowFilter(RowFilter.regexFilter(kata));
 
//        sql = "SELECT FROM buku kategori where nama like "+txt_search.getText();
        
        
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_stokActionPerformed

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    void setColor(JPanel panel){
 //       panel.setBackgroud(new Color(102,102,102));
    }
        
    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
       Tampil_Data();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            String path = "src/perpustakaan/Report/ReportBuku.jrxml";

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

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        O_Operator OOP = new O_Operator();
        this.dispose();
        OOP.setVisible(true);
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        O_Siswa sswa = new O_Siswa();
        this.dispose();
        sswa.setVisible(true);
    }//GEN-LAST:event_jPanel6MouseClicked

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

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        O_Profile prof = new O_Profile();
        this.dispose();
        prof.setVisible(true);
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked
       
 
    
    
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
            java.util.logging.Logger.getLogger(O_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(O_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(O_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(O_Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new O_Buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txt_hari_ini;
    private javax.swing.JTextField txt_id_buku;
    private javax.swing.JTextField txt_nama_buku;
    private javax.swing.JLabel txt_nama_operator;
    private javax.swing.JTextField txt_penerbit;
    private javax.swing.JTextField txt_penulis;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_stok;
    private javax.swing.JLabel txt_waktu_hari_ini;
    // End of variables declaration//GEN-END:variables
}
