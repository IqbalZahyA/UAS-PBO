import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static FieldManagementSystem system = new FieldManagementSystem();
    static User loggedInUser = null;

    public static void main(String[] args) {
        seedData();
        while (true) {
            if (loggedInUser == null) {
                showAuthMenu();
            } else {
                if (loggedInUser instanceof Admin) {
                    showAdminMenu();
                } else {
                    showMemberMenu();
                }
            }
        }
    }

    static void showAuthMenu() {
        System.out.println("\n=== MENU UTAMA ===");
        System.out.println("1. Login");
        System.out.println("2. Registrasi");
        System.out.println("0. Keluar");
        System.out.print("Pilih: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
            case 1 -> login();
            case 2 -> register();
            case 0 -> System.exit(0);
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean found = false;

        for (User user : system.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Login berhasil sebagai " + loggedInUser.getRole());
            if (loggedInUser instanceof Admin admin) {
                System.out.println("Halo " + admin.getadminName());
            } else if (loggedInUser instanceof Member member) {
                System.out.println("Halo " + member.getmemberName());
            }
        } else {
            System.out.println("Login gagal! Username atau password salah.");
        }
    }

    static void register() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Nama: ");
        String name = scanner.nextLine();
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();

        Member newMember = new Member(username, password, memberId, name);
        system.registerUser(newMember);
        System.out.println("Registrasi berhasil.");
    }

    // Tambahkan opsi CRUD di menu admin
    static void showAdminMenu() {
        System.out.println("\n=== MENU ADMIN ===");
        System.out.println("1. Tambah Lapangan");
        System.out.println("2. Lihat Semua Lapangan");
        System.out.println("3. Update Lapangan");
        System.out.println("4. Hapus Lapangan");
        System.out.println("5. Cari Lapangan");
        System.out.println("6. Logout");
        System.out.print("Pilih: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();
    
        switch (pilihan) {
            case 1 -> tambahLapangan();
            case 2 -> tampilkanLapangan();
            case 3 -> updateLapangan();
            case 4 -> hapusLapangan();
            case 5 -> cariLapangan(); // Tambahkan opsi pencarian
            case 6 -> loggedInUser = null;
            default -> System.out.println("Pilihan tidak valid.");
        }
    }
// Tambahkan metode untuk update lapangan
static void updateLapangan() {
    tampilkanLapangan();
    System.out.print("Masukkan ID lapangan yang ingin diupdate: ");
    String fieldId = scanner.nextLine();
    Field selectedField = null;

    for (Field f : system.getFields()) {
        if (f.getFieldId().equals(fieldId)) {
            selectedField = f;
            break;
        }
    }

    if (selectedField == null) {
        System.out.println("Lapangan tidak ditemukan.");
        return;
    }

    System.out.print("Nama baru: ");
    String newName = scanner.nextLine();
    System.out.print("Tipe baru: ");
    String newType = scanner.nextLine();

    selectedField.setFieldName(newName);
    selectedField.setFieldType(newType);
    System.out.println("Lapangan berhasil diupdate.");
}

// Tambahkan metode untuk hapus lapangan
static void hapusLapangan() {
    tampilkanLapangan();
    System.out.print("Masukkan ID lapangan yang ingin dihapus: ");
    String fieldId = scanner.nextLine();
    Field selectedField = null;

    for (Field f : system.getFields()) {
        if (f.getFieldId().equals(fieldId)) {
            selectedField = f;
            break;
        }
    }

    if (selectedField == null) {
        System.out.println("Lapangan tidak ditemukan.");
        return;
    }

    system.getFields().remove(selectedField);
    System.out.println("Lapangan berhasil dihapus.");
}
static void showMemberMenu() {
    System.out.println("\n=== MENU MEMBER ===");
    System.out.println("1. Booking Lapangan");
    System.out.println("2. Lihat Booking Saya");
    System.out.println("3. Cari Lapangan");
    System.out.println("4. Logout");
    System.out.print("Pilih: ");
    int pilihan = scanner.nextInt();
    scanner.nextLine();

    switch (pilihan) {
        case 1 -> bookingLapangan();
        case 2 -> lihatBookingSaya();
        case 3 -> cariLapangan(); // Tambahkan opsi pencarian
        case 4 -> loggedInUser = null;
        default -> System.out.println("Pilihan tidak valid.");
    }
}
    static void tambahLapangan() {
        System.out.print("ID Lapangan: ");
        String id = scanner.nextLine();
        System.out.print("Nama: ");
        String name = scanner.nextLine();
        System.out.print("Tipe: ");
        String type = scanner.nextLine();

        system.addField(new Field(id, name, type));
        System.out.println("Lapangan berhasil ditambahkan.");
    }

    static void tampilkanLapangan() {
        List<Field> daftar = system.searchField(""); // kosongkan keyword untuk menampilkan semua
        for (Field f : daftar) {
            System.out.println(f.getInfo());
        }
    }

    static void bookingLapangan() {
        tampilkanLapangan();
        System.out.print("Masukkan ID lapangan yang ingin dibooking: ");
        String fieldId = scanner.nextLine();
        Field selectedField = null;

        for (Field f : system.getFields()) {
            if (f.getFieldId().equals(fieldId)) {
                selectedField = f;
                break;
            }
        }

        if (selectedField == null) {
            System.out.println("Lapangan tidak ditemukan.");
            return;
        }

        System.out.print("Tanggal (yyyy-mm-dd): ");
        String tanggal = scanner.nextLine();
        System.out.print("Jam (contoh 16:00-18:00): ");
        String waktu = scanner.nextLine();

        if (BookingManager.isFieldAvaiable(selectedField.getFieldId(), tanggal, waktu)) {
            Booking newBooking = new Booking(
                    "B" + (Database.bookings.size() + 1),
                    tanggal,
                    waktu,
                    selectedField,
                    (Member) loggedInUser
            );
            Database.bookings.add(newBooking);
            selectedField.setStatus("Booked");
            System.out.println("Booking berhasil!");
        } else {
            System.out.println("Jadwal bentrok! Silakan pilih waktu lain.");
        }
    }

    static void lihatBookingSaya() {
        Member currentMember = (Member) loggedInUser;
        boolean found = false;

        System.out.println("\n=== BOOKING SAYA ===");
        for (Booking booking : Database.bookings) {
            if (booking.getMember().getUsername().equals(currentMember.getUsername())) {
                System.out.println(booking.getBookingDetails());
                System.out.println("-------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Anda belum melakukan booking apapun.");
        }
    }
    static void cariLapangan() {
        System.out.print("Masukkan kata kunci pencarian: ");
        String keyword = scanner.nextLine();
    
        List<Field> hasilPencarian = system.searchField(keyword);
    
        if (hasilPencarian.isEmpty()) {
            System.out.println("Tidak ada lapangan yang cocok dengan kata kunci \"" + keyword + "\".");
        } else {
            System.out.println("\n=== HASIL PENCARIAN ===");
            for (Field field : hasilPencarian) {
                System.out.println(field.getInfo());
            }
        }
    }
    static void seedData() {
        // Tambah satu admin
        system.registerUser(new Admin("admin", "admin123", "A01", "Administrator"));

        // Tambah lima lapangan
        system.addField(new Field("F01", "Lapangan Futsal", "Futsal"));
        system.addField(new Field("F02", "Lapangan Badminton", "Badminton"));
        system.addField(new Field("F03", "Lapangan Basket", "Basket"));
        system.addField(new Field("F04", "Lapangan Tenis", "Tenis"));
        system.addField(new Field("F05", "Lapangan Voli", "Voli"));
    }
}
