/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package perpustakaan;

/**
 *
 * @author User
 */

public class UserSession {

   
    private static String u_id_operator;
    private static String u_username;
    private static String u_password;
    private static String u_nama_siswa;
    private static String u_nama_guru;
    private static String u_nama_operator;
    private static String u_alamat;
    private static String u_no_telp;
    private static String u_nis;
    private static String u_ttl;
    private static String u_last_login;
    private static String u_status_login;
    private static String u_siswa_status_login;
    private static String u_guru_status_login;
    
    
  
    
    //SESSI OPERATOR
    public static String getU_id_operator() {
        return u_id_operator;
    }

    public static void setU_id_operator(String u_id_operator) {
        UserSession.u_id_operator = u_id_operator;
    }

    public static String getU_nama_operator() {
        return u_nama_operator;
    }

    public static void setU_nama_operator(String u_nama_operator) {
        UserSession.u_nama_operator = u_nama_operator;
    }
    
      public static String getU_no_telp() {
        return u_no_telp;
    }

    public static void setU_no_telp(String u_no_telp) {
        UserSession.u_no_telp = u_no_telp;
    }
    
     public static String getU_username() {
        return u_username;
    }

    public static void setU_username(String u_username) {
        UserSession.u_username = u_username;
    }
    
    //END SESSI OPERATOR
    
    
    //SESSI SISWA
    
    public static void setU_nis(String u_nis) {
        UserSession.u_nis = u_nis;
    }
     
    public static String getU_nis() {
        return u_nis;
    }
    
      public static void setU_nama_siswa(String u_nama_siswa) {
        UserSession.u_nama_siswa = u_nama_siswa;
    }
    
      public static String getU_nama_siswa() {
        return u_nama_siswa;
    }
      
       public static void setU_ttl(String u_ttl) {
        UserSession.u_ttl = u_ttl;
    }
    
      public static String getU_ttl() {
        return u_ttl;
    }
    //SESSI GURU
      
//       public static void setU_username(String u_username) {
//        UserSession.u_username = u_username;
//    }
//     
//    public static String getU_username() {
//        return u_username;
//    }
    
      public static void setU_nama_guru(String u_nama_guru) {
        UserSession.u_nama_guru = u_nama_guru;
    }
    
      public static String getU_nama_guru() {
        return u_nama_guru;
    }

      
       public static void setU_last_login(String u_last_login) {
        UserSession.u_last_login = u_last_login;
    }
    
      public static String getU_last_login() {
        return u_last_login;
    }
      
  
       public static void setU_siswa_status_login(String u_siswa_status_login) {
        UserSession.u_siswa_status_login = u_siswa_status_login;
    }
    
      public static String getU_siswa_status_login() {
        return u_siswa_status_login;
      }
      
      
      
       public static void setU_guru_status_login(String u_guru_status_login) {
        UserSession.u_guru_status_login = u_guru_status_login;
    }
    
      public static String getU_guru_status_login() {
        return u_guru_status_login;
      }
      
        public static void setU_status_login(String u_status_login) {
        UserSession.u_status_login = u_status_login;
    }
    
      public static String getU_status_login() {
        return u_status_login;
      }
}
