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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.HashMap;
import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author User
 */


    

public class P_Buku extends javax.swing.JFrame {
//    FullScreen frame = new FullScreen(); 
    Connection con;
    Statement stat;
    static ResultSet rs;
    String sql, test,sql_id;
    String nama_guru = UserSession.getU_nama_guru();
    String username = UserSession.getU_username();
    String nis = UserSession.getU_nis();
    String nama_siswa = UserSession.getU_nama_siswa();
    Timer timer;
      
        
    
    /**
     * Creates new form P_Buku
     */
    
 
    public P_Buku () {
        initComponents();
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        Tampil_Data();
        Data_Sessi();
        setExtendedState(JFrame.MAXIMIZED_BOTH);  
       
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
    
    
    public static void AddRowToJTable(Object[] dataRow)
    {
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.addRow(dataRow);
            DefaultTableCellRenderer dtr = new DefaultTableCellRenderer();
            dtr.setHorizontalAlignment(JLabel.CENTER);
            DefaultTableCellRenderer dti = new DefaultTableCellRenderer();
            dti.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(dtr);
            ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
            jTable1.setDefaultRenderer(Integer.class, dtr);
            jTable1.setDefaultRenderer(String.class, dtr);
                
    }
    
   
    
    private void Tampil_Data(){
        String cari = txt_search.getText(); 
        // membuat tampilan model tabel
        //DefaultTableModel model = new DefaultTableModel();
        //model.addColumn("Hasil");
        //menampilkan data database kedalam tabel
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Judul Buku", "Kategori", "Penulis", "Penerbit", "Stok"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable1.setAutoscrolls(false);
        jTable1.setEditingColumn(0);
        jTable1.setEditingRow(0);
        jTable1.setFillsViewportHeight(true);
        jTable1.setFocusable(false);
        jTable1.setOpaque(false);
        jTable1.setRequestFocusEnabled(false);
        jTable1.setRowHeight(25);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
        }
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
        try {
            sql = "SELECT * FROM `buku` as a INNER Join kategori as b ON a.id_kategori = b.id_kategori where id_buku like '%"+cari+"%' OR nama_buku like  '%"+cari+"%' OR penulis like  '%"+cari+"%' OR penerbit like  '%"+cari+"%' order by id_buku asc ";
           rs = stat.executeQuery(sql);
            while (rs.next()) {
                AddRowToJTable(new Object[]{
                    rs.getInt("id_buku"),
                    rs.getString("nama_buku"),
                    rs.getString("deskripsi"),
                    rs.getString("penulis"),
                    rs.getString("penerbit"),
                    rs.getInt("stok")
                });
            }
            
           if (jTable1.getRowCount()==0) {
                DefaultTableModel vcc = new DefaultTableModel();
                vcc.addColumn("NULL");
                DefaultTableCellRenderer nll = new DefaultTableCellRenderer();
                nll.setHorizontalAlignment(JLabel.CENTER);
                jTable1.setModel(vcc);
                jTable1.getColumnModel().getColumn(0).setCellRenderer(nll);
                AddRowToJTable(new Object[]{"Data Tidak Ditemukan"});
            }
           
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   private void Clear(){
         txt_search.setText(null);
  }
   
   
private void Data_Sessi(){
     try{
        String sql_siswa = "select nis from siswa where nis ='"+nis+"'";
        
        rs = stat.executeQuery(sql_siswa);
                                          if ( rs.next()){
                                txt_nama_pengunjung.setText(nama_siswa);
                               }

                                          
        String sql_guru = "select username from guru where username ='"+username+"'";
        rs = stat.executeQuery(sql_guru);
                                          if (rs.next()){
                                txt_nama_pengunjung.setText(nama_guru);
                                
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

        jPanel1 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
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
        jLabel7 = new javax.swing.JLabel();
        txt_nama_pengunjung = new javax.swing.JLabel();
        txt_hari_ini = new javax.swing.JLabel();
        txt_waktu_hari_ini = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 830, 30));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Judul Buku", "Kategori", "Penulis", "Penerbit", "Stok"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jTable1.setAutoscrolls(false);
        jTable1.setEditingColumn(0);
        jTable1.setEditingRow(0);
        jTable1.setFillsViewportHeight(true);
        jTable1.setFocusable(false);
        jTable1.setOpaque(false);
        jTable1.setRequestFocusEnabled(false);
        jTable1.setRowHeight(25);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 960, 380));

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jLabel1.setText("Masukkan ID / Judul buku / Nama Pengarang / Penerbit");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 307, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-search.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 34, 37));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-reset.png"))); // NOI18N
        jButton2.setText("Reset");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 80, 90, 30));

        jLabel3.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        jLabel3.setText("Cari Buku");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 110, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 1060, 570));

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

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 815));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setPreferredSize(new java.awt.Dimension(1034, 128));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nama_siswa8.setText("Nama Siswa");
        jPanel25.add(txt_nama_siswa8, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 70, -1, -1));

        txt_nis8.setText("nis");
        jPanel25.add(txt_nis8, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 85, 39, 32));

        jLabel15.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-book.png"))); // NOI18N
        jLabel15.setText(" Data Buku");
        jPanel25.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 20, 171, -1));

        getContentPane().add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 58, 1060, 67));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Hallo");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 23, -1, -1));

        txt_nama_pengunjung.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nama_pengunjung.setText("Nama Pengunjung");
        getContentPane().add(txt_nama_pengunjung, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 23, 176, -1));

        txt_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_hari_ini.setText("jLabel8");
        getContentPane().add(txt_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1188, 23, 81, -1));

        txt_waktu_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_waktu_hari_ini.setText("jLabel8");
        getContentPane().add(txt_waktu_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1275, 23, 74, -1));

        setBounds(0, 0, 1381, 777);
    }// </editor-fold>//GEN-END:initComponents

    
    
    
              
    
    
    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
   Tampil_Data(); 
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
     Tampil_Data();   
// TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        Clear ();
        Tampil_Data ();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

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
        P_Transaksi trsk = new P_Transaksi();
        this.dispose();
        trsk.setVisible(true);
    }//GEN-LAST:event_jPanel16MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
         P_Dashboard dsh = new P_Dashboard();
        this.dispose();
        dsh.setVisible(true);
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
       int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin Logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
       if (confirm == 0)  {
   
            
             Login lg = new Login();
        this.dispose();
        lg.setVisible(true);
        }
      
      
    }//GEN-LAST:event_jPanel19MouseClicked
       
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
                new P_Buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTable jTable1;
    private javax.swing.JLabel txt_hari_ini;
    private javax.swing.JLabel txt_nama_pengunjung;
    private javax.swing.JLabel txt_nama_siswa8;
    private javax.swing.JLabel txt_nis8;
    private javax.swing.JTextField txt_search;
    private javax.swing.JLabel txt_waktu_hari_ini;
    // End of variables declaration//GEN-END:variables
}
