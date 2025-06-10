
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection Koneksi;

    public static Connection getKoneksi() {
        if (Koneksi == null) {
            try {
                // Load driver JDBC MySQL modern
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Konfigurasi database
                String url = "jdbc:mysql://localhost:3306/tokobuku1";
                String user = "root";
                String password = "";

                Koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi database berhasil!");
            } catch (ClassNotFoundException e) {
                System.out.println("JDBC Driver tidak ditemukan: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Gagal koneksi database: " + e.getMessage());
            }
        }
        return Koneksi;
    }

    public static void main(String[] args) {
        getKoneksi();
    }
}
