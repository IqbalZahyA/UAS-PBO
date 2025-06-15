package uaspbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection koneksi;

    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                // Load driver JDBC MySQL modern
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Konfigurasi database
                String url = "jdbc:mysql://localhost:3306/uaspbo";
                String user = "root";
                String password = "";

                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi database berhasil!");
            } catch (ClassNotFoundException e) {
                System.out.println("JDBC Driver tidak ditemukan: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Gagal koneksi database: " + e.getMessage());
            }
        }
        return koneksi;
    }

    public static void main(String[] args) {
        getKoneksi();
    }
}
