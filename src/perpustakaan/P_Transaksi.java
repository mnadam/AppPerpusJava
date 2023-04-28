/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perpustakaan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.DateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.text.SimpleDateFormat;  
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import static perpustakaan.P_Buku.AddRowToJTable;

/**
 *
 * @author User
 */
public class P_Transaksi extends javax.swing.JFrame {
 Connection con;
    Statement stat;
    ResultSet rs;
    String sql,test,sql_id;
    String nama_guru = UserSession.getU_nama_guru();
    String username = UserSession.getU_username();
    String nis = UserSession.getU_nis();
    String nama_siswa = UserSession.getU_nama_siswa();
    String status_login = UserSession.getU_status_login();
    String siswa_status_login = UserSession.getU_siswa_status_login();
    String guru_status_login = UserSession.getU_guru_status_login();
    
   
    
    Timer timer;
    /**
     * Creates new form P_Transaksi
     */
    public P_Transaksi() {
        initComponents();
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
       // DataBaseToComboBoxsiswa();
        DataBaseToComboBoxbuku();
        txt_id.setEnabled(false);
        id_transaksi();
        Tampil_Data();
        Data_Sessi();
        
         
        //FULL SCREEN CODE
        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        
//        AutoCompleteDecorator.decorate(cmbo_buku);
      
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
    
    
private void Tampil_Data(){
         
        String cari = txt_cari.getText(); 
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel(){
           @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }  
        };
               
        model.addColumn("ID Transaksi");
        model.addColumn("Judul Buku");
        model.addColumn("Tanggal Peminjaman");
        model.addColumn("Tanggal Pengembalian");
        model.addColumn("Tanggal Transaksi");
        model.addColumn("Status");
        //menampilkan data database kedalam tabel
        jTable2.setFocusable(false);
        jTable2.setRowSelectionAllowed(true);
        
//        boolean cek_nis = nis.isNullEmpty();
//        boolean cek_guru = username.isEmpty();
        
        //CEK JIKA NIS AKTIF / SISWA AKTIF
        if(nis != null){
        try {
            
            sql = "SELECT a.id_transaksi,a.tgl_pinjam,a.tgl_transaksi,a.tgl_kembali,c.nama_buku,d.keterangan FROM `transaksi_siswa` as a\n"+
                    "INNER Join \n" +
                    "siswa as b ON a.nis = b.nis\n" +
                    "INNER JOIN\n" +
                    "buku as c on a.id_buku = c.id_buku "+
                    "INNER JOIN " +
                    "status as d on a.id_status = d.id_status"+
                    " where b.nis = '"+nis+"' AND (a.id_transaksi like '%"+cari+"%' OR c.nama_buku like  '%"+cari+"%' OR d.keterangan like  '%"+cari+"%')  order by a.id_status asc ";

            rs = stat.executeQuery(sql);
            
            
           
            while (rs.next()) {
              
                model.addRow(new Object[]{
                    rs.getString("a.id_transaksi"),
                    rs.getString("c.nama_buku"),
                    rs.getString("a.tgl_pinjam"),
                    rs.getString("a.tgl_kembali"),
                    rs.getString("a.tgl_transaksi"),
                    rs.getString("d.keterangan")
                } 
                );
              
            }
              
         jTable2.setModel(model);
            
         id_transaksi();
        } catch (Exception e) {
            System.out.println(e.getMessage());
           
        }
        
        }
       
        
        
          //CEK JIKA USERNAME AKTIF / GURU AKTIF
//        if (username != null ){
       
        else {
            try {
            
            sql = "SELECT a.id_transaksi,a.tgl_pinjam,a.tgl_kembali,c.nama_buku,d.keterangan FROM `transaksi_guru` as a \n" +
                  "INNER Join \n" +
                  "guru as b ON a.username = b.username \n" +
                  "INNER JOIN \n" +
                  "buku as c on a.id_buku = c.id_buku "+
                  "INNER JOIN " + 
                  "status as d on a.id_status = d.id_status where b.username = '"+username+"' AND (a.id_transaksi like '%"+cari+"%' OR c.nama_buku like  '%"+cari+"%')  order by a.id_status asc ";

            rs = stat.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("a.id_transaksi"),
                    rs.getString("c.nama_buku"),
                    rs.getString("a.tgl_pinjam"),
                    rs.getString("a.tgl_kembali"),
                    rs.getString("d.keterangan")
                });
            }
            
         jTable2.setModel(model);
         id_transaksi();
        } catch (Exception e) {
            System.out.println(e.getMessage());
           
        
        }
        }
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     private void Clear(){
         txt_cari.setText(null);
         cmbo_buku.setSelectedIndex(0);
         date_pinjam.setDate(null);
         date_kembali.setDate(null);
         Tampil_Data();
         
  }
    
     
   public void DataBaseToComboBoxbuku(){
       
        try {
            sql = "SELECT * FROM buku order by stok desc";
         
            rs = stat.executeQuery(sql);
            
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
        
         if(nis != null){
        try {
        
        String sql = "SELECT id_transaksi FROM transaksi_siswa";
        
            
            rs = stat.executeQuery(sql);
            if(rs.last()){
                txt_id.setText(String.valueOf(rs.getInt(1)+1));
            }
            else
                txt_id.setText("1");
        } catch (Exception e) {
        }
    }
         
         
         
          if(username != null){
        try {
        
        String sql = "SELECT id_transaksi FROM transaksi_guru";
        
            
            rs = stat.executeQuery(sql);
            if(rs.last()){
                txt_id.setText(String.valueOf(rs.getInt(1)+1));
            }
            else
                txt_id.setText("1");
        } catch (Exception e) {
        }
    }
         
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu7 = new javax.swing.JMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txt_cari = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        btn_proses = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        cmbo_buku = new javax.swing.JComboBox<>();
        date_pinjam = new com.toedter.calendar.JDateChooser();
        date_kembali = new com.toedter.calendar.JDateChooser();
        txt_hari_ini = new javax.swing.JLabel();
        txt_nama_pengunjung = new javax.swing.JLabel();
        txt_waktu_hari_ini = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        jMenu7.setText("jMenu7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PERPUSTAKAAN");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 25, -1, -1));

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

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 252, 43));

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

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 252, 43));

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

        jPanel5.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 141, 252, 43));

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

        jPanel5.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 252, 43));

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

        jPanel5.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 252, 43));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 889, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 750));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 30, 460, 29));

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
        )
        {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        }
    );
    jScrollPane2.setViewportView(jTable2);

    jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 84, 510, 340));

    jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-search.png"))); // NOI18N
    jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel8MouseClicked(evt);
        }
    });
    jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

    jLabel2.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
    jLabel2.setText("Masukkan ID / Judul buku / Status");
    jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 307, 20));

    jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 570, 450));

    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel1.setBackground(new java.awt.Color(51, 51, 51));
    jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
    jLabel1.setText("ID Transaksi");
    jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 41, -1, -1));

    jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
    jLabel3.setText("Judul Buku");
    jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 102, -1, -1));

    jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
    jLabel4.setText("Tanggal Peminjaman");
    jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 164, -1, -1));

    jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
    jLabel5.setText("Tanggal Pengembalian");
    jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 219, -1, -1));
    jPanel1.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 39, 217, 29));

    btn_proses.setBackground(new java.awt.Color(0, 255, 51));
    btn_proses.setFont(new java.awt.Font("NSimSun", 0, 11)); // NOI18N
    btn_proses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-check-mark.png"))); // NOI18N
    btn_proses.setText("Tambah");
    btn_proses.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_prosesActionPerformed(evt);
        }
    });
    jPanel1.add(btn_proses, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

    btn_batal.setBackground(new java.awt.Color(255, 0, 0));
    btn_batal.setFont(new java.awt.Font("NSimSun", 0, 11)); // NOI18N
    btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-reset.png"))); // NOI18N
    btn_batal.setText("Reset");
    btn_batal.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_batalActionPerformed(evt);
        }
    });
    jPanel1.add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 89, 33));

    jPanel1.add(cmbo_buku, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 100, 217, 29));
    jPanel1.add(date_pinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 155, 217, 29));
    jPanel1.add(date_kembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 219, 217, 28));

    jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 28, 424, 450));

    getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 1060, 510));

    txt_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    txt_hari_ini.setText("jLabel8");
    getContentPane().add(txt_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 30, 80, -1));

    txt_nama_pengunjung.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    txt_nama_pengunjung.setText("Nama Pengunjung");
    getContentPane().add(txt_nama_pengunjung, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, -1));

    txt_waktu_hari_ini.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    txt_waktu_hari_ini.setText("jLabel8");
    getContentPane().add(txt_waktu_hari_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 30, 60, -1));

    jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel9.setText("Hallo");
    getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

    jPanel6.setBackground(new java.awt.Color(255, 255, 255));

    jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/perpustakaan/gambar/icons-borrow-book.png"))); // NOI18N
    jLabel6.setText("Data Transaksi");

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(823, Short.MAX_VALUE))
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jLabel6)
            .addContainerGap(14, Short.MAX_VALUE))
    );

    getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, -1));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_prosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prosesActionPerformed


                SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");  
                String tgl_pinjam = fm.format(date_pinjam.getDate());
                String tgl_kembali = fm.format(date_kembali.getDate());
        
      
        
        
        //CEKK JIKA PENGUNJUNG SISWA
         if(nis != null){
          try{
              
              
                Date date_hari_ini = new Date();
                DateFormat timeFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                String hari_ini = timeFormat2.format(date_hari_ini);
                
                
                
                
             
        String sql_buku = "SELECT * FROM buku where nama_buku='"+cmbo_buku.getSelectedItem()+"'";
            rs = stat.executeQuery(sql_buku);
            rs.next();
            String id_buku =  rs.getString("id_buku"); 
            String stokawal =  rs.getString("stok");  
            int cstokawal = Integer.parseInt(stokawal);
            
            
            
            
            
        String sql_siswa = "select nis from siswa where nis ='"+nis+"'";
        rs = stat.executeQuery(sql_siswa);
      
                                          if ( rs.next()){
//                                String id_siswa = rs.getString("nis");
                              
                       String sql = "insert into transaksi_siswa values('"
                    +txt_id.getText()+"','"
                    +nis+"','"
                    +id_buku+"','"
                    +tgl_pinjam+"','"
                    +tgl_kembali+"','"
                    +('1')+"','"
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
            }
                               }
          }       
          catch (Exception e) {
    e.printStackTrace();
    
          }
         }
          
      //CEKK JIKA PENGUNJUNG GURU
      
      if(username != null){
          try{
              
              Date date_hari_ini = new Date();
                DateFormat timeFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                String hari_ini = timeFormat2.format(date_hari_ini);
                
        String sql_buku = "SELECT * FROM buku where nama_buku='"+cmbo_buku.getSelectedItem()+"'";
            rs = stat.executeQuery(sql_buku);
            rs.next();
            String id_buku =  rs.getString("id_buku"); 
            String stokawal =  rs.getString("stok");  
            int cstokawal = Integer.parseInt(stokawal);  
            
        String sql_guru = "select username from guru where username ='"+username+"'";
        rs = stat.executeQuery(sql_guru);
      
                                          if ( rs.next()){
                                String id_guru = rs.getString("username");
                               
                                
                       String sql = "insert into transaksi_guru values('"
                    +txt_id.getText()+"','"
                    +id_guru+"','"
                    +id_buku+"','"
                    +tgl_pinjam+"','"
                    +tgl_kembali+"','"
                    +("1")+"','"           
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
            }
            
            
                               }
          }

                                          
          catch (Exception e) {
    e.printStackTrace();
    
          }       
          
      } 
          
          Tampil_Data();
          Clear();
          
    }//GEN-LAST:event_btn_prosesActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
 //exit
        Clear();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_batalActionPerformed

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

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        Tampil_Data();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
            int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin Logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
       if (confirm == 0)  {
   
            
             Login lg = new Login();
        this.dispose();
        lg.setVisible(true);
        }
      
    }//GEN-LAST:event_jPanel19MouseClicked

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

    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P_Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_proses;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbo_buku;
    private com.toedter.calendar.JDateChooser date_kembali;
    private com.toedter.calendar.JDateChooser date_pinjam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JLabel txt_hari_ini;
    private javax.swing.JTextField txt_id;
    private javax.swing.JLabel txt_nama_pengunjung;
    private javax.swing.JLabel txt_waktu_hari_ini;
    // End of variables declaration//GEN-END:variables
}
