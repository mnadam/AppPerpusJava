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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import javax.swing.*;

/**
 *
 * @author User
 */
public class O_Kategori extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql,test,sql_id;
    Timer timer;
    
    String nama_operator = UserSession.getU_nama_operator();
    String id = UserSession.getU_id_operator();
    String nis = UserSession.getU_nis();
    String nama_siswa = UserSession.getU_nama_siswa();
    /**
     * Creates new form O_Kategori
     */
    public O_Kategori() {
        initComponents();
    Koneksi DB = new Koneksi();
   
        DB.config();
        con = DB.con;
        stat = DB.stm;
        
        ID.setEnabled(false);
        id_kategori();
        Tampil_Data();
        setExtendedState(JFrame.MAXIMIZED_BOTH);  
      
        
//         SESSI
          txt_nama_operator8.setText(nama_operator);
//        txt_sessi_id.setText(id);


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
    
//    
//    
//      private void today(){
//   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
//   LocalDateTime now = LocalDateTime.now();  
//   txt_hari_ini.setText((dtf.format(now)));  
//    }
//    
//    private void waktu(){
//        new Thread(){
//            int waktumulai;
//            @Override
//            public void run(){
//                while (waktumulai==0){
//                    Calendar calendar = new GregorianCalendar();
//                    int jam = calendar.get(Calendar.HOUR);
//                    int menit = calendar.get(Calendar.MINUTE);
//                    int detik = calendar.get(Calendar.SECOND);
//                    int AM_PM = calendar.get(Calendar.AM_PM);
//                    String siang_malam = "";
//                    if (AM_PM==1){
//                        siang_malam="PM";
//                    }else{
//                        siang_malam="AM";
//                    }
//                    String time = jam+" : "+menit+" : "+detik;
//                    String day = siang_malam;
//                    txt_waktu_hari_ini.setText(time);
//                    txt_am_pm.setText(day);
//                }
//            }
//        }.start();
//    }
       

    
    private void Tampil_Data(){
         
        
        String cari = txt_cari.getText(); 
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id kategori");
        model.addColumn("Nama kategori");
        
       
        
        
        //menampilkan data database kedalam tabel
        try {
            
            sql = "SELECT * FROM `kategori` where id_kategori like '%"+cari+"%' OR deskripsi like  '%"+cari+"%' order by id_kategori asc  ";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_kategori"),
                    rs.getString("deskripsi"),
                        
                });
            }
            id_kategori();
         btn_edit.setEnabled(false);
         btn_hapus.setEnabled(false);
         jTable1.setModel(model);
            System.out.println("xixixi");
        } catch (Exception e) {
            System.out.println(e.getMessage());
           
        }
    }
   
     private void Clear(){
         ID.setText(null);
        
         kategori.setText(null);
         }
    
     
     
    public void id_kategori(){
        
        String sql = "SELECT id_kategori FROM kategori";
        try {
            
            rs = stat.executeQuery(sql);
            if(rs.last()){
                ID.setText(String.valueOf(rs.getInt(1)+1));
            }
            else
                ID.setText("1");
        } catch (Exception e) {
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

        jMenu5 = new javax.swing.JMenu();
        jPanel22 = new javax.swing.JPanel();
        txt_nama_siswa8 = new javax.swing.JLabel();
        txt_nis8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        ID = new javax.swing.JTextField();
        kategori = new javax.swing.JTextField();
        btn_tambah = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_reset = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        txt_cari = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_hari_ini = new javax.swing.JLabel();
        txt_waktu_hari_ini = new javax.swing.JLabel();
        txt_nama_operator8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
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
        jLabel19 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1359, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setPreferredSize(new java.awt.Dimension(1034, 128));

        txt_nama_siswa8.setText("Nama Siswa");

        txt_nis8.setText("nis");

        jLabel15.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-category.png"))); // NOI18N
        jLabel15.setText("Data Kategori");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(877, Short.MAX_VALUE))
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addGap(744, 744, 744)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt_nis8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_nama_siswa8))
                    .addContainerGap(233, Short.MAX_VALUE)))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel15)
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(txt_nama_siswa8)
                    .addGap(1, 1, 1)
                    .addComponent(txt_nis8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 1070, 67));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1079, 498));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(468, 399));

        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });

        btn_tambah.setBackground(new java.awt.Color(51, 255, 0));
        btn_tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-check-mark.png"))); // NOI18N
        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(255, 255, 0));
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-edit.png"))); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 0, 0));
        btn_hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-trash.png"))); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        jLabel1.setText("KATEGORI");

        jLabel2.setText("ID KATEGORI");

        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-reset.png"))); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(34, 34, 34)
                                .addComponent(kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_tambah)))))
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_tambah)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_edit)
                    .addComponent(kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28)
                .addComponent(btn_reset)
                .addGap(33, 33, 33)
                .addComponent(btn_hapus)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 466, 425));

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.setPreferredSize(new java.awt.Dimension(510, 400));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-search.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel11.setText("Masukkan ID / Nama Kategori");

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

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 545, 425));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 149, 1070, 490));

        txt_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_hari_ini.setText("jLabel8");
        getContentPane().add(txt_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1179, 25, 81, -1));

        txt_waktu_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_waktu_hari_ini.setText("jLabel8");
        getContentPane().add(txt_waktu_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1266, 25, 74, -1));

        txt_nama_operator8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nama_operator8.setText("Nama Op");
        getContentPane().add(txt_nama_operator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 25, 71, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Hallo,");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 25, 37, -1));

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

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-man-student.png"))); // NOI18N
        jLabel19.setText("   Siswa");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel19)
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Profil");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 0, -1, 36));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-user.png"))); // NOI18N
        jPanel7.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, -1, 40));

        jPanel9.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 250, 40));

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
  
            try {
            
          
           
           
           
            String sql = "insert into kategori values('"
                    +ID.getText()+"','"
                    +kategori.getText()+"')";
                    
            stat.execute(sql);
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        }  catch (SQLException e) {
             
            JOptionPane.showMessageDialog(this, "ERROR, Id kategori sudah ada", "GAGAL",  JOptionPane.INFORMATION_MESSAGE );
        }
        Tampil_Data();
        
        Clear();
        id_kategori();
        
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
                                         

        
        
        try {
    
            sql = "update kategori SET "
                    + "deskripsi='"+kategori.getText()+"' "
                    + "where id_kategori='"+ID.getText()+"'";
             stat.executeUpdate(sql);
             
    JOptionPane.showMessageDialog(null, "Update Berhasil");
    
    Tampil_Data();
    Clear();
    id_kategori();
    btn_tambah.setEnabled(true);
  } catch (Exception e) {
    e.printStackTrace();
  }

    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
      
       int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
       if (confirm == 0)  {
        try {
            
        sql = "DELETE FROM kategori WHERE id_kategori='"+ID.getText()+"'";
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
        id_kategori(); 
        btn_tambah.setEnabled(true);
    }
 
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
         Clear();
        Tampil_Data();
        btn_tambah.setEnabled(true);
        btn_edit.setEnabled(false);
        btn_hapus.setEnabled(false);
    }//GEN-LAST:event_btn_resetActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      ID.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
      kategori.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
  
  btn_tambah.setEnabled(false);
  btn_edit.setEnabled(true);
  btn_hapus.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

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

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       Tampil_Data();
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(O_Kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(O_Kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(O_Kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(O_Kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new O_Kategori().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_tambah;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kategori;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JLabel txt_hari_ini;
    private javax.swing.JLabel txt_nama_operator8;
    private javax.swing.JLabel txt_nama_siswa8;
    private javax.swing.JLabel txt_nis8;
    private javax.swing.JLabel txt_waktu_hari_ini;
    // End of variables declaration//GEN-END:variables
}
