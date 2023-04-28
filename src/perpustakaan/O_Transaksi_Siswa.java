/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.io.File;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import java.util.Calendar;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
/**
 *
 * @author Kelana Service
 */
public class O_Transaksi_Siswa extends javax.swing.JFrame {
 Connection con;
    Statement stat;
    ResultSet rs;
    String sql,test,sql_id,status;
    String nama_operator = UserSession.getU_nama_operator();
    String id = UserSession.getU_id_operator();
    DefaultTableModel tabmode;
    JCalendar jcalendar;
    
    Timer timer;
    /**
     * Creates new form O_Tranksais_Siswa
     */
    public O_Transaksi_Siswa() {
        initComponents();
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        //time jakarta
        
        Locale locale = new Locale ("id", "ID");
        Locale.setDefault(locale);
        //Data Combo Box
        DataBaseToComboBoxsiswa();
        DataBaseToComboBoxstatus();
        DataBaseToComboBoxbuku();
//        AutoCompleteDecorator.decorate(cmbo_buku);
//        AutoCompleteDecorator.decorate(cmbo_siswa);
        
        txt_id.setEnabled(false);
        id_transaksi();
        Tampil_Data();
        jPanel11.setVisible(false);
        //Full Screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Real Time
         ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Time in 24Hrs Format
                Date date = new Date();
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String time = timeFormat.format(date);
                txt_waktu.setText(time);
                
                 Date date2 = new Date();
                DateFormat timeFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                String time2 = timeFormat2.format(date2);
                txt_hari.setText(time2);
            }
        };
        timer = new Timer(1000, actionListener);
        timer.setInitialDelay(0);
        timer.start();
    }
private void Tampil_Data(){
       
        String cari = txt_cari.getText(); 
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel(){
           @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }    
        };
        
        
        
        model.addColumn("Kode");
        model.addColumn("Siswa");
        model.addColumn("Judul Buku");
        model.addColumn("Tgl Pinjam");
        model.addColumn("Tgl Kembali");
        model.addColumn("Tgl Transaksi");
        model.addColumn("Status");
        
       
        
        
        //menampilkan data database kedalam tabel
        try {
            
sql = "SELECT a.id_transaksi,b.nama,a.tgl_transaksi,c.keterangan,a.tgl_pinjam,a.tgl_kembali,e.nama_buku FROM `transaksi_siswa`as a \n" +
"INNER JOIN \n" +
"siswa as b ON a.nis = b.nis\n" +
"INNER JOIN \n"+
"status as c on a.id_status = c.id_status "+
"INNER JOIN \n"+
"buku as e on a.id_buku = e.id_buku where a.id_transaksi like '%"+cari+"%' OR b.nama like  '%"+cari+"%' OR e.nama_buku like  '%"+cari+"%' OR c.keterangan like  '%"+cari+"%' order by a.id_status asc ";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("a.id_transaksi"),
                    rs.getString("b.nama"),
                    rs.getString("e.nama_buku"),
                    rs.getString("a.tgl_pinjam"),
                    rs.getString("a.tgl_kembali"),
                    rs.getString("a.tgl_transaksi"),
                    rs.getString("c.keterangan"),
//                    
                        
                });
            }
         id_transaksi();
         btn_edit.setEnabled(false);
         btn_hapus.setEnabled(false);
         date_pinjam.setEnabled(true);
         date_kembali.setEnabled(true);
         cmbo_buku.setEnabled(true);
         
         jTable2.setModel(model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
           
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     private void Clear(){
         txt_id.setText(null);        
         txt_cari.setText(null);
         cmbo_siswa.setSelectedIndex(0);
        
         cmbo_buku.setSelectedIndex(0);
         cmbo_status.setSelectedIndex(0);
         date_kembali.setDate(null);
         date_pinjam.setDate(null);
        
         cmbo_siswa.setEnabled(true);
         cmbo_status.setEnabled(true);
         btn_proses.setEnabled(true);
        
         
  }
//DataBaseToComboBox
    public void DataBaseToComboBoxsiswa(){
       
        try {
            sql = "SELECT * FROM siswa";
         
            rs = stat.executeQuery(sql);
            cmbo_siswa.addItem("");
            while (rs.next()) {                
                cmbo_siswa.addItem(rs.getString("nama"));
            }
            
//            rs.last();
//            int jumlahdata = rs.getRow();
//            rs.first();
            
        } catch (Exception e) {
        }
    }
        
    public void DataBaseToComboBoxstatus(){
       
        try {
            sql = "SELECT * FROM status";
         
            rs = stat.executeQuery(sql);
            
            cmbo_status.addItem("");
            while (rs.next()) {                
                cmbo_status.addItem(rs.getString("keterangan"));
            }
            
//            rs.last();
//            int jumlahdata = rs.getRow();
//            rs.first();
            
        } catch (Exception e) {
        }
    }
    
    
    
   public void DataBaseToComboBoxbuku(){
       
        try {
            sql = "SELECT * FROM buku";
         
            rs = stat.executeQuery(sql);
            cmbo_buku.addItem("");
            while (rs.next()) {                
                cmbo_buku.addItem(rs.getString("nama_buku"));
            }
            
//            rs.last();
//            int jumlahdata = rs.getRow();
//            rs.first();
            
        } catch (Exception e) {
        }
    }
    public void id_transaksi(){
        
        String sql = "SELECT id_transaksi FROM transaksi_siswa";
        try {
            
            rs = stat.executeQuery(sql);
            if(rs.last()){
                txt_id.setText(String.valueOf(rs.getInt(1)+1));
            }
            else
                txt_id.setText("1");
        } catch (Exception e) {
        }
    }

        public void Data_Sessi(){
        
        txt_op.setText(nama_operator);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        txt_op = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        date_dari = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        date_sampai = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_proses = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btn_hapus = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        cmbo_siswa = new javax.swing.JComboBox<>();
        txt_id = new javax.swing.JTextField();
        cmbo_status = new javax.swing.JComboBox<>();
        cmbo_buku = new javax.swing.JComboBox<>();
        date_pinjam = new com.toedter.calendar.JDateChooser();
        date_kembali = new com.toedter.calendar.JDateChooser();
        btn_edit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txt_cari = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_hari = new javax.swing.JLabel();
        txt_waktu = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
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
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Hallo,");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, -1, 54));

        txt_op.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_op.setText("Operator");
        getContentPane().add(txt_op, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 90, 54));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-borrow-book.png"))); // NOI18N
        jLabel11.setText(" Data Transaksi Siswa");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 20, 262, -1));

        jPanel11.setBackground(new java.awt.Color(153, 255, 255));
        jPanel11.setForeground(new java.awt.Color(102, 255, 255));

        jLabel9.setText("Dari");

        jLabel18.setText("Sampai");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-print.png"))); // NOI18N
        jButton2.setText("Cetak  Transaksi Siswa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("FILTER PERIODE LAPORAN ");

        jLabel19.setText(" CLOSE X");
        jLabel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(41, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(date_dari, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addGap(12, 12, 12)
                        .addComponent(date_sampai, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(date_sampai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date_dari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );

        jPanel7.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 590, 80));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons_report.png"))); // NOI18N
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, -1, -1));

        jLabel22.setText("Report");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 30, -1, -1));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 60, 1070, 70));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(424, 323));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel1.setText("ID Transaksi");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 22, 160, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel3.setText("Judul Buku");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 107, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel4.setText("Tanggal Peminjaman");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 145, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel5.setText("Tanggal Pengembalian");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 183, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel6.setText("Status");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 230, -1, -1));

        btn_proses.setBackground(new java.awt.Color(0, 255, 1));
        btn_proses.setFont(new java.awt.Font("NSimSun", 1, 11)); // NOI18N
        btn_proses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-check-mark.png"))); // NOI18N
        btn_proses.setText("Proses");
        btn_proses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prosesActionPerformed(evt);
            }
        });
        jPanel1.add(btn_proses, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, 40));

        btn_batal.setFont(new java.awt.Font("NSimSun", 1, 11)); // NOI18N
        btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-reset.png"))); // NOI18N
        btn_batal.setText("Reset");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        jPanel1.add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 100, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel8.setText("Siswa");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        btn_hapus.setBackground(new java.awt.Color(255, 51, 0));
        btn_hapus.setFont(new java.awt.Font("NSimSun", 1, 11)); // NOI18N
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-trash.png"))); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        jPanel1.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 100, 40));

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbo_siswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbo_siswaActionPerformed(evt);
            }
        });
        jPanel9.add(cmbo_siswa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 226, -1));

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        jPanel9.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 226, -1));

        jPanel9.add(cmbo_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 226, -1));

        jPanel9.add(cmbo_buku, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, 226, -1));
        jPanel9.add(date_pinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 133, 226, -1));
        jPanel9.add(date_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 171, 226, -1));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, 250));

        btn_edit.setBackground(new java.awt.Color(255, 255, 51));
        btn_edit.setFont(new java.awt.Font("NSimSun", 1, 11)); // NOI18N
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-edit.png"))); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        jPanel1.add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 90, 40));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 460, 460));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 21, 440, 30));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 500, 340));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-search.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel15.setText("Masukkan ID / Judul buku / Siswa / Status");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 307, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 560, 460));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 1070, 520));

        txt_hari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_hari.setText("jLabel2");
        getContentPane().add(txt_hari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1187, 19, 80, -1));

        txt_waktu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_waktu.setText("jLabel11");
        getContentPane().add(txt_waktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1277, 19, -1, -1));

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PERPUSTAKAAN");
        jPanel12.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 25, -1, -1));

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

        jPanel12.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 252, 43));

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

        jPanel12.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 252, 43));

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

        jPanel12.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 252, 43));

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

        jPanel12.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 252, 43));

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

        jPanel12.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 250, 43));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Operator");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-operator.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel12.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 250, 40));

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-man-student.png"))); // NOI18N
        jLabel26.setText("   Siswa");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel26)
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 250, 40));

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

        jPanel12.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 252, 43));

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

        jPanel12.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 252, 43));

        jPanel13.setBackground(new java.awt.Color(102, 102, 102));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Profil");
        jPanel13.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 0, -1, 36));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-user.png"))); // NOI18N
        jPanel13.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 250, 40));

        getContentPane().add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

         try {
            String path = "src/perpustakaan/Report/ReportTransaksiSiswa.jrxml";

            
        
            HashMap hash = new HashMap(2);
            Date dari = date_dari.getDate();
            Date sampai = date_sampai.getDate();
          
            
            String cdari = String.valueOf(dari);
            String csampai = String.valueOf(sampai);
           
            hash.put("filter_dari",dari);
            hash.put("filter_sampai",sampai);
           
           
            File file = new File(path);
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            JasperReport report = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint print = JasperFillManager.fillReport(report,hash,con);
            JasperViewer.viewReport(print, false);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal Mencetak Laporan ");
        }          // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void btn_prosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prosesActionPerformed
     
        try {
            
            if (cmbo_siswa.getSelectedItem().toString().trim().equals("") || cmbo_buku.getSelectedItem().toString().trim().equals("") || ((JTextField)date_pinjam.getDateEditor().getUiComponent()).getText().isEmpty() || ((JTextField)date_kembali.getDateEditor().getUiComponent()).getText().isEmpty() || cmbo_status.getSelectedItem().toString().trim().equals("")){
             JOptionPane.showMessageDialog(null, "Pastikan semua data sudah terisi" , "Pesan", JOptionPane.ERROR_MESSAGE);
           
            } else {
                
                       Date date_hari_ini = new Date();
                DateFormat timeFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                String hari_ini = timeFormat2.format(date_hari_ini);
          
          SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        String tgl_pinjam = fm.format(date_pinjam.getDate());
        String tgl_kembali = fm.format(date_kembali.getDate());
      
               
                String sql_siswa = "SELECT * FROM siswa where nama='"+cmbo_siswa.getSelectedItem()+"'";
                String sql_buku = "SELECT * FROM buku where nama_buku='"+cmbo_buku.getSelectedItem()+"'";
                String sql_status = "SELECT * FROM status where keterangan='"+cmbo_status.getSelectedItem()+"'";
                
                rs = stat.executeQuery(sql_siswa);
                rs.next();
                String nis =  rs.getString("nis");
                     
                      
                rs = stat.executeQuery(sql_buku);
                rs.next();
                String id_buku =  rs.getString("id_buku");
                String stokawal =  rs.getString("stok");  
                int cstokawal = Integer.parseInt(stokawal);
                
                
                rs = stat.executeQuery(sql_status);
                rs.next();
                String status =  rs.getString("id_status");
                
                String sql = "insert into transaksi_siswa values('"
                +txt_id.getText()+"','"
                +nis+"','"
                +id_buku+"','"

                +tgl_pinjam+"','"
                +tgl_kembali+"','"
                +status+"','"
                +hari_ini+"')";

                Integer stokakhir = cstokawal - 1;
                String cstokakhir = String.valueOf(stokakhir);
                String sql_buku2 = "update buku SET "

                        + "stok='"+cstokakhir+"'"
                        + "where id_buku='"+id_buku+"'";
             if (stokakhir < 0){
                JOptionPane.showMessageDialog(null, "Stok Buku Kosong!!" , "Pesan", JOptionPane.ERROR_MESSAGE);
            }
            else {
            stat.execute(sql);   
            stat.executeUpdate(sql_buku2);
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            JOptionPane.showMessageDialog(null, "Stok Buku Updated");
            }
             
            }
       }    catch(Exception e)
        {
           JOptionPane.showMessageDialog(this, e.getMessage() );
        }
        

        Clear();
        Tampil_Data();

        //        id_buku();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_prosesActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
       Clear();
       Tampil_Data();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void cmbo_siswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbo_siswaActionPerformed
        if (cmbo_siswa.getSelectedIndex() > 0){
        }
    }//GEN-LAST:event_cmbo_siswaActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        try {
             if (cmbo_siswa.getSelectedItem().toString().trim().equals("") || cmbo_buku.getSelectedItem().toString().trim().equals("") || ((JTextField)date_pinjam.getDateEditor().getUiComponent()).getText().isEmpty() || ((JTextField)date_kembali.getDateEditor().getUiComponent()).getText().isEmpty() || cmbo_status.getSelectedItem().toString().trim().equals("")){
             JOptionPane.showMessageDialog(null, "Pastikan semua data sudah terisi" , "Pesan", JOptionPane.ERROR_MESSAGE);
           
            } else {
            
            String tampilan = ("yyyy-MM-dd");
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tgl_kembali = fm.format(date_kembali.getDate());
            String tgl_pinjam = fm.format(date_pinjam.getDate());
            
                String sql_siswa = "SELECT * FROM siswa where nama='"+cmbo_siswa.getSelectedItem()+"'";
                String sql_buku = "SELECT * FROM buku where nama_buku='"+cmbo_buku.getSelectedItem()+"'";
                String sql_status = "SELECT * FROM status where keterangan='"+cmbo_status.getSelectedItem()+"'";
                
                rs = stat.executeQuery(sql_siswa);
                rs.next();
                String nis =  rs.getString("nis");
                     
//                      SQL BUKU
                rs = stat.executeQuery(sql_buku);
                rs.next();
                String id_buku =  rs.getString("id_buku");
                String stokawal =  rs.getString("stok");  
                int cstokawal = Integer.parseInt(stokawal);     
//                       END SQL BUKU
                
//                      SQL STATUS
                rs = stat.executeQuery(sql_status);
                rs.next();
                String status =  rs.getString("id_status");
                int cstatus = Integer.valueOf(status);
                
                if ((cstatus == 3) || (cstatus == 4)){
                    
                    
                    Integer minstokakhir = cstokawal + 1;
                    String cminstokakhir = String.valueOf(minstokakhir);
                     String sql2 = "update transaksi_siswa SET "
           
            + "nis='"+nis+"',"
            + "id_buku='"+id_buku+"',"
            
            + "tgl_pinjam='"+tgl_pinjam+"',"  
            + "tgl_kembali='"+tgl_kembali+"',"
            + "id_status='"+status+"' "
            + "where id_transaksi='"+txt_id.getText()+"'";
                     
                     String sql_buku2 = "update buku SET "
                   
                    + "stok='"+cminstokakhir+"'"
                    + "where id_buku='"+id_buku+"'";
            stat.executeUpdate(sql2);
            stat.executeUpdate(sql_buku2);
            JOptionPane.showMessageDialog(null, "Update Berhasil");
            JOptionPane.showMessageDialog(null, "Stok Buku Bertambah");
                }
                
                else{
            sql = "update transaksi_siswa SET "
           
            + "nis='"+nis+"',"
            + "id_buku='"+id_buku+"',"
            
            + "tgl_pinjam='"+tgl_pinjam+"',"  
            + "tgl_kembali='"+tgl_kembali+"',"
            + "id_status='"+status+"' "
            + "where id_transaksi='"+txt_id.getText()+"'";
            stat.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Update Berhasil");
                }
            Clear();
            Tampil_Data();

            btn_proses.setEnabled(true);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == 0)  {
            try {

                sql = "DELETE FROM transaksi_siswa WHERE id_transaksi='"+txt_id.getText()+"'";
                int row = jTable2.getSelectedRow();
                if(row  == -1){
                    JOptionPane.showMessageDialog(null, "Data Kosong", "PESAN",  JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    String nama_buku = cmbo_buku.getSelectedItem().toString();
                   
                    String sql_buku = "select id_buku,stok FROM buku WHERE nama_buku='"+nama_buku+"'";
                     rs = stat.executeQuery(sql_buku);
                    rs.next();
                    String id_buku =  rs.getString("id_buku");
                    String stokawal =  rs.getString("stok");  
                    int cstokawal = Integer.parseInt(stokawal); 
                    
                    
                    Integer minstokakhir = cstokawal + 1;
                    String cminstokakhir = String.valueOf(minstokakhir);
                    
                    String sql_buku2 = "update buku SET "
                   
                            + "stok='"+cminstokakhir+"'"
                            + "where id_buku='"+id_buku+"'";
                    
                    stat.executeUpdate(sql_buku2);
                    
                    
                    stat.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Sukses",  JOptionPane.INFORMATION_MESSAGE);

                }
            }
            catch (Exception e) {

                JOptionPane.showMessageDialog(this, e.getMessage() );
            }
            Clear();
            Tampil_Data();

            btn_proses.setEnabled(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
       try{
          
        
        txt_id.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
        cmbo_siswa.setSelectedItem(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
        cmbo_buku.setSelectedItem(jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString());
        
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)jTable2.getValueAt(jTable2.getSelectedRow(), 3));
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse((String)jTable2.getValueAt(jTable2.getSelectedRow(), 4));
        
        date_pinjam.setDate(date);
        date_kembali.setDate(date2);
        cmbo_status.setSelectedItem(jTable2.getValueAt(jTable2.getSelectedRow(), 6).toString());
        
        
        
        if (cmbo_status.getSelectedIndex() == 3 || cmbo_status.getSelectedIndex() == 4 ){
            cmbo_status.setEnabled(false);
            date_pinjam.setEnabled(false);
            date_kembali.setEnabled(false);
            btn_hapus.setEnabled(false);
            btn_edit.setEnabled(false);
        }
        
        else{
             cmbo_status.setEnabled(true);
             date_pinjam.setEnabled(true);
             date_kembali.setEnabled(true);
             btn_edit.setEnabled(true);
             btn_hapus.setEnabled(true);
        }
        
        cmbo_siswa.setEnabled(false);
        cmbo_buku.setEnabled(false);
        btn_proses.setEnabled(false);
       
     
       
       }
         catch (Exception e) {

                JOptionPane.showMessageDialog(this, e.getMessage() );
            }
       
    }//GEN-LAST:event_jTable2MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        Tampil_Data();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
       jPanel11.setVisible(true);
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        jPanel11.setVisible(false);
    }//GEN-LAST:event_jLabel19MouseClicked

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

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24MouseClicked

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
            java.util.logging.Logger.getLogger(O_Transaksi_Siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(O_Transaksi_Siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(O_Transaksi_Siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(O_Transaksi_Siswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new O_Transaksi_Siswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_proses;
    private javax.swing.JComboBox<String> cmbo_buku;
    private javax.swing.JComboBox<String> cmbo_siswa;
    private javax.swing.JComboBox<String> cmbo_status;
    private com.toedter.calendar.JDateChooser date_dari;
    private com.toedter.calendar.JDateChooser date_kembali;
    private com.toedter.calendar.JDateChooser date_pinjam;
    private com.toedter.calendar.JDateChooser date_sampai;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JLabel txt_hari;
    private javax.swing.JTextField txt_id;
    private javax.swing.JLabel txt_op;
    private javax.swing.JLabel txt_waktu;
    // End of variables declaration//GEN-END:variables
}
